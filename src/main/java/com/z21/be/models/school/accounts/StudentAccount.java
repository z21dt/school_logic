package com.z21.be.models.school.accounts;

import java.util.Date;

public class StudentAccount {
	
	private String notes;
	
	private Long studentId;
	
	private String accountStatus;
	
	private Date dateCreated;


	public static final String STATUS_PENDING = "Pending";
	public static final String STATUS_ENROLLED = "Enrolled";

	
	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Override
	public String toString() {
		return "StudentAccount [notes=" + notes + ", studentId=" + studentId + ", accountStatus=" + accountStatus
				+ ", dateCreated=" + dateCreated + "]";
	}
	
	
	
	
	

}
