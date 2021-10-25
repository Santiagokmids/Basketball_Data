package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.BasketballData;

public class BasketballDataGUI {

	//public BasketballData basketData;
	
	@FXML
    private TableView<?> tvPlayers;
	
	@FXML
    private AnchorPane anchorImport;

    @FXML
    private AnchorPane anchorSearch;

    @FXML
    private AnchorPane anchorDelete;

    @FXML
    private AnchorPane anchorAdd;

    @FXML
    private AnchorPane anchorPlayers;

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

	public BasketballData basketData;
	
    @FXML
    private ImageView imgTitle;
    
    @FXML
    private ImageView imgPlayers;

    @FXML
    private ImageView imgAdd;
    
    @FXML
    private ImageView imgDel;

    @FXML
    private ImageView imgSearch;
    
    @FXML
    private ImageView imgImp;

    @FXML
    private ImageView imgExit;
    
    @FXML
    private AnchorPane anchorBack;

    @FXML
    private ImageView imgBack;

	
	@FXML
	public void loadApp() throws IOException, InterruptedException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("startApp.fxml"));
		loader.setController(this);
		Parent load = loader.load();
		mainPane.getChildren().clear();
		
		Image title = new Image("/images/title.png");
		imgTitle.setImage(title);
		Image add = new Image("/images/addPlayer.png");
		imgAdd.setImage(add);
		Image delete = new Image("/images/deletePlayer.png");
		imgDel.setImage(delete);
		Image search = new Image("/images/searchPlayer.png");
		imgSearch.setImage(search);
		Image players = new Image("/images/players.png");
		imgPlayers.setImage(players);
		Image importP = new Image("/images/importPlayer.png");
		imgImp.setImage(importP);
		Image end = new Image("/images/end.png");
		imgExit.setImage(end);
		
		Image ball = new Image("/images/back.png");
		imgBack.setImage(ball);
		
		mainPane.setTop(load);
	}
	

    @FXML
    public void openning(MouseEvent event) {
    	changeAllButton();
    	changeVisibilityAnchor();
    	changeVisibilityMainAnchor();
    }


	private void changeVisibilityMainAnchor() {
		anchorBack.setVisible(true);
    	anchorBack.setDisable(false);
    	imgBack.setVisible(true);
    	imgBack.setDisable(false);
	}


	private void changeVisibilityAnchor() {
		anchorBack.setVisible(false);
    	anchorBack.setDisable(true);
    	imgBack.setVisible(false);
    	imgBack.setDisable(true);
		
		anchorPlayers.setVisible(false);
    	anchorPlayers.setDisable(true);
    	
    	anchorAdd.setVisible(false);
    	anchorAdd.setDisable(true);
    	
    	anchorDelete.setVisible(false);
    	anchorDelete.setDisable(true);
    	
    	anchorSearch.setVisible(false);
    	anchorSearch.setDisable(true);
    	
    	anchorImport.setVisible(false);
    	anchorImport.setDisable(true);
	}

	@FXML
	public void handleClicks(ActionEvent event) {

		Button button = (Button) event.getSource();
		String s = button.getText();
		String style = "-fx-background-color: #D63320;";
		DropShadow dropShadow = new DropShadow();
		
		changeAllButton();
		changeVisibilityAnchor();
		
		switch (s) {
		case "     Jugadores":
			btnPlayers.setStyle(style);
			btnPlayers.setEffect(dropShadow);
			btnPlayersVerify = false;
			anchorPlayers.setVisible(true);
	    	anchorPlayers.setDisable(false);
			break;
		case "  Agregar":
			btnAdd.setStyle(style);
			btnAdd.setEffect(dropShadow);
			btnAddVerify = false;
			anchorAdd.setVisible(true);
	    	anchorAdd.setDisable(false);
			break;
		case "  Eliminar":
			btnDelete.setStyle(style);
			btnDelete.setEffect(dropShadow);
			btnDeleteVerify = false;
			anchorDelete.setVisible(true);
	    	anchorDelete.setDisable(false);
			break;
		case "Buscar":
			btnSearch.setStyle(style);
			btnSearch.setEffect(dropShadow);
			btnSearchVerify = false;
			anchorSearch.setVisible(true);
	    	anchorSearch.setDisable(false);
			break;
		case "   Importar":
			btnImport.setStyle(style);
			btnImport.setEffect(dropShadow);
			btnImportVerify = false;
			anchorImport.setVisible(true);
	    	anchorImport.setDisable(false);
			break;
		default:
			break;
		}
	}


	private void changeAllButton() {
		btnPlayersVerify = btnAddVerify = btnDeleteVerify = btnSearchVerify = btnImportVerify = true;
		changeStyle(btnPlayers.getText());
		changeStyle(btnAdd.getText());
		changeStyle(btnDelete.getText());
		changeStyle(btnSearch.getText());
		changeStyle(btnImport.getText());
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
