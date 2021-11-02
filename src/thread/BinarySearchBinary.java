package thread;

import java.util.ArrayList;

import dataStructures.NodoBinaryTree;
import model.Players;

public class BinarySearchBinary extends Thread{

	private ArrayList<NodoBinaryTree<Players,Integer>> player;
	private NodoBinaryTree<Players,Integer> newPlayer;
	private String name;
	private String lastName;
	private int age;
	private String team;
	private int points;
	private int bounce;
	private int assistance;
	private int block;
	private int theft;

	public BinarySearchBinary(ArrayList<NodoBinaryTree<Players,Integer>> player, String name, String lastName, int age, String team, int points, int bounce, int assistance, int theft, int block) {
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
			for (int j = i; j > 0 && player.get(j-1).getValue().compareTo(player.get(j).getValue().getName()+" "+player.get(j).getValue().getLastName()+" "+player.get(j).getValue().getTeam()+" "+player.get(j).getValue().getAge()+
					" "+player.get(j).getValue().getPoints()+" "+player.get(j).getValue().getBounce()+" "+player.get(j).getValue().getAssistance()+" "+player.get(j).getValue().getTheft()+" "+player.get(j).getValue().getBlock()) > 0; j--) {
				NodoBinaryTree<Players,Integer> tempAvlTree = player.get(j);

				player.set(j, player.get(j-1));
				player.set(j-1, tempAvlTree);
			}
		}
		setNewPlayer(binarySearch(player, name+" "+lastName+" "+team+" "+age+" "+points+" "+bounce+" "+assistance+" "+theft+" "+block));
	}

	public NodoBinaryTree<Players,Integer> binarySearch(ArrayList<NodoBinaryTree<Players, Integer>> newList, String toSearch) {

		ArrayList<NodoBinaryTree<Players, Integer>> listPlayersArrayList = newList;

		int pos = -1;
		int i = 0;
		int j = listPlayersArrayList.size()-1;
		NodoBinaryTree<Players, Integer> playerFindPlayers = null;

		while (i <= j && pos < 0) {
			int m = (i+j)/2;
			
			if(listPlayersArrayList.get(m).getValue().compareTo(toSearch) == 0) {
				pos = m;
				playerFindPlayers = listPlayersArrayList.get(pos);
				
			}else if (listPlayersArrayList.get(m).getValue().compareTo(toSearch) > 0) {
				j = m-1;
			}else {
				i = m+1;
			}
		}

		return playerFindPlayers;
	}

	public NodoBinaryTree<Players, Integer> getNewPlayer() {
		return newPlayer;
	}

	public void setNewPlayer(NodoBinaryTree<Players, Integer> newPlayer) {
		this.newPlayer = newPlayer;
	}
}
