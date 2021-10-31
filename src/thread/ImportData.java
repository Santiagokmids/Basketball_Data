package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import model.BasketballData;
import model.Players;

public class ImportData extends Thread{

	private BasketballData basketballData;
	private BufferedReader file;
	private ArrayList<Players> newList;

	public ImportData(BasketballData basketballData, BufferedReader file) {
		this.basketballData = basketballData;
		this.file = file;
		newList = new ArrayList<>();
	}

	public void run() {

		Platform.runLater(new Thread() {

			@Override
			public void run() {
				String line = "";
				try {
					line = file.readLine();
					
					int i = 0;

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
							
							Players newPlayers = new Players(name, lastName, team, age, points, bounce, assistance, theft, block);
							newList.add(newPlayers);
							
							line = file.readLine();

						} catch (NumberFormatException e) {
							line = file.readLine();
						}
						i++;
					}
					
					if(i%2 == 0 && i > 2) {
						newThreadMethod(i, 4);
					}else{
						newThreadMethod(i, 3);
					}

				}catch (IOException e) {
				}
				try {
					file.close();
				} catch (IOException e) {
				}
			}

			private void newThreadMethod(int i, int division) {
				int cont = (i/division); 
				for (int j = 0; j < newList.size()-1; j+=(i/division)) {
					cont += j;
					SecondPartImport newThreadImport = new SecondPartImport(basketballData, newList, j, cont);
					newThreadImport.start();
				}
			}
		});
	}
}
