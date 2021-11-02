package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import model.BasketballData;
import thread.BinarySearch;
import thread.SearchByName;

class BasketballDataTest<K extends Comparable<K>, V> {
		private K k;
		private V v;
		private AVLTree<K, V> treeAvl;
		private BinaryTree<V,K> biTree;
		private BasketballData bt;
		private BinarySearch bns;
		
		public void setupScenary1() {
			bt = new BasketballData();
		}

		public void setupScenary2() {
			bt = new BasketballData();
		}

		public void setupScenary3() throws IOException {
			setupScenary1();
			bt.addPlayer("Santiago", "Trochez", "Nwy", 19, 40, 15, 12, 6, 12);
		}

		public void setupScenary4() throws IOException  {
			setupScenary3();		
			bt.addPlayer("Luis","Ossa","Spurs",22,32,13,10,4,10);
		}

		public void setupScenary5() throws IOException {
			
			setupScenary2();
			bt.addPlayer("Juan","Angulo","Reds",19,26,7,4,1,2);
			
		}

		public void setupScenary6() throws IOException  {
			setupScenary5();
			bt.addPlayer("Sebastian","Rojas","Neds",17,24,6,3,0,1);
			bt.addPlayer("Juan","Reyes","Bulls",20,28,9,6,2,3);
			bt.addPlayer("Luis","Diaz","Lw",21,29,10,7,3,4);
		}
		public void setupScenary7() {
			biTree = new BinaryTree<V,K>();
		}
		public void septupScenary8() {
			setupScenary7();
			biTree.addNode(v, k);
			
		}
		
		public void setupScenary9() {
			setupScenary7();
			biTree.addNode(v, k);
			biTree.addNode(v, k);
		}
		public void setupScenary10() {
			treeAvl = new AVLTree<K,V>();
		}
			
		public void setupScenary11() {
			setupScenary10();
			treeAvl.addNode(k, v);		
			treeAvl.addNode(k, v);
		}
		
		@Test
		void testCreateNode() throws IOException {
			setupScenary3();		
			bt.addPlayer("Luis","Ossa","Spurs",22,32,13,10,4,10);
			assertEquals(10, bt.getAssistanceTree().getRoot().getLeft().getKey());
			
			
		}
		@Test
		void testDeleteNode() throws IOException, InterruptedException  {
			setupScenary4();
			bt.deletePlayer("Luis","Ossa","Spurs",22,32,13,10,4,10);
			assertEquals("12 ",bt.getAssistanceTree().searchInOrder(bt.getAssistanceTree().getRoot()));
			
			
		}
		@Test
		void testAddNodeAvl() throws IOException  {
			setupScenary5();
			bt.addPlayer("Sebastian","Rojas","Neds",17,24,6,3,0,1);
			bt.addPlayer("Juan","Reyes","Bulls",20,28,9,6,2,3);
			bt.addPlayer("Luis","Diaz","Lw",21,29,10,7,3,4);
			assertEquals("24 26 28 29 ",bt.getPointsAVLTree().searchInOrder(bt.getPointsAVLTree().getRoot()));
		}
		@Test
		void testDeleteAvl() throws IOException, InterruptedException {
			setupScenary6();	
			bt.deletePlayer("Juan","Reyes","Bulls",20,28,9,6,2,3);
			assertEquals("24 26 29 ",bt.getPointsAVLTree().searchInOrder(bt.getPointsAVLTree().getRoot()));
		}
		@Test
		void testAddPlayer() throws IOException {
			bt = new BasketballData();
			bt.addPlayer("Juan","Reyes","Bulls",20,28,9,6,2,3);
			assertEquals(6, bt.getAssistanceTree().getRoot().getKey());
			assertEquals(6,bt.getAssitanceAVLTree().getRoot().getKey());
			assertEquals(3,bt.getBlockAVLTree().getRoot().getKey());
			assertEquals(9,bt.getBounceAVLTree().getRoot().getKey());
			assertEquals(28,bt.getPointsAVLTree().getRoot().getKey());
			assertEquals(2,bt.getTheftTree().getRoot().getKey());
		}
		@Test
		void testAddPlayerEqual() throws InterruptedException, IOException {
			setupScenary4();		
			SearchByName search = new SearchByName(bt.getPlayers(),"Luis","Ossa" ,22);
			search.start();
			search.join();
			assertTrue(search.getVerify());
			
		}
		@Test
		void testAddObjectAVl() {
			setupScenary11();
			assertEquals(2, treeAvl.height(treeAvl.getRoot()));
		}
		@Test
		void testAddObjectBinary() {
			setupScenary7();
			assertTrue(biTree.addNode(v, k));
		}
		@Test
		void testDeleteAVl() {
			setupScenary11();
			//treeAvl.deleteNode(bns.sear);
			assertEquals(2, treeAvl.height(treeAvl.getRoot()));
		}
		@Test
		void testDeleteBinary() {
			septupScenary8();
			biTree.deleteNode(k);
			assertNull(biTree.getRoot());
		}
	}


