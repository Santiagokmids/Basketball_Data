package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import dataStructures.AVLTree;
import dataStructures.BinaryTree;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class BasketballData<H, F, V, K, T> {
	
	public static final String NODES = "data/node.txt";
	public static final String TREE_BOUNCE = "data/node_bounce.txt";
	public static final String TREE_ASSISTANCE = "data/node_assistance.txt";
	public static final String TREE_BLOCK = "data/node_block.txt";
	public static final String TREE_POINTS = "data/node_points.txt";
	
	private ArrayList<Players> players;
	private AVLTree<K, V, F, H> pointsAVLTree;
	private AVLTree<K, V, F, H> bounceAVLTree;
	private AVLTree<K, V, F, H> assitanceAVLTree;
	private AVLTree<K, V, F, H> blockAVLTree;
	private BinaryTree<Players, Integer> theftTree;
	private BinaryTree<Players, Integer> assistanceTree;
	
	public BasketballData() {
		players = new ArrayList<>();
		pointsAVLTree = new AVLTree<>();
		bounceAVLTree = new AVLTree<>();
		assitanceAVLTree = new AVLTree<>();
		blockAVLTree = new AVLTree<>();
		theftTree = new BinaryTree<>();
		assistanceTree = new BinaryTree<>(); 
	}

	public void addPlayer(String name, String lastName, String team, int age, int points, int bounce, int assistance, int theft, int block) {
		Players newPlayers = new Players(name, lastName, team, age, points, bounce, assistance, theft, block);
		players.add(newPlayers);
		theftTree.addNode(newPlayers, theft);
		assistanceTree.addNode(newPlayers, assistance);
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
			//if (code <=  players.getTheft()) {
				player.add(players.get(i));
			//}
		}
		return player;
	}
	public ArrayList<Players> searchArrayMax(int code) {
		ArrayList<Players> player= new ArrayList<Players>();
		for (int i = 0; i < players.size(); i++) {
			//if (code >=  players.getTheft()) {
				player.add(players.get(i));
			//}
		}
		return player;
	}
	public ArrayList<Players> searchArrayEquals(int code) {
		ArrayList<Players> player= new ArrayList<Players>();
		for (int i = 0; i < players.size(); i++) {
			//if (code ==  players.getTheft()) {
				player.add(players.get(i));
			//}
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

	public AVLTree<K, V, F, H> getPointsAVLTree() {
		return pointsAVLTree;
	}

	public void setPointsAVLTree(AVLTree<K, V, F, H> pointsAVLTree) {
		this.pointsAVLTree = pointsAVLTree;
	}

	public AVLTree<K, V, F, H> getBounceAVLTree() {
		return bounceAVLTree;
	}

	public void setBounceAVLTree(AVLTree<K, V, F, H> bounceAVLTree) {
		this.bounceAVLTree = bounceAVLTree;
	}

	public AVLTree<K, V, F, H> getAssitanceAVLTree() {
		return assitanceAVLTree;
	}

	public void setAssitanceAVLTree(AVLTree<K, V, F, H> assitanceAVLTree) {
		this.assitanceAVLTree = assitanceAVLTree;
	}

	public AVLTree<K, V, F, H> getBlockAVLTree() {
		return blockAVLTree;
	}

	public void setBlockAVLTree(AVLTree<K, V, F, H> blockAVLTree) {
		this.blockAVLTree = blockAVLTree;
	}

	public BinaryTree<Players, Integer> getTheftTree() {
		return theftTree;
	}

	public void setTheftTree(BinaryTree<Players, Integer> theftTree) {
		this.theftTree = theftTree;
	}

	public BinaryTree<Players, Integer> getAssistanceTree() {
		return assistanceTree;
	}

	public void setAssistanceTree(BinaryTree<Players, Integer> assistanceTree) {
		this.assistanceTree = assistanceTree;
	}
}

	
