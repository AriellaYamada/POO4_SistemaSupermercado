package Interface.Client.Controller;

import Structure.Product;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class cartListController {

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
}