package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BasketballData {
	
	private ArrayList<Players> players;
	
	public BasketballData() {
		players = new ArrayList<>();
	}
	
	public Players searchPlayer() {
		return null;
	}
	
	public void loadData() {}

	public void saveData() {}
	
	public void importData(String fileName) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();
		
		while(line != null) {
			//String[] dataPlayers = line.split(";");
			
			//createPlayer(players);
			
			line = br.readLine();
		}
		br.close();
	}

	public ArrayList<Players> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Players> players) {
		this.players = players;
	}
}
