package Client;

import java.util.Scanner;
import static java.lang.System.*;

public class Client {

	public Connection conn;
	String split = ",";
	String signal;

	private boolean logged;
	private String id;

	public Client (String ip, int port) {
		conn = new Connection(ip, port);
		logged = false;
	}

	public void AddNewUser (String name, String address, String tel, String email, String id, String password) {

		signal = "newuser" + split + name + split + address + split + tel + split + email + split + id + split + password;

		conn.SendSignal(signal);
	}

	public int Login (String id, String password) {
		signal = "login" + split + id + split + password;

		conn.SendSignal(signal);

		String response = conn.ReceiveSignal();
		if (response.equals("wrong_user")) {
			return 1;
		} else if (response.equals("wrong_password")) {
				return 2;
		} else if (response.equals("true")) {
			logged = true;
			this.id = id;
			return 0;
		} else {
			return -1;
		}
	}

	public void Logout () {
		logged = false;
		this.id = null;
	}
}
