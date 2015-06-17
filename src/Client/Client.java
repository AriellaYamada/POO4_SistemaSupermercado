package Client;

import java.util.Scanner;
import static java.lang.System.*;

public class Client {

	public Connection conn;

	public Client () {
		conn = new Connection();
	}

	public void AddNewUser (String name, String address, String tel, String email, String id, String password) {
		String split = ",";
		String signal = "newuser" + name + split + address + split + tel + split + email + split + id + split + password;

		conn.SendSignal(signal);
	}
}
