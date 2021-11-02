package thread;

import java.util.ArrayList;

import dataStructures.NodeAVLTree;
import model.Players;

public class BinarySearchNode extends Thread{
	
	private ArrayList<NodeAVLTree<Integer, Players>> player;
	private NodeAVLTree<Integer, Players> newPlayer;
	private String name;
	private String lastName;
	private int age;
	private String team;
	private int points;
	private int bounce;
	private int assistance;
	private int block;
	private int theft;

	public BinarySearchNode(ArrayList<NodeAVLTree<Integer, Players>> player, String name, String lastName, int age, String team, int points, int bounce, int assistance, int theft, int block) {
		this.player = player;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.team = team;
		this.points = points;
		this.bounce = bounce;
		this.assistance = assistance;
		this.block = block;
		this.theft = theft;
	}

	public void run() {
		for (int i = 1; i < player.size(); i++) {
			for (int j = i; j > 0 && player.get(j-1).getObject().compareTo(player.get(j).getObject().getName()+" "+player.get(j).getObject().getLastName()+" "+player.get(j).getObject().getTeam()+" "+player.get(j).getObject().getAge()+
					" "+player.get(j).getObject().getPoints()+" "+player.get(j).getObject().getBounce()+" "+player.get(j).getObject().getAssistance()+" "+player.get(j).getObject().getTheft()+" "+player.get(j).getObject().getBlock()) > 0; j--) {
				NodeAVLTree<Integer, Players> tempAvlTree = player.get(j);

				player.set(j, player.get(j-1));
				player.set(j-1, tempAvlTree);
			}
		}
		setNewPlayer(binarySearch(player, name+" "+lastName+" "+team+" "+age+" "+points+" "+bounce+" "+assistance+" "+theft+" "+block));
	}

	public NodeAVLTree<Integer, Players> binarySearch(ArrayList<NodeAVLTree<Integer, Players>> newList, String toSearch) {

		ArrayList<NodeAVLTree<Integer, Players>> listPlayersArrayList = newList;

		int pos = -1;
		int i = 0;
		int j = listPlayersArrayList.size()-1;
		NodeAVLTree<Integer, Players> playerFindPlayers = null;
		
		while (i <= j && pos < 0) {
			int m = (i+j)/2;
			if(listPlayersArrayList.get(m).getObject().compareTo(toSearch) == 0) {
				pos = m;
				playerFindPlayers = listPlayersArrayList.get(pos);
			}else if (listPlayersArrayList.get(m).getObject().compareTo(toSearch) > 0) {
				j = m-1;
			}else {
				i = m+1;
			}
		}

		return playerFindPlayers;
	}

	public NodeAVLTree<Integer, Players> getNewPlayer() {
		return newPlayer;
	}

	public void setNewPlayer(NodeAVLTree<Integer, Players> newPlayer) {
		this.newPlayer = newPlayer;
	}
}
