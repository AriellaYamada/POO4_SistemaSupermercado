package Interface;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class productListController {

	@FXML private VBox vbox_list;

	public void initialize(){
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
	}

	public void salesCart() {
	}

	@FXML
	public void backToMenu() {
	}
}
