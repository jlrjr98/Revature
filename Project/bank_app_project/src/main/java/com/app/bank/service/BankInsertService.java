package com.app.bank.service;

import java.math.BigDecimal;

import com.app.bank.exception.BusinessException;

public interface BankInsertService {

	public void createNewAccount(String first_name, String last_name, String user_password, BigDecimal balance) throws BusinessException;
	public void createNewAccountFromExistingUser(String user_id, String user_password, BigDecimal balance) throws BusinessException;
	
	//for withdrawals and deposits
	public void insertTransactionLog(String user_subject, int account_subject, String transaction_action, BigDecimal funds) throws BusinessException;	
	//for posting transfers and accepting transfers
	public void insertTransactionLog(String user_subject, int account_subject, String transaction_action, BigDecimal funds, int account_object) throws BusinessException;	
	//for rejecting transfers
	public void insertTransactionLog(String user_subject, String transaction_action, BigDecimal funds, String user_object, int account_object) throws BusinessException;
}
