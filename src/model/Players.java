package model;

public class Players {
	
	String name;
	String lastName;
	int age;
	String team;
	String bounce;
	String assistance;
	String theft;
	String block;
	String points;
	
	public Players(String name, String lastName, int age, String team, String bounce, String assistance, String theft, String block, String points) {
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

	public String getBounce() {
		return bounce;
	}

	public void setBounce(String bounce) {
		this.bounce = bounce;
	}

	public String getAssistance() {
		return assistance;
	}

	public void setAssistance(String assistance) {
		this.assistance = assistance;
	}

	public String getTheft() {
		return theft;
	}

	public void setTheft(String theft) {
		this.theft = theft;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}
}
