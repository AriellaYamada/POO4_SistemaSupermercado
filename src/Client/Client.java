package Client;

import java.io.IOException;

public class Client {

	private static Client client;
	String split = ",";
	String signal;

	private boolean logged;
	private String id;

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

		signal = "newuser" + split + name + split + address + split + tel + split + email + split + id + split + password;

		System.out.println(signal);
		Connection.getInstance().SendSignal(signal);

		String response = Connection.getInstance().ReceiveSignal();
		return response;
	}

	public String Login (String id, String password) {

		//Comando a ser enviado ao servidor
		signal = "login" + split + id + split + password;
		Connection.getInstance().SendSignal(signal);

		//Resposta recebida do servidor
		String response = Connection.getInstance().ReceiveSignal();

		//Login efetuado com sucesso
		if (response.equals("ok")) {
			logged = true;
			this.id = id;
		}
		return response;
	}

	public void Logout () {
		logged = false;
		this.id = null;
	}
}
