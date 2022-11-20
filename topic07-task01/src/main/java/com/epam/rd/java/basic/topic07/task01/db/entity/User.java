package com.epam.rd.java.basic.topic07.task01.db.entity;

public class User {

	private int id;

	private String login;

	public User(int id, String login) {
		this.id = id;
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	@Override
	public String toString() {
		return login;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		User user = (User) obj;
		return login.equals(user.getLogin());
	}

	public static User createUser(String login) {
		return new User(0, login);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}