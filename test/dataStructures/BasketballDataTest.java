package dataStructures;

import model.Players;

public class BastketballDataTest {
	private AVLTree<Integer,Players,Integer,Integer> avlTree;
	private BinaryTree<Players,Integer> binaryTree;
	private NodeAVLTree<Integer,Players> nodeAvl;
	private NodoBinaryTree<Players,Integer> nodeBinary;
	public void setupScenary1() {
		binaryTree = new BinaryTree<Players, Integer>();
	}
	public void setupScenary2() {
		avlTree = new AVLTree<Integer, Players>();
		
	}
	public void setupScenary3() {
		
	}

}
