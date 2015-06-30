package Interface.Client.Controller;

import Client.Client;
import Interface.MainInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class menuController {

	@FXML private Label l_greetings;

	@FXML
	void initialize(){
		l_greetings.setText("Olá, " + Client.getUserName());
	}

	@FXML
	void listProducts() {
		MainInterface.changeSceneWE("Client/Model/productList.fxml");
	}

	@FXML
	void salesCart() {
		MainInterface.changeSceneWE("Client/Model/cartList.fxml");
	}

	@FXML
	void logOut() {
		Client.Logout();
		System.exit(0);
	}
}


