package com.app.bank.service;

import com.app.bank.exception.BusinessException;

public interface BankDeleteService {
	
	public void deleteUser(String user_id) throws BusinessException;
	public void deleteAccount(int account_id) throws BusinessException;
}
