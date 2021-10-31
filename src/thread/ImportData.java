package thread;



import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.BasketballDataGUI;

public class ImportData extends Thread{
	private Label lb;
	private Button btn;
	private BasketballDataGUI bd;
	public ImportData(BasketballDataGUI bd,Label lb,Button btn) {
		this.lb = lb;
		this.btn = btn;
		this.bd = bd;
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			});
	 }
	 
	 private void delay() {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
