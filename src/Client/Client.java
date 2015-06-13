package Client;

import java.util.Scanner;
import static java.lang.System.*;

public class Client {

	public Connection conn;

	public Client () {
		conn = new Connection();
	}

	public void SendSignal () {
		out.println("Mensagem a ser enviada para o servidor: ");
		Scanner scan = new Scanner(in);
		String message = scan.nextLine();

		conn.SendSignal(message);
	}

	public void ReceiveSignal () {
		out.println("Mensagem recebida do servidor: " + conn.ReceiveSignal());
	}

}
