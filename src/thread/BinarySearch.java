package thread;

import java.util.ArrayList;

import dataStructures.NodeAVLTree;
import model.Players;

public class BinarySearch extends Thread{
	
	private ArrayList<NodeAVLTree<Integer, Players>> player;
	private Players newPlayer;
	private String name;
	private String lastName;
	
	public BinarySearch(ArrayList<NodeAVLTree<Integer, Players>> player, String name, String lastName) {
		this.player = player;
		this.name = name;
		this.lastName = lastName;
	}
	
	public void run() {
		for (int i = 1; i < player.size(); i++) {
			for (int j = i; j > 0 && player.get(j-1).getObject().compareTo(player.get(j).getObject().getName()+" "+player.get(j).getObject().getLastName()) > 0; j--) {
				NodeAVLTree<Integer, Players> tempAvlTree = player.get(j);
				
				player.set(j, player.get(j-1));
				player.set(j-1, tempAvlTree);
			}
		}
		newPlayer = binarySearch(player, name+" "+lastName);
	}
	
	public Players binarySearch(ArrayList<NodeAVLTree<Integer, Players>> newList, String toSearch) {
		
		ArrayList<NodeAVLTree<Integer, Players>> listPlayersArrayList = newList;
		
		int pos = -1;
		int i = 0;
		int j = listPlayersArrayList.size()-1;
		Players playerFindPlayers = null;
		
		while (i <= j && pos < 0) {
			int m = (i+j)/2;
			if(listPlayersArrayList.get(m).getObject().compareTo(toSearch) == 0) {
				pos = m;
				playerFindPlayers = listPlayersArrayList.get(pos).getObject();
			}else if (listPlayersArrayList.get(m).getObject().compareTo(toSearch) > 0) {
				j = m-1;
			}else {
				i = m+1;
			}
		}
		
		return playerFindPlayers;
	}
	
	public Players getNewPlayer() {
		return newPlayer;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
