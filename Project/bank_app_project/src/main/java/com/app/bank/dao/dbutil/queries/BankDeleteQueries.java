package com.app.bank.dao.dbutil.queries;

public class BankDeleteQueries {
	
	public static final String DELETE_USER_IN_USER_TABLE = "DELETE FROM bank.user WHERE user_id = ?;";
	public static final String DELETE_USER_IN_ACCOUNT_TABLE = "DELETE FROM bank.account WHERE user_id = ?;";
	public static final String DELETE_ACCOUNT_IN_TRANSFER_TABLE = "DELETE FROM bank.transfer WHERE account_id = ?;";
	public static final String DELETE_ACCOUNT_IN_ACCOUNT_TABLE = "DELETE FROM bank.account WHERE account_id = ?;";

}
