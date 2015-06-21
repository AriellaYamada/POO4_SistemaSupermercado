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

	//Processa o comando recebido do cliente
	public static String process (String line){
		String[] cmd = line.split(",");
		int response = -2;
		switch (cmd[0]){
			//Cadastro de novo usuario
			case "newuser":
				response = Users.getInstance().Register(cmd[1], cmd[2], cmd[3], cmd[4], cmd[5], cmd[6]);
				if (response == 0)
					line = "ok";
				else
					line = "fail|f_id&Este login ja esta sendo utilizado";
				break;
			//Efetuar login
			case "login":
				response = Users.getInstance().Login(cmd[1], cmd[2]);
				if (response == 0)
					line = "ok|" + cmd[1];
				else if (response == 1)
					line = "fail|f_userlogin&Usuario nao encontrado";
				else
					line = "fail|f_userpassword&Senha incorreta";
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
