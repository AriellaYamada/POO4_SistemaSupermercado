package Interface.Server.Controller;

import Interface.MainInterface;
import Structure.CartItem;
import Structure.Sale;
import Structure.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class saleListController implements Initializable{

	ObservableList<Sale> data = FXCollections.observableArrayList();

	@FXML private TableView<Sale> tv_table;
	@FXML private TableColumn<Sale, String> c_date;
	@FXML private TableColumn<Sale, String> c_name;
	@FXML private TableColumn<Sale, CartItem> c_items;
	@FXML private TableColumn<Sale, String> c_item_name;
	@FXML private TableColumn<Sale, Integer> c_item_amount;
	@FXML private TableColumn<Sale, Float> c_item_price_unit;
	@FXML private TableColumn<Sale, Float> c_item_price_total;
	@FXML private TableColumn<Sale, Float> c_total_price;
	@FXML private Label l_name;
	@FXML private VBox modal_details;
	@FXML private Label l_total_price;
	@FXML private Label l_date;

	/*private User user;
	private List<CartItem> products;
	private String date;*/


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*c_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		c_name.setCellValueFactory(new PropertyValueFactory<>("user.name"));
		c_items.setCellValueFactory(new PropertyValueFactory<>("user.name"));
		c_item_name.setCellValueFactory(new PropertyValueFactory<>("user.name"));
		c_item_amount.setCellValueFactory(new PropertyValueFactory<>("user.name"));
		c_item_price_unit.setCellValueFactory(new PropertyValueFactory<>("user.name"));
		c_item_price_total.setCellValueFactory(new PropertyValueFactory<>("user.name"));
		c_total_price.setCellValueFactory(new PropertyValueFactory<>("user.name"));*/
	}

	@FXML
	void backToMenu(ActionEvent event) {
		try {
			MainInterface.changeScene("Server/Model/menu.fxml");
		} catch (IOException e) {
			System.out.println("Erro ao exibir a tela");
		}
	}

	@FXML
	void refresh(ActionEvent event) {

	}

	@FXML
	void pdfGenerate(ActionEvent event) {

	}

	@FXML
	void confirm_edit(ActionEvent event) {

	}

	@FXML
	void dismiss(ActionEvent event) {

	}

	@FXML
	void confirmClear(ActionEvent event) {

	}
}
