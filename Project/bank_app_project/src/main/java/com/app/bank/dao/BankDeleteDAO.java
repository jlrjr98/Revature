package com.app.bank.dao;

import java.util.List;

import com.app.bank.exception.BusinessException;

public interface BankDeleteDAO {
	
	public void deleteUserInUserTable(String user_id) throws BusinessException;
	public void deleteUserInAccountTable(String user_id) throws BusinessException;
	
	public void deleteAccountListInTransferTable(List<Integer> account_id_list) throws BusinessException;
	public void deleteAccountInAccountTable(int account_id) throws BusinessException;

}
