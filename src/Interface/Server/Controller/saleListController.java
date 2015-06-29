package Interface.Server.Controller;

import Interface.MainInterface;
import Server.Database.Sales;
import Server.PDFCreator;
import Structure.CartItem;
import Structure.Def;
import Structure.Sale;
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
	@FXML private TableColumn<Sale, Float> c_total_price;

	@FXML private VBox modal_details;

	@FXML private Label l_name;
	@FXML private Label l_date;

	@FXML private TableView<CartItem> tv_item;
	@FXML private TableColumn<CartItem, String> c_item_name;
	@FXML private TableColumn<CartItem, Integer> c_item_amount;
	@FXML private TableColumn<CartItem, Float> c_item_price_unit;
	@FXML private TableColumn<CartItem, Float> c_item_price_total;
	@FXML private Label l_total_price;

	public VBox modal_pdf;
	@FXML private DatePicker f_day;
	@FXML private ComboBox<Integer> f_month;
	@FXML private ComboBox<Integer> f_year;

	ObservableList<Sale> data = FXCollections.observableArrayList();
	ObservableList<CartItem> items = FXCollections.observableArrayList();

	ObservableList<Integer> months = FXCollections.observableArrayList();
	ObservableList<Integer> years = FXCollections.observableArrayList();

	public void initialize() {
		c_name.setCellValueFactory(new PropertyValueFactory<>("userId"));
		c_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		c_items.setCellValueFactory(new PropertyValueFactory<>("numberItems"));
		c_total_price.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		c_item_name.setCellValueFactory(new PropertyValueFactory<>("productName"));
		c_item_amount.setCellValueFactory(new PropertyValueFactory<>("reservedQtd"));
		c_item_price_unit.setCellValueFactory(new PropertyValueFactory<>("price"));
		c_item_price_total.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

		tv_table.setItems(data);
		tv_item.setItems(items);

		f_month.setItems(months);
		f_year.setItems(years);

		refresh();
	}

	@FXML
	void backToMenu() {
		MainInterface.changeSceneWE("Server/Model/menu.fxml");
	}

	@FXML
	void refresh() {
		data.clear();
		data.addAll(Sales.getSales());
	}

	@FXML
	void detail() {
		items.clear();
		Sale selected = tv_table.getSelectionModel().getSelectedItem();
		l_name.setText(selected.getUser().getName());
		l_date.setText(selected.getDate());
		items.addAll(selected.getProducts());
		l_total_price.setText(Float.toString(selected.getTotalPrice()));

		modal_details.setVisible(true);
	}

	@FXML
	void pdfGenerate() {
		data.stream()
				.map(Sale::getYear)
				.distinct()
				.sorted()
				.forEach(years::add);

		f_month.setDisable(true);

		modal_pdf.setVisible(true);
	}

	@FXML
	void dismiss() {
		modal_details.setVisible(false);
		modal_pdf.setVisible(false);
	}

	@FXML
	public void genPdfDay() {
		Def.clearErrorStyle(f_day.getEditor());
		Def.validateField(f_day.getEditor(), Def.FieldType.DATE);

		final String date = f_day.getEditor().getText();
		List<Sale> list = data.stream()
							.filter(s -> s.getDate().equals(date))
							.collect(Collectors.toList());

		String[] splited = date.split("/");
		String filename = "Vendas_" + splited[2] + "-" + splited[1] + "-" + splited[0];

		PDFCreator.CreatePDF(filename, list);
	}

	@FXML
	public void genPdfMonthYear() {
		final int month = f_month.getSelectionModel().getSelectedItem();
		final int year = f_year.getSelectionModel().getSelectedItem();

		List<Sale> list = data.stream()
				.filter(s -> s.getYear() == year)
				.filter(s -> s.getMonth() == month)
				.sorted(Comparator.comparing(Sale::getDay))
				.collect(Collectors.toList());

		String filename = "Vendas_" + year + "-" + month;


		PDFCreator.CreatePDF(filename, list);

	}

	@FXML
	public void filterMonths() {
		final int year = f_year.getSelectionModel().getSelectedItem();

		months.clear();

		data.stream()
				.filter(s -> s.getYear() == year)
				.map(Sale::getMonth)
				.distinct()
				.sorted()
				.forEach(months::add);

		f_month.setDisable(false);
	}
}
