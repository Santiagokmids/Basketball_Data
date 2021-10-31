package thread;

import java.io.BufferedReader;
import java.io.IOException;

import javafx.application.Platform;
import model.BasketballData;
import ui.BasketballDataGUI;

public class ImportData extends Thread{

	private BasketballDataGUI basketballDataGUI;
	private BasketballData basketballData;
	private BufferedReader file;

	public ImportData(BasketballDataGUI basketballDataGUI, BasketballData basketballData, BufferedReader file) {
		this.basketballDataGUI = basketballDataGUI;
		this.basketballData = basketballData;
		this.file = file;
	}

	public void run() {

		Platform.runLater(new Thread() {

			@Override
			public void run() {
				String line = "";
				try {
					line = file.readLine();

					while(line != null) {

						try {
							String[] parts = line.split(";");

							String name = parts[0];
							String lastName = parts[1];
							String team = parts[3];
							int age = Integer.parseInt(parts[2]);
							int points = Integer.parseInt(parts[4]);
							int bounce = Integer.parseInt(parts[5]);
							int assistance = Integer.parseInt(parts[6]);
							int theft = Integer.parseInt(parts[7]);
							int block = Integer.parseInt(parts[8]);

							basketballData.addPlayer(name, lastName, team, age, points, bounce, assistance, theft, block);
							basketballDataGUI.inicializateTableView();

							line = file.readLine();

						} catch (NumberFormatException e) {
							line = file.readLine();
						}
					}

				}catch (IOException e) {
				}
				try {
					file.close();
				} catch (IOException e) {
				}
			}
		});
	}
}
