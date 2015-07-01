package Client;

import Def.Split;

import java.io.IOException;

public class Client {

	private static Client client;

	private static String signal;

	private static boolean logged;
	private static String id;
	private static String name;

	//Singleton
	public static Client getInstance() {
		if (client == null) {
			client = new Client();
		}
		return client;
	}

	private Client(){
		id = name = null;
		logged = false;
	}

	//Estabelece conexao com a aplicacao servidor
	static public void Connect (String ip, int port) throws IOException {
		Connection.getInstance().Connect(ip, port);
		logged = true;
	}

	//Comando para solicitar a criacao de um novo usuario no sistema
	static public String AddNewUser (String name, String address, String tel, String email, String id, String password) {

		signal = "newuser"
				+ Split.regSep   + name
				+ Split.fieldSep + address
				+ Split.fieldSep + tel
				+ Split.fieldSep + email
				+ Split.fieldSep + id
				+ Split.fieldSep + password;
		
		Connection.getInstance().SendSignal(signal);

		return Connection.getInstance().ReceiveSignal();
	}

	static public String Login (String userID, String password) {

		//Comando a ser enviado ao servidor
		signal = "login" + Split.regSep + userID + Split.fieldSep + password;
		Connection.getInstance().SendSignal(signal);

		//Resposta recebida do servidor
		String response = Connection.getInstance().ReceiveSignal();

		//Login efetuado com sucesso
		if (response.equals("ok")) {
			logged = true;
			id = userID;
			name = getUserName(id);
		}
		return response;
	}

	//Solicita um username no servidor a partir do id do usuario
	static private String getUserName(String id) {
		signal = "getname" + Split.regSep + id;
		Connection.getInstance().SendSignal(signal);
		return Connection.getInstance().ReceiveSignal();
	}

	static public String getUserName() {
		if (name == null) {
			name = getUserName(id);
		}
		return name;
	}

	//Realiza o logout no servidor
	static public void Logout () {
		//Envia o comando de logout
		Connection.getInstance().SendSignal("logout");
		//Aguarda uma resposta
		Connection.getInstance().ReceiveSignal();
		//Fecha a conexao do socket
		Connection.getInstance().CloseConnectionClient();
		logged = false;
		id = null;
		name = null;
		//Finaliza a aplicação
		System.exit(0);
	}
}
