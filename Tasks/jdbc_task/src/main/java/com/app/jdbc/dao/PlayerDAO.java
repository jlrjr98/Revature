package com.app.jdbc.dao;

import com.app.jdbc.exception.BusinessException;
import com.app.jdbc.model.Player;
//because it's the PlayerDAO, must import Player

//for Abstraction
public interface PlayerDAO {
//DAO - Data Access Object
//DAO represents all the code which is related to the database or persistance or storage will be inside this.
	
	
	//create abstract methods of actions to perform
	//add throws BusinessException because in order for the Implementer to override these methods, the signiture must be the same.
	public int createPlayer(Player player) throws BusinessException;
	public int updatePlayerContact(int id, long newContact) throws BusinessException;
	public void deletePlayer(int id) throws BusinessException;
	
	//Player Class as return type because that is where the player lives
	public Player getPlayerById(int id) throws BusinessException;

}
