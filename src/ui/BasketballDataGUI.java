package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.BasketballData;
import model.Players;
import thread.LoadData;
import thread.SearchByName;

public class BasketballDataGUI {

	@FXML
    private TextField lblImport;

	@FXML
	private Button btnWaitting;

	@FXML
	private Label lblWaitting;

	@FXML
	private ImageView imgSmile;

	@FXML
	private TextField tfName;

	@FXML
	private TextField tfLastName;

	@FXML
	private TextField tfAge;

	@FXML
	private TextField tfPoints;

	@FXML
	private TextField tfBounces;

	@FXML
	private TextField tfAssistances;

	@FXML
	private TextField tfTheft;

	@FXML
	private TextField tfBlock;

	@FXML
	private TextField tfTeam;

	@FXML
	private TextField lblName;

	@FXML
	private TextField lblLastName;

	@FXML
	private TextField lblAge;

	@FXML
	private TextField lblBlock;

	@FXML
	private TextField lblAssistance;

	@FXML
	private TextField lblTeam;

	@FXML
	private TextField lblTheft;

	@FXML
	private TextField lblBounce;

	@FXML
	private TextField lblPoints;

	@FXML
	private TableColumn<Players, String> tcName;

	@FXML
	private TableColumn<Players, String> tcLastName;

	@FXML
	private TableColumn<Players, String> tcAge;

	@FXML
	private TableColumn<Players, String> tcBlock;

	@FXML
	private TableColumn<Players, String> tcAssistance;

	@FXML
	private TableColumn<Players, String> tcTeam;

	@FXML
	private TableColumn<Players, String> tcTheft;

	@FXML
	private TableColumn<Players, String> tcBounce;

	@FXML
	private TableColumn<Players, String> tcPoints;

	@FXML
	private TableView<Players> tvPlayers;

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
	private TextField txtDeleteName;

	@FXML
	private TextField txtDeleteLast;

	@FXML
	private TextField txtDeleteAge;

	@FXML
	private TextField txtDeletePoint;

	@FXML
	private TextField txtDeleteBounce;

	@FXML
	private TextField txtDeleteAss;

	@FXML
	private TextField txtDeleteTheft;

	@FXML
	private TextField txtDeleteBlock;

	@FXML
	private TextField txtDeleteTeam;


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
	private ImageView titleImpP;

	@FXML
	private ImageView titleSearchP;

	@FXML
	private ImageView titleDeleteP;

	@FXML
	private ImageView titleAddPlayers;

	@FXML
	private ImageView titleAnchPlayers;

	@FXML
	private ImageView titleMethods;

	@FXML
	private Button btnBack;

	@FXML
	private ImageView iconImport;

	@FXML
	private Button btnSave;

	@FXML
	private ImageView imgWarning;

	@FXML
	private Button btnExit;

	@FXML
	private ComboBox<String> comboBDates;

	@FXML
	private ComboBox<String> comboBMethods;

	@FXML
	private ComboBox<String> comboBCriters;

	@FXML
	private TextField txtDatesSearch;

	public int players;

	private String direction = "";
	
	public static ObservableList<Players> listPlayers;

	public BasketballDataGUI(BasketballData basketballData) {
		this.basketData = basketballData;
	}

