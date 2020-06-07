package com.z21.be.models.school.accounts;

import java.util.Date;

public class Transaction {
	
	private Long transactionId;
	
	private Long feeId;
	
	private Long studentAccountId;
	
	private Date transactionDate;
	
	private String notes;
	
	private Long userId;
	
	

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getFeeId() {
		return feeId;
	}

	public void setFeeId(Long feeId) {
		this.feeId = feeId;
	}

	public Long getStudentAccountId() {
		return studentAccountId;
	}

	public void setStudentAccountId(Long studentAccountId) {
		this.studentAccountId = studentAccountId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
	
}
