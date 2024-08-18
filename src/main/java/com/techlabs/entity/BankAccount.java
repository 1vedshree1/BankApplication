package com.techlabs.entity;

public class BankAccount {
	
	private int accountId;
	private int customerId;
	private long accountNumber;
	private String accountType;
	private double balance;
	
	public BankAccount(int customerId, long accountNumber, String accountType, double balance) {
		super();
		this.accountId = accountId;
		this.customerId = customerId;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "BankAccount [accountId=" + accountId + ", customerId=" + customerId + ", accountNumber=" + accountNumber
				+ ", accountType=" + accountType + ", balance=" + balance + "]";
	}
	
	

}
