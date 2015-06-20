package Client;

import java.io.IOException;

public class Client {

	public Connection conn;
	String split = ",";
	String signal;

	private boolean logged;
	private String id;

	public Client (String ip, int port) throws IOException {
		conn = new Connection(ip, port);
		logged = false;
	}

	public String AddNewUser (String name, String address, String tel, String email, String id, String password) {

		signal = "newuser" + split + name + split + address + split + tel + split + email + split + id + split + password;

		System.out.println(signal);
		conn.SendSignal(signal);

		String response = conn.ReceiveSignal();
		return response;
	}

	public String Login (String id, String password) {

		//Comando a ser enviado ao servidor
		signal = "login" + split + id + split + password;
		conn.SendSignal(signal);

		//Resposta recebida do servidor
		String response = conn.ReceiveSignal();

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
