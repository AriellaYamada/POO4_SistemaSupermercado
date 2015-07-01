package Interface.Server.Controller;

import Interface.MainInterface;

public class menuController {

	//Direciona para a listagem de produtos
	public void listProducts() {
		MainInterface.changeSceneWE("Server/Model/productList.fxml");
	}

	//Direciona para a listagem de clientes
	public void listClients() {
		MainInterface.changeSceneWE("Server/Model/userList.fxml");
	}

	//Direciona para a listagem de vendas
	public void listSell() {
		MainInterface.changeSceneWE("Server/Model/saleList.fxml");
	}

	//Encerra o programa
	public void shutdownServer() {
		System.exit(0);
	}
}
