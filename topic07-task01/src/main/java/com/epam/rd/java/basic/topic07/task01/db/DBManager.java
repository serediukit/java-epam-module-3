package com.epam.rd.java.basic.topic07.task01.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.epam.rd.java.basic.topic07.task01.Constants;
import com.epam.rd.java.basic.topic07.task01.db.entity.*;

import static com.epam.rd.java.basic.topic07.task01.Constants.FULL_URL;

public class DBManager {

	private static DBManager instance;

	public static synchronized DBManager getInstance() {
		if(instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

//§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§

	public List<User> findAllUsers() throws DBException {
		List<User> userList = new ArrayList<>();
		String url = getUrl();
		try (	Connection con = DriverManager.getConnection(url);
				 PreparedStatement ps = con.prepareStatement(Constants.FIND_ALL_USERS);
		) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				userList.add(mapUser(rs));
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return userList;
	}

	private User mapUser(ResultSet rs) throws SQLException {
		User user = User.createUser(rs.getString("login"));
		user.setId(rs.getInt("id"));
		return user;
	}

	private String getUrl(){
		String url = null;
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("app.properties"));
			url = properties.getProperty("connection.url"); //??? FULL_URL or "connection.url"
		} catch (IOException e) {
			e.printStackTrace();
		}
		return url;
	}

	public List<Team> findAllTeams() throws DBException {
		List<Team> listTeams = new ArrayList<>();
		String url = getUrl();
		try(
				Connection conn = DriverManager.getConnection(url);
				PreparedStatement ps =conn.prepareStatement(Constants.FIND_ALL_TEAMS);
		)
		{
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listTeams.add(mapTeam(rs));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listTeams;
	}

	private Team mapTeam(ResultSet rs) throws SQLException {
		Team team = Team.createTeam(rs.getString("name"));
		team.setId(rs.getInt("id"));
		return team;
	}

	//§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§

	public boolean insertTeam(Team team) throws DBException {
		String url = getUrl();
		try(
				Connection conn = DriverManager.getConnection(url);
				PreparedStatement ps = conn.prepareStatement(Constants.INSERT_TEAM, Statement.RETURN_GENERATED_KEYS)
		)
		{
			ps.setString(1, team.getName());
			int affectedRows = ps.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException ("No rows affected");
			}
			try(ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					team.setId(generatedKeys.getInt(1));
				}
				else {
					throw new SQLException ("Creating teams failed, no ID obtained.");
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return true;
	}


	public boolean insertUser(User user) throws DBException {
		String url = getUrl();
		try(
				Connection con = DriverManager.getConnection(url);
				PreparedStatement ps = con.prepareStatement(Constants.INSERT_USER, Statement.RETURN_GENERATED_KEYS);
		)
		{
			ps.setString(1, user.getLogin());
			int affectedRows = ps.executeUpdate();
			if(affectedRows == 0) {
				throw new SQLException ("No rows affected");
			}
			try(ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if(generatedKeys.next()) {
					user.setId(generatedKeys.getInt(1));
				} else {
					throw new SQLException ("Creating user failed, no ID obtained");
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	//§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§

	public User getUser(String login) throws DBException {
		User user = User.createUser(login);
		try (
				Connection conn = DriverManager.getConnection(getUrl());
				PreparedStatement ps = conn.prepareStatement (Constants.GET_USER);
		)
		{
			ps.setString(1, login);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()) {
				user.setId(resultSet.getInt("id"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public Team getTeam(String name) throws DBException {
		Team team = Team.createTeam(name);
		try(
				Connection conn = DriverManager.getConnection(getUrl());
				PreparedStatement ps = conn.prepareStatement(Constants.GET_TEAM);
		)
		{
			ps.setString(1, name);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()) {
				team.setId(resultSet.getInt("id"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return team;
	}

}