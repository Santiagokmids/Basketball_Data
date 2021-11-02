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
import thread.BinarySearchBinary;
import thread.BinarySearchNode;

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

	public synchronized void addPlayer(String name, String lastName, String team, int age, int points, int bounce, int assistance, int theft, int block) throws IOException {
		
		Players newPlayers = new Players(name, lastName, team, age, points, bounce, assistance, theft, block);
		
		players.add(newPlayers);
		pointsAVLTree.addNode(points, newPlayers);
		bounceAVLTree.addNode(bounce, newPlayers);
		assistanceAVLTree.addNode(assistance, newPlayers);
		blockAVLTree.addNode(block, newPlayers);
		theftTree.addNode(newPlayers, theft);
		assistanceTree.addNode(newPlayers, assistance);
		saveData();
	}

	public void deletePlayer(String name, String lastName, String team, int age, int points, int bounce, int assistance,int theft, int block) throws IOException, InterruptedException {

		deleteInArray(name, lastName);
		
		ArrayList<NodeAVLTree<Integer, Players>> nodeAVLTreePoints = pointsAVLTree.searchNodeObject(points);
		NodeAVLTree<Integer, Players> nodeAvlPoint = startThreads(nodeAVLTreePoints, name, lastName, age, team, points, bounce, assistance, theft, block);
		
		ArrayList<NodeAVLTree<Integer, Players>> nodeAVLTreeBounce = bounceAVLTree.searchNodeObject(bounce);
		NodeAVLTree<Integer, Players> nodeAvlBounce = startThreads(nodeAVLTreeBounce, name, lastName, age, team, points, bounce, assistance, theft, block);
		
		ArrayList<NodeAVLTree<Integer, Players>> nodeAVLTreeAssistance = assistanceAVLTree.searchNodeObject(assistance);
		NodeAVLTree<Integer, Players> nodeAvlAssistence = startThreads(nodeAVLTreeAssistance, name, lastName, age, team, points, bounce, assistance, theft, block);
		
		ArrayList<NodeAVLTree<Integer, Players>> nodeAVLTreeBlock = blockAVLTree.searchNodeObject(block);
		NodeAVLTree<Integer, Players> nodeAvlBlock = startThreads(nodeAVLTreeBlock, name, lastName, age, team, points, bounce, assistance, theft, block);
		
		ArrayList <NodoBinaryTree<Players, Integer>> nodeTreeAssistence = assistanceTree.searchNode(assistance);
		NodoBinaryTree<Players, Integer> treeAssistence = startThreadsBb(nodeTreeAssistence, name, lastName, age, team, points, bounce, assistance, theft, block);
		
		ArrayList <NodoBinaryTree<Players, Integer>> nodeTheftTree = theftTree.searchNode(theft);
		NodoBinaryTree<Players, Integer> treeTheft = startThreadsBb(nodeTheftTree, name, lastName, age, team, points, bounce, assistance, theft, block);
		
		if (nodeAvlPoint != null && nodeAvlBounce != null && nodeAvlAssistence != null && nodeAvlBlock != null && treeAssistence != null && treeTheft != null) {
			pointsAVLTree.deleteNode(nodeAvlPoint);
			bounceAVLTree.deleteNode(nodeAvlBounce);
			assistanceAVLTree.deleteNode(nodeAvlAssistence);
			blockAVLTree.deleteNode(nodeAvlBlock);
			theftTree.deleteNode(treeAssistence);
			assistanceTree.deleteNode(treeTheft);
		}
		saveData();
	}
	
	public NodeAVLTree<Integer, Players> startThreads(ArrayList<NodeAVLTree<Integer, Players>> nodes, String name, String lastName, int age, String team, int points, int bounce, int assistance, int theft, int block) throws InterruptedException {
		BinarySearchNode binary = new BinarySearchNode(nodes, name, lastName, age, team, points, bounce, assistance, theft, block);
		binary.start();
		binary.join();
		return binary.getNewPlayer();
	}
	
	public NodoBinaryTree<Players, Integer> startThreadsBb(ArrayList<NodoBinaryTree<Players, Integer>> nodes, String name, String lastName, int age, String team, int points, int bounce, int assistance, int theft, int block) throws InterruptedException {
		BinarySearchBinary binary = new BinarySearchBinary(nodes, name, lastName, age, team, points, bounce, assistance, theft, block);
		binary.start();
		binary.join();
		return binary.getNewPlayer();
	}

	public void saveData() throws IOException {

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
	}

	@SuppressWarnings("unchecked")
	public boolean loadData() throws FileNotFoundException, IOException, ClassNotFoundException {
		boolean loaded = false;
		File lineaTheftFile = new File(SAVE_PATH_FILE_LINEAL_THEFT);

		if (lineaTheftFile.exists()) {
			ObjectInputStream linealTheft = new ObjectInputStream(new FileInputStream(lineaTheftFile));
			players = (ArrayList<Players>) linealTheft.readObject();
			linealTheft.close();
			loaded = true;
		}

		File bbTheftFile = new File(SAVE_PATH_FILE_BB_THEFT);

		if (bbTheftFile.exists()) {
			ObjectInputStream bbTheft = new ObjectInputStream(new FileInputStream(bbTheftFile));
			theftTree = (BinaryTree<Players, Integer>) bbTheft.readObject();
			bbTheft.close();
			loaded = true;
		}

		File bbAssistantFile = new File(SAVE_PATH_FILE_BB_ASSISTANCE);

		if (bbAssistantFile.exists()) {
			ObjectInputStream bbAssistant = new ObjectInputStream(new FileInputStream(bbAssistantFile));
			assistanceTree = (BinaryTree<Players, Integer>) bbAssistant.readObject();
			bbAssistant.close();
			loaded = true;
		}

		File avlBounceFile = new File(SAVE_PATH_FILE_AVL_BOUNCE);

		if (avlBounceFile.exists()) {
			ObjectInputStream avlBounce = new ObjectInputStream(new FileInputStream(avlBounceFile));
			bounceAVLTree = (AVLTree<Integer, Players>) avlBounce.readObject();
			avlBounce.close();
			loaded = true;
		}

		File avlAssistanceFile = new File(SAVE_PATH_FILE_AVL_ASSISTANCE);

		if (avlAssistanceFile.exists()) {
			ObjectInputStream avlAssistance = new ObjectInputStream(new FileInputStream(avlAssistanceFile));
			assistanceAVLTree = (AVLTree<Integer, Players>) avlAssistance.readObject();
			avlAssistance.close();
			loaded = true;
		}

		File avlBlockFile = new File(SAVE_PATH_FILE_AVL_BLOCK);

		if (avlBlockFile.exists()) {
			ObjectInputStream avlBlock = new ObjectInputStream(new FileInputStream(avlBlockFile));
			blockAVLTree = (AVLTree<Integer, Players>) avlBlock.readObject();
			avlBlock.close();
			loaded = true;
		}

		File avlPointsFile = new File(SAVE_PATH_FILE_AVL_POINTS);

		if (avlPointsFile.exists()) {
			ObjectInputStream avlPoints = new ObjectInputStream(new FileInputStream(avlPointsFile));
			pointsAVLTree = (AVLTree<Integer, Players>) avlPoints.readObject();
			avlPoints.close();
			loaded = true;
		}

		return loaded;
	}

	public ArrayList<Players> searchArrayMin(int code) {

		ArrayList<Players> player = new ArrayList<Players>();

		for (int i = 0; i < players.size(); i++) {

			if (code >= players.get(i).getTheft()) {
				player.add(players.get(i));
			}
		}
		return player;
	}

	public ArrayList<Players> searchArrayMax(int code) {

		ArrayList<Players> player = new ArrayList<Players>();

		for (int i = 0; i < players.size(); i++) {

			if (code <= players.get(i).getTheft()) {
				player.add(players.get(i));
			}
		}
		return player;
	}

	public ArrayList<Players> searchArrayEquals(int code) {

		ArrayList<Players> player = new ArrayList<Players>();

		for (int i = 0; i < players.size(); i++) {

			if (code == players.get(i).getTheft()) {
				player.add(players.get(i));
			}
		}
		return player;
	}

	public void deleteInArray(String name, String lastName) throws IOException {
		
		for (int i = 0; i < players.size(); i++) {
			
			if (name.equalsIgnoreCase(players.get(i).getName()) && lastName.equalsIgnoreCase(players.get(i).getLastName())) {
				players.remove(i);
			}
		}
		saveData();
	}

	public NodeAVLTree<Integer, Players> searchNodesPoint() {
		return pointsAVLTree.getRoot();
	}

	public NodeAVLTree<Integer, Players> searchNodesBounce() {
		return bounceAVLTree.getRoot();
	}

	public NodeAVLTree<Integer, Players> searchNodesAssitence() {
		return assistanceAVLTree.getRoot();
	}

	public NodeAVLTree<Integer, Players> searchNodesBlocks() {
		return blockAVLTree.getRoot();
	}

	public ArrayList<Players> searchNodeEqualsAVL(int key, String date) {
		ArrayList<Players> player = new ArrayList<Players>();

		switch (date) {
		case "points":
			player = pointsAVLTree.searchNode(key);
			break;

		case "bounces":
			player = bounceAVLTree.searchNode(key);
			break;

		case "assistence":
			player = assistanceAVLTree.searchNode(key);
			break;

		case "blocks":
			player = blockAVLTree.searchNode(key);
			break;

		default:
			break;
		}
		return player;
	}

	public ArrayList<Players> searchNodeMinAVL(int key, String date) {
		ArrayList<Players> player = new ArrayList<Players>();

		switch (date) {
		case "points":
			player = pointsAVLTree.searchNodeMin(key);
			break;

		case "bounces":
			player = bounceAVLTree.searchNodeMin(key);
			break;

		case "assistence":
			player = assistanceAVLTree.searchNodeMin(key);
			break;

		case "blocks":
			player = blockAVLTree.searchNodeMin(key);
			break;

		default:
			break;
		}
		
		return player;
	}

	public ArrayList<Players> searchNodeMaxAVL(int key, String date) {
		ArrayList<Players> player = new ArrayList<Players>();

		switch (date) {
		case "points":
			player = pointsAVLTree.searchNodeMax(key);
			break;

		case "bounces":
			player = bounceAVLTree.searchNodeMax(key);
			break;

		case "assistence":
			player = assistanceAVLTree.searchNodeMax(key);
			break;

		case "blocks":
			player = blockAVLTree.searchNodeMax(key);
			break;

		default:
			break;
		}
		return player;
	}

	public void setPlayers(ArrayList<Players> players) {
		this.players = players;
	}

	public ArrayList<Players> getPlayers() {
		return players;
	}

	public Players searchPlayer(String name, String lastName, int age, String team, int points, int bounce,int assistance, int theft, int block) {
		ArrayList<Players> player;

		player = pointsAVLTree.searchNode(points);

		BinarySearch binarySearch = new BinarySearch(player, name, lastName, age, team, points, bounce, assistance,	theft, block);
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

	public NodoBinaryTree<Players, Integer> searchNodesAssitenceTree() {
		return assistanceTree.getRoot();
	}

	public ArrayList<Players> searchNodeEqualsTree(int key, NodoBinaryTree<Players, Integer> assistaNodeTree,ArrayList<Players> players) {

		if (assistaNodeTree == null) {
			return players;
		}
		if (assistaNodeTree != null && assistaNodeTree.getKey() == key) {
			players.add(assistaNodeTree.getValue());
			if ((Integer) key <= (Integer) assistaNodeTree.getKey()) {
				return searchNodeEqualsTree(key, assistaNodeTree.getLeft(), players);
			} else {
				return searchNodeEqualsTree(key, assistaNodeTree.getRight(), players);
			}
		}
		else {
			if ((Integer) key <= (Integer) assistaNodeTree.getKey()) {
				return searchNodeEqualsTree(key, assistaNodeTree.getLeft(), players);
			} else {
				return searchNodeEqualsTree(key, assistaNodeTree.getRight(), players);
			}
		}
	}

	public ArrayList<Players> searchNodeMaxTree(int key, String date) {
		ArrayList<Players> player = new ArrayList<Players>();

		switch (date) {
		case "assistances":
			player = assistanceTree.searchNodeMax(key);
			break;

		case "theft":
			player = theftTree.searchNodeMax(key);
			break;

		default:
			break;
		}
		return player;
	}

	public ArrayList<Players> searchNodeMinTree(int key, String date) {
		ArrayList<Players> player = new ArrayList<Players>();

		switch (date) {
		case "assistances":
			player = assistanceTree.searchNodeMin(key);
			break;

		case "theft":
			player = theftTree.searchNodeMin(key);
			break;

		default:
			break;
		}
		return player;
	}

	public NodoBinaryTree<Players, Integer> searchNodesTheftTree(int key) {
		return theftTree.getRoot();
	}
}
