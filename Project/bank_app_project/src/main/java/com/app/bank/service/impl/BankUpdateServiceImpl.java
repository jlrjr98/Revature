package com.app.bank.service.impl;

import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.Date;

import org.apache.log4j.Logger;

//import com.app.bank.model.Account;
//import com.app.bank.dao.BankSearchDAO;
import com.app.bank.dao.BankUpdateDAO;
//import com.app.bank.dao.Impl.BankSearchDAOImpl;
import com.app.bank.dao.Impl.BankUpdateDAOImpl;
import com.app.bank.exception.BusinessException;
import com.app.bank.main.BankAppMain;
import com.app.bank.service.BankInsertService;
import com.app.bank.service.BankSearchService;
import com.app.bank.service.BankUpdateService;

public class BankUpdateServiceImpl implements BankUpdateService {
	
	public static Logger log = Logger.getLogger(BankAppMain.class);
	
	private BankUpdateDAO bankUpdateDAO = new BankUpdateDAOImpl();
	private BankSearchService bankSearchService = new BankSearchServiceImpl();
	private BankInsertService bankInsertService = new BankInsertServiceImpl();

	@Override
	public void withdrawFromAccount(BigDecimal withdrawal, int account_id, String user_id) throws BusinessException {
		
	if (bankSearchService.verifyAccountId(user_id, account_id) != 0) {		
		if (withdrawal.floatValue() <= bankSearchService.getBalance(account_id).floatValue() && withdrawal.floatValue() > 0.00) {			
			bankUpdateDAO.withdrawFromAccount(withdrawal, account_id);

			bankInsertService.insertTransactionLog(user_id, account_id, "TRANSFER POST", withdrawal);			
		} else {
			log.warn("Entered WITHDRAWAL amount is INVALID.");
			throw new BusinessException("Exception in BankUpdateServiceImpl.withdrawFromAccount. WITHDRAWAL amount invalid.\n");
			}
		} else {
			log.warn("Entered USER ID and ACCOUNT ID do not match. Please try again");
		throw new BusinessException("Exception in BankUpdateServiceImpl.withdrawFromAccount. VerifyAccountId failure.\n");
		}
	}

	@Override
	public void depositIntoAccount(BigDecimal deposit, int account_id, String user_id) throws BusinessException {
		
		if (bankSearchService.verifyAccountId(user_id, account_id) != 0) {			
			if (deposit.floatValue() > 0.00) {	
				bankUpdateDAO.depositIntoAccount(deposit, account_id);
						
				bankInsertService.insertTransactionLog(user_id, account_id, "DEPOSIT", deposit);				
			} else {
				log.warn("Entered DEPOSIT amount is INVALID.\n");
				throw new BusinessException("Excepton in BankUpdateServiceImpl.depositIntoAccount. DEPOSIT amount invalid.");
			}
		} else {
			log.warn("Entered ACCOUNT ID does not match your USER ID. Please try again.\n");
			throw new BusinessException("Excepton in BankUpdateServiceImpl.depositIntoAccount. VerifyAccountId failure.\n");
		}
	}

	//post transfer
	@Override
	public void postTransfer(int transfer_to_account_id, String user_id, int transfer_from_account_id, BigDecimal transfer) throws BusinessException {
	
		if (transfer_from_account_id >= 1000 && transfer_from_account_id <= 9999 && transfer_to_account_id >= 1000 && transfer_to_account_id <= 9999) {				
			if (transfer.floatValue() > 0.00) {															
				if (bankSearchService.verifyAccountId(user_id, transfer_from_account_id) != 0) {					
					if (bankSearchService.verifyAccountId(transfer_to_account_id) != 0 && transfer_to_account_id != transfer_from_account_id) {						
						if (transfer.floatValue() <= bankSearchService.getBalance(transfer_from_account_id).floatValue()) {					
							bankUpdateDAO.postTransferToAccount(transfer_to_account_id, transfer_from_account_id, transfer);
							bankUpdateDAO.removePostedTransferFromBalance(transfer_from_account_id, transfer);				
									
							bankInsertService.insertTransactionLog(user_id, transfer_from_account_id, "TRANSFER POST", transfer, transfer_to_account_id);					
						} else {
							log.warn("There are INSUFFICIENT FUNDS to perform this transfer.\n");
							throw new BusinessException("Exception in BankUpdateServiceImpl.postTransfer. TRANSFER amount invalid.\n");
						}
					} else {
						log.warn("Entered TRANSFER ACCOUNT ID is INVALID.\n");
						throw new BusinessException("Exception in BankUpdateServiceImpl.postTransfer. VerifyAccountId failuer.\n");
					}					
				} else {
					log.warn("Entered ACCOUNT ID does not match your USER ID. Please try again.\n");
					throw new BusinessException("BankUpdateServiceImpl.postTransfer. VerifyAccountId failure.\n");
				}			
			} else {
				log.warn("Entered TRANSFER amount is INVALID.\n");
				throw new BusinessException("Exception in BankUpdateServiceImpl.postTransfer. TRANSFER amount invalid.\n");
			}					
		} else {
			log.warn("Entered ACCOUNT ID and/or TRANSFER ACCOUNT ID is INVALID.\n");
			throw new BusinessException("Exception in BankUpdateServiceImpl.postTransfer. TRANSFER ACCOUNT ID wrong format.\n");
		}					
	}

