package Structure;

import javafx.scene.control.TextInputControl;
import javafx.scene.control.Tooltip;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Def {
	public static final String regSep = "!";    // Register separator
	public static final String fieldSep = "&";  // Field separator
	public static final String comma = ",";     // Comma separator

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

	public static void setError (TextInputControl field, String str){
		field.getStyleClass().add("red-field");
		field.setTooltip(new Tooltip(str));
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
	}

	public static String validate (String text, FieldType TYPE) {
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
				if (f%0.001f != 0) return "O preço só pode ter 2 casas decimais";
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

}
