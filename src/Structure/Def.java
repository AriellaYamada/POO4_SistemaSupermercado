package Structure;

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
}
