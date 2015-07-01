package Client;

import java.io.IOException;

public class ClientMain {

	//Inicializa a aplicacao do cliente
	public static void main (String[] args) throws IOException {
		Client.Connect(args[0], Integer.parseInt(args[1]));
	}
}
