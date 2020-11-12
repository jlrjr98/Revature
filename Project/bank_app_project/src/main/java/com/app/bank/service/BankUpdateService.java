package com.app.bank.service;

import java.math.BigDecimal;

//import com.app.bank.model.Account;
import com.app.bank.exception.BusinessException;

public interface BankUpdateService {

	public void withdrawFromAccount(BigDecimal withdrawal, int account_id, String user_id) throws BusinessException;
	public void depositIntoAccount(BigDecimal deposit, int account_id, String user_id) throws BusinessException;
	
	public void postTransfer(int transfer_to_account_id, String user_id, int transfer_from_account_id, BigDecimal transfer) throws BusinessException;
	public void acceptTransfer(int transfer_account_id, String user_id, int into_account_id) throws BusinessException;
	public void rejectTransfer(String user_id, BigDecimal transfer, int transfer_account_id, String transfer_user_id) throws BusinessException;
}
