package Interface.Client.Controller;

import Interface.MainInterface;
import Structure.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

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

	@FXML
	void editAmount() {

	}

	@FXML
	void dismiss() {
		alert.setVisible(false);
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
	}

	@FXML
	public void confirmClear() {
	}

	@FXML
	public void confirmEndSale() {
	}
}