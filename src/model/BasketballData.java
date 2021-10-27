package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BasketballData {
	
	public static final String NODES = "data/node.txt";
	private ArrayList<Players> players;

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

	public void loadData() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(NODES);
		if (f.exists()) {
			ObjectInputStream ob = new ObjectInputStream(new FileInputStream(f));
			//ARbol = (noooddooo ) ob.readObject();
			ob.close();
		}
	}
	
	public void saveData() throws FileNotFoundException, IOException {
		ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream(NODES));
		//	ob.writeObject(nodoooo);
		ob.close();
	}

	public void setPlayers(ArrayList<Players> players) {
		this.players = players;
	}
	
	public ArrayList<Players> getPlayers() {
		return players;
	}
	
	public BasketballData() {
		players = new ArrayList<>();
	}

	public Players searchPlayer() {
		return null;
	}
}

	
