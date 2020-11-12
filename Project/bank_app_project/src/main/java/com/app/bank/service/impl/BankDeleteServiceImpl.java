package com.app.bank.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.bank.dao.BankDeleteDAO;
import com.app.bank.dao.Impl.BankDeleteDAOImpl;
import com.app.bank.exception.BusinessException;
import com.app.bank.main.BankAppMain;
import com.app.bank.service.BankDeleteService;
import com.app.bank.service.BankSearchService;

public class BankDeleteServiceImpl implements BankDeleteService {
	
	public static Logger log = Logger.getLogger(BankAppMain.class);
	
	private BankSearchService bankSearchService = new BankSearchServiceImpl();
	private BankDeleteDAO bankDeleteDAO = new BankDeleteDAOImpl();

	@Override
	public void deleteUser(String user_id) throws BusinessException {
		
		if ( user_id.length() == 4) {			
			if (bankSearchService.verifyUserId(user_id) != null) {
					bankDeleteDAO.deleteAccountListInTransferTable(bankSearchService.verifyAccountId(user_id));	
					bankDeleteDAO.deleteUserInAccountTable(user_id);
					bankDeleteDAO.deleteUserInUserTable(user_id);					
			} else {
				log.warn("Sorry. USER ID Is INVALID.\n");
				throw new BusinessException("Exception in BankDeleteServiceImpl.deleteUser.VerifyUserId failure.\n");
			}				
		} else {
			log.warn("Sorry. USER ID Is INVALID. Must be 4 characters in length.\n");
			throw new BusinessException("Exception in BankDeleteServiceImpl.deleteUser. USER ID wrong format.\n");
		}						
	}

	@Override
	public void deleteAccount(int account_id) throws BusinessException {								
		
		if (account_id >= 1000 && account_id <= 9999) {			
				if (bankSearchService.verifyUserId(account_id) != null) {
					
				List<Integer> account_id_list = new ArrayList<>();
					account_id_list.add(account_id);
					
					bankDeleteDAO.deleteAccountListInTransferTable(account_id_list);
					bankDeleteDAO.deleteAccountInAccountTable(account_id);				
			} else {
				log.warn("Sorry. ACCOUNT ID is INVALID.\n");
				throw new BusinessException("Exception in BankDeleteServiceImpl. VerifyUserId failure.\n");
			}				
		} else {
			log.warn("Sorry. ACCOUNT ID is INVALID. Must be 4 digits in length.\n");
			throw new BusinessException("Exception in BankDeleteServiceImpl.deleteAccount. ACCOUNT ID wrong format.\n");
		}							
	}
}
