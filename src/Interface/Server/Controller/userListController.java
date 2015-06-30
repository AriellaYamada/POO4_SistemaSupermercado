package Interface.Server.Controller;

import Interface.MainInterface;
import Server.Database.Users;
import Structure.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class userListController {

	ObservableList<User> data = FXCollections.observableArrayList();

	@FXML private TableView<User> tv_table;
	@FXML private TableColumn<User, String> c_name;
	@FXML private TableColumn<User, String> c_address;
	@FXML private TableColumn<User, String> c_phone;
	@FXML private TableColumn<User, String> c_email;
	@FXML private TableColumn<User, String> c_userid;

	@FXML
	public void initialize() {
		c_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		c_address.setCellValueFactory(new PropertyValueFactory<>("address"));
		c_phone.setCellValueFactory(new PropertyValueFactory<>("tel"));
		c_email.setCellValueFactory(new PropertyValueFactory<>("email"));
		c_userid.setCellValueFactory(new PropertyValueFactory<>("id"));

		tv_table.setItems(data);

		refresh();
	}

	@FXML
	void backToMenu() {
		MainInterface.changeSceneWE("Server/Model/menu.fxml");
	}

	@FXML
	void refresh() {
		data.clear();
		data.addAll(Users.ListAll());
	}

	@FXML
	void pdfGenerate() {

	}

}
