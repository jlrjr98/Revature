package com.app.bank.dao.Impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.bank.model.Account;
import com.app.bank.model.Employee;
import com.app.bank.model.TransactionLog;
import com.app.bank.dao.BankSearchDAO;
import com.app.bank.dao.dbutil.BankConnection;
import com.app.bank.dao.dbutil.queries.BankSearchQueries;
import com.app.bank.exception.BusinessException;

public class BankSearchDAOImpl implements BankSearchDAO {
	
	public static Logger log = Logger.getLogger(BankSearchDAOImpl.class);

	@Override
	public List<Account> getAccountByUserIdAndPassword(String user_id, String user_password) throws BusinessException {
		
		List<Account> accountList = new ArrayList<>();
		
		try(Connection connection = BankConnection.getConnection()) {
			
			String sql = BankSearchQueries.GET_ACCOUNT_BY_USER_ID_AND_PASSWORD;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user_id);
			preparedStatement.setString(2, user_password);		
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Account account = new Account(user_id, resultSet.getString("first_name"), resultSet.getString("last_name"), 
						resultSet.getInt("account_id"), resultSet.getBigDecimal("balance"), resultSet.getBigDecimal("transfer"), 
						resultSet.getInt("transfer_account_id"));
				accountList.add(account);
			} 						
			
