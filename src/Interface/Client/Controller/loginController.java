package Interface.Client.Controller;

import Client.Client;
import Interface.MainInterface;
import Structure.Def;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class loginController {
	@FXML private TextField f_userlogin;
	@FXML private TextField f_userpassword;
	@FXML private TextField f_name;
	@FXML private TextArea f_address;
	@FXML private TextField f_tel;
	@FXML private TextField f_email;
	@FXML private TextField f_id;
	@FXML private TextField f_password;
	@FXML private TextField f_confirmp;


	@FXML
	public void handleSendLogin() {
		f_userlogin.getStyleClass().remove("red-field");
		f_userpassword.getStyleClass().remove("red-field");

		String userlogin = f_userlogin.getText();
		String userpassword = f_userpassword.getText();

		if (userlogin.isEmpty()){ Def.setError(f_userlogin, "Este campo é obrigatório"); }
		if (userpassword.isEmpty()) { Def.setError(f_userpassword, "Este campo é obrigatório"); }

		if (!userlogin.isEmpty() && !userlogin.isEmpty()) {
			String response = Client.getInstance().Login(f_userlogin.getText(), f_userpassword.getText());
			if (response.equals("ok")) {
				try {
					MainInterface.changeScene("Client/Model/menu.fxml");
				} catch (IOException e) {
					System.err.println("Erro ao carregar a tela");
				}
			} else {
				String[] splited = Def.splitReg(response);
				String[] error = Def.splitField(splited[1]);
				Def.setError(getField(error[0]), error[1]);
			}
		}


	}

	@FXML
	public void handleCleanBtn() {
		f_name.clear();
		f_address.clear();
		f_tel.clear();
		f_email.clear();
		f_id.clear();
		f_password.clear();
		f_confirmp.clear();
	}

	@FXML
	public void handleAddUserBtn() {
		boolean validateFields = true;

		// Creates a list with all fields
		List<TextInputControl> controls = new LinkedList<>();
		controls.add(f_name);
		controls.add(f_address);
		controls.add(f_tel);
		controls.add(f_email);
		controls.add(f_id);
		controls.add(f_password);
		controls.add(f_confirmp);

		// Reset field style then check if its not empty
		for (TextInputControl c : controls){
			c.getStyleClass().remove("red-field");
			c.setTooltip(null);
			if (c.getText().isEmpty()){
				validateFields = false;
				Def.setError(c, "Este campo é obrigatório");
			}
		}

		// If all fields have some text...
		if (validateFields) {

			// Verify if "password" and "confirm password" are the same
			if (!f_password.getText().equals(f_confirmp.getText())) {
				Def.setError(f_password, "As senhas digitadas não são iguais");
				Def.setError(f_confirmp, "As senhas digitadas não são iguais");
			} else {
				// Send the data to server and read the answer from server
				String answer = Client.getInstance().AddNewUser(f_name.getText(),
																f_address.getText(),
																f_tel.getText(),
																f_email.getText(),
																f_id.getText(),
																f_password.getText());

				if (!answer.equals("ok")) {  // If the answer is not ok
					String[] splited = Def.splitReg(answer);

					// Ignores the first item of the array (probally a "fail")
					for (int i = 1; i < splited.length; i++){
						String[] error = Def.splitField(splited[i]);
						TextInputControl fieldError = getField(error[0]);

						// Mark the errors on screen
						if (fieldError != null) { Def.setError(fieldError, error[1]);
						}
					}
				} else {// If all data is ok
					try {
						MainInterface.changeScene("Client/Model/menu.fxml");
					} catch (IOException e) {
						System.err.println("Erro ao exibir a tela");
					}
				}
			}
		}
	}

	private TextInputControl getField(String name){
		switch (name){
			case "f_userlogin":
				return f_userlogin;
			case "f_userpassword":
				return f_userpassword;
			case "f_name":
				return f_name;
			case "f_address":
				return f_address;
			case "f_tel":
				return f_tel;
			case "f_email":
				return f_email;
			case "f_id":
				return f_id;
			case "f_password":
				return f_password;
			case "f_confirmp":
				return f_confirmp;
		}
		return null;
	}
}
