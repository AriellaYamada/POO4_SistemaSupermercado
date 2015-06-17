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
		String[] cmd = line.split(",");
		switch (cmd[0]){
			case "newuser":
				break;
			case "login":
				break;
			case "search":
				break;
			case "startsell":
				break;
			case "reserve":
				break;
			case "dereserve":
				break;
			case "cancelsell":
				break;
			case "confirmsell":
				break;
			default:
				break;
		}
		return line;
	}
}
