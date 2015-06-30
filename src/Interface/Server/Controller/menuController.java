package Interface.Server.Controller;

import Interface.MainInterface;

public class menuController {
	public void listProducts() {
		MainInterface.changeSceneWE("Server/Model/productList.fxml");
	}

	public void listClients() {
		MainInterface.changeSceneWE("Server/Model/userList.fxml");
	}

	public void listSell() {
		MainInterface.changeSceneWE("Server/Model/saleList.fxml");
	}

	public void shutdownServer() {
		System.exit(0);
	}
}
