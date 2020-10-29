package com.app.jdbc;

import com.app.jdbc.dao.PlayerDAO;
import com.app.jdbc.dao.impl.PlayerDAOImpl;
import com.app.jdbc.exception.BusinessException;
import com.app.jdbc.model.Player;

public class JdbcDemoMain {
	
	public static void main(String[] args) {
		
		//Player player = new Player(909, "Pam", "Tigers", 8853730875L, "F", 30);
		
		//instantiate interface
		PlayerDAO playerDAO = new PlayerDAOImpl();
		
//		try {
//			
//			//playerDAO is variable. createPlayer is method created in PlayerDAO. player is variable.
//			if(playerDAO.createPlayer(player) > 0) {
//				System.out.println("Player Created in DataBase with below details:");
//				
//				//prints newly created player
//				System.out.println(player);
//			}
//		} catch (BusinessException e) {
//			
//			//prints out message written in PlayerDAOImpl Class
//			System.out.println(e.getMessage());
//		}
		
		
		
//		try {
//			int id = 808;
//			long newContact = 1234567899;
//			playerDAO.updatePlayerContact(id, newContact);
//			
//			if (playerDAO.updatePlayerContact(id, newContact) > 0) {
//				System.out.println("Update Complete. Player of id " + id + " now has contact of " + newContact);
//			}
//			else {
//				System.out.println(id + " is an Invalid Id");
//			}
//			
//		} catch (BusinessException e) {
//			System.out.println(e.getMessage());
//		}
		
		
//		try {
//			
//			int id = 211;
//			
//			System.out.println("Deleting " + playerDAO.getPlayerById(id));
//			
//			playerDAO.deletePlayer(id);
//			
//			System.out.println("...\nPlayer deleted");
//			
//		} catch (BusinessException e) {
//			System.out.println(e.getMessage());
//		}
		
		
//		try {
//			
//			int id = 808;
//			Player player= playerDAO.getPlayerById(id);
//			
//			if (player != null) {
//				System.out.println("Player found with id " + id);
//				System.out.println(player);
//			}
//			
//		} catch (BusinessException e) {
//			System.out.println(e.getMessage());
//		}
		
	}

}
