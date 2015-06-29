package Interface.Client.Controller;

import Client.Client;
import Interface.MainInterface;
import Structure.Def;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

import static Structure.Def.FieldType.*;
import static Structure.Def.validateField;

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
		Def.clearErrorStyle(f_userlogin, f_userpassword);

		boolean valid  = validateField(f_userlogin, TEXT);
		valid = valid && validateField(f_userpassword, TEXT);

		if (valid) {    // Try to Login
			String response = Client.Login(f_userlogin.getText(), f_userpassword.getText());

			if (response.equals("ok")) {    // if success, go to Main Page
				MainInterface.changeSceneWE("Client/Model/menu.fxml");
			} else {    // If fail, set error
				String[] splited = Def.splitReg(response);
				String[] error = Def.splitField(splited[1]);
				Def.setError(getField(error[0]), error[1]);
			}
		}
	}

	@FXML
	public void handleCleanBtn() {
		Def.clearErrorStyle(f_name, f_address, f_tel, f_email, f_id, f_password, f_confirmp);
		Def.clearField(f_name, f_address, f_tel, f_email, f_id, f_password, f_confirmp);
	}

	@FXML
	public void handleAddUserBtn() {
		// Reset field style then check if its not empty
		Def.clearErrorStyle(f_name, f_address, f_tel, f_email, f_id, f_password, f_confirmp);

		boolean valid  = validateField(f_name,      TEXT);
		valid = valid && validateField(f_address, TEXT);
		valid = valid && validateField(f_tel, TEXT);
		valid = valid && validateField(f_email, EMAIL);
		valid = valid && validateField(f_id, TEXT);
		valid = valid && validateField(f_password,  TEXT);
		valid = valid && validateField(f_confirmp,  TEXT);


		// If all fields have some text...
		if (valid) {

			// Verify if "password" and "confirm password" are the same
			if (!f_password.getText().equals(f_confirmp.getText())) {
				Def.setError(f_password, "As senhas digitadas não são iguais");
				Def.setError(f_confirmp, "As senhas digitadas não são iguais");
			} else {
				// Send the data to server and read the answer from server
				String answer = Client.AddNewUser(f_name.getText(),
												  f_address.getText(),
												  f_tel.getText(),
												  f_email.getText(),
												  f_id.getText(),
												  f_password.getText());

				if (!answer.equals("ok")) {  // If the answer is not ok
					String[] splited = Def.splitReg(answer);

					// Ignores the first item of the array (probably a "fail")
					for (int i = 1; i < splited.length; i++){
						String[] error = Def.splitField(splited[i]);
						TextInputControl fieldError = getField(error[0]);

						// Mark the errors on screen
						if (fieldError != null) { Def.setError(fieldError, error[1]);
						}
					}
				} else {// If all data is ok
					f_userlogin.setText(f_id.getText());
					f_userpassword.setText(f_password.getText());
					handleCleanBtn();
					handleSendLogin();
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
