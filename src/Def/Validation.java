package Def;

import javafx.scene.control.Control;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Tooltip;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

	public static void setError (Control field, String str){
		field.getStyleClass().add("red-field");
		field.setTooltip(new Tooltip(str));
	}

	public static void clearErrorStyle (TextInputControl... controls){
		for (TextInputControl c : controls) {
			c.getStyleClass().remove("red-field");
			c.setTooltip(null);
		}
	}

	public static void clearField(TextInputControl... control){
		for (TextInputControl c : control) c.clear();
	}

	public enum FieldType {
		TEXT,
		INTEGER,
		INTEGER_POSITIVE,
		INTEGER_NEGATIVE,
		INTEGER_NON_ZERO,
		INTEGER_POSITIVE_NON_ZERO,
		INTEGER_NEGATIVE_NON_ZERO,
		DOUBLE,
		DOUBLE_POSITIVE,
		DOUBLE_NEGATIVE,
		DOUBLE_NON_ZERO,
		DOUBLE_POSITIVE_NON_ZERO,
		DOUBLE_NEGATIVE_NON_ZERO,
		DATE,
		PRICE,
		IP,
		EMAIL,
	}

	public static String validate (String text, FieldType TYPE) {
		return validate(text, TYPE, true);
	}

	public static String validate (String text, FieldType TYPE, boolean required) {
		if (required) if (text.isEmpty()) return "Este campo é obrigatório";
		if (text.contains(",")) return "Este campo contém um caractere inválido (vírgula)";

		Integer i;
		Double d;

		switch (TYPE){
			case INTEGER:
				if (checkInteger(text) == null) return "Este campo não é um inteiro válido";
				return "ok";

			case INTEGER_POSITIVE:
				i = checkInteger(text);
				if (i == null) return "Este campo não é um inteiro válido";
				if (i < 0) return "Este campo precisa ser positivo ou zero";
				return "ok";

			case INTEGER_NEGATIVE:
				i = checkInteger(text);
				if (i == null) return "Este campo não é um inteiro válido";
				if (i > 0) return "Este campo precisa ser negativo ou zero";
				return "ok";

			case INTEGER_NON_ZERO:
				i = checkInteger(text);
				if (i == null) return "Este campo não é um inteiro válido";
				if (i == 0) return "Este campo precisa ser diferente de zero";
				return "ok";

			case INTEGER_POSITIVE_NON_ZERO:
				i = checkInteger(text);
				if (i == null) return "Este campo não é um inteiro válido";
				if (i <= 0) return "Este campo precisa ser positivo não-zero";
				return "ok";

			case INTEGER_NEGATIVE_NON_ZERO:
				i = checkInteger(text);
				if (i == null) return "Este campo não é um inteiro válido";
				if (i >= 0) return "Este campo precisa ser negativo não-zero";
				return "ok";

			case DOUBLE:
				if (checkDouble(text) == null) return "Este campo não é um float válido";
				return "ok";

			case DOUBLE_POSITIVE:
				d = checkDouble(text);
				if (d == null) return "Este campo não é um float válido";
				if (d < 0) return "Este campo precisa ser positivo ou zero";
				return "ok";

			case DOUBLE_NEGATIVE:
				d = checkDouble(text);
				if (d == null) return "Este campo não é um float válido";
				if (d > 0) return "Este campo precisa ser negativo ou zero";
				return "ok";

			case DOUBLE_NON_ZERO:
				d = checkDouble(text);
				if (d == null) return "Este campo não é um float válido";
				if (d == 0) return "Este campo precisa ser diferente de zero";
				return "ok";

			case DOUBLE_POSITIVE_NON_ZERO:
				d = checkDouble(text);
				if (d == null) return "Este campo não é um float válido";
				if (d <= 0) return "Este campo precisa ser positivo não-zero";
				return "ok";

			case DOUBLE_NEGATIVE_NON_ZERO:
				d = checkDouble(text);
				if (d == null) return "Este campo não é um float válido";
				if (d >= 0) return "Este campo precisa ser negativo não-zero";
				return "ok";

			case DATE:
				if (!checkDate(text)) return  "A data precisa estar no formato dd/MM/aaaa";
				return "ok";

			case PRICE:
				d = checkDouble(text);
				if (d == null) return "Preço inválido";
				if (d <= 0) return "O preço precisa ser positivo não-zero";
				if (!checkDecimals(text, 2)) return "O preço só pode ter 2 casas decimais";
				return "ok";

			case EMAIL:
				break;
		}

		return "ok";
	}

	private static Integer checkInteger(String text){
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private static Double checkDouble(String text){
		try {
			return Double.parseDouble(text);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private static boolean checkDate(String text){
		SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
		form.setLenient(false);

		try {
			form.parse(text);
		} catch (ParseException e){
			return false;
		}

		return true;
	}

	private static boolean checkDecimals (String text, int decimals){
		if (!text.contains(".")) return true;

		String dec = text.substring(text.indexOf(".") + 1);
		return dec.length() <= decimals;
	}

	public static boolean validateField(TextInputControl field, FieldType TYPE){
		return validateField(field, TYPE, true);
	}

	public static boolean validateField(TextInputControl field, FieldType TYPE, boolean required){
		String text = field.getText();
		String valid = validate(text, TYPE, required);

		if (valid.equals("ok")) return true;

		setError(field, valid);
		return false;
	}
	/*
	Pattern emailPattern = Pattern.compile("[a-zA-Z0-9[!#$%&'()*+,/\-_\\"]]+@[a-zA-Z0-9[!#$%&'()*+,/\-_\"]]+\.[a-zA-Z0-9[!#$%&'()*+,/\-_\"\.]]+");

	public static boolean isValidEmail(String email) {

		Matcher m = emailPattern.matcher(email); return !m.matches();

	}*/

}
