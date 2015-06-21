package Interface;

import Client.ClientMain;
import Server.ServerMain;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class mainController {

	@FXML private TextField clientIP;
	@FXML private TextField clientPort;
	@FXML private TextField serverPort;

	@FXML
	public void connectAsServer() {
		String port = serverPort.getText();

		if (!port.isEmpty()) {
			String[] str = new String[1];
			str[0] = port;
			ServerMain.main(str);
		}
		else {
			serverPort.getStyleClass().add("red-field");
		}
	}

	@FXML
	public void connectAsClient() {
		String ip = clientIP.getText();
		String port = clientPort.getText();

		if (port.isEmpty()){
			clientPort.getStyleClass().add("red-field");
		}

		if (ip.isEmpty()){
			clientIP.getStyleClass().add("red-field");
		}

		if (!port.isEmpty() && !ip.isEmpty()){
			String[] str = new String[2];
			str[0] = ip;
			str[1] = port;
			try {
				MainInterface.changeScene("waitingConnection.fxml");
				ClientMain.main(str);
			} catch (IOException e) {
				try {
					MainInterface.changeScene("main.fxml");
				} catch (IOException e1) {
					System.err.println("Erro ao exibir tela");
				}
			}
		}
	}
}
