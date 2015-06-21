package Interface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class menuController {
	
	@FXML private Label l_greetings;

	@FXML
	void initialize(){
		/*Isso aqui será feito quando a tela for chamada,
		então requisite o nome do usuário ao servidor e coloque no lugar de 'pessoa' */
		l_greetings.setText("Olá, pessoa!");
	}

	@FXML
	void listProducts(ActionEvent event) {
		try {
			MainInterface.changeScene("productList.fxml");
		} catch (IOException e) {
			System.err.println("Erro ao carregar a tela");
		}
	}

	@FXML
	void salesCart(ActionEvent event) {
		try {
			MainInterface.changeScene("cartList.fxml");
		} catch (IOException e) {
			System.err.println("Erro ao carregar a tela");
		}
	}

	@FXML
	void logOut(ActionEvent event) {

	}
}