			if (accountList.size() == 0) {
				log.warn("Sorry. USER ID and/or PASSWORD is INVALID.\n");
				throw new BusinessException("Exception in BankSearchDAOImpl.getAccountByUserIdAndPassword. ACCOUNT LIST size is 0.\n");
			}			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankSearchDAOImpl.getAccountByUserIdAndPassword. DataBase connection issue.\n");
		}		
		return accountList;
	}

	@Override
	public BigDecimal getBalanceByAccountId(int account_id) throws BusinessException {
		
		BigDecimal balance = null;
		
		try (Connection connection = BankConnection.getConnection()) {
				String sql = BankSearchQueries.GET_BALANCE_BY_ACCOUNT_ID;
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1,  account_id);
		
				ResultSet resultSet = preparedStatement.executeQuery();
				
				if (resultSet.next()) {
					balance = (resultSet.getBigDecimal("balance"));
				}				
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankSearchDAOImpl.getBalanceByAccountId. DataBase connection issue.\n");
		}		
		return balance;
	}

	@Override
	public int getAccountIdByUserIdAndAccountId(String user_id, int account_id) throws BusinessException {
		
		int verify_account_id = 0;
		
		try (Connection connection = BankConnection.getConnection()) {
			
			String sql = BankSearchQueries.GET_ACCOUNT_ID_BY_USER_ID_AND_ACCOUNT_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user_id);
			preparedStatement.setInt(2, account_id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				verify_account_id = resultSet.getInt("account_id");
			}			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankSearchDAOImpl.getAccountIdByUserIdAndAccountId. DataBase connection issue.\n");
		}				
		return verify_account_id;
	}

	@Override
	public BigDecimal getTransferFromTransferAccountId(int transfer_account_id) throws BusinessException {
		
		BigDecimal transfer_recieve = null;
		
		try (Connection connection = BankConnection.getConnection()) {
			
			String sql = BankSearchQueries.GET_TRANSFER_RECIEVE_FROM_TRANSFER_ACCOUNT_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, transfer_account_id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				transfer_recieve = resultSet.getBigDecimal("transfer");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankSearchDAOImpl.getTransferFromTransferAccountId. DataBase connection issue.\n");
		}
		return transfer_recieve;
	}

	@Override
	public int getTransferAccountIdByUserIdAndTransferAccountId(String user_id, int transfer_account_id) throws BusinessException {
		
		int verify_transfer_account_id = 0;
		
		try (Connection connection = BankConnection.getConnection()) {
			
			String sql = BankSearchQueries.GET_TRANSFER_ACCOUNT_ID_BY_USER_ID_AND_TRANSFER_ACCOUNT_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user_id);
			preparedStatement.setInt(2, transfer_account_id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				verify_transfer_account_id = resultSet.getInt("transfer_account_id");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankSearchDAOImpl.getTransferAccountIdByUserIdAndTransferAccountId. DataBase connection issue.\n");
		}		
		return verify_transfer_account_id;
	}

	@Override
	public String getUserIdByAccountId(int account_id) throws BusinessException {
		
		String user_id = null;
		
		try (Connection connection = BankConnection.getConnection()) {
			
			String sql = BankSearchQueries.GET_USER_ID_BY_ACCOUNT_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, account_id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				user_id = resultSet.getString("user_id");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankSearchDAOImpl.getUserIdByAccountId. DataBase connection issue.\n");
		}		
		return user_id;
	}
	
	@Override
	public String getUserIdByUserId(String user_id) throws BusinessException {
		
		String verify_user_id = null;
		
		try (Connection connection = BankConnection.getConnection()) {
			
			String sql = BankSearchQueries.GET_USER_ID_BY_USER_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				verify_user_id = resultSet.getString("user_id");
			} 			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankSearchDAOImpl.getUserIfByUserId. DataBase connection issue.\n");
		}		
		return verify_user_id;
	}

	@Override
	public int getAccountIdByAccountId(int account_id) throws BusinessException {
		
		int verify_account_id = 0;
		
		try (Connection connection = BankConnection.getConnection()) {
			
			String sql = BankSearchQueries.GET_ACCOUNT_ID_BY_ACCOUNT_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, account_id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				verify_account_id = resultSet.getInt("account_id");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankSearchDAOImpl.getAccountIdByAccountId. DataBase connection issue.\n");
		}		
		return verify_account_id;
	}

	@Override
	public String getUserIdByLastNameAndPassword(String last_name, String user_password) throws BusinessException {
		
		String user_id = null;
		
		try (Connection connection = BankConnection.getConnection()) {
			
			String sql = BankSearchQueries.GET_USER_ID_BY_LAST_NAME_AND_PASSWORD;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, last_name);
			preparedStatement.setString(2, user_password);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				user_id = resultSet.getString("user_id");
			} 
			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankSearchDAOImpl.getUserIdByLastNameAndPassword. DataBase connection issue.\n");
		}		
		return user_id;
	}

	@Override
	public Employee getEmployeeByEmployeeIdAndPassword(String employee_id, String employee_password) throws BusinessException {
		
		Employee employee = null;
		
		try (Connection connection = BankConnection.getConnection()) {
			
			String sql = BankSearchQueries.GET_EMPLOYEE_BY_EMPLOYEE_ID_AND_PASSWORD;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, employee_id);
			preparedStatement.setString(2, employee_password);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				employee = new Employee(resultSet.getString("employee_id"), resultSet.getString("first_name"), 
						resultSet.getString("last_name"));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankSearchDAOImpl.getEmployeeByEmployeeIdAndPassword. DataBase connection issue.\n");
		}		
		return employee;
	}

	@Override
	public List<Integer> getAccountIdByUserId(String user_id) throws BusinessException {
		
		List<Integer> account_id_list = new ArrayList<>();
		
		try (Connection connection = BankConnection.getConnection()) {
			
			String sql = BankSearchQueries.GET_ACCOUNT_ID_BY_USER_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user_id);
			
			ResultSet resultSet = preparedStatement.executeQuery();						
			
			while (resultSet.next()) {
				Integer account_id = resultSet.getInt("account_id");
				account_id_list.add(account_id);
			}		
			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankSearchDAOImpl.getAccountIdByUserId. DataBase connection issue.\n");
		}
		return account_id_list;
	}

	@Override
	public List<TransactionLog> getTransactionLogList() throws BusinessException {
		
		List<TransactionLog> transaction_log_list = new ArrayList<>();
		
		try (Connection connection = BankConnection.getConnection()) {
			String sql = BankSearchQueries.GET_TRANSACTION_LOG_LIST;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			ResultSet resultSet = preparedStatement.executeQuery();		
			
			while (resultSet.next()) {
				TransactionLog transaction_log = new TransactionLog(resultSet.getTimestamp("time_stamp"), 
						resultSet.getString("user_subject"), resultSet.getInt("account_subject"), 
						resultSet.getString("transaction_action"), resultSet.getBigDecimal("funds"), 
						resultSet.getString("user_object"), resultSet.getInt("account_object"));
				transaction_log_list.add(transaction_log);
			} 	
			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankSearchDAOImpl.getTransactionLogList. DataBase connection issue.\n");
		}		
		return transaction_log_list;
	}

	@Override
	public List<Account> getAllAccounts() throws BusinessException {
		
		List<Account> account_list = new ArrayList<>();

		try (Connection connection = BankConnection.getConnection()) {
			
			String sql = BankSearchQueries.GET_ALL_ACCOUNTS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Account account = new Account(resultSet.getString("user_id"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getInt("account_id"), resultSet.getBigDecimal("balance"), 
						resultSet.getBigDecimal("transfer"), resultSet.getInt("transfer_account_id"));
				account_list.add(account);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e.getMessage());
			log.error("An INTERNAL ERROR has occurred. Please try again later.\n");
			throw new BusinessException("Exception in BankSearchDAOImpl.getAllAccounts. DataBase connection issue.\n");
		}		
		return account_list;
	}

}
