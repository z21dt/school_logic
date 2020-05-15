package com.z21.be.models.school;

public class SchoolYear {
	
	private String syAccountId;
	
	private Long schoolId;
	
	private Long schoolCode;
	
	private Long yearStart;
	
	private Long yearEnd;

	public String getSyAccountId() {
		return syAccountId;
	}

	public void setSyAccountId(String syAccountId) {
		this.syAccountId = syAccountId;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public Long getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(Long schoolCode) {
		this.schoolCode = schoolCode;
	}

	public Long getYearStart() {
		return yearStart;
	}

	public void setYearStart(Long yearStart) {
		this.yearStart = yearStart;
	}

	public Long getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(Long yearEnd) {
		this.yearEnd = yearEnd;
	}
	
	
	
	

}
