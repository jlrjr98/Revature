package com.app.bank.dao.dbutil.queries;

public class BankInsertQueries {	

	public static final String INSERT_ACCOUNT_INTO_USER_TABLE = "INSERT INTO bank.user (user_id, first_name, last_name, user_password) VALUES (?, ?, ?, ?);";
	public static final String INSERT_ACCOUNT_INTO_ACCOUNT_TABLE = "INSERT INTO bank.account (account_id, balance, user_id) VALUES (?, ?, ?);";
	public static final String INSERT_ACCOUNT_INTO_TRANSFER_TABLE = "INSERT INTO bank.transfer (account_id, transfer, transfer_account_id) VALUES (?, 0, 0);";	
	
	public static final String INSERT_TRANSACTION_LOG = "INSERT INTO bank.transactions (time_stamp, user_subject, account_subject, transaction_action, funds, user_object, account_object) VALUES (?, ?, ?, ?, ?, ?, ?);";
}
