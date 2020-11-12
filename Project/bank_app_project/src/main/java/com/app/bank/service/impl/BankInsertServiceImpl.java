package com.app.bank.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;

import com.app.bank.dao.BankInsertDAO;
import com.app.bank.dao.Impl.BankInsertDAOImpl;
import com.app.bank.exception.BusinessException;
import com.app.bank.main.BankAppMain;
import com.app.bank.model.TransactionLog;
import com.app.bank.service.BankInsertService;
import com.app.bank.service.BankSearchService;

public class BankInsertServiceImpl implements BankInsertService {

	private BankInsertDAO bankInsertDAO = new BankInsertDAOImpl();
	private BankSearchService bankSearchService = new BankSearchServiceImpl();
	public static Logger log = Logger.getLogger(BankAppMain.class);
	TransactionLog transactionLog = null;
	
	@Override
	public void createNewAccount(String first_name, String last_name, String user_password, BigDecimal balance) throws BusinessException {
								
		if (first_name != null && first_name.length() > 0 && first_name.length() <= 15 && first_name.matches("[0-9]") == false && first_name.matches("[\s]") == false) {
			if (last_name != null && last_name.length() > 0 && last_name.length() <= 15 && last_name.matches("[0-9]") == false && last_name.matches("[\s]") == false) {
				if (user_password != null && user_password.length() > 0 && user_password.length() <= 15 && user_password.matches("[\s]") ==false) {
					if (balance.floatValue() > 0) {
						String user_id = bankSearchService.getRandomUserId();
						int account_id = bankSearchService.getRandomAccountId();
						
						bankInsertDAO.insertNewAccountInUserTable(first_name, last_name, user_password, user_id);
						bankInsertDAO.insertNewAccountInAccountTable(account_id, balance, user_id);
						bankInsertDAO.insertNewAccountInTransferTable(account_id);
					} else {
						log.warn("Sorry. Entered BALANCE is INVALID.\n");
						throw new BusinessException("Exception in BankInsertServiceImpl.createNewAccount. BALANCE is 0.\n");
					}										
				} else {
					log.warn("Sorry. Entered PASSWORD is INVALID. Must be less than 16 characters in length. Cannot contain spaces.\n");
					throw new BusinessException("Exception in BankInsertServiceImpl.createNewAccount. USER PASSWORD is invalid.\n");
				}
			} else {
				log.warn("Sorry. Entered LAST NAME is INVALID. Must be less than 16 characters in length. Cannot contain numbers or spaces.\n");
				throw new BusinessException("Exception in BankInsertServiceImpl.createNewAccount. LAST NAME is invalid.\n");
			}
		} else {
			log.warn("Sorry. Entered FIRST NAME is INVALID. Must be less than 16 characters in length. Cannot contain numbers or spaces.\n");
			throw new BusinessException("Exception in BankInsertServiceImpl.createNewAccount. FIRST NAME is invalid.\n");
		}				
	}

	@Override
	public void createNewAccountFromExistingUser(String user_id, String user_password, BigDecimal balance) throws BusinessException {

		int account_id = bankSearchService.getRandomAccountId();
		
		if (balance.floatValue() > 0) {
			if (bankSearchService.verifyUserId(user_password) != null) {				
				bankInsertDAO.insertNewAccountInAccountTable(account_id, balance, user_id);
				bankInsertDAO.insertNewAccountInTransferTable(account_id);
			} else {
				log.warn("Sorry. Entered USER ID and/or PASSWORD is INVALID.\n");
				throw new BusinessException("Exception in BankInsertServiceImpl.createNewAccountFromExistingUser. VerifyUserId failuer.\n");
			}		
		} else {
			log.warn("Sorry. Entered BALANCE is INVALID.\n");
			throw new BusinessException("Exception in BankInsertServiceImpl.createNewAccountFromExistingUser. BALANCE is 0.\n");
		}
	}

	@Override
	public void insertTransactionLog(String user_subject, int account_subject, String transaction_action, BigDecimal funds) throws BusinessException {
			
		transactionLog = new TransactionLog(new Date(), user_subject, account_subject, transaction_action, funds, "0", 0);
				
		if (transactionLog != null) {
			bankInsertDAO.insertTransactionLog(transactionLog);
		} else {
			log.warn("Sorry. An ISSUE has occurred in the recording of this TRANSACTION LOG.\n");
			throw new BusinessException("Exception in BankInsertServiceImpl.insertTransactionLog. TRANSACTION LOG is invalid.\n");
		}		
	}
	
	@Override
	public void insertTransactionLog(String user_subject, int account_subject, String transaction_action, BigDecimal funds, int account_object) throws BusinessException {
		
		String user_object = bankSearchService.verifyUserId(account_object);		
		transactionLog = new TransactionLog(new Date(), user_subject, account_subject, transaction_action, funds, user_object, account_object);
		
		if (transactionLog != null && user_object != null) {
			bankInsertDAO.insertTransactionLog(transactionLog);
		} else {
			log.warn("Sorry. An ISSUE has occurred in the recording of this TRANSACTION LOG.\n");
			throw new BusinessException("Exception in BankInsertServiceImpl.insertTransactionLog. TRANSACTION LOG and/or USER ID is invalid.\n");
		}		
	}

	@Override
	public void insertTransactionLog(String user_subject, String transaction_action, BigDecimal funds, String user_object, int account_object) throws BusinessException {
			
		transactionLog = new TransactionLog(new Date(), user_subject, 0, transaction_action, funds, user_object, account_object);
		
		if (transactionLog != null) {
			bankInsertDAO.insertTransactionLog(transactionLog);
		} else {
			log.warn("Sorry. An ISSUE has occurred in the recording of this TRANSACTION LOG.\n");
			throw new BusinessException("Exception in BankInsertServiceImpl.insertTransactionLog. TRANSACTION LOG is invalid.\n");
		}		
	}	
}
