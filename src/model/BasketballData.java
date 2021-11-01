package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import dataStructures.AVLTree;
import dataStructures.BinaryTree;
import dataStructures.NodeAVLTree;
import dataStructures.NodoBinaryTree;
import thread.BinarySearch;

public class BasketballData {

	public static final String SAVE_PATH_FILE_LINEAL_THEFT = "data/node_lineal_theft.txt";
	public static final String SAVE_PATH_FILE_BB_THEFT = "data/node_BB_theft.txt";
	public static final String SAVE_PATH_FILE_BB_ASSISTANCE = "data/node_BB_assistance.txt";
	public static final String SAVE_PATH_FILE_AVL_BOUNCE = "data/node_AVL_bounce.txt";
	public static final String SAVE_PATH_FILE_AVL_ASSISTANCE = "data/node_AVL_assistance.txt";
	public static final String SAVE_PATH_FILE_AVL_BLOCK = "data/node_AVL_block.txt";
	public static final String SAVE_PATH_FILE_AVL_POINTS = "data/node_AVL_points.txt";

	private ArrayList<Players> players;
	private AVLTree<Integer, Players> pointsAVLTree;
	private AVLTree<Integer, Players> bounceAVLTree;
	private AVLTree<Integer, Players> assistanceAVLTree;
	private AVLTree<Integer, Players> blockAVLTree;
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

	public synchronized void addPlayer(String name, String lastName, String team, int age, int points, int bounce, int assistance, int theft, int block) {
		Players newPlayers = new Players(name, lastName, team, age, points, bounce, assistance, theft, block);
		players.add(newPlayers);
		pointsAVLTree.addNode(points,newPlayers);
		bounceAVLTree.addNode(bounce, newPlayers);
		assistanceAVLTree.addNode(assistance, newPlayers);
		blockAVLTree.addNode(block, newPlayers);
		theftTree.addNode(newPlayers, theft);
		assistanceTree.addNode(newPlayers, assistance);
		saveData();
	}

	public void deletePlayer(String name, String lastName, String team, int age, int points, int bounce, int assistance, int theft, int block) {

		deleteInArray(name, lastName);

		NodeAVLTree<Integer, Players> nodeAVLTree =  pointsAVLTree.searchNodeObject(points);
		NodoBinaryTree< Players,Integer> nodeTree =  assistanceTree.searchNodeObject(assistance);

		if(nodeAVLTree != null && nodeTree != null) {
			pointsAVLTree.deleteNode(nodeAVLTree);
			bounceAVLTree.deleteNode( nodeAVLTree);
			assistanceAVLTree.deleteNode( nodeAVLTree);
			blockAVLTree.deleteNode( nodeAVLTree);
			theftTree.deleteNode( nodeTree);
			assistanceTree.deleteNode( nodeTree);
		}
		saveData();
	}

	public void saveData(){

		try {
			ObjectOutputStream linealTheft = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_LINEAL_THEFT));
			linealTheft.writeObject(players);

