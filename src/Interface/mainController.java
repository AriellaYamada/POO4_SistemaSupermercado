package Interface;

import Client.ClientMain;
import Server.ServerMain;
import Structure.Def;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class mainController {

	@FXML private TextField clientIP;
	@FXML private TextField clientPort;
	@FXML private TextField serverPort;

	@FXML
	public void connectAsServer() {
		Def.clearErrorStyle(serverPort);

		boolean valid = Def.validateField(serverPort, Def.FieldType.INTEGER_POSITIVE_NON_ZERO);

		if (valid){ // Start Server
			String[] str = new String[1];
			str[0] = serverPort.getText();
			MainInterface.changeSceneWE("Server/Model/menu.fxml");
			ServerMain.main(str);
		}
	}

	@FXML
	public void connectAsClient() {
		Def.clearErrorStyle(clientIP, clientPort);

		boolean valid;
		valid = Def.validateField(clientIP, Def.FieldType.IP);
		valid = valid && Def.validateField(clientPort, Def.FieldType.INTEGER_POSITIVE_NON_ZERO);

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
