package Interface;

import Client.ClientMain;
import Server.ServerMain;
import Def.Validation;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class mainController {

	@FXML private TextField clientIP;
	@FXML private TextField clientPort;
	@FXML private TextField serverPort;

	//Inicializa a aplicacao como servidor
	@FXML
	public void connectAsServer() {
		Validation.clearErrorStyle(serverPort);

		//Validacao da porta
		boolean valid = Validation.validateField(serverPort, Validation.FieldType.INTEGER_POSITIVE_NON_ZERO);

		if (valid){ // Start Server
			String[] str = new String[1];
			str[0] = serverPort.getText();
			MainInterface.changeSceneWE("Server/Model/menu.fxml");
			ServerMain.main(str);
		}
	}

	//Inicializa a aplicacao como cliente
	@FXML
	public void connectAsClient() {
		Validation.clearErrorStyle(clientIP, clientPort);

		//Validacao dos campos de ip e porta
		boolean valid;
		valid = Validation.validateField(clientIP, Validation.FieldType.IP);
		valid = valid && Validation.validateField(clientPort, Validation.FieldType.INTEGER_POSITIVE_NON_ZERO);

		if (valid) {    // Try to connect Server
			String[] str = new String[2];
			str[0] = clientIP.getText();
			str[1] = clientPort.getText();

			MainInterface.changeSceneWE("waitingConnection.fxml");
			try {
				ClientMain.main(str);
				MainInterface.changeSceneWE("Client/Model/login.fxml");
			} catch (IOException e) {
				System.out.println("Não foi possível conectar ao servidor");
			}

		}
	}
}
