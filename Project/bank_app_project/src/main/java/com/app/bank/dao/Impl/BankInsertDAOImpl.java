package com.app.bank.dao.Impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.app.bank.dao.BankInsertDAO;
import com.app.bank.dao.dbutil.BankConnection;
import com.app.bank.dao.dbutil.queries.BankInsertQueries;
import com.app.bank.exception.BusinessException;
import com.app.bank.model.TransactionLog;

public class BankInsertDAOImpl implements BankInsertDAO {
	
	public static Logger log = Logger.getLogger(BankUpdateDAOImpl.class);

	@Override
	public void insertNewAccountInUserTable(String first_name, String last_name, String user_password, String user_id) throws BusinessException {
		
		try (Connection connection = BankConnection.getConnection()) {
			
			String sql = BankInsertQueries.INSERT_ACCOUNT_INTO_USER_TABLE;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user_id);
			preparedStatement.setString(2, first_name);
			preparedStatement.setString(3, last_name);
			preparedStatement.setString(4, user_password);
			
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankInsertDAOImpl.insertNewAccountInUserTable. DataBase connection issue.\n");
		}		
	}
	
	@Override
	public void insertNewAccountInAccountTable(int account_id, BigDecimal balance, String user_id) throws BusinessException {
		
		try (Connection connection = BankConnection.getConnection()) {
			
			String sql = BankInsertQueries.INSERT_ACCOUNT_INTO_ACCOUNT_TABLE;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, account_id);
			preparedStatement.setBigDecimal(2, balance);
			preparedStatement.setString(3, user_id);
			
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankInsertDAOImpl.insertNewAccountInAccountTable. DataBase connection issue.\n");
		}		
	}

	@Override
	public void insertNewAccountInTransferTable(int account_id) throws BusinessException {
		
		try (Connection connection = BankConnection.getConnection()) {
			
			String sql = BankInsertQueries.INSERT_ACCOUNT_INTO_TRANSFER_TABLE;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, account_id);
			
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankInsertDAOImpl.insertNewAccountInTransferTable. DataBase connection issue.\n");
		}		
	}
	
	@Override
	public void insertTransactionLog(TransactionLog transactionLog) throws BusinessException {		
		
		try (Connection connection = BankConnection.getConnection()) {
			
			String sql = BankInsertQueries.INSERT_TRANSACTION_LOG;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setTimestamp(1, new java.sql.Timestamp(transactionLog.gettimestamp().getTime()));
			preparedStatement.setString(2, transactionLog.getUser_subject());
			preparedStatement.setInt(3, transactionLog.getAccount_subject());
			preparedStatement.setString(4, transactionLog.getTransaction_action());
			preparedStatement.setBigDecimal(5, transactionLog.getFunds());
			preparedStatement.setString(6, transactionLog.getUser_object());
			preparedStatement.setInt(7, transactionLog.getAccount_object());
			
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankInsertDAOImpl.insertTransactionLog. DataBase connection issue.\n");
		}				
	}
}
