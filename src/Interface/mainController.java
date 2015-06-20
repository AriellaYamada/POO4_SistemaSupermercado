package Interface;

import Client.ClientMain;
import Server.ServerMain;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.event.ActionEvent;

public class mainController {

	@FXML private Button connectClientBtn;
	@FXML private TextField clientIP;
	@FXML private TextField clientPort;
	@FXML private TextField serverPort;
	@FXML private Button connectServerBtn;

	@FXML
	public void connectAsServer(ActionEvent event) {
		String port = serverPort.getText();

		if (!port.equals("")) {
			String[] str = new String[1];
			str[0] = port;
			ServerMain.main(str);
		}
		else {
			serverPort.getStyleClass().add("red-field");
		}
	}

	@FXML
	public void connectAsClient(ActionEvent event) {
		String ip = clientIP.getText();
		String port = clientPort.getText();

		if (port.equals("")){
			clientPort.getStyleClass().add("red-field");
		}

		if (ip.equals("")){
			clientIP.getStyleClass().add("red-field");
		}

		if (!port.equals("") && !ip.equals("")){
			String[] str = new String[2];
			str[0] = ip;
			str[1] = port;
			ClientMain.main(str);
		}
	}
}
