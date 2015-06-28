package Interface.Client.Controller;

import Interface.MainInterface;
import Structure.Def;
import Structure.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import Client.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class productListController implements Initializable {

	@FXML private TableView<Product> tv_table;
	@FXML private TableColumn<Product, String> c_name;
	@FXML private TableColumn<Product, Float> c_price;
	@FXML private TableColumn<Product, String> c_expiration;
	@FXML private TableColumn<Product, String> c_provider;
	@FXML private TableColumn<Product, Integer> c_amount;

	@FXML private VBox alert;
	@FXML private Text alert_product_name;

	ObservableList<Product> data = FXCollections.observableArrayList();

	@FXML
	public void initialize(URL location, ResourceBundle resources) {
		// Configura TableView
		c_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		c_price.setCellValueFactory(new PropertyValueFactory<>("price"));
		c_expiration.setCellValueFactory(new PropertyValueFactory<>("expiration"));
		c_provider.setCellValueFactory(new PropertyValueFactory<>("provider"));
		c_amount.setCellValueFactory(new PropertyValueFactory<>("amount_virtual"));

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
		Product p = tv_table.getSelectionModel().getSelectedItem();

		//Solicita a reserva no servidor
		if (!p.RequestReservation(1)) {
			alert_product_name.setText(p.getName());
			alert.setVisible(true);
		}
		// Atualiza quantidade no objeto
		data.filtered( d -> d.getName().equals(p.getName()) ).get(0).selfRefresh();

		// Atualiza a exibição da quantidade na tabela, sem desmarcar a linha e sem perder a ordenação
		TableColumn<Product, ?> column = tv_table.getColumns().get(2);
		if (column.isVisible()) {
			column.setVisible(false);
			column.setVisible(true);
		}
	}

	@FXML
	void dismiss() {
		alert.setVisible(false);
	}

	@FXML
	public void refresh() {
		data.clear();
		// Requisitar a lista de produtos para o servidor
		Connection.getInstance().SendSignal("listall");
		//Resposta do servidor com todos os produtos
		String response = Connection.getInstance().ReceiveSignal();

		String[] products = Def.splitReg(response);
		for (String s : products) {
			String[] splited = Def.splitField(s);
			data.add(new Product(splited[0],
					Float.parseFloat(splited[1]),
					splited[2],
					splited[3],
					Integer.parseInt(splited[4]))
			);
		}
	}
}
