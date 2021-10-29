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
import java.util.ArrayList;

public class BasketballData {
	
	public static final String NODES = "data/node.txt";
	private ArrayList<Players> players;

	public static final String TREE_POINST = "data/node_poinst.txt";
	public static final String TREE_ASSISTANCE = "data/node_assistance.txt";
	public static final String TREE_BLOCK = "data/node_block.txt";
	public static final String TREE_POINTS = "data/node_points.txt";
	
	public BasketballData() {
		players = new ArrayList<>();
	}

	public void addPlayer(String name, String lastName, String team, int age, int points, int bounce, int assistance, int theft, int block) {
		Players newPlayers = new Players(name, lastName, team, age, points, bounce, assistance, theft, block);
		players.add(newPlayers);
	}

	public void importData(String fileName) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();

		while(line != null) {
			//String[] dataPlayers = line.split(";");

			//createPlayer(players);

			// createPlayer(players);

			line = br.readLine();
		}
		br.close();
	}

	public void saveData() throws FileNotFoundException, IOException {
		// ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream(NODES));
		// ob.writeObject(nodoooo);
		// ob.close();
	}

	public boolean loadData() throws FileNotFoundException, IOException, ClassNotFoundException {
		
		// File f = new File(NODE);
		// if (f.exists()) {
		// ObjectInputStream ob = new ObjectInputStream(new FileInputStream(f));
		// ARbol = (noooddooo ) ob.readObject();
		// ob.close();
		return false;
	}

	public ArrayList<Players> searchArrayMin(int code) {
		
		ArrayList<Players> player= new ArrayList<Players>();
		
		for (int i = 0; i < players.size(); i++) {
			
			if (code >= players.get(i).getTheft()) {
				player.add(players.get(i));
			}
		}
		return player;
	}
	
	public ArrayList<Players> searchArrayMax(int code) {
		
		ArrayList<Players> player= new ArrayList<Players>();
		
		for (int i = 0; i < players.size(); i++) {
			
			if (code <=  players.get(i).getTheft()) {
				player.add(players.get(i));
			}
		}
		return player;
	}
	
	public ArrayList<Players> searchArrayEquals(int code) {
		
		ArrayList<Players> player= new ArrayList<Players>();

		for (int i = 0; i < players.size(); i++) {
			
			if (code ==  players.get(i).getTheft()) {
				player.add(players.get(i));
			}
		}
		return player;
	}
	
	public void setPlayers(ArrayList<Players> players) {
		this.players = players;
	}
	
	public ArrayList<Players> getPlayers() {
		return players;
	}

	public Players searchPlayer() {
		return null;
	}
}

	
