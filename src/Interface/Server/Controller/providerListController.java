package Interface.Server.Controller;

import Interface.MainInterface;
import Server.Database.Products;
import Structure.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class providerListController {

	@FXML private TableView<Product> tv_table;
	@FXML private TableColumn<Product, String> c_name;
	@FXML private TableColumn<Product, String> c_provider;
	@FXML private TableColumn<Product, String> c_price;
	@FXML private TableColumn<Product, Integer> c_amount_real;
	@FXML private TableColumn<Product, Integer> c_amount_virtual;
	@FXML private TableColumn<Product, String> c_expiration;

	ObservableList<Product> data = FXCollections.observableArrayList();

	@FXML
	public void initialize() {
		// Configura TableView
		c_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		c_price.setCellValueFactory(new PropertyValueFactory<>("priceAsStr"));
		c_expiration.setCellValueFactory(new PropertyValueFactory<>("expiration"));
		c_provider.setCellValueFactory(new PropertyValueFactory<>("provider"));
		c_amount_real.setCellValueFactory(new PropertyValueFactory<>("amount_real"));
		c_amount_virtual.setCellValueFactory(new PropertyValueFactory<>("amount_virtual"));

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
		Products.ListAll().stream()
				.filter(p -> p.getAmount_real() == 0)
				.forEach(data::add);
	}

}
