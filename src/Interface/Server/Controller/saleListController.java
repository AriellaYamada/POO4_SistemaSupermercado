package Interface.Server.Controller;

import Def.Month;
import Interface.MainInterface;
import Server.Database.Sales;
import Server.PDFCreator.SalesCreator;
import Structure.CartItem;
import Structure.Sale;
import Def.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class saleListController {

	@FXML private TableView<Sale> tv_table;
	@FXML private TableColumn<Sale, String> c_name;
	@FXML private TableColumn<Sale, String> c_date;
	@FXML private TableColumn<Sale, Integer> c_items;
	@FXML private TableColumn<Sale, Double> c_total_price;

	@FXML private VBox modal_details;

	@FXML private Label l_name;
	@FXML private Label l_date;

	@FXML private TableView<CartItem> tv_item;
	@FXML private TableColumn<CartItem, String> c_item_name;
	@FXML private TableColumn<CartItem, Integer> c_item_amount;
	@FXML private TableColumn<CartItem, Double> c_item_price_unit;
	@FXML private TableColumn<CartItem, Double> c_item_price_total;
	@FXML private Label l_total_price;

	public VBox modal_pdf;
	@FXML private DatePicker f_day;
	@FXML private ComboBox<String> f_month;
	@FXML private ComboBox<Integer> f_year;

	ObservableList<Sale> data = FXCollections.observableArrayList();
	ObservableList<CartItem> items = FXCollections.observableArrayList();

	ObservableList<String> months = FXCollections.observableArrayList();
	ObservableList<Integer> years = FXCollections.observableArrayList();

	public void initialize() {
		//Inicializacao da tableview
		c_name.setCellValueFactory(new PropertyValueFactory<>("userId"));
		c_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		c_items.setCellValueFactory(new PropertyValueFactory<>("numberItems"));
		c_total_price.setCellValueFactory(new PropertyValueFactory<>("totalPriceAsStr"));

		c_item_name.setCellValueFactory(new PropertyValueFactory<>("productName"));
		c_item_amount.setCellValueFactory(new PropertyValueFactory<>("reservedQtd"));
		c_item_price_unit.setCellValueFactory(new PropertyValueFactory<>("priceAsStr"));
		c_item_price_total.setCellValueFactory(new PropertyValueFactory<>("totalPriceAsStr"));

		tv_table.setItems(data);
		tv_item.setItems(items);

		f_month.setItems(months);
		f_year.setItems(years);

		tv_table.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2) detail();
		});

		refresh();
	}

	//Volta ao menu principal
	@FXML
	void backToMenu() {
		MainInterface.changeSceneWE("Server/Model/menu.fxml");
	}

	//Atualiza a lista de vendas
	@FXML
	void refresh() {
		data.clear();
		data.addAll(Sales.getSales());
	}

	//Abre o detalhamento da venda selecionada
	@FXML
	void detail() {
		//Busca o item selecionado na tebela
		Sale selected = tv_table.getSelectionModel().getSelectedItem();
		if (selected == null) return;

		items.clear();
		//Busca nos registros os dados da venda selecionada
		l_name.setText(selected.getUser().getName());
		l_date.setText(selected.getDate());
		items.addAll(selected.getProducts());
		l_total_price.setText(selected.getTotalPriceAsStr());

		//Imprime na tableview
		modal_details.setVisible(true);
	}

	//Geracao de PDF
	@FXML
	void pdfGenerate() {
		f_month.getSelectionModel().clearSelection();
		f_year.getSelectionModel().clearSelection();
		years.clear();

		//Busca pelo mês e ano selecionado
		data.stream()
				.map(Sale::getYear)
				.distinct()
				.sorted()
				.forEach(years::add);

		f_month.setDisable(true);

		modal_pdf.setVisible(true);
	}

	//Cancela a visualizacao dos detalhes da venda
	@FXML
	void dismiss() {
		modal_details.setVisible(false);
		modal_pdf.setVisible(false);
	}

	//Gera o PDF do dia
	@FXML
	public void genPdfDay() {
		Validation.clearErrorStyle(f_day.getEditor());
		Validation.validateField(f_day.getEditor(), Validation.FieldType.DATE);

		String[] splited = f_day.getEditor().getText().split("/");
		String filename = "Vendas_" + splited[2] + "-" + splited[1] + "-" + splited[0];

		if (splited[1].startsWith("0")) splited[1] = splited[1].substring(1);

		final String date = String.format("%s/%s/%s", splited[0], splited[1], splited[2]);

		System.out.println(date);
		List<Sale> list = data.stream()
							.filter(s -> s.getDate().equals(date))
				.collect(Collectors.toList());


		SalesCreator.CreatePDFSales(filename, list);
	}

	//Gera o PDF do mês e ano solicitado
	@FXML
	public void genPdfMonthYear() {
		String monthStr = f_month.getValue();
		int month = Month.monthToInt(monthStr);
		int year = f_year.getValue();

		List<Sale> list = data.stream()
				.filter(s -> s.getYear() == year)
				.filter(s -> s.getMonth() == month)
				.sorted(Comparator.comparing(Sale::getDay))
				.collect(Collectors.toList());

		String filename = "Vendas_" + year + "-" + String.format("%02d", month);


		SalesCreator.CreatePDFSales(filename, list);

	}

	//Busca vendas por mês
	@FXML
	public void filterMonths() {
		Integer year = f_year.getValue();

		if (year != null) {

			months.clear();

			data.stream()
					.filter(s -> s.getYear() == year)
					.map(Sale::getMonth)
					.distinct()
					.sorted()
					.forEach(d -> months.add(Month.intToMonth(d)));

			f_month.setDisable(false);
		}
	}
}
