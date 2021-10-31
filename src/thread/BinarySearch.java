package thread;

import java.util.ArrayList;

import model.Players;

public class BinarySearch extends Thread{

	private ArrayList<Players> player;
	private Players newPlayer;
	private String name;
	private String lastName;
	private int age;
	private String team;
	private int points;
	private int bounce;
	private int assistance;
	private int block;
	private int theft;

	public BinarySearch(ArrayList<Players> player, String name, String lastName, int age, String team, int points, int bounce, int assistance, int theft, int block) {
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
			for (int j = i; j > 0 && player.get(j-1).compareTo(player.get(j).getName()+" "+player.get(j).getLastName()+" "+player.get(j).getTeam()+" "+player.get(j).getAge()+
					" "+player.get(j).getPoints()+" "+player.get(j).getBounce()+" "+player.get(j).getAssistance()+" "+player.get(j).getTheft()+" "+player.get(j).getBlock()) > 0; j--) {
				Players tempAvlTree = player.get(j);

				player.set(j, player.get(j-1));
				player.set(j-1, tempAvlTree);
			}
		}
		newPlayer = binarySearchDel(player, name+" "+lastName+" "+team+" "+age+" "+points+" "+bounce+" "+assistance+" "+theft+" "+block);
	}

	public Players binarySearchDel(ArrayList<Players> newList, String toSearch) {

		ArrayList<Players> listPlayersArrayList = newList;

		int pos = -1;
		int i = 0;
		int j = listPlayersArrayList.size()-1;
		Players playerFindPlayers = null;

		while (i <= j && pos < 0) {
			int m = (i+j)/2;
			if(listPlayersArrayList.get(m).compareTo(toSearch) == 0) {
				pos = m;
				playerFindPlayers = listPlayersArrayList.get(pos);
			}else if (listPlayersArrayList.get(m).compareTo(toSearch) > 0) {
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

	public ArrayList<Players> getPlayer() {
		return player;
	}

	public void setPlayer(ArrayList<Players> player) {
		this.player = player;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getBounce() {
		return bounce;
	}

	public void setBounce(int bounce) {
		this.bounce = bounce;
	}

	public int getAssistance() {
		return assistance;
	}

	public void setAssistance(int assistance) {
		this.assistance = assistance;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	public int getTheft() {
		return theft;
	}

	public void setTheft(int theft) {
		this.theft = theft;
	}

	public void setNewPlayer(Players newPlayer) {
		this.newPlayer = newPlayer;
	}
}
