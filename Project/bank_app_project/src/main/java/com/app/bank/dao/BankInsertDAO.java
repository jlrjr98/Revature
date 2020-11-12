package com.app.bank.dao;

import java.math.BigDecimal;

import com.app.bank.exception.BusinessException;
import com.app.bank.model.TransactionLog;

public interface BankInsertDAO {

	public void insertNewAccountInUserTable(String first_name, String last_name, String user_password, String user_id) throws BusinessException;
	public void insertNewAccountInAccountTable(int account_id, BigDecimal balance, String user_id) throws BusinessException;
	public void insertNewAccountInTransferTable(int account_id) throws BusinessException;
	
	public void insertTransactionLog(TransactionLog transactionLog) throws BusinessException;
}
