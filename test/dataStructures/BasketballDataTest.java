package dataStructures;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.BasketballData;
import model.Players;

class BasketballDataTest {
		private AVLTree<Integer, Players> avlTree;
		private BinaryTree<Players, Integer> binaryTree;
		private NodeAVLTree<Integer, Players> nodeAvl;
		private NodoBinaryTree<Players, Integer> nodeBinary;
		private BasketballData bt;
		public void setupScenary1() {
			binaryTree = new BinaryTree<Players, Integer>();
			
		}

		public void setupScenary2() {
			avlTree = new AVLTree<Integer, Players>();
		}

		public void setupScenary3() {
			setupScenary1();
			Players player = new Players("Santiago", "Trochez", "Nwy", 19, 40, 15, 12, 6, 12);
			binaryTree.addNode(player, 12);
			
		}

		public void setupScenary4() {
			setupScenary3();		
			Players player1 = new Players("Luis","Ossa","Spurs",22,32,13,10,4,10);
			binaryTree.addNode(player1, 10);
		}

		public void setupScenary5() {
			setupScenary2();
			Players player1 = new Players("Juan","Angulo","Reds",19,26,7,4,1,2);
			avlTree.addNode(26,player1);
		}

		public void setupScenary6() {
			setupScenary5();
			Players player = new Players("Sebastian","Rojas","Neds",17,24,6,3,0,1);
			avlTree.addNode(24, player);
			Players player1 = new Players("Juan","Reyes","Bulls",20,28,9,6,2,3);
			avlTree.addNode(28, player1);
			Players player2 = new Players("Luis","Diaz","Lw",21,29,10,7,3,4);
			avlTree.addNode(29, player2);
			bt = new BasketballData();
		}

		@Test
		void testCreateNode() {
			setupScenary3();		
			Players player1 = new Players("Luis","Ossa","Spurs",22,32,13,10,4,10);
			binaryTree.addNode(player1, 10);
			assertEquals(10, binaryTree.getRoot().getLeft().getKey());
			//metodo para borrar
			
			//Añadir objeto al arbol Avl
			//scenary5
			//Players player1 = new Players("Juan","Angulo","Reds",19,26,7,4,1,2);
			
			//scenary6
			Players player2 = new Players("Sebastian","Rojas","Neds",17,24,6,3,0,1);
			Players player3 = new Players("Juan","Reyes","Bulls",20,28,9,6,2,3);
			Players player4 = new Players("Luis","Diaz","Lw",21,29,10,7,3,4);
			//debo borrar uno 
			
		}
		@Test
		void testDeleateNode() {
			setupScenary4();
			assertEquals(10, binaryTree.getRoot().getLeft().getKey());
			//binaryTree.deleteNode(10);
		//	assertNull(binaryTree.getRoot().getLeft());
			
		}
		@Test
		void testAddNodeAvl() {
			setupScenary5();
			Players player = new Players("Sebastian","Rojas","Neds",17,24,6,3,0,1);
			avlTree.addNode(24, player);
			Players player1 = new Players("Juan","Reyes","Bulls",20,28,9,6,2,3);
			avlTree.addNode(28, player1);
			Players player2 = new Players("Luis","Diaz","Lw",21,29,10,7,3,4);
			avlTree.addNode(29, player2);
			assertEquals("24 26 28 29 ",avlTree.searchInOrder(avlTree.getRoot()));
		}
		@Test
		void testDeleteAvl() {
			setupScenary6();	
			bt.deletePlayer("Juan","Reyes","Bulls",20,28,9,6,2,3);
			assertEquals("24 26 29 ",avlTree.searchInOrder(avlTree.getRoot()));
		}
	}


