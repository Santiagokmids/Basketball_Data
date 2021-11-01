package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import model.BasketballData;

class BasketballDataTest {

	private BasketballData bt;
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

		@Test
		void testCreateNode() throws IOException {
			setupScenary3();		
			bt.addPlayer("Luis","Ossa","Spurs",22,32,13,10,4,10);
			assertEquals(10, bt.getAssistanceTree().getRoot().getLeft().getKey());
			
			
		}
		@Test
		void testDeleteNode() throws IOException  {
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
		void testDeleteAvl() throws IOException {
			setupScenary6();	
			bt.deletePlayer("Juan","Reyes","Bulls",20,28,9,6,2,3);
			assertEquals("24 26 29 ",bt.getPointsAVLTree().searchInOrder(bt.getPointsAVLTree().getRoot()));
		}
	}


