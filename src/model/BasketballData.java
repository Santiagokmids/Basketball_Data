package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


import dataStructures.AVLTree;
import dataStructures.BinaryTree;
import dataStructures.NodeAVLTree;
import dataStructures.NodoBinaryTree;
import thread.BinarySearch;

public class BasketballData {

	public static final String NODES = "data/node.txt";
	public static final String TREE_BOUNCE = "data/node_bounce.txt";
	public static final String TREE_ASSISTANCE = "data/node_assistance.txt";
	public static final String TREE_BLOCK = "data/node_block.txt";
	public static final String TREE_POINTS = "data/node_points.txt";

	private ArrayList<Players> players;
	private AVLTree<Integer, Players, Integer, Integer> pointsAVLTree;
	private AVLTree<Integer, Players, Integer, Integer> bounceAVLTree;
	private AVLTree<Integer, Players, Integer, Integer> assistanceAVLTree;
	private AVLTree<Integer, Players, Integer, Integer> blockAVLTree;
	private BinaryTree<Players, Integer> theftTree;
	private BinaryTree<Players, Integer> assistanceTree;

	public BasketballData() {
		players = new ArrayList<>();
		pointsAVLTree = new AVLTree<>();
		bounceAVLTree = new AVLTree<>();
		assistanceAVLTree = new AVLTree<>();
		blockAVLTree = new AVLTree<>();
		theftTree = new BinaryTree<>();
		assistanceTree = new BinaryTree<>(); 
	}

	public void addPlayer(String name, String lastName, String team, int age, int points, int bounce, int assistance, int theft, int block) {
		Players newPlayers = new Players(name, lastName, team, age, points, bounce, assistance, theft, block);
		players.add(newPlayers);
		pointsAVLTree.addNode(points,newPlayers);
		/*bounceAVLTree.addNode(bounce, newPlayers);
		assistanceAVLTree.addNode(assistance, newPlayers);
		blockAVLTree.addNode(block, newPlayers);
		theftTree.addNode(newPlayers, theft);
		assistanceTree.addNode(newPlayers, assistance);
		*/
		if(points == 20) {
			System.out.println(pointsAVLTree.searchInOrder(pointsAVLTree.getRoot()));
		}
	}
	
