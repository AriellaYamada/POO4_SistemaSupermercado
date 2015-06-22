package Structure;

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
}
