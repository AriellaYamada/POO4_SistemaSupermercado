package Interface.Server.Controller;

import Interface.MainInterface;
import Structure.Sale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class saleListController {

	@FXML private TableView<Sale> tv_table;
	@FXML private TableColumn<Sale, String> c_address;
	@FXML private VBox clearDialog;
	@FXML private TableColumn<Sale, String> c_email;
	@FXML private TableColumn<Sale, String> c_name;
	@FXML private TableColumn<Sale, String> c_userid;

	@FXML
	void backToMenu(ActionEvent event) {
		try {
			MainInterface.changeScene("Server/Model/menu.fxml");
		} catch (IOException e) {
			System.out.println("Erro ao exibir a tela");
		}
	}

	@FXML
	void refresh(ActionEvent event) {

	}

	@FXML
	void pdfGenerate(ActionEvent event) {

	}

	@FXML
	void dismiss(ActionEvent event) {

	}

	@FXML
	void confirmClear(ActionEvent event) {

	}

}
