package Interface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class menuController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	void listProducts(ActionEvent event) {
		try {
			MainInterface.changeScene("productList.fxml");
		} catch (IOException e) {
			System.out.println("Erro ao carregar a tela");
		}
	}

	@FXML
	void salesCart(ActionEvent event) {
		try {
			MainInterface.changeScene("cartList.fxml");
		} catch (IOException e) {
			System.out.println("Erro ao carregar a tela");
		}
	}

	@FXML
	void logOut(ActionEvent event) {

	}
}


