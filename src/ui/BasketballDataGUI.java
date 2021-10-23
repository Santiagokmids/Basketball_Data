package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
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
    private Button a;

    @FXML
    private Button s;

    @FXML
    private Button d;

    @FXML
    private Button f;

	
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
		anch.setVisible(false);
		anch.setDisable(true);
		two.setVisible(false);
		two.setDisable(true);
		Button button = (Button) event.getSource();
		String butt = button.getText();
		
		switch(butt) {
		case "a":
			anch.setVisible(true);
			anch.setDisable(false);
			break;
		case "s":
			two.setVisible(true);
			two.setDisable(false);
			break;
		}
	}
	
	@FXML
    public void closeApp(MouseEvent event) {
		System.exit(0);
    }
} 
