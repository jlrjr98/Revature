package com.app.bank.dao;

import java.math.BigDecimal;
import java.util.List;

import com.app.bank.model.Account;
import com.app.bank.model.Employee;
import com.app.bank.model.TransactionLog;
import com.app.bank.exception.BusinessException;

public interface BankSearchDAO {
	
	public List<Account> getAccountByUserIdAndPassword(String user_id, String account_password) throws BusinessException;
	public List<Account> getAllAccounts() throws BusinessException;
	
	public int getAccountIdByUserIdAndAccountId(String user_id, int account_id) throws BusinessException;
	public List<Integer> getAccountIdByUserId(String user_id) throws BusinessException;
	public int getAccountIdByAccountId(int account_id) throws BusinessException;
	public int getTransferAccountIdByUserIdAndTransferAccountId(String user_id, int transfer_account_id) throws BusinessException;	
	public int getTransferAccountIdByAccountId(int account_id) throws BusinessException;
	
	public BigDecimal getBalanceByAccountId(int account_id) throws BusinessException;
	public BigDecimal getTransferFromTransferAccountId(int transfer_account_id) throws BusinessException;
	
	public String getUserIdByAccountId(int account_id) throws BusinessException;
	public String getUserIdByLastNameAndPassword(String last_name, String user_password) throws BusinessException;
	public String getUserIdByUserId(String user_id) throws BusinessException;
	
	public Employee getEmployeeByEmployeeIdAndPassword(String employee_id, String employee_password) throws BusinessException;
	
	public List<TransactionLog> getTransactionLogList() throws BusinessException;
}
