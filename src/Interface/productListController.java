package Interface;

import Client.Connection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class productListController {

	@FXML private VBox vbox_list;

	public void initialize(){
		// Requisitar a lista de produtos para o servidor
		Connection.getInstance().SendSignal("listall");
		//Resposta do servidor com todos os produtos
		String response = Connection.getInstance().ReceiveSignal();
		String splitField = "|";
		String splitRegister = ";";

		String[] products = response.split(splitRegister);

		for (String s : products){
			String[] splited = s.split(splitField);
			Label id = new Label("1");
			Label product = new Label(splited[0]);
			Label price = new Label(splited[1]);
			Label amount = new Label(splited[2]);
			Label add = new Label("+");

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
		}
	}

	public void salesCart() {
	}

	@FXML
	public void backToMenu() {
	}
}
