package com.app.bank.model;

import java.math.BigDecimal;
//import java.sql.Date;
import java.util.Date;

public class TransactionLog {
	
	private Date timestamp;
	private String user_subject;
	private int account_subject;
	private String transaction_action;
	private BigDecimal funds;
	private String user_object;
	private int account_object;
	
	public TransactionLog(Date timestamp, String user_subject, int account_subject, String transaction_action, BigDecimal funds,
			String user_object, int account_object) {
		super();
		this.timestamp = timestamp;
		this.user_subject = user_subject;
		this.account_subject = account_subject;
		this.transaction_action = transaction_action;
		this.funds = funds;
		this.user_object = user_object;
		this.account_object = account_object;
	}
	
	public Date gettimestamp() {
		return timestamp;
	}
	public void settimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getUser_subject() {
		return user_subject;
	}
	public void setUser_subject(String user_subject) {
		this.user_subject = user_subject;
	}
	public String getTransaction_action() {
		return transaction_action;
	}
	public void setTransaction_action(String transaction_action) {
		this.transaction_action = transaction_action;
	}
	public BigDecimal getFunds() {
		return funds;
	}
	public void setFunds(BigDecimal funds) {
		this.funds = funds;
	}
	public String getUser_object() {
		return user_object;
	}
	public void setUser_object(String user_objext) {
		this.user_object = user_objext;
	}

	public int getAccount_subject() {
		return account_subject;
	}
	public void setAccount_subject(int account_subject) {
		this.account_subject = account_subject;
	}
	public int getAccount_object() {
		return account_object;
	}
	public void setAccount_object(int account_object) {
		this.account_object = account_object;
	}
	@Override
	public String toString() {
		return "[" + timestamp + "] USER " + user_subject + " with ACCOUNT " + account_subject + " performed " + transaction_action 
				+ " of $" + funds + " on USER " + user_object + " with ACCOUNT " + account_object;
	}
	
	//For J-Unit Testing
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    TransactionLog that = (TransactionLog) o;
	    return timestamp.equals(that.timestamp) &&
	      user_subject.equals(that.user_subject) &&
	      transaction_action.equals(that.transaction_action) &&
	      funds.equals(that.funds) &&
	      user_object.equals(that.user_object);
	}
	
}
