package com.techlabs.entity;

public class Transaction {
	private int transactionId;
	private int accountId;
	private String transaction_type;
	private double amount;
	private Long accountNumber;
	private Long transfer_accountNumber;
	private String transactionDate;
	public Transaction(int transactionId,String transactionDate, String transaction_type, double amount,Long accountNumber,
			Long transfer_accountNumber ) {
		super();
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.transaction_type = transaction_type;
		this.amount = amount;
		this.accountNumber=accountNumber;
		this.transfer_accountNumber = transfer_accountNumber;
		this.transactionDate = transactionDate;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Long getTransfer_accountNumber() {
		return transfer_accountNumber;
	}
	public void setTransfer_accountNumber(Long transfer_accountNumber) {
		this.transfer_accountNumber = transfer_accountNumber;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", accountId=" + accountId + ", transaction_type="
				+ transaction_type + ", amount=" + amount+",accountNumber=" + accountNumber + ", transfer_accountNumber=" + transfer_accountNumber
				+ ", transactionDate=" + transactionDate + "]";
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	

}
