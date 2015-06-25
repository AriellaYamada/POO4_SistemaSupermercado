package Client;

import Structure.Def;

import java.io.IOException;

public class Client {

	private static Client client;

	String signal;

	private boolean logged;
	private String id;
	private String name;

	//Singleton
	public static Client getInstance() {
		if (client == null) {
			client = new Client();
		}
		return client;
	}

	public void Connect (String ip, int port) throws IOException {
		Connection.getInstance().Connect(ip, port);
		logged = false;
	}

	public String AddNewUser (String name, String address, String tel, String email, String id, String password) {

		signal = "newuser"
				+ Def.regSep   + name
				+ Def.fieldSep + address
				+ Def.fieldSep + tel
				+ Def.fieldSep + email
				+ Def.fieldSep + id
				+ Def.fieldSep + password;

		System.out.println(signal);
		Connection.getInstance().SendSignal(signal);

		String response = Connection.getInstance().ReceiveSignal();
		return response;
	}

	public String Login (String id, String password) {

		//Comando a ser enviado ao servidor
		signal = "login" + Def.regSep + id + Def.fieldSep + password;
		Connection.getInstance().SendSignal(signal);

		//Resposta recebida do servidor
		String response = Connection.getInstance().ReceiveSignal();

		//Login efetuado com sucesso
		if (response.equals("ok")) {
			logged = true;
			this.id = id;
			GetUserName();
		}
		return response;
	}

	public void GetUserName() {
		signal = "getname" + Def.regSep + id;
		Connection.getInstance().SendSignal(signal);

		name = Connection.getInstance().ReceiveSignal();
	}

	public void Logout () {
		logged = false;
		id = null;
		name = null;
	}
}
