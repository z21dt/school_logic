package com.z21.fe.models.resp;

public class EnrolleesResp {
	
	private Long studentId;
	
	private String studentName;
	
	private String gradeLevel;
	
	private String studentType;
	
	private String status;
	
	private String notes;
	
	private String preferredPayment;
	
	private String regNotes;
	


	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public String getStudentType() {
		return studentType;
	}

	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getPreferredPayment() {
		return preferredPayment;
	}

	public void setPreferredPayment(String preferredPayment) {
		this.preferredPayment = preferredPayment;
	}

	public String getRegNotes() {
		return regNotes;
	}

	public void setRegNotes(String regNotes) {
		this.regNotes = regNotes;
	}


	

}
