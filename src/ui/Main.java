package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.BasketballData;

public class Main extends Application{

	private BasketballData basketData;
	private BasketballDataGUI basketDataGUI;

	public Main() {

		boolean loadInformation = true;
		basketData = new BasketballData();
		basketDataGUI = new BasketballDataGUI(basketData);
		
		try {
			loadInformation = basketData.loadData();
		} catch (ClassNotFoundException | IOException e) {
			loadInformation = false;
		}
		
		if (!loadInformation) {
			Alert alert = new Alert(AlertType.INFORMATION);

			alert.setTitle("Basket");
			alert.setContentText("Error cargando el archivo");
			alert.showAndWait();
		}
		
	}
	public static void main(String [] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-pane.fxml"));

		fxmlLoader.setController(basketDataGUI);

		Parent root = fxmlLoader.load();
		Image icon = new Image("/images/icon.png");
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/images/icon.png");
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(icon);

		primaryStage.setTitle("Basketball Data");
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
		basketDataGUI.loadApp();
	}
}
