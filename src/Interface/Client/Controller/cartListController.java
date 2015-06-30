package Interface.Client.Controller;

import Client.Connection;
import Interface.MainInterface;
import Structure.Def;
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
	public void initialize() {
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
	void editAmount() {

	}

	@FXML
	void dismiss() {
		alertDialog.setVisible(false);
		clearDialog.setVisible(false);
		confirmDialog.setVisible(false);
	}

	public void backToMenu() {
		MainInterface.changeSceneWE("Client/Model/menu.fxml");
	}

	@FXML
	public void showClearDialog() {
		if (!data.isEmpty())
			clearDialog.setVisible(true);
	}

	@FXML
	public void showConfirmDialog() {
		if (!data.isEmpty())
			confirmDialog.setVisible(true);
	}

	@FXML
	public void confirmClear() {
		Connection.getInstance().SendSignal("clearcart");
		Connection.getInstance().ReceiveSignal();
		refresh();
		clearDialog.setVisible(false);
	}

	@FXML
	public void confirmEndSale() {
		Connection.getInstance().SendSignal("sell");
		Connection.getInstance().ReceiveSignal();
		confirmDialog.setVisible(false);
		refresh();
	}

	public void refresh(){
		data.clear();

		Float value = 0f;

		// Requisitar a lista de produtos para o servidor
		Connection.getInstance().SendSignal("listcart");

		//Resposta do servidor com todos os produtos
		String response = Connection.getInstance().ReceiveSignal();

		if (!response.isEmpty()) {
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

			for (Product p : data)
				value += p.getPrice()*p.getAmount_virtual();
		}

		l_total_value.setText(String.format("%.2f", value));
	}
}