package Server;

public class Database {
	private Database instance = null;

	public Database getDB(){
		if (instance == null){
			instance = new Database();
		}
		return instance;
	}

	private Database (){}

	public static String process (String line){
		return line;
	}
}
