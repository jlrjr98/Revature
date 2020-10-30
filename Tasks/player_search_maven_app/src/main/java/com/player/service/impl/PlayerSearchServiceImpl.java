package com.player.service.impl;

import java.util.List;
import com.player.dao.PlayerSearchDAO;
import com.player.dao.impl.PlayerSearchDAOImpl;
import com.player.exception.BusinessException;
import com.player.model.Player;
import com.player.service.PlayerSearchService;

//This is the logical layer. It validates the user input.
public class PlayerSearchServiceImpl implements PlayerSearchService {

	private PlayerSearchDAO searchDAO = new PlayerSearchDAOImpl();

	@Override
	public Player getPlayerById(int id) throws BusinessException {
		Player player = null;
		
		//checks for validity before sending input
		if (id > 99 && id < 1000) {
			
			//code here for DAO
			player = searchDAO.getPlayerById(id);
		} else {
			throw new BusinessException("Entered Player ID " + id + " is INVALID!!!.. Please ReEnter");
		}
		return player;
	}

	@Override
	public List<Player> getAllPlayers() throws BusinessException {
		List<Player> playersList = null;
		
		//code for DAO
		playersList = searchDAO.getAllPlayers();
		
		return playersList;
	}

	@Override
	public List<Player> getPlayersByName(String name) throws BusinessException {
		List<Player> playersListName = null;		
		
		//checks for validity before sending input
		if(name!=null && name.length() > 0 && name.length() < 30) {
			//code for dao
			playersListName=searchDAO.getPlayersByName(name);
		}else {
			throw new BusinessException("Entered Name "+name+" is INVALID...Please ReEnter");
		}
		playersListName = searchDAO.getPlayersByName(name);
		
		return playersListName;
	}

	@Override
	public List<Player> getPlayersByAge(int age) throws BusinessException {
		List<Player> playersListAge = null;
		
		//checks for validity before sending input
		if(age < 100 && age > 0) {
			//code for DAO
			playersListAge=searchDAO.getPlayersByAge(age);
		}else {
			throw new BusinessException("Entered Name "+age+" is INVALID...Please ReEnter");
		}
		playersListAge = searchDAO.getPlayersByAge(age);
		
		return playersListAge;
	}

	@Override
	public Player getPlayerByContactNumber(long contact) throws BusinessException {
		Player player = null;
		
		//checks for validity before sending input
		if(contact <= 11 && contact > 0) {
			//code for DAO
			player = searchDAO.getPlayerByContactNumber(contact);
		} else {
			throw new BusinessException("Entered Contact " + contact + " is INVALID...Please ReEnter");
		}
		player = searchDAO.getPlayerByContactNumber(contact);
		
		return player;
	}

	@Override
	public List<Player> getPlayersByTeamName(String teamName) throws BusinessException {
		List<Player> playerListTeamName = null;
		
		//checks for validity before sending input
		if (teamName != null && teamName.length() < 0 && teamName.length() < 30) {
			//code for DAO
			playerListTeamName = searchDAO.getPlayersByTeamName(teamName);
		} else {
			throw new BusinessException("Entered Team Name " + teamName + " is INVALID...Please ReEnter");
		}
		
		return playerListTeamName;
	}

	@Override
	public List<Player> getPlayersByGender(String gender) throws BusinessException {
		List<Player> playerListGender=null;
		
		//checks for validity before sending input
		if(gender!=null && gender.matches("[mMFfoO]{1}")) {
			//code for DAO
			playerListGender=searchDAO.getPlayersByGender(gender);
		}else {
			throw new BusinessException("Entered Gender "+gender+" is INVALID...Please ReEnter");
		}
		
		return playerListGender;
	}

}
