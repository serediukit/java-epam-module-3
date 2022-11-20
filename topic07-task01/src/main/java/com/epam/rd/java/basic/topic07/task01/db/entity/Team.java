package com.epam.rd.java.basic.topic07.task01.db.entity;

public class Team {

	private int id;
		
	private String name;

	public Team(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Team team = (Team) obj;
		return name.equals(team.getName());
	}

	public static Team createTeam(String name) {
		return new Team(0, name);
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}