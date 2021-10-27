package model;

import java.io.Serializable;

public class Players implements Serializable{
	private static final long serialVersionUID = 1L;
	String name;
	String lastName;
	int age;
	String team;
	int bounce;
	int assistance;
	int theft;
	int block;
	int points;
	
	public Players(String name, String lastName, int age, String team, int bounce, int assistance, int theft, int block, int points) {
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.team = team;
		this.bounce = bounce;
		this.assistance = assistance;
		this.theft = theft;
		this.block = block;
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public int getTheft() {
		return theft;
	}

	public void setTheft(int theft) {
		this.theft = theft;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
