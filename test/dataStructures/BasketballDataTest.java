
package dataStructuresTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dataStructures.AVLTree;
import dataStructures.BinaryTree;
import dataStructures.NodeAVLTree;
import dataStructures.NodoBinaryTree;
import model.Players;

class BastketballDataTest {
	private AVLTree<Integer, Players> avlTree;
	private BinaryTree<Players, Integer> binaryTree;
	private NodeAVLTree<Integer, Players> nodeAvl;
	private NodoBinaryTree<Players, Integer> nodeBinary;

	public void setupScenary1() {
		binaryTree = new BinaryTree<Players, Integer>();
	}

	public void setupScenary2() {
		avlTree = new AVLTree<Integer, Players>();
	}

	public void setupScenary3() {
		
	}

	public void setupScenary4() {
		
	}

	public void setupScenary5() {
		setupScenary2();
		Players player1 = new Players("Juan","Angulo","Reds",19,26,7,4,1,2);
	}

	public void setupScenary6() {

	}

	@Test
	void testCreateNode() {
		setupScenary1();
		Players player = new Players("Santiago", "Trochez", "Nwy", 19, 40, 15, 12, 6, 12);
		binaryTree.addNode(player, 12);
		assertEquals(binaryTree.getRoot().getValue(), player);
	}
}