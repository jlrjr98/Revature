package com.app.bank.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.bank.dao.BankSearchDAO;
import com.app.bank.dao.Impl.BankSearchDAOImpl;
import com.app.bank.exception.BusinessException;
import com.app.bank.main.BankAppMain;
import com.app.bank.model.Account;
import com.app.bank.model.Employee;
import com.app.bank.model.TransactionLog;
import com.app.bank.service.BankSearchService;
import com.app.bank.util.RandomId;

public class BankSearchServiceImpl implements BankSearchService {
	
	private BankSearchDAO bankSearchDAO = new BankSearchDAOImpl();	
	public static Logger log = Logger.getLogger(BankAppMain.class);
	private RandomId randomId = new RandomId();

	@Override
	public List<Account> getAccount(String user_id, String user_password) throws BusinessException {
		
		List<Account> accountList = null;
			
		if (user_id != null && user_password != null && user_password.length() > 0 && user_id.length() == 4) {
			accountList = bankSearchDAO.getAccountByUserIdAndPassword(user_id, user_password);	
		} else {
			log.warn("Sorry. USER ID is INVALID.\n");
			throw new BusinessException("Exception in BankSearchServiceImpl.getAccount. USER ID and/or USER PASSWORD invalid.\n");
		}			
		return accountList;
		}

	@Override
	public BigDecimal getBalance(int account_id) throws BusinessException {
		
		BigDecimal balance = new BigDecimal(0);
		
		if (account_id > 1000 && account_id < 9999) {			
			balance = bankSearchDAO.getBalanceByAccountId(account_id);			
		} else {
			log.warn("Sorry. ACCOUNT ID is INVALID.");
			throw new BusinessException("Exception in BankSearchServiceImpl.getTransfer. ACCOUNT ID wrong format.\n");
		}		
		return balance;
	}
	
	@Override
	public BigDecimal getTransfer(int transfer_account_id) throws BusinessException {
		
		BigDecimal transfer = new BigDecimal(0);
		
		if (transfer_account_id >= 1000 && transfer_account_id <= 9999) {			
			transfer = bankSearchDAO.getTransferFromTransferAccountId(transfer_account_id);			
		} else {
			log.warn("Sorry. Entered ACCOUNT ID is INVALD.\n");
			throw new BusinessException("Exception in BankSearchServiceImpl.getTransfer. TRANSFER ACCOUNT ID wrong format.\n");
		}
		return transfer;
	}	

	@Override
	public int verifyAccountId(String user_id, int account_id) throws BusinessException{
		
		int verify_account_id = 0;
		
		if (user_id.length() == 4 && account_id >= 1000 && account_id <= 9999) {			
			verify_account_id = bankSearchDAO.getAccountIdByUserIdAndAccountId(user_id, account_id);			
		} else {
			log.warn("Sorry, USER ID and/or ACCOUNT ID are INVALD");
			throw new BusinessException("Exception in BankSearchServiceImpl.verifyAccountId. USER ID and/or ACCOUNT ID wrong format.\n");
		}				
		return verify_account_id;
	}

	@Override
	public List<Integer> verifyAccountId(String user_id) throws BusinessException {
		
		List<Integer> account_id_list = new ArrayList<>();
		
		if (user_id.length() == 4) {		
			account_id_list = bankSearchDAO.getAccountIdByUserId(user_id);			
		} else {
			log.warn("Sorry. USER ID is INVALID.\n");
			throw new BusinessException("Exception in BankSearchServiceImpl.verifyAccountId. USER ID wrong format.\n");
		}				
		return account_id_list;
	}
	
	@Override
	public int verifyAccountId(int account_id) throws BusinessException {
		
		int verify_account_id = 0;
		
		if (account_id >= 1000 && account_id <= 9999) {
			verify_account_id = bankSearchDAO.getAccountIdByAccountId(account_id);
		} else {
			log.warn("Sorry. ACCOUNT ID is INVALID.\n");
			throw new BusinessException("Exception in BankSearchServiceImpl.verifyAccountId. ACCOUNT ID wrong format.\n");
		}		
		return verify_account_id;
	}
	
