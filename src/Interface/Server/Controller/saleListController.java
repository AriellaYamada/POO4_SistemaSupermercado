package Interface.Server.Controller;

import Interface.MainInterface;
import Structure.Sale;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class saleListController {

	@FXML private TableView<Sale> tv_table;
	@FXML private TableColumn<Sale, ?> c_name;
	@FXML private TableColumn<Sale, ?> c_date;
	@FXML private TableColumn<Sale, ?> c_items;
	@FXML private TableColumn<Sale, ?> c_total_price;

	@FXML private VBox modal_details;

	@FXML private Label l_name;
	@FXML private Label l_date;

	@FXML private TableColumn<Sale, ?> c_item_name;
	@FXML private TableColumn<Sale, ?> c_item_amount;
	@FXML private TableColumn<Sale, ?> c_item_price_unit;
	@FXML private TableColumn<Sale, ?> c_item_price_total;
	@FXML private Label l_total_price;

	public void initialize() {
		c_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		c_name.setCellValueFactory(new PropertyValueFactory<>("user"));
		c_items.setCellValueFactory(new PropertyValueFactory<>("user.name"));
		c_item_name.setCellValueFactory(new PropertyValueFactory<>("user.name"));
		c_item_amount.setCellValueFactory(new PropertyValueFactory<>("user.name"));
		c_item_price_unit.setCellValueFactory(new PropertyValueFactory<>("user.name"));
		c_item_price_total.setCellValueFactory(new PropertyValueFactory<>("user.name"));
		c_total_price.setCellValueFactory(new PropertyValueFactory<>("user.name"));
	}

	@FXML
	void backToMenu() {
		try {
			MainInterface.changeScene("Server/Model/menu.fxml");
		} catch (IOException e) {
			System.out.println("Erro ao exibir a tela");
		}
	}

	@FXML
	void refresh() {

	}

	@FXML
	void detail() {

	}

	@FXML
	void pdfGenerate() {

	}

	@FXML
	void dismiss() {

	}

}
