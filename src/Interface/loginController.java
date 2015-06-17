package Interface;

import Client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class loginController {
	@FXML public Button b_login;
	@FXML public Button b_cleanFields;
	@FXML public Button b_addUser;
	@FXML public TextField f_userlogin;
	@FXML public TextField f_userpassword;
	@FXML public TextField f_name;
	@FXML public TextField f_address;
	@FXML public TextField f_tel;
	@FXML public TextField f_email;
	@FXML public TextField f_id;
	@FXML public TextField f_password;
	@FXML public TextField f_confirmp;

	public Client client;

	public loginController () {
		client = new Client();
	}

	@FXML
	void handleSendLogin(ActionEvent event) {

	}

	@FXML
	void handleCleanBtn(ActionEvent event) {
		f_name.clear();
		f_address.clear();
		f_tel.clear();
		f_email.clear();
		f_id.clear();
		f_password.clear();
		f_confirmp.clear();
	}

	@FXML
	void handleAddUserBtn(ActionEvent event) {

		if(f_name.getText().isEmpty() || f_address.getText().isEmpty() || f_tel.getText().isEmpty()
				|| f_email.getText().isEmpty() || f_id.getText().isEmpty()|| f_password.getText().isEmpty()
				|| f_confirmp.getText().isEmpty()) {

		} else if(f_password.getText() != f_confirmp.getText()) {

		} else {
			client.AddNewUser(f_name.getText(), f_address.getText(), f_tel.getText(), f_email.getText(),
					f_id.getText(), f_password.getText());
		}

	}
}
