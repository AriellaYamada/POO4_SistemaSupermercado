package Interface.Server.Controller;

import Interface.MainInterface;
import Server.Database.Sales;
import Structure.CartItem;
import Structure.Sale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class saleListController {

	@FXML private TableView<Sale> tv_table;
	@FXML private TableColumn<Sale, String> c_name;
	@FXML private TableColumn<Sale, String> c_date;
	@FXML private TableColumn<Sale, Integer> c_items;
	@FXML private TableColumn<Sale, Float> c_total_price;

	@FXML private VBox modal_details;

	@FXML private Label l_name;
	@FXML private Label l_date;

	@FXML private TableView<CartItem> tv_item;
	@FXML private TableColumn<CartItem, String> c_item_name;
	@FXML private TableColumn<CartItem, Integer> c_item_amount;
	@FXML private TableColumn<CartItem, Float> c_item_price_unit;
	@FXML private TableColumn<CartItem, Float> c_item_price_total;
	@FXML private Label l_total_price;

	ObservableList<Sale> data = FXCollections.observableArrayList();
	ObservableList<CartItem> items = FXCollections.observableArrayList();

	public void initialize() {
		c_name.setCellValueFactory(new PropertyValueFactory<>("user"));
		c_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		c_items.setCellValueFactory(new PropertyValueFactory<>("numberItems"));
		c_total_price.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

		c_item_name.setCellValueFactory(new PropertyValueFactory<>("productName"));
		c_item_amount.setCellValueFactory(new PropertyValueFactory<>("reservedQtd"));
		c_item_price_unit.setCellValueFactory(new PropertyValueFactory<>("price"));
		c_item_price_total.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

		tv_table.setItems(data);
		tv_item.setItems(items);

		refresh();
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
		data.clear();
		data.addAll(Sales.getSales());
	}

	@FXML
	void detail() {
		items.clear();
		Sale selected = tv_table.getSelectionModel().getSelectedItem();
		l_name.setText(selected.getUser().getName());
		l_date.setText(selected.getDate());
		items.addAll(selected.getProducts());
		l_total_price.setText(Float.toString(selected.getTotalPrice()));

		modal_details.setVisible(true);
	}

	@FXML
	void pdfGenerate() {

	}

	@FXML
	void dismiss() {
		modal_details.setVisible(false);
	}

}
