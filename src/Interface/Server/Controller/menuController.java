package Interface.Server.Controller;

import Interface.MainInterface;

import java.io.IOException;

public class menuController {
	public void listProducts() {
		try {
			MainInterface.changeScene("Server/Model/productList.fxml");
		} catch (IOException e) {
			System.err.println("Erro ao exibir tela");
		}
	}

	public void listClients() {
	}

	public void listSell() {
	}

	public void shutdownServer() {
	}
}
