package Interface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainInterface extends Application {
	static Stage stg;

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
		stg = primaryStage;
		stg.setTitle("Supermercado");
		stg.setScene(new Scene(root));
		stg.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	// Same as changeScene, Without Exception
	public static void changeSceneWE(String file){
		try {
			changeScene(file);
		} catch (IOException e) {
			System.out.println("Erro ao abrir a tela");
		}
	}

	public static void changeScene(String file) throws IOException{
		Parent root = FXMLLoader.load(MainInterface.class.getResource(file));
		stg.setScene(new Scene(root));
		stg.show();
	}
}