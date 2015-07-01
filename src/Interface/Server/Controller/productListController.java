package Interface.Server.Controller;

import Interface.MainInterface;
import Server.Database.Products;
import Server.Database.ProductsDatabase;
import Def.Validation;
import static Def.Validation.FieldType.*;
import static Def.Validation.validateField;

import Server.PDFCreator.ProductsCreator;
import Structure.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;


public class productListController {

	@FXML private VBox modal_edit;
	@FXML private TextField f_edit_name;
	@FXML private TextField f_edit_price;
	@FXML private DatePicker f_edit_expiration;
	@FXML private TextField f_edit_provider;
	@FXML private Label l_amount_now;
	@FXML private TextField f_edit_amount;

	@FXML private VBox modal_new;
	@FXML private TextField f_new_name;
	@FXML private TextField f_new_price;
	@FXML private DatePicker f_new_expiration;
	@FXML private TextField f_new_provider;
	@FXML private TextField f_new_amount;

	@FXML private TableView<Product> tv_table;
	@FXML private TableColumn<Product, String> c_name;
	@FXML private TableColumn<Product, String> c_price;
	@FXML private TableColumn<Product, String> c_expiration;
	@FXML private TableColumn<Product, String> c_provider;
	@FXML private TableColumn<Product, Integer> c_amount_real;
	@FXML private TableColumn<Product, Integer> c_amount_virtual;

	private Product selected = null;

	ObservableList<Product> data = FXCollections.observableArrayList();

	@FXML
	public void initialize() {
		// Configura TableView
		c_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		c_price.setCellValueFactory(new PropertyValueFactory<>("priceAsStr"));
		c_expiration.setCellValueFactory(new PropertyValueFactory<>("expiration"));
		c_provider.setCellValueFactory(new PropertyValueFactory<>("provider"));
		c_amount_real.setCellValueFactory(new PropertyValueFactory<>("amount_real"));
		c_amount_virtual.setCellValueFactory(new PropertyValueFactory<>("amount_virtual"));

		tv_table.setItems(data);

		tv_table.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2) showEdit();
		});

		refresh();
	}

	//Volta ao menu principal
	@FXML
	public void backToMenu() {
		MainInterface.changeSceneWE("Server/Model/menu.fxml");
	}

	//Cancela o cadastro de um produto ou a sua edicao
	@FXML
	void dismiss() {
		modal_edit.setVisible(false);
		modal_new.setVisible(false);
	}

	//Atualiza a lista de produtos
	@FXML
	public void refresh() {
		data.clear();
		data.addAll(Products.ListAll());
	}

	//Abre a tela de cadastro de um novo produto
	@FXML
	public void showNew() {
		Validation.clearField(f_new_name, f_new_price, f_new_expiration.getEditor(), f_new_provider, f_new_amount);
		Validation.clearErrorStyle(f_new_name, f_new_price, f_new_expiration.getEditor(), f_new_provider, f_new_amount);
		modal_new.setVisible(true);
	}

	//Abre a tela de edicao de um produto
	@FXML
	public void showEdit() {
		selected = tv_table.getSelectionModel().getSelectedItem();
		if (selected == null) return;

		f_edit_amount.clear();
		Validation.clearErrorStyle(f_edit_name, f_edit_price, f_edit_expiration.getEditor(), f_edit_provider);

		//Os campos recebem os atuais valores do produto que deseja ser alterado
		f_edit_name.setText(selected.getName());
		f_edit_price.setText(Double.toString(selected.getPrice()));
		f_edit_expiration.getEditor().setText(selected.getExpiration());
		f_edit_provider.setText(selected.getProvider());
		l_amount_now.setText(selected.getAmountRealAsStr());
		modal_edit.setVisible(true);
	}

	//Geracao de relatorio
	@FXML
	public void pdfGenerate() {

		if(!Products.ListAll().isEmpty())
			ProductsCreator.CreatePDF(Products.ListAll());
	}

	//Atualiza a quantidade de produtos
	@FXML
	public void updateAmount() {
		//Valida o campo
		boolean valid = validateField(f_edit_amount, INTEGER_NON_ZERO);
		if (valid) {
			//Atualiza no banco de dados
			int now = selected.updateAmount(Integer.parseInt(f_edit_amount.getText()));

			//Tratamento de erros
			if (now < 0) Validation.setError(f_edit_amount, "Esta alteração deixaria o estoque negativo.");
			else {
				l_amount_now.setText(selected.getAmountRealAsStr());
				f_edit_amount.clear();
			}
		}
	}

	//Confirmacao de edicao de um produto
	@FXML
	public void confirm_edit() {
		Validation.clearErrorStyle(f_edit_expiration.getEditor(), f_edit_price, f_edit_provider);

		//Validacao dos campos
		boolean valid  = validateField(f_edit_expiration.getEditor(), DATE);
		valid = valid && validateField(f_edit_price, PRICE);
		valid = valid && validateField(f_edit_provider, TEXT);

		if (valid) {
			//Realiza as alteracoes no banco de dados
			selected.setExpiration(f_edit_expiration.getEditor().getText());
			selected.setPrice(f_edit_price.getText());
			selected.setProvider(f_edit_provider.getText());

			//Reescrita do .csv
			ProductsDatabase.getInstance().WriteFile();

			modal_edit.setVisible(false);
		}
	}

	//Confirmacao de cadastro de um novo produto
	@FXML
	public void confirm_new() {
		Validation.clearErrorStyle(f_new_name, f_new_price, f_new_expiration.getEditor(), f_new_provider, f_new_amount);

		//Validacao dos campos
		boolean valid  = validateField(f_new_name, TEXT);
		valid = valid && validateField(f_new_price, PRICE);
		valid = valid && validateField(f_new_expiration.getEditor(), DATE);
		valid = valid && validateField(f_new_provider, TEXT);
		valid = valid && validateField(f_new_amount, INTEGER_POSITIVE);

		if (valid){
			//Verifica se o produto ja esta cadastrado no banco de dados
			if (!Products.checkProduct(f_new_name.getText())) {
				Validation.setError(f_new_name, "Já existe um produto com este nome.");
			} else {
				//Cadastra o produto no sistema
				Products.getInstance().Register(f_new_name.getText(),
						Double.parseDouble(f_new_price.getText()),
						f_new_expiration.getEditor().getText(),
						f_new_provider.getText(),
						Integer.parseInt(f_new_amount.getText())
				);
				refresh();
				modal_new.setVisible(false);
			}
		}
	}
}
