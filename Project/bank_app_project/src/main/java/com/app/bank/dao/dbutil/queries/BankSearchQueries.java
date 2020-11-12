package com.app.bank.dao.dbutil.queries;

public class BankSearchQueries {
		
	public static final String GET_ACCOUNT_ID_BY_USER_ID_AND_ACCOUNT_ID = "SELECT account_id FROM bank.user FULL OUTER JOIN bank.account "
			+ "ON bank.user.user_id = bank.account.user_id WHERE bank.user.user_id = ? AND account_id = ?;";	
	public static final String GET_ACCOUNT_ID_BY_ACCOUNT_ID = "SELECT account_id FROM bank.account WHERE account_id = ?;";
	public static final String GET_ACCOUNT_ID_BY_USER_ID = "SELECT account_id FROM bank.account WHERE user_id = ?;";
	public static final String GET_TRANSFER_ACCOUNT_ID_BY_USER_ID_AND_TRANSFER_ACCOUNT_ID = "SELECT transfer_account_id FROM bank.transfer JOIN "
			+ "bank.account ON bank.transfer.account_id = bank.account.account_id JOIN bank.user ON bank.user.user_id = bank.account.user_id "
			+ "WHERE bank.account.user_id = ? AND transfer_account_id = ?";
		
	public static final String GET_USER_ID_BY_ACCOUNT_ID = "SELECT user_id FROM bank.account WHERE account_id = ?;";	
	public static final String GET_USER_ID_BY_LAST_NAME_AND_PASSWORD = "SELECT user_id FROM bank.user WHERE last_name = ? AND user_password = ?;";	
	public static final String GET_USER_ID_BY_USER_ID = "SELECT user_id FROM bank.user WHERE user_id = ?;";	
	
	public static final String GET_ACCOUNT_BY_USER_ID_AND_PASSWORD = "SELECT bank.user.user_id, first_name, last_name, "
			+ "bank.account.account_id, balance, bank.transfer.transfer, bank.transfer.transfer_account_id "
			+ "FROM bank.transfer JOIN bank.account ON bank.transfer.account_id = bank.account.account_id JOIN bank.user ON bank.user.user_id "
			+ "= bank.account.user_id WHERE bank.user.user_id = ? AND user_password = ?";	
	public static final String GET_ALL_ACCOUNTS = "SELECT bank.user.user_id, first_name, last_name, bank.account.account_id, balance, transfer, transfer_account_id "
			+ "FROM bank.user JOIN bank.account ON bank.user.user_id = bank.account.user_id JOIN bank.transfer ON bank.account.account_id = bank.transfer.account_id "
			+ "ORDER BY bank.user.user_id;";
	
	public static final String GET_BALANCE_BY_ACCOUNT_ID = "SELECT balance FROM bank.account WHERE account_id = ?";
	public static final String GET_TRANSFER_RECIEVE_FROM_TRANSFER_ACCOUNT_ID = "SELECT transfer FROM bank.transfer WHERE transfer_account_id = ?;";	
	
	public static final String GET_EMPLOYEE_BY_EMPLOYEE_ID_AND_PASSWORD = "SELECT employee_id, first_name, last_name FROM bank.employee WHERE "
			+ "employee_id = ? AND employee_password = ?;";

	public static final String GET_TRANSACTION_LOG_LIST = "SELECT time_stamp, user_object, account_object, transaction_action, funds, user_subject, "
			+ "account_subject FROM bank.transactions ORDER BY time_stamp;";
}
