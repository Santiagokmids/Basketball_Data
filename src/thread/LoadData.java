package thread;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.BasketballDataGUI;

public class LoadData extends Thread {
	
	private Label lb;
	private Button btn;
	private BasketballDataGUI bd;
	
	public LoadData(BasketballDataGUI bd, Label lb, Button btn) {
		this.bd = bd;
		this.lb = lb;
		this.btn = btn;
	}
	
	@Override
	public void run() {
		Platform.runLater(()->{
			lb.setText("Los jugadores se están importando, por favor espere...");
			btn.setVisible(false);
		});	 
		delay();
		Platform.runLater(() -> { 
			try {
				bd.change();
			} catch (InterruptedException e) {
			}

		});
	}

	private void delay() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
	}
}
