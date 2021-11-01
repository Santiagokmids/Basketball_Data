package thread;

import java.io.BufferedReader;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.BasketballData;
import ui.BasketballDataGUI;

public class LoadData extends Thread {
	
	private Label label;
	private Button button;
	private BasketballDataGUI basketballDataGUI;
	private BasketballData basketballData;
	private BufferedReader file;
	
	public LoadData(BasketballDataGUI basketballDataGUI, Label label, Button button,BasketballData basketballData, BufferedReader file) {
		this.basketballData = basketballData;
		this.basketballDataGUI = basketballDataGUI;
		this.button = button;
		this.label = label;
		this.file = file;
	}
	
	@Override
	public void run() {
		Platform.runLater(()->{
			label.setText("Los jugadores se están importando, por favor espere...");
			button.setVisible(false);
		});	 
		delay();
		Platform.runLater(() -> { 
			try {
				ImportData importD = new ImportData(basketballData, file);
				importD.start();
				importD.join();
				basketballDataGUI.change();
				
			} catch (InterruptedException e) {
			}

		});
	}

	private void delay() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
	}
}
