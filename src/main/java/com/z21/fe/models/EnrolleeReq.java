package com.z21.fe.models;

public class EnrolleeReq {

private String regCode;
private String studentType;
private String firstName;
private String middleName;
private String lastName;

private String gender;
private String birthDay;
private String gradeLevel;
private String lastSchoolAttended;
private String lastSchoolAddress;
 

private String parentsName;
private String address;
private String contactNumber;
private String emailAddress;
private String notes;

private String enrollmentStatus;

private String schoolId;

private String paymentSelected;
 
public String getRegCode() {
	return regCode;
}

public void setRegCode(String regCode) {
	this.regCode = regCode;
}

public String getStudentType() {
	return studentType;
}

public void setStudentType(String studentType) {
	this.studentType = studentType;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getMiddleName() {
	return middleName;
}

public void setMiddleName(String middleName) {
	this.middleName = middleName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getBirthDay() {
	return birthDay;
}

public void setBirthDay(String birthDay) {
	this.birthDay = birthDay;
}

public String getGradeLevel() {
	return gradeLevel;
}

public void setGradeLevel(String gradeLevel) {
	this.gradeLevel = gradeLevel;
}

public String getLastSchoolAttended() {
	return lastSchoolAttended;
}

public void setLastSchoolAttended(String lastSchoolAttended) {
	this.lastSchoolAttended = lastSchoolAttended;
}

public String getLastSchoolAddress() {
	return lastSchoolAddress;
}

public void setLastSchoolAddress(String lastSchoolAddress) {
	this.lastSchoolAddress = lastSchoolAddress;
}

public String getParentsName() {
	return parentsName;
}

public void setParentsName(String parentsName) {
	this.parentsName = parentsName;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getContactNumber() {
	return contactNumber;
}

public void setContactNumber(String contactNumber) {
	this.contactNumber = contactNumber;
}

public String getEmailAddress() {
	return emailAddress;
}

public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
}

public String getNotes() {
	return notes;
}

public void setNotes(String notes) {
	this.notes = notes;
}

public String getEnrollmentStatus() {
	return enrollmentStatus;
}

public void setEnrollmentStatus(String enrollmentStatus) {
	this.enrollmentStatus = enrollmentStatus;
}

public String getSchoolId() {
	return schoolId;
}

public void setSchoolId(String schoolId) {
	this.schoolId = schoolId;
}

public String getPaymentSelected() {
	return paymentSelected;
}

public void setPaymentSelected(String paymentSelected) {
	this.paymentSelected = paymentSelected;
}

@Override
public String toString() {
	return "EnrolleeReq [regCode=" + regCode + ", studentType=" + studentType + ", firstName=" + firstName
			+ ", middleName=" + middleName + ", lastName=" + lastName + ", gender=" + gender + ", birthDay=" + birthDay
			+ ", gradeLevel=" + gradeLevel + ", lastSchoolAttended=" + lastSchoolAttended + ", lastSchoolAddress="
			+ lastSchoolAddress + ", parentsName=" + parentsName + ", address=" + address + ", contactNumber="
			+ contactNumber + ", emailAddress=" + emailAddress + ", notes=" + notes + ", enrollmentStatus="
			+ enrollmentStatus + ", schoolId=" + schoolId + ", paymentSelected=" + paymentSelected + "]";
}




}
