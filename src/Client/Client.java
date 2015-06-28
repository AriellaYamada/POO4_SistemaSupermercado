package Client;

import Structure.Def;

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

	static public void Connect (String ip, int port) throws IOException {
		Connection.getInstance().Connect(ip, port);
		logged = false;
	}

	static public String AddNewUser (String name, String address, String tel, String email, String id, String password) {

		signal = "newuser"
				+ Def.regSep   + name
				+ Def.fieldSep + address
				+ Def.fieldSep + tel
				+ Def.fieldSep + email
				+ Def.fieldSep + id
				+ Def.fieldSep + password;

		System.out.println(signal);
		Connection.getInstance().SendSignal(signal);

		return Connection.getInstance().ReceiveSignal();
	}

	static public String Login (String userID, String password) {

		//Comando a ser enviado ao servidor
		signal = "login" + Def.regSep + userID + Def.fieldSep + password;
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

	static private String getUserName(String id) {
		signal = "getname" + Def.regSep + id;
		Connection.getInstance().SendSignal(signal);
		return Connection.getInstance().ReceiveSignal();
	}

	static public String getUserName() {
		if (name == null) {
			name = getUserName(id);
		}
		return name;
	}

	static public void Logout () {
		Connection.getInstance().CloseConnectionClient();
		logged = false;
		id = null;
		name = null;
	}
}
