package Interface;

import Client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class loginController {
	@FXML private Button b_login;
	@FXML private Button b_cleanFields;
	@FXML private Button b_addUser;
	@FXML private TextField f_userlogin;
	@FXML private TextField f_userpassword;
	@FXML private TextField f_name;
	@FXML private TextArea f_address;
	@FXML private TextField f_tel;
	@FXML private TextField f_email;
	@FXML private TextField f_id;
	@FXML private TextField f_password;
	@FXML private TextField f_confirmp;

	public Client client;


	@FXML
	public void handleSendLogin(ActionEvent event) {
		f_userlogin.getStyleClass().remove("red-field");
		f_userpassword.getStyleClass().remove("red-field");

		String userlogin = f_userlogin.getText();
		String userpassword = f_userpassword.getText();

		if (userlogin.isEmpty()){
			f_userlogin.getStyleClass().add("red-field");
		}

		if (userpassword.isEmpty()) {
			f_userpassword.getStyleClass().add("red-field");
		}

		if (!userlogin.isEmpty() && !userlogin.isEmpty()) {
			String response = client.Login(f_userlogin.getText(), f_userpassword.getText());
			String[] splited = response.split("|");

			if (splited[0].equals("ok")) {
				try {
					MainInterface.changeScene("menu.fxml");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				String[] error = splited[1].split("&");

				switch (error[0]){
					case "f_userlogin":
						f_userlogin.getStyleClass().add("red-field");
						break;
					case "f_userpassword":
						f_userpassword.getStyleClass().add("red-field");
						break;
				}
			}
		}
	}

	@FXML
	public void handleCleanBtn(ActionEvent event) {
		f_name.clear();
		f_address.clear();
		f_tel.clear();
		f_email.clear();
		f_id.clear();
		f_password.clear();
		f_confirmp.clear();
	}

	@FXML
	public void handleAddUserBtn(ActionEvent event) {

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
