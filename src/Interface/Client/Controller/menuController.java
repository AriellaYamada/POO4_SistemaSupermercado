package Interface.Client.Controller;

import Interface.MainInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class menuController {

	@FXML private Label l_greetings;

	@FXML
	void initialize(){
		/*Isso aqui será feito quando a tela for chamada,
		então requisite o nome do usuário ao servidor e coloque no lugar de 'pessoa' */
		l_greetings.setText("Olá, pessoa!");
	}

	@FXML
	void listProducts() {
		try {
			MainInterface.changeScene("Client/Model/productList.fxml");
		} catch (IOException e) {
			System.err.println("Erro ao exibir a tela");
		}
	}

	@FXML
	void salesCart() {
		try {
			MainInterface.changeScene("Client/Model/cartList.fxml");
		} catch (IOException e) {
			System.err.println("Erro ao exibir a tela");
		}
	}

	@FXML
	void logOut() {

	}
}


