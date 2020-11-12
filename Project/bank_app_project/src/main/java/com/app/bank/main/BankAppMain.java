package com.app.bank.main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.bank.exception.BusinessException;
import com.app.bank.model.Account;
import com.app.bank.model.Employee;
import com.app.bank.service.BankDeleteService;
import com.app.bank.service.BankInsertService;
import com.app.bank.service.BankSearchService;
import com.app.bank.service.BankUpdateService;
import com.app.bank.service.impl.BankDeleteServiceImpl;
import com.app.bank.service.impl.BankInsertServiceImpl;
import com.app.bank.service.impl.BankSearchServiceImpl;
import com.app.bank.service.impl.BankUpdateServiceImpl;
import com.app.bank.util.RandomId;

public class BankAppMain {
	
	public static RandomId randomId = new RandomId();	
	public static Logger log = Logger.getLogger(BankAppMain.class);

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		BankSearchService bankSearchService = new BankSearchServiceImpl();
		BankUpdateService bankUpdateService = new BankUpdateServiceImpl();
		BankInsertService bankInsertService = new BankInsertServiceImpl();
		BankDeleteService bankDeleteService = new BankDeleteServiceImpl();
				
		int s1 = 0;
				
		do {						
			try {
				log.info("Welcome to the JOHN RIOS BANKING APPLICATION!\n\nAre you a customer or employee?\n|Press (1) for CUSTOMER.\n|Press (2) for EMPLOYEE.\n|Press (3) to EXIT.");		
				s1 = Integer.parseInt(scanner.nextLine());
			} 
			catch (NumberFormatException e) {}
			
			switch (s1) {			
			
			case 1:	//CUSTOMER
					int s2 = 0;
					
					do {
														
						try {
							log.info("Hello Customer! You need an account to proceed.\n|Press (1) to LOGIN and view your existing account(s).\n|Press (2) to CREATE a new account.\n|Press (3) to RETURN to the home menu.");				
							s2 = Integer.parseInt(scanner.nextLine());
						} catch (NumberFormatException e) {}
						
						switch (s2) {						
						
						case 1: //LOGIN
								String user_id = null;
								String user_password = null;	
								List<Account> account_list = new ArrayList<>();
								
							try {		
								log.info("Login:\nPlease enter your USER ID");
								user_id = scanner.nextLine();
								log.info("\nPlease enter your PASSWORD");
								user_password = scanner.nextLine();
																
								account_list = bankSearchService.getAccount(user_id, user_password);
																
								if (account_list != null && account_list.size() > 0) {
									log.info("You have successfully logged in. Welcome Back! Here are your details:\n");								
									account_list.forEach(a -> log.info(a));
								}
							} catch (BusinessException e) {
								log.debug(e.getMessage());
								break;
							}
								int s3 = 0;
								
								do {
									
									log.info("\n|Press (1) to make a WITHDRAWL\n|Press (2) to make a DEPOSIT\n|Press (3) to post a TRANSFER to another account.\n|Press (4) to manage pending TRANSFERS.\n|Press (5) to RETURN.");									
									try {
										s3 = Integer.parseInt(scanner.nextLine());
									} catch (NumberFormatException e) {}
									
									switch (s3) {
									
									case 1:	//WITHDRAWL							
										
										try {																												
											log.info("Enter the ACCOUNT ID of the account you wish to make a withdrawal from:");
											int account_id = Integer.parseInt(scanner.nextLine());
											log.info("Enter the amount you wish to WITHDRAW:");							
											BigDecimal withdrawal = new BigDecimal(Float.parseFloat(scanner.nextLine()));																						
											
											bankUpdateService.withdrawFromAccount(withdrawal, account_id, user_id);
											
											log.info("Withdrawal complete! Here are your updated details:\n");											
											bankSearchService.getAccount(user_id, user_password).forEach(a -> log.info(a));
											
										} catch (NumberFormatException e) { 	
											log.warn("INVALID Input. Please try again.");	
										} catch (BusinessException e) {
											log.debug(e.getMessage());										
										}									
										break;
									
									case 2: //DEPOSIT
										
										try {												
											log.info("Enter the ACCOUNT ID of the account you wish to make a DEPOSIT into:");
											int account_id = Integer.parseInt(scanner.nextLine());
											log.info("Enter the amount you wish to DEPOSIT:");
											BigDecimal deposit = new BigDecimal(Float.parseFloat(scanner.nextLine()));
																		
											bankUpdateService.depositIntoAccount(deposit, account_id, user_id);
											
											log.info("Deposit complete! Here are your updated details:\n");
											bankSearchService.getAccount(user_id, user_password).forEach(a -> log.info(a));
											
										} catch (NumberFormatException e) { 
											log.warn("INVALID Input. Please try again.");											
										} catch (BusinessException e) {
											log.debug(e.getMessage());
										}										
										break;
									
									case 3: //POST TRANSFER
										
										try {
											log.info("Enter the ACCOUNT ID of the account you wish to TRANSFER funds TO:");
											int transfer_to_account_id = Integer.parseInt(scanner.nextLine());
											log.info("Enter the ACCOUNT ID of the account you wish to TRANSFER funds FROM:");
											int transfer_from_account_id = Integer.parseInt(scanner.nextLine());
											log.info("Enter the amount you wish to TRANSFER:");
											BigDecimal transfer = new BigDecimal(Float.parseFloat(scanner.nextLine()));
											
											bankUpdateService.postTransfer(transfer_to_account_id, user_id, transfer_from_account_id, transfer);
											log.info("Funds successfully TRANSFERRED. Here are your updated details:\n");
											bankSearchService.getAccount(user_id, user_password).forEach(a -> log.info(a));
											
										} catch (NumberFormatException e) {
											log.warn("INVALID Input. Please try again.");											
										} catch (BusinessException e) {
											log.debug(e.getMessage());
										}
										
										break;
									
									case 4: //RECIEVE TRANSFER
										
										int s5 = 0;
																														
										try {
											log.info("|Press (1) to ACCEPT a TRANSFER.\n|Press (2) to REJECT a TRANSFER.\n|Press (3) to RETURN.\n");
											s5 = Integer.parseInt(scanner.nextLine());
										} catch (NumberFormatException e) { 
											log.warn("INVALID Input. Please try again.");
											break;											
										}
										
										do {
											switch (s5) {
											
											case 1:	//ACCEPT TRANSFER																																			
												
												try {													
													log.info("Enter the ACCOUNT ID you wish to ACCEPT a TRANSFER FROM.\n");
													int transfer_account_id = Integer.parseInt(scanner.nextLine());											
													log.info("Enter the ACCOUNT ID you wish to deposit TRANSFER INTO.\n");
													int into_account_id = Integer.parseInt(scanner.nextLine());						
												
													bankUpdateService.acceptTransfer(transfer_account_id, user_id, into_account_id);
												
													log.info("TRANSFER successfully accepted. Here are your updated details:\n");
													bankSearchService.getAccount(user_id, user_password).forEach(a -> log.info(a));
													
												} catch (NumberFormatException e) {
													log.warn("INVALID Input. Please try again.");
												} catch (BusinessException e) {
													log.debug(e.getMessage());					
												}																
												s5 = 3;
												break;
											
											case 2: //REJECT TRANSFER
												
												try {
													log.info("Enter the ACCOUNT ID you wish to REJECT a TRANSFER FROM.\n");
													int transfer_account_id = Integer.parseInt(scanner.nextLine());																																	
													
													bankUpdateService.rejectTransfer(user_id, bankSearchService.getTransfer(transfer_account_id), transfer_account_id, bankSearchService.verifyUserId(transfer_account_id));
													
												} catch (NumberFormatException e) {
													log.warn("INVALID Input. Please try again.");
												} catch (BusinessException e) {
													log.debug(e.getMessage());
												}																							
												s5 = 3;
												break;
												
											case 3: //RETURN
												break;
												
											default: log.info("Sorry. That option is INVALID. Please try again.");
												break;													
											}
										} while (s5 != 3);										
										
										break;
									
									case 5: //RETURN
										break;
									
									default: log.info("Sorry. That option is INVALID. Please try again.");
										break;									
									}									
								} while (s3 != 5);
								
								s3 = 5;
							break;
													
						case 2:	//CREATE ACCOUNT					
							
							int s6 = 0;
							
							try {
								log.info("|Press (1) if you have created an ACCOUNT with us before.\n|Press (2) if you have NOT created an ACCOUNT with us before.\n|Press (3) to RETURN.\n");
								s6 = Integer.parseInt(scanner.nextLine());
							} catch (NumberFormatException e) {
								log.warn("INVALID Input. Please try again.\n");
								break;
							}
						
							do {
								switch (s6) {
							
							case 1:  //ALREADY A USER
							
									try {
										log.info("Enter your USER ID:");
										user_id = scanner.nextLine();
										log.info("Enter your PASSWORD:");
										user_password = scanner.nextLine();
										log.info("Enter a starting BALANCE:");
										BigDecimal balance = new BigDecimal(Float.parseFloat(scanner.nextLine()));
										
										bankInsertService.createNewAccountFromExistingUser(user_id, user_password, balance);
										log.info("ACCOUNT successfully created! LOGIN to view your details.\n");
									} catch (NumberFormatException e) {
										log.warn("INVALID Input. Please try again.\n");
									} catch (BusinessException e2) {
										log.debug(e2.getMessage());
									}
								
									s6 = 3;
								break;
								
							case 2:	//NEW USER							
									
									try {
										log.info("Enter your FRIST NAME:");
										String first_name = scanner.nextLine();
										log.info("Enter your LAST NAME:");
										String last_name = scanner.nextLine();
										log.info("Create a PASSWORD:");
										user_password = scanner.nextLine();
										log.info("Enter a starting BALANCE:");
										BigDecimal starting_balance = new BigDecimal(Float.parseFloat(scanner.nextLine()));						
										
										bankInsertService.createNewAccount(first_name, last_name, user_password, starting_balance);										
										log.info("ACCOUNT successfully created! Here is your USER ID: [" + bankSearchService.verifyUserId(last_name, user_password) + "]\nLOGIN to view your details.\n");									
									} catch (NumberFormatException e) {
										log.warn("INVALID Input. Please try again.\n");
									} catch (BusinessException e) {
										log.debug(e.getMessage());
									}
						
									s6 = 3;
								break;
								
							case 3: //RETURN
								break;
								
							default: log.info("Sorry. That option is INVALID. Please try again.\n");
								break;						
								}
							} while (s6 != 3);														
								
								s2 = 3;
							break;
						
						case 3: //RETURN
							break;
							
						default: log.info("Sorry. That option is INVALID. Please try again.\n");
							break;
						}
						
					} while (s2 != 3);
				
				break;
			
			//EMPLOYEE
			case 2: 
					
					try {														
						log.info("Hello Employee! Enter your EMPLOYEE ID:");
						String employee_id = scanner.nextLine();
						log.info("Enter your PASSWORD:");
						String employee_password = scanner.nextLine();
						
						Employee employee = bankSearchService.getEmployee(employee_id, employee_password);																		
						
						if (employee != null) {
							log.info("You have successfully logged in. Welcome Back, " + employee );
						} else {
							log.info("Sorry, USER ID or PASSWORD is INVALID.\n");
							break;
						}
					} catch (BusinessException e) {
						log.debug(e.getMessage());
						break;
					}								
				
					int e1 = 0;										
				
				do {	
										
					try {
						log.info("\n|Press (1) to VIEW all ACCOUNTS.\n|Press (2) to VIEW all TRANSACTION LOGS.\n|Press (3) to REJECT a USER REGISTRATION.\n|Press (4) to REJECT an ACCOUNT.\n|Press (5) to RETURN.\n");					
						e1 = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {
						log.warn("INVALID Input. Please try again.\n");
					}
					
					switch (e1) {
					
					case 1:
						
						try {							
							log.info("Here is a list of all ACCOUNTS:\n");							
							bankSearchService.getAllAccounts().forEach(a -> log.info(a));							
						} catch (BusinessException e3) {
							log.debug(e3.getMessage());
						}												
						break;
									
					case 2: 
																											
						try {
							log.info("Here is a LOG of all TRANSACTIONS:\n");
							bankSearchService.getTransactionLogList().forEach(t -> log.info(t));														
						} catch (BusinessException e2) {
							log.debug(e2.getMessage());
						}												
						break;							
					
					case 3: //REJECT USER											
						
						try {							
							log.info("\nEnter the USER ID of the USER REGISTRATION you wish to REJECT:");
							String user_id = scanner.nextLine();
							
							bankDeleteService.deleteUser(user_id);
							log.info("USER ACCOUNT successfully DELETED.\n");
						} catch (BusinessException e) {
							log.debug(e.getMessage());
							break;
						}						
						break;						
					
					case 4: //REJECT ACCOUNT														
						
						try {							
							log.info("Enter the ACCOUNT ID of the ACCOUNT you wish to REJECT:");															
							int account_id = Integer.parseInt(scanner.nextLine());	
							
							bankDeleteService.deleteAccount(account_id);
							log.info("ACCOUNT successfully DELETED.\n");
						} catch (NumberFormatException e) {
							log.warn("INVALID Input. Please try again.\n");
						} catch (BusinessException e) {
							log.debug(e.getMessage());
						break;
						}					
					break;
				
					case 5: //RETURN
						break;
						
					default: log.info("Sorry. That option is INVALID. Please try again.\n");
						break;				
					}
				} while (e1 != 5);																	
				break;
				
			case 3: log.info("Thank you for using the JOHN RIOS BANKING APPLICATION! Goodbye.");
					scanner.close();
				break;
				
			default: log.info("Sorry. That option is INVALID. Please try again.\n");
				break;
				}
			
			} while (s1 != 3);		
	}		
}
