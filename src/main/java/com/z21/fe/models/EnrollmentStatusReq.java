package com.z21.fe.models;

public class EnrollmentStatusReq {
	
	private Long studentId;
	
	private String schoolId;
	
	private String enrollmentStatus;
	
	private String regNotes;
	

	public Long getStudentId() {
		return studentId; 
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getEnrollmentStatus() {
		return enrollmentStatus;
	}

	public void setEnrollmentStatus(String enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}

	public String getRegNotes() {
		return regNotes;
	}

	public void setRegNotes(String regNotes) {
		this.regNotes = regNotes;
	}


	

}
