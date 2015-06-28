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
		try {
			MainInterface.changeScene("Server/Model/userList.fxml");
		} catch (IOException e) {
			System.out.println("Erro ao abrir a tela");
		}
	}

	public void listSell() {
		try {
			MainInterface.changeScene("Servidor/Model/saleList.fxml");
		} catch (IOException e) {
			System.out.println("Erro ao abrir a tela");
		}
	}

	public void shutdownServer() {
	}
}
