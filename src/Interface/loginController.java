package Interface;

import Client.Client;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
			String response = Client.getInstance().Login(f_userlogin.getText(), f_userpassword.getText());
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
		boolean validateFields = true;
		String response;

		List<TextInputControl> controls = new LinkedList<>();
		controls.add(f_name);
		controls.add(f_address);
		controls.add(f_tel);
		controls.add(f_email);
		controls.add(f_id);
		controls.add(f_password);
		controls.add(f_confirmp);

		for (TextInputControl c : controls){
			if (c.getText().isEmpty()){
				validateFields = false;
				c.getStyleClass().add("red-field");
				c.setTooltip(new Tooltip("Este campo é obrigatório"));
			}
		}

		if (validateFields) {
			if (!f_password.getText().equals(f_confirmp.getText())) {
				f_password.getStyleClass().add("red-field");
				f_confirmp.getStyleClass().add("red-field");
				f_password.setTooltip(new Tooltip("As senhas digitadas não são iguais"));
				f_confirmp.setTooltip(new Tooltip("As senhas digitadas não são iguais"));
			} else {
				response = Client.getInstance().AddNewUser(f_name.getText(), f_address.getText(), f_tel.getText(), f_email.getText(),
						f_id.getText(), f_password.getText());

				if (response.equals("ok")){
					handleCleanBtn(event);
					/*try {
						MainInterface.changeScene("menu.fxml");
					} catch (IOException e) {
						System.out.println("Erro ao exibir a tela");
					}*/
				}
				else {
					System.out.println(response);
				}
			}
		}
	}
}
