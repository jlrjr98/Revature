package com.app.bank.service;

import java.math.BigDecimal;
import java.util.List;

import com.app.bank.model.Account;
import com.app.bank.model.Employee;
import com.app.bank.model.TransactionLog;
import com.app.bank.exception.BusinessException;

public interface BankSearchService {	

	public List<Account> getAccount(String user_id, String Password) throws BusinessException;
	public List<Account> getAllAccounts() throws BusinessException;	
	
	public BigDecimal getBalance(int account_id) throws BusinessException;
	public BigDecimal getTransfer(int transfer_account_id) throws BusinessException;				
	
	public String getRandomUserId() throws BusinessException;
	public int getRandomAccountId() throws BusinessException;
	
	public Employee getEmployee(String employee_id, String employee_password) throws BusinessException;

	public List<TransactionLog> getTransactionLogList() throws BusinessException;
	
	public List<Integer> verifyAccountId(String user_id) throws BusinessException;
	public int verifyAccountId(String user_id, int account_id) throws BusinessException;
	public int verifyAccountId(int account_id) throws BusinessException;
	public int verifyTransferAccountId(String user_id, int transfer_account_id) throws BusinessException;
	
	public String verifyUserId(int account_id) throws BusinessException;
	public String verifyUserId(String last_name, String user_password) throws BusinessException;
	public String verifyUserId(String user_id) throws BusinessException;
}
