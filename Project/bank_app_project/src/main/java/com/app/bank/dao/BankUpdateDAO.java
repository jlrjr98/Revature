package com.app.bank.dao;

import java.math.BigDecimal;

import com.app.bank.exception.BusinessException;

public interface BankUpdateDAO {
	
	public void withdrawFromAccount(BigDecimal withdrawal, int account_id) throws BusinessException;	
	public void depositIntoAccount(BigDecimal deposit, int account_id) throws BusinessException;

	public void postTransferToAccount(int transfer_to_account_id, int transfer_from_account_id, BigDecimal transfer)throws BusinessException;
	public void removePostedTransferFromBalance(int transfer_from_account_id, BigDecimal transfer) throws BusinessException;
	public void withdrawFromTransferAccount(int transfer_account_id) throws BusinessException;
}
