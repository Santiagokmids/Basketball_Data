package thread;

import javafx.application.Platform;
import ui.BasketballDataGUI;

public class LoadData extends Thread {
	private BasketballDataGUI gui;
	public LoadData(BasketballDataGUI gui) {
		this.gui = gui;
	}
	@Override
    public void run(){
		 Platform.runLater(()->{
			 gui.loadSanti();
			
         });
	}
}
