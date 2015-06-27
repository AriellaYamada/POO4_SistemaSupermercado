package Server;

import Server.Database.Products;
import Server.Database.Users;
import Server.Database.UsersDatabase;
import Structure.Def;

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
		String[] cmd = Def.splitReg(line);
		String[] args;
		int response = -2;
		switch (cmd[0]){
			//Cadastro de novo usuario
			case "newuser":
				args = Def.splitField(cmd[1]);
				response = Users.getInstance().Register(args[0], args[1], args[2], args[3], args[4], args[5]);
				UsersDatabase.getInstance().WriteFile();
				if (response == 0)
					line = "ok";
				else
					line = "fail"+ Def.regSep +"f_id"+ Def.fieldSep +"Este login ja esta sendo utilizado";
				break;
			//Efetuar login
			case "login":
				args = Def.splitField(cmd[1]);
				response = Users.getInstance().Login(args[0], args[1]);
				//Se o login foi efetuado corretamente
				if (response == 0)
					line = "ok";
				//Caso o usuario nao seja encontrado
				else if (response == 1)
					line = "fail"+ Def.regSep +"f_userlogin"+ Def.fieldSep +"Usuario nao encontrado";
				//Caso a senha digitada for incorreta
				else
					line = "fail"+ Def.regSep +"f_userpassword"+ Def.fieldSep +"Senha incorreta";
				break;
				//Busca o nome do usuario logado
			case "getname":
				line = Users.getInstance().GetUserName(cmd[1]);
				break;
			//Busca todos os produtos cadastrados no sistema
			case "listall":
				line = Products.getInstance().AllProducts();
				break;
			//Solicita a reserva de um produto
			case "reserve":
				args = Def.splitField(cmd[1]);
				if (Products.getInstance().Reserve(args[0], Integer.parseInt(args[1])))
					line = "ok";
				else
					line = "fail";
				break;
			case "dereserve":
				args = Def.splitField(cmd[1]);
				Products.getInstance().CancelReservation(args[0], Integer.parseInt(args[1]));
				line = "ok";
				break;
			default:
				break;
		}
		return line;
	}
}
