package com.app.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.jdbc.dao.PlayerDAO;
import com.app.jdbc.dbutil.PostreSqlConnection;
import com.app.jdbc.exception.BusinessException;
import com.app.jdbc.model.Player;

//implementer for the PlayerDAO interface
public class PlayerDAOImpl implements PlayerDAO {

	//Override the abstract methods from PlayerDAO with bodies
	@Override
	public int createPlayer(Player player) throws BusinessException {
		int c = 0;
		
		//try with resources takes care of step 6. The JVM will close the connection automatically
		try (Connection connection = PostreSqlConnection.getConnection()) {
			
			//create SELECT statement for PostreSQL
			String sql = "INSERT INTO roc_revature.player(id, name, age, gender, teamname, contact) VALUES(?,?,?,?,?,?)";
																						//A question mark for each column
			
			//Instantiate PreparedStatement with the query
			//compiles here, so at execution, you don't need to pass a query, it just runs it.
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			//replace ? with their values
			//1 refers to the 1st ?
			//the id is with the player object. Call the getters from Player Class.
			preparedStatement.setInt(1, player.getId());
			preparedStatement.setString(2, player.getName());
			preparedStatement.setInt(3, player.getAge());
			preparedStatement.setString(4, player.getGender());
			preparedStatement.setString(5, player.getTeamName());
			preparedStatement.setLong(6, player.getContact());
			
			//executes
			//doesn't need query here because it was already supplied.
			c = preparedStatement.executeUpdate();
			
			//choose to Add Multi-Catch Block
			//Both exceptionsmust be child Classes
		} catch (ClassNotFoundException | SQLException e) {
			//Don't use e.printStackTrace();
			
			System.out.println(e); //take off this line when in production
			
			//calling method from BusinessException Class and adding a message. 
			throw new BusinessException("Internal Error has Occurred.");
		}
		
		//execution was placed in variable c.
		return c;
	}

	@Override
	public int updatePlayerContact(int id, long newContact) throws BusinessException {
		int c = 0;
		
		try (Connection connection = PostreSqlConnection.getConnection()) {
			
			//create UPDATE statement for PostreSQL
			String sql = "UPDATE roc_revature.player SET contact = ? WHERE id = ?";
																						
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setLong(1, newContact);
			preparedStatement.setInt(2, id);
			
			c = preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println(e); 
			
			throw new BusinessException("Internal Error has Occurred.");
		}
		
		//execution was placed in variable c.
		return c;
	}

	@Override
	public void deletePlayer(int id) throws BusinessException {
		
		
		
		try (Connection connection = PostreSqlConnection.getConnection()) {
			
			String sql = "DELETE FROM roc_revature.player WHERE id = ?";
																						
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println(e);
			 
			throw new BusinessException("Internal Error has Occurred.");
		}

		
	}

	@Override
	public Player getPlayerById(int id) throws BusinessException {
		//Don't create player object until after search is done.
		Player player = null;
		
		try (Connection connection = PostreSqlConnection.getConnection()) {
			
			//create SELECT statement for PostreSQL
			String sql = "SELECT name, age, gender, teamname, contact FROM roc_revature.player WHERE id = ?";																					
			
			//Instantiate PreparedStatement with the query
			//compiles here, so at execution, you don't need to pass a query, it just runs it.
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			//just id, instead of getId, because 
			preparedStatement.setInt(1, id);
			
			//Create resultSet object, because it's a Query
			//change executeUpdate to executeQuery
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				
				//only if the player exists do we create a player object.
				//don't need to get id since that's what's being put in.
				player = new Player(id, resultSet.getString("name"), resultSet.getString("teamName"), resultSet.getLong("contact"), resultSet.getString("gender"), resultSet.getInt("age"));
			}
			else {
				throw new BusinessException(id + " is an Invalid Id");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println(e); 
			throw new BusinessException("Internal Error has Occurred.");
		}
		
		return player;
	}
	
	

}
