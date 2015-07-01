package Interface.Client.Controller;

import Client.Client;
import Interface.MainInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class menuController {

	@FXML private Label l_greetings;

	//Inicializa o menu do cliente
	@FXML
	void initialize(){
		l_greetings.setText("Olá, " + Client.getUserName());
	}

	//Direcionamento para a listagem dos produtos
	@FXML
	void listProducts() {
		MainInterface.changeSceneWE("Client/Model/productList.fxml");
	}

	//Direcionamento para o carrinho
	@FXML
	void salesCart() {
		MainInterface.changeSceneWE("Client/Model/cartList.fxml");
	}

	//Realiza o logout no systema
	@FXML
	void logOut() {
		Client.Logout();
		System.exit(0);
	}
}


