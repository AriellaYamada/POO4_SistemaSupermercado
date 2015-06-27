package Interface.Server.Controller;

import Interface.MainInterface;
import Server.Database.Products;
import Structure.Def;
import Structure.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class productListController implements Initializable {

	@FXML private VBox modal_edit;
	@FXML private TextField f_edit_name;
	@FXML private TextField f_edit_price;
	@FXML private TextField f_edit_expiration;
	@FXML private TextField f_edit_provider;
	@FXML private Label l_amount_now;
	@FXML private TextField f_edit_amount;

	@FXML private VBox modal_new;
	@FXML private TextField f_new_name;
	@FXML private TextField f_new_price;
	@FXML private TextField f_new_expiration;
	@FXML private TextField f_new_provider;
	@FXML private TextField f_new_amount;

	@FXML private TableView<Product> tv_table;
	@FXML private TableColumn<Product, String> c_name;
	@FXML private TableColumn<Product, Float> c_price;
	@FXML private TableColumn<Product, String> c_expiration;
	@FXML private TableColumn<Product, String> c_provider;
	@FXML private TableColumn<Product, Integer> c_amount;

	@FXML private VBox alert;
	@FXML private Text alert_product_name;

	private Product selected = null;

	ObservableList<Product> data = FXCollections.observableArrayList();

	@FXML
	public void initialize(URL location, ResourceBundle resources) {
		// Configura TableView
		c_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		c_price.setCellValueFactory(new PropertyValueFactory<>("price"));
		c_expiration.setCellValueFactory(new PropertyValueFactory<>("expiration"));
		c_provider.setCellValueFactory(new PropertyValueFactory<>("provider"));
		c_amount.setCellValueFactory(new PropertyValueFactory<>("quantity"));

		tv_table.setItems(data);

		refresh();
	}

	@FXML
	public void salesCart() {
		try {
			MainInterface.changeScene("Client/Model/cartList.fxml");
		} catch (IOException e) {
			System.err.println("Erro ao exibir tela");
		}
	}

	@FXML
	public void backToMenu() {
		try {
			MainInterface.changeScene("Client/Model/menu.fxml");
		} catch (IOException e) {
			System.err.println("Erro ao exibir tela");
		}
	}

	@FXML
	public void addCart() {
		// Pegar o elemento que está selecionado no TableView
		selected = tv_table.getSelectionModel().getSelectedItem();

		// e chamar o método "addCart" dele
		// p.addCart();

		// Se for possível adicionar -> Ok, adicionado.
		// Se não for possível, então:
		//      alert.setVisible(true);
		selected.addToCart();
	}

	@FXML
	void dismiss() {
		alert.setVisible(false);
	}

	@FXML
	public void refresh() {
		data.clear();
		data.addAll(Products.getInstance().ListAll());
	}

	@FXML
	public void showNew() {
		modal_new.setVisible(true);
	}

	@FXML
	public void showEdit() {
		selected = tv_table.getSelectionModel().getSelectedItem();
		f_edit_name.setText(selected.getName());
		f_edit_price.setText(selected.getPriceAsStr());
		f_edit_expiration.setText(selected.getExpirationAsStr());
		f_edit_provider.setText(selected.getProvider());
		l_amount_now.setText(selected.getQuantityAsStr());
	}

	@FXML
	public void pdfGenerate() {
		System.out.println("Este comando ainda não está implementado.");
	}

	@FXML
	public void updateAmount() {
		int now = selected.updateAmount(Integer.parseInt(f_edit_amount.getText()));

		if (now < 0) Def.setError(f_edit_amount, "Esta alteração deixaria o estoque negativo.");
		else {

		}
	}

	@FXML
	public void confirm_edit() {
	}

	@FXML
	public void confirm_new() {
	}
}
