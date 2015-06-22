package Interface;

import Database.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class productListController implements Initializable{

	@FXML private TableView<Table> tv_table;
	@FXML private TableColumn<Table, String> c_product;
	@FXML private TableColumn<Table, Integer> c_id;
	@FXML private TableColumn<Table, Float> c_price;
	@FXML private TableColumn<Table, Integer> c_amount;

	final ObservableList<Table> data = FXCollections.observableArrayList(new Table(2, "nome teste", 20.50f, 70));

	public void initialize(URL location, ResourceBundle resources){
		/*
		// Requisitar a lista de produtos para o servidor
		// answer = getSignalServer()

		// String[] products = answer.split("Separador de linha; Talvez o '\n' funcione aqui.");

		// for (String s : products){
		//      String[] splited = s.split("Separador de campo; Acho que o '|' ficaria muito bem.");
		Label id = new Label("ID qqr");                 // splited[0]
		Label product = new Label("Nome do produto");   // splited[1]
		Label price = new Label("59,90");               // splited[2]
		Label amount = new Label("42");                 // splited[3]
		Label add = new Label("+");                     // splited[4]

		id.getStylesheets().add("Interface/style.css");
		product.getStylesheets().add("Interface/style.css");
		price.getStylesheets().add("Interface/style.css");
		amount.getStylesheets().add("Interface/style.css");
		add.getStylesheets().add("Interface/style.css");

		id.getStyleClass().addAll("table-cell", "col-id");
		product.getStyleClass().addAll("table-cell", "col-product");
		price.getStyleClass().addAll("table-cell", "col-price");
		amount.getStyleClass().addAll("table-cell", "col-amount");
		add.getStyleClass().addAll("table-cell", "col-add");

		// Se o produto não estiver disponível é aqui que será incluída
		// uma classe indicando que o produto está zerado no estoque.

		// Configurar EXATAMENTE o que o clique do mouse vai fazer.
		// Não basta apenas reduzir o numero. Tem que mandar reservar no servidor,
		// pegar a resposta do servidor de volta e tratar isso.
		add.setOnMouseClicked(c -> {
			int am = Integer.parseInt(amount.getText());
			amount.setText(Integer.valueOf(am--).toString());
		});

		// Cria um HBox pra linha e adiciona tudo nela
		HBox hb = new HBox();
		hb.getStyleClass().add("table-line");
		hb.getChildren().addAll(id, product, price, amount, add);

		// Adiciona o HBox na VBox já existente
		vbox_list.getChildren().add(hb);
		//} (END FOR)
		*/

		c_id.setCellValueFactory(new PropertyValueFactory<Table, Integer>("p_id"));
		c_product.setCellValueFactory(new PropertyValueFactory<Table, String>("p_product"));
		c_price.setCellValueFactory(new PropertyValueFactory<Table, Float>("p_price"));
		c_amount.setCellValueFactory(new PropertyValueFactory<Table, Integer>("p_amount"));

		tv_table.setItems(data);

		Table t = new Table(7, "nome teste", 15.50f, 50);
		Table u = new Table(20, "nome teste", 10.50f, 90);
		data.addAll(t, u);
	}

	public void salesCart() {
	}

	@FXML
	public void backToMenu() {
	}
}
