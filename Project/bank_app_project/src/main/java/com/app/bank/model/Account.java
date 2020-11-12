package com.app.bank.model;

import java.math.BigDecimal;

public class Account {
	
	private String user_id;
	private int account_id;
	private String first_name;
	private String last_name;
	private String account_password;
	private BigDecimal balance;
	private BigDecimal transfer;
	private int transfer_account_id;
	
	public Account(String user_id, String first_name, String last_name, int account_id, BigDecimal balance, BigDecimal transfer, int transfer_account_id) {
		super();
		this.user_id = user_id;
		this.account_id = account_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.balance = balance;
		this.transfer = transfer;
		this.transfer_account_id = transfer_account_id;				
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAccount_password() {
		return account_password;
	}

	public void setAccount_password(String account_password) {
		this.account_password = account_password;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public BigDecimal getTransfer() {
		return transfer;
	}

	public void setTransfer(BigDecimal transfer) {
		this.transfer = transfer;
	}

	public int getTransfer_account_id() {
		return transfer_account_id;
	}

	public void setTransfer_account_id(int transfer_account_id) {
		this.transfer_account_id = transfer_account_id;
	}

	@Override
	public String toString() {
		return "User Id = " + user_id + ", Account Id = " + account_id + ", Name = " + first_name + " " + last_name + ", Balance = $" + balance + 
				", Pending Transfer = $" + transfer + " from Account Id = " + transfer_account_id;
	}
	
	//for J-Unit testing
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    Account that = (Account) o;
	    return user_id.equals(that.user_id) &&
	      first_name.equals(that.first_name) &&
	      last_name.equals(that.last_name) &&
	      balance.equals(that.balance) &&
	      transfer.equals(that.transfer);
	}

}
