package com.app.jdbc.model;
//model reflects real world objects

public class Player {
	
	//create private variables for the data, to be instantiated by constructor
	private int id;
	private String name;
	private String teamName;
	private long contact;
	private String gender;
	private int age;
	
	
	//constructor using superclass
	public Player() {
		//super();
		// TODO Auto-generated constructor stub
	}

	//Constructor for initializing variables
	public Player(int id, String name, String teamName, long contact, String gender, int age) {
		super();
		this.id = id;
		this.name = name;
		this.teamName = teamName;
		this.contact = contact;
		this.gender = gender;
		this.age = age;
	}

	//setters and getters
	//the getters are called later to retrieve variables
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	//Override toString so that JdbcMain prints out player properly.
	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", teamName=" + teamName + ", contact=" + contact + ", gender="
				+ gender + ", age=" + age + "]";
	}

}
