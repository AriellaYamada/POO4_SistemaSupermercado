package Interface.Client.Controller;

import Client.Connection;
import Interface.MainInterface;
import Structure.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class cartListController {

	@FXML public Label l_total_value;
	@FXML public VBox alertDialog;
	@FXML public VBox clearDialog;
	@FXML public VBox confirmDialog;
	@FXML private TableView<Product> tv_table;
	@FXML private TableColumn<Product, String> c_name;
	@FXML private TableColumn<Product, Float> c_price;
	@FXML private TableColumn<Product, String> c_expiration;
	@FXML private TableColumn<Product, String> c_provider;
	@FXML private TableColumn<Product, Integer> c_amount;

	@FXML private VBox alert;
	@FXML private Text alert_product_name;

	private ObservableList<Product> data = FXCollections.observableArrayList();

	@FXML
	public void initialize(URL location, ResourceBundle resources) {
		// Configura TableView
		c_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		c_price.setCellValueFactory(new PropertyValueFactory<>("price"));
		c_expiration.setCellValueFactory(new PropertyValueFactory<>("expiration"));
		c_provider.setCellValueFactory(new PropertyValueFactory<>("provider"));
		c_amount.setCellValueFactory(new PropertyValueFactory<>("quantity"));

		tv_table.setItems(data);
	}

	@FXML
	void editAmount() {

	}

	@FXML
	void dismiss() {
		alert.setVisible(false);
		clearDialog.setVisible(false);
		confirmDialog.setVisible(false);
	}

	public void backToMenu() {
		try {
			MainInterface.changeScene("Client/Model/menu.fxml");
		} catch (IOException e) {
			System.err.println("Erro ao exibir tela");
		}
	}

	@FXML
	public void showClearDialog() {
		clearDialog.setVisible(true);
	}

	@FXML
	public void showConfirmDialog() {
		confirmDialog.setVisible(true);
	}

	@FXML
	public void confirmClear() {
	}

	@FXML
	public void confirmEndSale() {
		Connection.getInstance().SendSignal("confirmEndSale");
	}

	public void refresh(){}
}