	public void deletePlayer(String name, String lastName, String team, int age, int points, int bounce, int assistance, int theft, int block) {
		Players newPlayers = new Players(name, lastName, team, age, points, bounce, assistance, theft, block);
		players.add(newPlayers);
		pointsAVLTree.addNode(points,newPlayers);
		/*bounceAVLTree.addNode(bounce, newPlayers);
		assistanceAVLTree.addNode(assistance, newPlayers);
		blockAVLTree.addNode(block, newPlayers);
		theftTree.addNode(newPlayers, theft);
		assistanceTree.addNode(newPlayers, assistance);
		*/
		if(points == 20) {
			System.out.println(pointsAVLTree.searchInOrder(pointsAVLTree.getRoot()));
		}
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

	public NodeAVLTree<Integer, Players> searchNodesPoint(){
		return pointsAVLTree.getRoot();
	}
	
	public NodeAVLTree<Integer, Players> searchNodesBounce(){
		return bounceAVLTree.getRoot();
	}
	
	public NodeAVLTree<Integer, Players> searchNodesAssitence(){
		return assistanceAVLTree.getRoot();
	}
	
	public NodeAVLTree<Integer, Players> searchNodesBlocks(){
		return blockAVLTree.getRoot();
	}
	
	public ArrayList<Players> searchNodeEqualsAVL(int key, NodeAVLTree<Integer, Players> assistaNodeAVLTree, ArrayList<Players> players,boolean stop) {

		if(assistaNodeAVLTree == null) {
			return players;

		}if(assistaNodeAVLTree.getKey() == key) {
			stop = true;
			players.add(assistaNodeAVLTree.getObject());

		}if(assistaNodeAVLTree.getKey() != key && stop) {
			return players;
		}
		else {
			if((Integer)key <= (Integer)assistaNodeAVLTree.getKey()) {
				return searchNodeEqualsAVL(key, assistaNodeAVLTree.getLeft(),players, stop);
			}else {
				return searchNodeEqualsAVL(key, assistaNodeAVLTree.getRight(),players,stop);
			}
		}
	}

	public ArrayList<Players> searchNodeMinAVL(int key, NodeAVLTree<Integer, Players> assistaNodeAVLTree, ArrayList<Players> players) {
		ArrayList<Players> pl = new ArrayList<Players>();
		
		if(assistaNodeAVLTree == null) {
			pl = players;

		}if(assistaNodeAVLTree.getKey() <= key) {
			players.add(assistaNodeAVLTree.getObject());
			
		}
		else {
			if((Integer)key <= (Integer)assistaNodeAVLTree.getKey()) {
				pl = searchNodeMinAVL(key, assistaNodeAVLTree.getLeft(),players);
				
			}else {
				pl = searchNodeMinAVL(key, assistaNodeAVLTree.getRight(),players);
			}
		}
		return pl;
	}

	public ArrayList<Players> searchNodeMaxAVL(int key, NodeAVLTree<Integer, Players> assistaNodeAVLTree, ArrayList<Players> players) {
		ArrayList<Players> pl = new ArrayList<Players>();
		
		if(assistaNodeAVLTree == null) {
			pl = players;

		}if(assistaNodeAVLTree.getKey() >= key) {
			players.add(assistaNodeAVLTree.getObject());
			
		}
		else {
			if((Integer)key <= (Integer)assistaNodeAVLTree.getKey()) {
				pl = searchNodeMaxAVL(key, assistaNodeAVLTree.getLeft(),players);
				
			}else {
				pl = searchNodeMaxAVL(key, assistaNodeAVLTree.getRight(),players);
			}
		}
		return pl;
	}

	public void setPlayers(ArrayList<Players> players) {
		this.players = players;
	}

	public ArrayList<Players> getPlayers() {
		return players;
	}

	public Players searchPlayer(int point, String name, String lastName) {
		ArrayList<NodeAVLTree<Integer, Players>> player = pointsAVLTree.searchNode(point);
		
		BinarySearch binarySearch = new BinarySearch(player, name, lastName);
		binarySearch.start();
		return binarySearch.getNewPlayer();
	}

	public AVLTree<Integer, Players, Integer, Integer> getPointsAVLTree() {
		return pointsAVLTree;
	}

	public void setPointsAVLTree(AVLTree<Integer, Players, Integer, Integer> pointsAVLTree) {
		this.pointsAVLTree = pointsAVLTree;
	}

	public AVLTree<Integer, Players, Integer, Integer> getBounceAVLTree() {
		return bounceAVLTree;
	}

	public void setBounceAVLTree(AVLTree<Integer, Players, Integer, Integer> bounceAVLTree) {
		this.bounceAVLTree = bounceAVLTree;
	}

	public AVLTree<Integer, Players, Integer, Integer> getAssitanceAVLTree() {
		return assistanceAVLTree;
	}

	public void setAssitanceAVLTree(AVLTree<Integer, Players, Integer, Integer> assitanceAVLTree) {
		this.assistanceAVLTree = assitanceAVLTree;
	}

	public AVLTree<Integer, Players, Integer, Integer> getBlockAVLTree() {
		return blockAVLTree;
	}

	public void setBlockAVLTree(AVLTree<Integer, Players, Integer, Integer> blockAVLTree) {
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
	
	public NodoBinaryTree<Players, Integer> searchNodesAssitenceTree(){
		return assistanceTree.getRoot();
	}

	public ArrayList<Players> searchNodeEqualsTree(int key, NodoBinaryTree<Players, Integer> assistaNodeTree, ArrayList<Players> players,boolean stop) {

		if(assistaNodeTree == null) {
			return players;

		}if(assistaNodeTree.getKey() == key) {
			stop = true;
			players.add(assistaNodeTree.getValue());

		}if(assistaNodeTree.getKey() != key && stop) {
			return players;
		}
		else {
			if((Integer)key <= (Integer)assistaNodeTree.getKey()) {
				return searchNodeEqualsTree(key, assistaNodeTree.getLeft(),players, stop);
			}else {
				return searchNodeEqualsTree(key, assistaNodeTree.getRight(),players,stop);
			}
		}
	}

	public ArrayList<Players> searchNodeMinTree(int key, NodoBinaryTree<Players, Integer> assistaNodeTree, ArrayList<Players> players) {
		ArrayList<Players> pl = new ArrayList<Players>();
		
		if(assistaNodeTree == null) {
			pl = players;

		}if(assistaNodeTree.getKey() <= key) {
			players.add(assistaNodeTree.getValue());
			
		}
		else {
			if((Integer)key <= (Integer)assistaNodeTree.getKey()) {
				pl = searchNodeMinTree(key, assistaNodeTree.getLeft(),players);
				
			}else {
				pl = searchNodeMinTree(key, assistaNodeTree.getRight(),players);
			}
		}
		return pl;
	}

	public ArrayList<Players> searchNodeMaxTree(int key, NodoBinaryTree<Players, Integer> assistaNodeTree, ArrayList<Players> players) {
		ArrayList<Players> pl = new ArrayList<Players>();
		
		if(assistaNodeTree == null) {
			pl = players;

		}if(assistaNodeTree.getKey() >= key) {
			players.add(assistaNodeTree.getValue());
			
		}
		else {
			if((Integer)key <= (Integer)assistaNodeTree.getKey()) {
				pl = searchNodeMaxTree(key, assistaNodeTree.getLeft(),players);
				
			}else {
				pl = searchNodeMaxTree(key, assistaNodeTree.getRight(),players);
			}
		}
		return pl;
	}
	
	public NodoBinaryTree<Players, Integer> searchNodesTheftTree(int key){
		return theftTree.searchNode(key);
	}
}


