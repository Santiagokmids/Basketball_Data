package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BasketballData {
	
	public BasketballData() {}
	
	public void searchPlayer() {}
	
	public void loadData() {}

	public void saveData() {}
	
	public void importData(String fileName) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();
		
		while(line != null) {
			String[] players = line.split(";");
			
			//createPlayer(players);
			
			line = br.readLine();
		}
		br.close();
	}
}