			ObjectOutputStream bbTheft = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_BB_THEFT));
			bbTheft.writeObject(theftTree);

			ObjectOutputStream bbAssistant = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_BB_ASSISTANCE));
			bbAssistant.writeObject(assistanceTree);

			ObjectOutputStream avlBounce = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_AVL_BOUNCE));
			avlBounce.writeObject(bounceAVLTree);

			ObjectOutputStream avlAssistance = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_AVL_ASSISTANCE));
			avlAssistance.writeObject(assistanceAVLTree);

			ObjectOutputStream avlBlock = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_AVL_BLOCK));
			avlBlock.writeObject(blockAVLTree);

			ObjectOutputStream avlPoints = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_AVL_POINTS));
			avlPoints.writeObject(pointsAVLTree);

			linealTheft.close();
			bbTheft.close();
			bbAssistant.close();
			avlBounce.close();
			avlAssistance.close();
			avlBlock.close();
			avlPoints.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public boolean loadData() throws FileNotFoundException, IOException, ClassNotFoundException {
		boolean loaded = false;
		File lineaTheftFile = new File(SAVE_PATH_FILE_LINEAL_THEFT);

		if(lineaTheftFile.exists()) {
			ObjectInputStream linealTheft = new ObjectInputStream(new FileInputStream(lineaTheftFile));
			players = (ArrayList<Players>) linealTheft.readObject();
			linealTheft.close();
			loaded = true;
		}

		File bbTheftFile = new File(SAVE_PATH_FILE_BB_THEFT);

		if(bbTheftFile.exists()) {
			ObjectInputStream bbTheft = new ObjectInputStream(new FileInputStream(bbTheftFile));
			
			theftTree.setRoot((NodoBinaryTree<Players, Integer>) bbTheft.readObject());
			bbTheft.close();
			loaded = true;
		}

		File bbAssistantFile = new File(SAVE_PATH_FILE_BB_ASSISTANCE);

		if(bbAssistantFile.exists()) {
			ObjectInputStream bbAssistant = new ObjectInputStream(new FileInputStream(bbAssistantFile));
			assistanceTree = (BinaryTree<Players, Integer>) bbAssistant.readObject();
			bbAssistant.close();
			loaded = true;
		}

		File avlBounceFile = new File(SAVE_PATH_FILE_AVL_BOUNCE);

		if(avlBounceFile.exists()) {
			ObjectInputStream avlBounce = new ObjectInputStream(new FileInputStream(avlBounceFile));
			bounceAVLTree = (AVLTree<Integer, Players>) avlBounce.readObject();
			avlBounce.close();
			loaded = true;
		}

		File avlAssistanceFile = new File(SAVE_PATH_FILE_AVL_ASSISTANCE);

		if(avlAssistanceFile.exists()) {
			ObjectInputStream avlAssistance = new ObjectInputStream(new FileInputStream(avlAssistanceFile));
			assistanceAVLTree = (AVLTree<Integer, Players>) avlAssistance.readObject();
			avlAssistance.close();
			loaded = true;
		}

		File avlBlockFile = new File(SAVE_PATH_FILE_AVL_BLOCK);

		if(avlBlockFile.exists()) {
			ObjectInputStream avlBlock = new ObjectInputStream(new FileInputStream(avlBlockFile));
			blockAVLTree = (AVLTree<Integer, Players>) avlBlock.readObject();
			avlBlock.close();
			loaded = true;
		}

		File avlPointsFile = new File(SAVE_PATH_FILE_AVL_POINTS);

		if(avlPointsFile.exists()) {
			ObjectInputStream avlPoints = new ObjectInputStream(new FileInputStream(avlPointsFile));
			pointsAVLTree = (AVLTree<Integer, Players>) avlPoints.readObject();
			avlPoints.close();
			loaded = true;
		}

		return loaded;
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

	public void deleteInArray(String name, String lastName) {
		for (int i = 0; i < players.size(); i++) {
			if (name.equalsIgnoreCase(players.get(i).getName()) && lastName.equalsIgnoreCase(players.get(i).getLastName())) {
				players.remove(i);
			}
		}
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

	public ArrayList<Players> searchNodeEqualsAVL(int key, String date) {
		ArrayList<Players> player = new ArrayList<Players>();

		switch (date) {
		case "points":
			player = pointsAVLTree.searchNode(key);
			break;

		case "bounces":
			player = pointsAVLTree.searchNode(key);
			break;

		case "assistence":
			player = pointsAVLTree.searchNode(key);
			break;

		case "blocks":
			player = pointsAVLTree.searchNode(key);
			break;

		default:
			break;
		}
		return player;
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

	public Players searchPlayer(String name, String lastName, int age, String team, int points, int bounce, int assistance, int theft, int block) {

		ArrayList<Players> player = pointsAVLTree.searchNode(points);
		BinarySearch binarySearch = new BinarySearch(player, name, lastName, age, team, points, bounce, assistance, theft, block);
		binarySearch.start();
		try {
			binarySearch.join();
		} catch (InterruptedException e) {
		}
		return binarySearch.getNewPlayer();
	}

	public AVLTree<Integer, Players> getPointsAVLTree() {
		return pointsAVLTree;
	}

	public void setPointsAVLTree(AVLTree<Integer, Players> pointsAVLTree) {
		this.pointsAVLTree = pointsAVLTree;
	}

	public AVLTree<Integer, Players> getBounceAVLTree() {
		return bounceAVLTree;
	}

	public void setBounceAVLTree(AVLTree<Integer, Players> bounceAVLTree) {
		this.bounceAVLTree = bounceAVLTree;
	}

	public AVLTree<Integer, Players> getAssitanceAVLTree() {
		return assistanceAVLTree;
	}

	public void setAssitanceAVLTree(AVLTree<Integer, Players> assitanceAVLTree) {
		this.assistanceAVLTree = assitanceAVLTree;
	}

	public AVLTree<Integer, Players> getBlockAVLTree() {
		return blockAVLTree;
	}

	public void setBlockAVLTree(AVLTree<Integer, Players> blockAVLTree) {
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


