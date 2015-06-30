package Structure;

import javafx.scene.control.Control;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Tooltip;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class Def {
	public static final String regSep = "!";    // Register separator
	public static final String fieldSep = "&";  // Field separator
	public static final String comma = ",";     // Comma separator
	public static final List<String> months = new LinkedList<>();

	public static String[] splitReg(String str){
		return str.split(regSep);
	}

	public static String[] splitField(String str){
		return str.split(fieldSep);
	}

	public static String[] splitComma(String str){
		return str.split(comma);
	}

	public static String CalendarToString (GregorianCalendar date) {
		return date.get(Calendar.DAY_OF_MONTH) + "/"
				+(date.get(Calendar.MONTH)+1) + "/"
				+date.get(Calendar.YEAR);
	}

	public static GregorianCalendar StringToCalendar (String date) {
		String[] split_date = date.split("/");

		return new GregorianCalendar(Integer.parseInt(split_date[2]),
				Integer.parseInt(split_date[1])-1,
				Integer.parseInt(split_date[0]));
	}

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
		FLOAT,
		FLOAT_POSITIVE,
		FLOAT_NEGATIVE,
		FLOAT_NON_ZERO,
		FLOAT_POSITIVE_NON_ZERO,
		FLOAT_NEGATIVE_NON_ZERO,
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
		Float f;

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

			case FLOAT:
				if (checkFloat(text) == null) return "Este campo não é um float válido";
				return "ok";

			case FLOAT_POSITIVE:
				f = checkFloat(text);
				if (f == null) return "Este campo não é um float válido";
				if (f < 0) return "Este campo precisa ser positivo ou zero";
				return "ok";

			case FLOAT_NEGATIVE:
				f = checkFloat(text);
				if (f == null) return "Este campo não é um float válido";
				if (f > 0) return "Este campo precisa ser negativo ou zero";
				return "ok";

			case FLOAT_NON_ZERO:
				f = checkFloat(text);
				if (f == null) return "Este campo não é um float válido";
				if (f == 0) return "Este campo precisa ser diferente de zero";
				return "ok";

			case FLOAT_POSITIVE_NON_ZERO:
				f = checkFloat(text);
				if (f == null) return "Este campo não é um float válido";
				if (f <= 0) return "Este campo precisa ser positivo não-zero";
				return "ok";

			case FLOAT_NEGATIVE_NON_ZERO:
				f = checkFloat(text);
				if (f == null) return "Este campo não é um float válido";
				if (f >= 0) return "Este campo precisa ser negativo não-zero";
				return "ok";

			case DATE:
				if (!checkDate(text)) return  "A data precisa estar no formato dd/MM/aaaa";
				return "ok";

			case PRICE:
				f = checkFloat(text);
				if (f == null) return "Preço inválido";
				if (f <= 0) return "O preço precisa ser positivo não-zero";
				if (!checkDecimals(text, 2)) return "O preço só pode ter 2 casas decimais";
				return "ok";

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

	private static Float checkFloat(String text){
		try {
			return Float.parseFloat(text);
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

	private static void initializeMonthsList () {
		months.add(0, null);
		months.add(1, "Janeiro");
		months.add(2, "Fevereiro");
		months.add(3, "Março");
		months.add(4, "Abril");
		months.add(5, "Maio");
		months.add(6, "Junho");
		months.add(7, "Julho");
		months.add(8, "Agosto");
		months.add(9, "Setembro");
		months.add(10, "Outubro");
		months.add(11, "Novembro");
		months.add(12, "Dezembro");
	}

	public static String intToMonth (int n) {
		if (n < 1 || n > 12) return null;
		if (months.isEmpty()) initializeMonthsList();
		return months.get(n);
	}

	public static int monthToInt (String m) {
		if (months.isEmpty()) initializeMonthsList();
		return months.indexOf(m);
	}

}
