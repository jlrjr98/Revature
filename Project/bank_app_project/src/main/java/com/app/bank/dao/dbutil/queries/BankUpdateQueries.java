package com.app.bank.dao.dbutil.queries;

public class BankUpdateQueries {
	
	public static final String WITHDRAW_FROM_ACCOUNT = "UPDATE bank.account SET balance = balance - ? WHERE account_id = ?;";
	public static final String DEPOSIT_INTO_ACCOUNT = "UPDATE bank.account SET balance = balance + ? WHERE account_id = ?;";
	
	public static final String POST_TRANSFER_TO_ACCOUNT = "UPDATE bank.transfer SET transfer = transfer + ?, transfer_account_id = ? WHERE account_id = ?;";
	public static final String REMOVE_POSTED_TRANSFER_FROM_BALANCE = "UPDATE bank.account SET balance = balance - ? WHERE account_id = ?;";	
	public static final String WITHDRAW_FROM_TRANSFER_ACCOUNT = "UPDATE bank.transfer SET transfer = 0, transfer_account_id = 0 WHERE transfer_account_id = ?;";
}
