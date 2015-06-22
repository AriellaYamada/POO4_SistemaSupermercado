package Server;

import Server.Database.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
				UsersDatabase.getInstance().WriteFile();
				if (response == 0)
					line = "ok";
				else
					line = "fail!f_id&Este login ja esta sendo utilizado";
				break;
			//Efetuar login
			case "login":
				response = Users.getInstance().Login(cmd[1], cmd[2]);
				//Se o login foi efetuado corretamente
				if (response == 0)
					line = "ok";
				//Caso o usuario nao seja encontrado
				else if (response == 1)
					line = "fail!f_userlogin&Usuario nao encontrado";
				//Caso a senha digitada for incorreta
				else
					line = "fail!f_userpassword&Senha incorreta";
				break;
				//Busca o nome do usuario logado
			case "getname":
				line = Users.getInstance().GetUserName(cmd[1]);
				break;
			//Busca todos os produtos cadastrados no sistema
			case "listall":
				line = Products.getInstance().AllProducts();
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
