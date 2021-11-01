package thread;

import java.util.ArrayList;

import model.BasketballData;
import model.Players;

public class SecondPartImport extends Thread{

	private BasketballData basketballData;
	private ArrayList<Players> newList;
	private int initial;
	private int finall;

	public SecondPartImport(BasketballData basketballData, ArrayList<Players> newList, int initial, int finall) {
		this.basketballData = basketballData;
		this.newList = newList;
		this.initial = initial;
		this.finall = finall;
	}

	public void run() {
		for (int i = initial; i < newList.size() && i < finall; i++) {
			basketballData.addPlayer(newList.get(i).getName(), newList.get(i).getLastName(), newList.get(i).getTeam(), newList.get(i).getAge(), newList.get(i).getPoints(), newList.get(i).getBounce(), newList.get(i).getAssistance(), newList.get(i).getTheft(), newList.get(i).getBlock());
		}
	}
}
