package com.app.bank.dao.Impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.app.bank.dao.BankUpdateDAO;
import com.app.bank.dao.dbutil.BankConnection;
import com.app.bank.dao.dbutil.queries.BankUpdateQueries;
import com.app.bank.exception.BusinessException;

public class BankUpdateDAOImpl implements BankUpdateDAO {
	
	public static Logger log = Logger.getLogger(BankUpdateDAOImpl.class);

	@Override
	public void withdrawFromAccount(BigDecimal withdrawal, int account_id) throws BusinessException {
		//Account account = null;
		
		try (Connection connection = BankConnection.getConnection()) {
			
			String sql = BankUpdateQueries.WITHDRAW_FROM_ACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBigDecimal(1, withdrawal);
			preparedStatement.setInt(2, account_id);
			
			preparedStatement.executeUpdate();			
			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankUpdateDAOImpl.withdrawFromAccount. DataBase connection issue.\n");
		}
		
		//return account;
	}

	@Override
	public void depositIntoAccount(BigDecimal deposit, int account_id) throws BusinessException {
		
		try (Connection connection = BankConnection.getConnection()) {
			
			String sql = BankUpdateQueries.DEPOSIT_INTO_ACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBigDecimal(1, deposit);
			preparedStatement.setInt(2, account_id);
			
			preparedStatement.executeUpdate();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankUpdateDAOImpl.depositIntoAccount. DataBase connection issue.\n");
		}
	}

	@Override
	public void postTransferToAccount(int transfer_to_account_id, int transfer_from_account_id, BigDecimal transfer) throws BusinessException {
		
		try (Connection connection = BankConnection.getConnection()) {
			
			String sql = BankUpdateQueries.POST_TRANSFER_TO_ACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBigDecimal(1, transfer);
			preparedStatement.setInt(2, transfer_from_account_id);
			preparedStatement.setInt(3, transfer_to_account_id);
			
			preparedStatement.executeUpdate();		
			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankUpdateDAOImpl.postTransferToAccount. DataBase connection issue.\n");
		}
		
	}

	@Override
	public void removePostedTransferFromBalance(int transfer_from_account_id, BigDecimal transfer) throws BusinessException {
		
	try (Connection connection = BankConnection.getConnection()) {
		
		String sql = BankUpdateQueries.REMOVE_POSTED_TRANSFER_FROM_BALANCE;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setBigDecimal(1, transfer);
		preparedStatement.setInt(2, transfer_from_account_id);
		
		preparedStatement.executeUpdate();
		
	} catch (ClassNotFoundException | SQLException e) {
		log.debug(e.getMessage());
		log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
		throw new BusinessException("Exception in BankUpdateDAOImpl.removePostedTransferFromBalance. DataBase connection issue.\n");
	}
		
	}

	@Override
	public void withdrawFromTransferAccount(int transfer_account_id) throws BusinessException {
		
		try (Connection connection = BankConnection.getConnection()) {
			
			String sql = BankUpdateQueries.WITHDRAW_FROM_TRANSFER_ACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, transfer_account_id);
			
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankUpdateDAOImpl.withdrawFromTransferAccount. DataBase connection issue.\n");
		}		
		
	}
		
}
