package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class BasketballDataGUI {

	//public BasketballData basketData;

	@FXML
	private BorderPane mainPane;

	@FXML
	private AnchorPane anch;

	@FXML
	private TableView<?> table;

	@FXML
	private AnchorPane two;

	@FXML
	private Button btnPlayers;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnSearch;

	@FXML
	private Button btnImport;

	boolean btnPlayersVerify = true;
	boolean btnAddVerify = true;
	boolean btnDeleteVerify = true;
	boolean btnSearchVerify = true;
	boolean btnImportVerify = true;

	@FXML
	public void loadApp() throws IOException, InterruptedException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("startApp.fxml"));
		loader.setController(this);
		Parent load = loader.load();
		mainPane.getChildren().clear();

		mainPane.setTop(load);
	}

	@FXML
	public void handleClicks(ActionEvent event) {

		Button button = (Button) event.getSource();
		String s = button.getText();
		String style = "-fx-background-color: #D63320;";
		DropShadow dropShadow = new DropShadow();
		btnPlayersVerify = btnAddVerify = btnDeleteVerify = btnSearchVerify = btnImportVerify = true;
		changeStyle(btnPlayers.getText());
		changeStyle(btnAdd.getText());
		changeStyle(btnDelete.getText());
		changeStyle(btnSearch.getText());
		changeStyle(btnImport.getText());
		
		switch (s) {
		case "     Jugadores":
			btnPlayers.setStyle(style);
			btnPlayers.setEffect(dropShadow);
			btnPlayersVerify = false;
			break;
		case "  Agregar":
			btnAdd.setStyle(style);
			btnAdd.setEffect(dropShadow);
			btnAddVerify = false;
			break;
		case "  Eliminar":
			btnDelete.setStyle(style);
			btnDelete.setEffect(dropShadow);
			btnDeleteVerify = false;
			break;
		case "Buscar":
			btnSearch.setStyle(style);
			btnSearch.setEffect(dropShadow);
			btnSearchVerify = false;
			break;
		case "   Importar":
			btnImport.setStyle(style);
			btnImport.setEffect(dropShadow);
			btnImportVerify = false;
			break;
		default:
			break;
		}
	}

	@FXML
	public void closeApp(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	public void color(MouseEvent event) {

		Button button = (Button) event.getSource();
		String s = button.getText();
		String style = "-fx-background-color: #D63320;";
		DropShadow dropShadow = new DropShadow();

		switch (s) {
		case "     Jugadores":
			if(btnPlayersVerify) {
				btnPlayers.setStyle(style);
				btnPlayers.setEffect(dropShadow);
			}
			break;
		case "  Agregar":
			if(btnAddVerify) {
				btnAdd.setStyle(style);
				btnAdd.setEffect(dropShadow);
			}
			break;
		case "  Eliminar":
			if(btnDeleteVerify) {
				btnDelete.setStyle(style);
				btnDelete.setEffect(dropShadow);
			}
			break;
		case "Buscar":
			if(btnSearchVerify) {
				btnSearch.setStyle(style);
				btnSearch.setEffect(dropShadow);
			}
			break;
		case "   Importar":
			if(btnImportVerify) {
				btnImport.setStyle(style);
				btnImport.setEffect(dropShadow);	
			}
			break;
		default:
			break;
		}
	}

	@FXML
	public void normalColor(MouseEvent event) {
		Button button = (Button) event.getSource();
		String s = button.getText();
		changeStyle(s);
	}

	private void changeStyle(String s) {
		
		String style = "-fx-background-color: #D54939;";
		switch (s) {
		case "     Jugadores":
			if(btnPlayersVerify) {
				btnPlayers.setStyle(style);
				btnPlayers.setEffect(null);
			}
			break;
		case "  Agregar":
			if(btnAddVerify) {
				btnAdd.setStyle(style);
				btnAdd.setEffect(null);
			}
			break;
		case "  Eliminar":
			if(btnDeleteVerify) {
				btnDelete.setStyle(style);
				btnDelete.setEffect(null);	
			}
			break;
		case "Buscar":
			if(btnSearchVerify) {
				btnSearch.setStyle(style);
				btnSearch.setEffect(null);	
			}
			break;
		case "   Importar":
			if(btnImportVerify) {
				btnImport.setStyle(style);
				btnImport.setEffect(null);	
			}
			break;
		default:
			break;
		}
	}
} 
