package Interface.Client.Controller;

import Client.Connection;
import Interface.MainInterface;
import Def.Split;
import Structure.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
	@FXML public VBox changeAmoutDialog;
	@FXML public Label l_product_amount;
	@FXML public Text t_product_name;

	@FXML private TableView<Product> tv_table;
	@FXML private TableColumn<Product, String> c_name;
	@FXML private TableColumn<Product, Double> c_price;
	@FXML private TableColumn<Product, String> c_expiration;
	@FXML private TableColumn<Product, String> c_provider;
	@FXML private TableColumn<Product, Integer> c_amount;

	@FXML private VBox alert;
	@FXML private Text alert_product_name;

	private ObservableList<Product> data = FXCollections.observableArrayList();

	private Product p;

	@FXML
	public void initialize() {
		// Configura TableView
		c_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		c_price.setCellValueFactory(new PropertyValueFactory<>("priceAsStr"));
		c_expiration.setCellValueFactory(new PropertyValueFactory<>("expiration"));
		c_provider.setCellValueFactory(new PropertyValueFactory<>("provider"));
		c_amount.setCellValueFactory(new PropertyValueFactory<>("amount_virtual"));

		tv_table.setItems(data);

		tv_table.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2) showChangeAmountDialog();
		});

		refresh();
	}

	@FXML
	void editAmount() {

	}

	//Configuracao dos botoes de cancelar nas 'janelas'
	@FXML
	void dismiss() {
		alertDialog.setVisible(false);
		clearDialog.setVisible(false);
		confirmDialog.setVisible(false);
		changeAmoutDialog.setVisible(false);
		refresh();
	}

	//Configuracao do botao de voltar
	public void backToMenu() {
		MainInterface.changeSceneWE("Client/Model/menu.fxml");
	}

	//Confirmacao de limpeza do carrinho
	@FXML
	public void showClearDialog() {
		if (!data.isEmpty())
			clearDialog.setVisible(true);
	}

	//Confirmacao de solicitacao de compra
	@FXML
	public void showConfirmDialog() {
		if (!data.isEmpty())
			confirmDialog.setVisible(true);
	}

	//Solicita a limpeza do carrinho no servidor
	@FXML
	public void confirmClear() {
		Connection.getInstance().SendSignal("clearcart");
		Connection.getInstance().ReceiveSignal();
		refresh();
		clearDialog.setVisible(false);
	}

	//Solicitacao da compra no servidor
	@FXML
	public void confirmEndSale() {
		Connection.getInstance().SendSignal("sell");
		Connection.getInstance().ReceiveSignal();
		confirmDialog.setVisible(false);
		refresh();
	}

	//Atualizacao do carrinho
	public void refresh(){
		data.clear();

		double value = 0f;

		// Requisitar a lista de produtos para o servidor
		Connection.getInstance().SendSignal("listcart");

		//Resposta do servidor com todos os produtos
		String response = Connection.getInstance().ReceiveSignal();

		if (!response.isEmpty()) {
			String[] products = Split.splitReg(response);
			for (String s : products) {
				String[] splited = Split.splitField(s);
				data.add(new Product(splited[0],
								Double.parseDouble(splited[1]),
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

	@FXML
	public void showChangeAmountDialog() {
		p = tv_table.getSelectionModel().getSelectedItem();
		t_product_name.setText(p.getName());
		l_product_amount.setText(p.getAmountVirtualAsStr());
		if(!data.isEmpty())
			changeAmoutDialog.setVisible(true);
	}

	@FXML
	public void removeOneCart() {
		if (Integer.parseInt(l_product_amount.getText()) > 1) {
			String signal = "dereserve" + Split.regSep + p.getName() + Split.fieldSep + "1";
			Connection.getInstance().SendSignal(signal);
			if (Connection.getInstance().ReceiveSignal().equals("ok"))
				p.selfRefreshCart();
			l_product_amount.setText(p.getAmountVirtualAsStr());
		} else {
			removAllCart();
		}
	}

	@FXML
	public void addOneCart() {
		String signal = "reserve" + Split.regSep + p.getName() + Split.fieldSep + "1";
		Connection.getInstance().SendSignal(signal);
		if (Connection.getInstance().ReceiveSignal().equals("ok"))
			p.selfRefreshCart();
		l_product_amount.setText(p.getAmountVirtualAsStr());
	}

	@FXML
	public void removAllCart() {
		String signal = "dereserve" + Split.regSep + p.getName() + Split.fieldSep + p.getAmountVirtualAsStr();
		Connection.getInstance().SendSignal(signal);
		if (Connection.getInstance().ReceiveSignal().equals("ok"))
			dismiss();
		refresh();
	}
}