	@Override
	public void acceptTransfer(int transfer_account_id, String user_id, int into_account_id) throws BusinessException {
		
		if (transfer_account_id >= 1000 && transfer_account_id <= 9999 && into_account_id >= 1000 && into_account_id <= 9999) {			
			if (bankSearchService.verifyAccountId(user_id, into_account_id) != 0) {				
				if(bankSearchService.verifyTransferAccountId(user_id, transfer_account_id) != 0) {	
				
					BigDecimal transfer_recieve = bankSearchService.getTransfer(transfer_account_id);

					if (transfer_recieve.floatValue() != 0) {	
						bankUpdateDAO.depositIntoAccount(transfer_recieve, into_account_id);
						bankUpdateDAO.withdrawFromTransferAccount(transfer_account_id);	
											
						bankInsertService.insertTransactionLog(user_id, into_account_id, "TRANSFER ACCEPTANCE", transfer_recieve, transfer_account_id);					
					} else {
						log.warn("Sorry. There are no funds to TRANSFER.\n");
						throw new BusinessException("Exception in BankUpdateServiceImpl.acceptTransfer. TRANSFER amoutn is 0.\n");
					}
				} else {
					log.warn("Entered ACCOUNT ID is INVALID. Please try again.\n");
					throw new BusinessException("Exception in BankUpdateServiceImpl.acceptTransfer. VerifyTransferAccountId failure.\n");
				}			
			}else {
				log.warn("Entered ACCOUNT ID does not match your USER ID. Please try again.\n");
				throw new BusinessException("Exception in BankUpdateServiceImpl.acceptTransfer. VerifyAccountId failure.\n");
			}				
		} else {
			log.warn("Entered ACCOUNT ID and/or TRANSFER ACCOUNT ID is INVALID.\n");
			throw new BusinessException("Exception in BankUpdateServiceImpl.acceptTransfer. ACCOUNT ID wrong format.\n");
		}							
	}

	@Override
	public void rejectTransfer(String user_id, BigDecimal transfer, int transfer_account_id, String transfer_user_id) throws BusinessException {
		
		if (transfer.floatValue() > 0) {
			if (transfer_account_id >= 1000 && transfer_account_id <= 9999) {
				if (transfer_user_id != null && transfer_user_id.length() == 4) {										
					depositIntoAccount(transfer, transfer_account_id, transfer_user_id);		
					bankUpdateDAO.withdrawFromTransferAccount(transfer_account_id);
				
					bankInsertService.insertTransactionLog(user_id, "TRANSFER REJECTION", transfer, transfer_user_id, transfer_account_id);					
				} else {
					log.warn("Sorry. ACCOUNT ID is INVALID.\n");
					throw new BusinessException("Exception in BankUpdateService.rejectTransfer. TRANSFER USER ID wrong format.\n");
				}								
			} else {
				log.warn("Sorry.Entered ACCOUNT ID is INVALID.\n");
				throw new BusinessException("Exception in BankUpdateService.rejectTransfer. TRANSFER ACCOUNT ID wrong format.\n");
			}
		} else {
			log.warn("Sorry. No transferable funds found.\n");
			throw new BusinessException("Exception in BankUpdateService.rejectTransfer. TRANSFER amount invalid.\n");
		}						
	}	
}
