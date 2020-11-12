package com.app.bank.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.bank.dao.BankDeleteDAO;
import com.app.bank.dao.dbutil.BankConnection;
import com.app.bank.dao.dbutil.queries.BankDeleteQueries;
//import com.app.bank.dao.dbutil.queries.BankUpdateQueries;
import com.app.bank.exception.BusinessException;
import com.app.bank.main.BankAppMain;

public class BankDeleteDAOImpl implements BankDeleteDAO {
	
	public static Logger log = Logger.getLogger(BankAppMain.class);
	
	@Override
	public void deleteUserInUserTable(String user_id) throws BusinessException {
		
		try (Connection connection = BankConnection.getConnection()) {
			
			String sql = BankDeleteQueries.DELETE_USER_IN_USER_TABLE;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user_id);			
			
			preparedStatement.executeUpdate();						
			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankDeleteDAOImpl.deleteUserInUserTable. DataBase connection issue.\n");
		}		
	}

	@Override
	public void deleteUserInAccountTable(String user_id) throws BusinessException {
		
		try (Connection connection = BankConnection.getConnection()) {
			
			String sql = BankDeleteQueries.DELETE_USER_IN_ACCOUNT_TABLE;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user_id);			
			
			preparedStatement.executeUpdate();						
			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankDeleteDAOImpl.deleteUserInAccountTable. DataBase connection issue.\n");
		}		
	}

	@Override
	public void deleteAccountListInTransferTable(List<Integer> account_id_list) throws BusinessException {		
		
		for (int i = 0; i < account_id_list.size(); i++) {
			
			int account_id = account_id_list.get(i);
			
			try (Connection connection = BankConnection.getConnection()) {
				
				String sql = BankDeleteQueries.DELETE_ACCOUNT_IN_TRANSFER_TABLE;
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, account_id);
				
				preparedStatement.executeUpdate();
								
			} catch (ClassNotFoundException | SQLException e) {
				log.debug(e.getMessage());
				log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
				throw new BusinessException("Exception in BankDeleteDAOImpl.deleteAccountListInTransferTable. DataBase connection issue.\n");
			}			
		}
	}

	@Override
	public void deleteAccountInAccountTable(int account_id) throws BusinessException {		
		
		try (Connection connection = BankConnection.getConnection()) {
			
			String sql = BankDeleteQueries.DELETE_ACCOUNT_IN_ACCOUNT_TABLE;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, account_id);
			
			preparedStatement.executeUpdate();
							
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankDeleteDAOImpl.deleteAccountInAccountTable. DataBase connection issue.\n");
		}				
	}		
}
