package Client;

import Interface.MainInterface;

import java.io.IOException;

public class ClientMain {

	public static void main (String[] args) throws IOException {

		Client.getInstance().Connect(args[0], Integer.parseInt(args[1]));
		
		try {
			MainInterface.changeScene("login.fxml");
		} catch (IOException e){
			System.out.println("Erro ao carregar a tela");
		}
	}
}