	@Override
	public int verifyTransferAccountId(String user_id, int transfer_account_id) throws BusinessException {
		
		int verify_transfer_account_id = 0;
		
		if (transfer_account_id >= 1000 && transfer_account_id <= 9999) {			
			verify_transfer_account_id = bankSearchDAO.getTransferAccountIdByUserIdAndTransferAccountId(user_id, transfer_account_id);			
		} else {
			log.warn("Sorry. Entered ACCOUNT ID is INVALID.\n");
			throw new BusinessException("Exception in BankSearchServiceImpl.verifyTransferAccountId. TRANSFER ACCOUNT ID wrong format.\n");
		}
		return verify_transfer_account_id;
	}

	@Override
	public String verifyUserId(int account_id) throws BusinessException {
		
		String user_id = null;
		
		if (account_id >= 1000 && account_id <= 9999 ) {			
			user_id = bankSearchDAO.getUserIdByAccountId(account_id);			
		} else {
			log.warn("Sorry. ACCOUNT ID is INVALID.\n");
			throw new BusinessException("Exception in BankSearchServiceImpl.verifyUserId. ACCOUNT ID wrong format.\n");
		}		
		return user_id;
	}

	@Override
	public String verifyUserId(String last_name, String user_password) throws BusinessException {
		
		String user_id = null;
		
		if (user_password != null && user_password.length() > 0 && user_password.length() <= 15 && user_password.matches("[\s]") ==false) {
			user_id = bankSearchDAO.getUserIdByLastNameAndPassword(last_name, user_password);
		} else {
			log.warn("Sorry. Entered PASSWORD is INVALID");
			throw new BusinessException("Exception in BankSearchServiceImpl.verifyUserId. USER PASSWORD wrong format.\n");
		}				
		return user_id;
	}
	
	@Override
	public String verifyUserId(String user_id) throws BusinessException {
		
		String verify_user_id = null;
		
		if (user_id.length() == 4) {			
			verify_user_id = bankSearchDAO.getUserIdByUserId(user_id);			
		} else {
			log.warn("Sorry. USER ID is INVALID.\n");
			throw new BusinessException("Exception in BankSearchServiceImpl.verifyUserId. USER ID wrong format.\n"); 
		}		
		return verify_user_id;
	}
	
	@Override
	public String getRandomUserId() throws BusinessException {
		
		String user_id = null;
		
		do {
			user_id = randomId.randomUserId();
		} while (bankSearchDAO.getUserIdByUserId(user_id) != null);	
		
		return user_id;
	}
	
	@Override
	public int getRandomAccountId() throws BusinessException {
		
		int account_id = 0;
		
		do {
			account_id = randomId.randomAccountId();
		} while (verifyAccountId(account_id) != 0);		
		return account_id;
	}	
	
	@Override
	public Employee getEmployee(String employee_id, String employee_password) throws BusinessException {
		
		Employee employee = null;
		
		if (employee_id.length() == 5) {
			if (employee_password.length() > 0) {
				employee = bankSearchDAO.getEmployeeByEmployeeIdAndPassword(employee_id, employee_password);
			} else {
				log.warn("Sorry. PASSWORD is INVALID.\n");
				throw new BusinessException("Exception in BankSearchServiceImpl.getEmployee. EMPLOYEE PASSWORD is 0 in length.\n");
			}
		} else {
			log.warn("Sorry. EMPLOYEE ID is INVALID.\n");
			throw new BusinessException("Exception in BankSearchServiceImpl.getEmployee. EMPLOYEE ID wrong format.\n");
		}		
		return employee;
	}

	@Override
	public List<TransactionLog> getTransactionLogList() throws BusinessException {
		
		List<TransactionLog> transactionLogList = new ArrayList<>();
		
		transactionLogList = bankSearchDAO.getTransactionLogList();				
		return transactionLogList;
	}

	@Override
	public List<Account> getAllAccounts() throws BusinessException {
		
		List<Account> account_list = new ArrayList<>();
		
		account_list = bankSearchDAO.getAllAccounts();		
		return account_list;
	}	
}
