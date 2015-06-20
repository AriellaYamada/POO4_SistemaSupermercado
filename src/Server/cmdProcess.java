package Server;

import Database.*;

public class cmdProcess {
	private cmdProcess instance = null;

	public cmdProcess getDB(){
		if (instance == null){
			instance = new cmdProcess();
		}
		return instance;
	}

	private cmdProcess(){}

	public static String process (String line){
		String[] cmd = line.split(",");
		int response = -2;
		switch (cmd[0]){
			case "newuser":
				response = Users.getInstance().Register(cmd[1], cmd[2], cmd[3], cmd[4], cmd[5], cmd[6]);
				break;
			case "login":
				response = Users.getInstance().Login(cmd[1], cmd[2]);
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
		line = "" + response;
		return line;
	}
}