	public void inicializateTableView() {

		listPlayers = FXCollections.observableArrayList(basketData.getPlayers());
		tvPlayers.setItems(listPlayers);
		tcName.setCellValueFactory(new PropertyValueFactory<Players, String>("name"));
		tcLastName.setCellValueFactory(new PropertyValueFactory<Players, String>("lastName"));
		tcAge.setCellValueFactory(new PropertyValueFactory<Players, String>("age"));
		tcBlock.setCellValueFactory(new PropertyValueFactory<Players, String>("block"));
		tcAssistance.setCellValueFactory(new PropertyValueFactory<Players, String>("assistance"));
		tcTeam.setCellValueFactory(new PropertyValueFactory<Players, String>("team"));
		tcTheft.setCellValueFactory(new PropertyValueFactory<Players, String>("theft"));
		tcBounce.setCellValueFactory(new PropertyValueFactory<Players, String>("bounce"));
		tcPoints.setCellValueFactory(new PropertyValueFactory<Players, String>("points"));
	}

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
		inicializateTableView();
	}

	@FXML
	public void openning(MouseEvent event) {
		changeAllButton();
		changeVisibilityAnchor();
		changeVisibilityMainAnchor();
		System.out.println(basketData.getPointsAVLTree().getRoot().getObject().getName());
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

			Image title = new Image("/images/listPlayersT.png");
			titleAnchPlayers.setImage(title);
			//inicializateTableView();
			break;
		case "  Agregar":

			btnAdd.setStyle(style);
			btnAdd.setEffect(dropShadow);
			btnAddVerify = false;

			anchorAdd.setVisible(true);
			anchorAdd.setDisable(false);

			Image add = new Image("/images/addPlayersT.png");
			titleAddPlayers.setImage(add);

			break;
		case "  Eliminar":

			btnDelete.setStyle(style);
			btnDelete.setEffect(dropShadow);
			btnDeleteVerify = false;

			anchorDelete.setVisible(true);
			anchorDelete.setDisable(false);

			Image delete = new Image("/images/deletePlayersT.png");
			titleDeleteP.setImage(delete);

			break;
		case "Buscar":

			btnSearch.setStyle(style);
			btnSearch.setEffect(dropShadow);
			btnSearchVerify = false;

			anchorSearch.setVisible(true);
			anchorSearch.setDisable(false);

			Image search = new Image("/images/searchPlayersT.png");
			titleSearchP.setImage(search);
			Image info = new Image("/images/warning.png");
			imgWarning.setImage(info);
			addCombo();

			break;
		case "Volver":

			Stage stage = (Stage) this.btnBack.getScene().getWindow();
			stage.close();

			btnSearch.setStyle(style);
			btnSearch.setEffect(dropShadow);
			btnSearchVerify = false;

			anchorSearch.setVisible(true);
			anchorSearch.setDisable(false);

			Image search1 = new Image("/images/searchPlayersT.png");
			titleSearchP.setImage(search1);
			addCombo();

			break;
		case "   Importar":

			btnImport.setStyle(style);
			btnImport.setEffect(dropShadow);
			btnImportVerify = false;

			anchorImport.setVisible(true);
			anchorImport.setDisable(false);

			Image imp = new Image("/images/importPlayerT.png");
			titleImpP.setImage(imp);
			Image iconImp = new Image("/images/iconImport.png");
			iconImport.setImage(iconImp);

			break;
		default:
			break;
		}
	}

	@FXML
	public void searchOptions(ActionEvent event) {

		String date = comboBDates.getValue();
		String method = comboBMethods.getValue();
		String criter = comboBCriters.getValue();
		String txt = txtDatesSearch.getText();

		if(criter != null && date != null && method != null && !txt.equals("") ) {

			int value = putCriter(criter);
			int dates = getDateToSearch(txtDatesSearch.getText());	

			if(date.equalsIgnoreCase("Puntos por partido") && dates != -1) {
				searchPoints(method, value, dates);

			}else if(date.equalsIgnoreCase("Rebotes por partido") && dates != -1) {
				searchBounces(method, value, dates);

			}else if(date.equalsIgnoreCase("Asistencias por partido") && dates != -1) {
				searchAssistence(method, value, dates);

			}else if(date.equalsIgnoreCase("Robos por partido") && dates != -1) {
				searchTheft(method, value, dates);

			}else if(date.equalsIgnoreCase("Bloqueos por partido") && dates != -1) {
				searchBlock(method, value, dates);
			}
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("Datos inválidos");
			alert.setContentText("Debe ingresar todas las opciones para buscar a un jugador");
			alert.showAndWait();
		}

	}

	public int getDateToSearch(String dates) {

		int date = -1;

		try {

			date = Integer.parseInt(dates);

		}catch(NumberFormatException nfe){

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("Datos inválidos");
			alert.setContentText("Debe ingresar un valor númerico para los datos");
			alert.showAndWait();
		}

		return date;
	}

	public int putCriter(String criter) {
		int value = 0;

		if(criter.equalsIgnoreCase("Igual")) {
			value = 0;

		}else if(criter.equalsIgnoreCase("Menor o igual")) {
			value = -1;

		}else if(criter.equalsIgnoreCase("Mayor o igual")) {
			value = 1;
		}

		return value;
	}

	public void searchPoints(String method, int criter, int date) {

		if(method.equalsIgnoreCase("Árbol Binario Balanceado")) {
			String message = "El o los jugadores encontrados son: \n";
			ArrayList<Players> player = new ArrayList<Players>();
			String type = "points";

			if(criter == 0) {
				player = basketData.searchNodeEqualsAVL(date, type);
				showPlayers(player, message);

			}else if(criter == 1) {
				player = basketData.searchNodeMaxAVL(date,basketData.searchNodesPoint(),player);
				System.out.println("sale o que "+player.isEmpty());
				showPlayers(player, message);

			}else if(criter == -1) {
				player = basketData.searchNodeMinAVL(date,basketData.searchNodesPoint(),player);
				showPlayers(player, message);
			}
		} else {
			showAlert();
		}
	}

	public void searchBounces(String method, int criter, int date) {

		if(method.equalsIgnoreCase("Árbol Binario Balanceado")) {
			String message = "El o los jugadores encontrados son: \n";
			ArrayList<Players> player = new ArrayList<Players>();
			String type = "bounces";

			if(criter == 0) {
				player = basketData.searchNodeEqualsAVL(date, type);
				showPlayers(player, message);

			}else if(criter == 1) {
				player = basketData.searchNodeMaxAVL(date,basketData.searchNodesBounce(),player);
				showPlayers(player, message);

			}else if(criter == -1) {
				player = basketData.searchNodeMinAVL(date,basketData.searchNodesBounce(),player);
				showPlayers(player, message);
			}
		} else {
			showAlert();
		}
	}

	public void searchAssistence(String method, int criter, int date) {
		String message = "El o los jugadores encontrados son: \n";
		ArrayList<Players> player = new ArrayList<Players>();

		if(method.equalsIgnoreCase("Árbol Binario Balanceado")) {

			String type = "assistence";

			if(criter == 0) {
				player = basketData.searchNodeEqualsAVL(date, type);
				showPlayers(player, message);

			}else if(criter == 1) {
				player = basketData.searchNodeMaxAVL(date,basketData.searchNodesAssitence(),player);
				showPlayers(player, message);

			}else if(criter == -1) {
				player = basketData.searchNodeMinAVL(date,basketData.searchNodesAssitence(),player);
				showPlayers(player, message);
			}
		}else if(method.equalsIgnoreCase("Árbol Binario de búsqueda")) {

			if(criter == 0) {
				boolean stop = false;
				player = basketData.searchNodeEqualsTree(date,basketData.searchNodesAssitenceTree(),player,stop);
				showPlayers(player, message);

			}else if(criter == 1) {
				player = basketData.searchNodeMaxTree(date,basketData.searchNodesAssitenceTree(),player);
				showPlayers(player, message);

			}else if(criter == -1) {
				player = basketData.searchNodeMinTree(date,basketData.searchNodesAssitenceTree(),player);
				showPlayers(player, message);
			}
		}
		else {
			showAlert();
		}
	}

	public void searchTheft(String method, int criter, int date) {
		String message = "El o los jugadores encontrados son: \n";
		ArrayList<Players> player = new ArrayList<Players>();;

		if(method.equalsIgnoreCase("Búsqueda lineal")) {

			if(criter == 0) {
				player = basketData.searchArrayEquals(date);
				showPlayers(player, message);

			}else if(criter == 1) {
				player = basketData.searchArrayMax(date);
				showPlayers(player, message);

			}else if(criter == -1) {
				player = basketData.searchArrayMin(date);
				showPlayers(player, message);
			}
		}else if(method.equalsIgnoreCase("Árbol Binario de búsqueda")) {

			if(criter == 0) {
				boolean stop = false;
				player = basketData.searchNodeEqualsTree(date,basketData.searchNodesTheftTree(date),player,stop);
				showPlayers(player, message);

			}else if(criter == 1) {
				player = basketData.searchNodeMaxTree(date,basketData.searchNodesTheftTree(date),player);
				showPlayers(player, message);

			}else if(criter == -1) {
				player = basketData.searchNodeMinTree(date,basketData.searchNodesTheftTree(date),player);
				showPlayers(player, message);
			}
		} 
		else {
			showAlert();
		}
	}

	public void searchBlock(String method, int criter, int date) {

		if(method.equalsIgnoreCase("Árbol Binario Balanceado")) {
			String message = "El o los jugadores encontrados son: \n";
			ArrayList<Players> player = new ArrayList<Players>();

			String type = "blocks";

			if(criter == 0) {
				player = basketData.searchNodeEqualsAVL(date, type);
				showPlayers(player, message);

			}else if(criter == 1) {
				player = basketData.searchNodeMaxAVL(date,basketData.searchNodesBlocks(),player);
				showPlayers(player, message);

			}else if(criter == -1) {
				player = basketData.searchNodeMinAVL(date,basketData.searchNodesBlocks(),player);
				showPlayers(player, message);
			}
		} else {
			showAlert();
		}
	}

	public void showPlayers(ArrayList<Players> player, String message) {

		String ms = message;
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("INFORMACIÓN");

		if(player.isEmpty()) {
			ms = "No se encontraron jugadores con ese dato estadístico";
			alert.setHeaderText("VACÍO");

		}else {

			alert.setHeaderText("Los atributos a mostrar serán mostrados de la siguiente forma: \nNombre - Apellido - Edad - Equipo - Puntos - Rebotes - Asistencias - Robos - Bloqueos");

			for (int i = 0; i < player.size(); i++) {
				ms += player.get(i).toString()+"\n";
			}
		}

		alert.setContentText(ms);
		alert.showAndWait();
	}

	public void showAlert() {

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("ERROR");
		alert.setHeaderText("Método Inválido");
		alert.setContentText("El método de búsqueda elegido NO está disponible para este dato estadístico");
		alert.showAndWait();
	}

	public void addCombo() {
		comboBDates.getItems().clear();
		comboBMethods.getItems().clear();
		comboBCriters.getItems().clear();

		comboBDates.getItems().addAll("Puntos por partido","Rebotes por partido","Asistencias por partido","Robos por partido","Bloqueos por partido");
		comboBMethods.getItems().addAll("Árbol Binario de búsqueda","Árbol Binario Balanceado","Búsqueda lineal");
		comboBCriters.getItems().addAll("Igual","Menor o igual","Mayor o igual");
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

	@FXML
	public void btnModify(ActionEvent event) {


		Players players = (Players) this.tvPlayers.getSelectionModel().getSelectedItem();

		if(players == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Es necesario seleccionar un jugador");
			alert.showAndWait();
		}else {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("modify-pane.fxml"));

				loader.setController(this);
				Parent root = loader.load();

				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(scene);
				stage.showAndWait();
				
				basketData.saveData();
				
			} catch (IOException e) {
			}
		}
	}

	@FXML
	public void btnInfo(MouseEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("methodsOfSearch.fxml"));

			loader.setController(this);
			Parent root = loader.load();
			Image method = new Image("/images/methodsSearch.png");
			titleMethods.setImage(method);
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.showAndWait();

		} catch (IOException e) {

		}
	}

	@FXML
	public void btnModifyExit(ActionEvent event) {
		Stage stage = (Stage) this.btnExit.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void btnSave(ActionEvent event) {
		Stage stage = (Stage) this.btnSave.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void btnAdd(ActionEvent event) throws InterruptedException {
		
		Alert alert = new Alert(AlertType.INFORMATION);

		if(!tfName.getText().equals("") && !tfLastName.getText().equals("") && !tfTeam.getText().equals("") && !tfAge.getText().equals("") && !tfPoints.getText().equals("") && !tfBounces.getText().equals("") && !tfAssistances.getText().equals("") && !tfTheft.getText().equals("") && !tfBlock.getText().equals("")) {

			try {
				int age = Integer.parseInt(tfAge.getText());
				int points = Integer.parseInt(tfPoints.getText());
				int bounce = Integer.parseInt(tfBounces.getText());
				int assistance = Integer.parseInt(tfAssistances.getText());
				int theft = Integer.parseInt(tfTheft.getText());
				int block = Integer.parseInt(tfBlock.getText());

				SearchByName search = new SearchByName(basketData.getPlayers(), tfName.getText(), tfLastName.getText(),age);
				search.start();
				search.join();
				
				if(!search.getVerify()) {
					
					if(age > -1 && points > -1 && bounce > -1 && assistance > -1 && theft > -1 && block > -1) {
						try {
							basketData.addPlayer(tfName.getText(), tfLastName.getText(), tfTeam.getText(), age, points, bounce, assistance, theft, block);
						} catch (IOException e) {
						}
						inicializateTableView();

						alert.setTitle("EXCELENTE");
						alert.setHeaderText("Se ha registrado exitosamente");
						alert.setContentText("Se ha registrado a "+tfName.getText()+" "+tfLastName.getText()+" exitosamente");
						alert.showAndWait();
						players = 0;
					}else {
						alert.setHeaderText("No se pudo agregar el jugador");
						alert.setContentText("Debe ingresar números mayores o iguales a 0");
						alert.showAndWait();
					}

				}else {
					alert.setAlertType(AlertType.ERROR);
					alert.setHeaderText("No se pudo agregar el jugador");
					alert.setContentText("Ya hay jugadores en la base de datos con esa información");
					alert.showAndWait();
					players = 0;
				}

				tfName.setText("");
				tfLastName.setText("");
				tfAge.setText("");
				tfTeam.setText("");
				tfPoints.setText("");
				tfBounces.setText("");
				tfAssistances.setText("");
				tfTheft.setText("");
				tfBlock.setText("");

			} catch (NumberFormatException nfe) {

				alert.setAlertType(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setHeaderText("No se pudo agregar el jugador");
				alert.setContentText("Algunos de los valores tienen que ser de tipo numérico");
				alert.showAndWait();
			}

		}else {
			alert.setAlertType(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("No se pudo agregar el jugador");
			alert.setContentText("Debe llenar todos los campos para crear al jugador");
			alert.showAndWait();
		}
	}

	@FXML
	public void btnDelete(ActionEvent event) {

		Alert alert = new Alert(AlertType.INFORMATION);

		if(!txtDeleteName.getText().equals("") && !txtDeleteLast.getText().equals("") && !txtDeleteTeam.getText().equals("") && !txtDeleteAge.getText().equals("") && !txtDeletePoint.getText().equals("") && !txtDeleteBounce.getText().equals("") && !txtDeleteAss.getText().equals("") && !txtDeleteTheft.getText().equals("") && !txtDeleteBlock.getText().equals("")) {

			try {
				int age = Integer.parseInt(txtDeleteAge.getText());
				int points = Integer.parseInt(txtDeletePoint.getText());
				int bounce = Integer.parseInt(txtDeleteBounce.getText()); 
				int assistance = Integer.parseInt(txtDeleteAss.getText());
				int theft = Integer.parseInt(txtDeleteTheft.getText());
				int block = Integer.parseInt(txtDeleteBlock.getText());

				Players player = basketData.searchPlayer(txtDeleteName.getText(), txtDeleteLast.getText(),age,txtDeleteTeam.getText(),points,bounce,assistance,theft,block);

				if (player != null) {

					if(age > -1 && points > -1 && bounce > -1 && assistance > -1 && theft > -1 && block > -1) {
						try {
							basketData.deletePlayer(txtDeleteName.getText(), txtDeleteLast.getText(), txtDeleteTeam.getText(), age, points, bounce, assistance, theft, block);
						} catch (IOException e) {
						}
						inicializateTableView();

						alert.setTitle("EXCELENTE");
						alert.setHeaderText("Se ha eliminado exitosamente");
						alert.setContentText("Se ha eliminado a "+txtDeleteName.getText()+" "+txtDeleteLast.getText()+" de la base de datos");
						alert.showAndWait();
						
						txtDeleteName.setText("");
						txtDeleteLast.setText("");
						txtDeleteAge.setText("");
						txtDeleteTeam.setText("");
						txtDeletePoint.setText("");
						txtDeleteBounce.setText("");
						txtDeleteAss.setText("");
						txtDeleteTheft.setText("");
						txtDeleteBlock.setText("");
					}

				}else {
					alert.setAlertType(AlertType.ERROR);
					alert.setHeaderText("No se pudo eliminar el jugador");
					alert.setContentText("En la base de datos no se encuentran jugadores registrados con esa información");
					alert.showAndWait();
				}

			} catch (NumberFormatException nfe) {

				alert.setAlertType(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setHeaderText("No se pudo eliminar el jugador");
				alert.setContentText("Algunos de los valores tienen que ser de tipo numérico");
				alert.showAndWait();
			}

		}else {
			alert.setTitle("ERROR");
			alert.setHeaderText("No se pudo eliminar el jugador");
			alert.setContentText("Debe llenar todos los campos para eliminar al jugador");
			alert.showAndWait();
		}
	}

	@FXML
	public void btnImport(MouseEvent event) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Abrir un archivo");
		File file = fileChooser.showOpenDialog(mainPane.getScene().getWindow());

		if(file != null) {
			lblImport.setText(file.getAbsolutePath());
			direction = file.getAbsolutePath();
		}
	}

	@FXML
	public void btnGoImport(ActionEvent event) {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		
		if (!lblImport.getText().equals(direction)) {
			alert.setTitle("ERROR");
			alert.setHeaderText("No se pudo importar");
			alert.setContentText("El campo debe contener una dirección válida");
			alert.showAndWait();
		}else if(lblImport.getText().isEmpty()){
			alert.setTitle("ERROR");
			alert.setHeaderText("No se pudo importar");
			alert.setContentText("El campo debe contener una dirección de documento");
			alert.showAndWait();
		}else if(!lblImport.getText().isEmpty()) {
			try {
				waitting();
			} catch (IOException e) {
			}
		}
	}

	@FXML
	private void exitWaitting(ActionEvent event) throws InterruptedException, FileNotFoundException {

		LoadData loadDate = new LoadData(this, lblWaitting, btnWaitting,basketData, new BufferedReader(new FileReader(direction)));
		imgSmile.setVisible(true);
		loadDate.start();
	}

	public void change() throws InterruptedException {

		Stage stage = (Stage) this.imgSmile.getScene().getWindow();
		stage.close();
		imgSmile.setVisible(false);
		inicializateTableView();
		lblImport.setText("");
	}

	private void waitting() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("importWait-pane.fxml"));
		loader.setController(this);

		Parent load;
		load = loader.load();
		Image image = new Image("/images/feliz.png");
		imgSmile.setImage(image);

		Scene scene = new Scene(load);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(scene);

		btnWaitting.setDisable(false);
		btnWaitting.setVisible(true);

		stage.showAndWait();
	}
}
