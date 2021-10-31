package thread;

import java.util.ArrayList;

import model.Players;

public class SearchByName extends Thread{

	private ArrayList<Players> player;
	private String name;
	private String lastName;
	private boolean verify;

	public SearchByName(ArrayList<Players> player, String name, String lastName) {
		this.player = player;
		this.name = name;
		this.lastName = lastName;
		verify = false;
	}

	public void run() {
		for (int i = 0; i < player.size(); i++) {

			if(player.get(i).compareToName(name+" "+lastName) == 0) {
				verify = true;
			}
		}
	}

	public boolean getVerify() {
		return verify;
	}

	public void setVerify(boolean verify) {
		this.verify = verify;
	}
}
