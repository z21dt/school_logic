package com.z21.be.models.school;

public class Student {
		
	private Long studentId;
		
	private String lrn;
	
	private String firstName;
	
	private String lastName;
	
	private String middleName; 
	
	private String gender;
	
	private String nationality;
	
	private String gradeLevel; 
	
	private String address;
	
	private String birthDate;
	
	private Long age;
	
	private String mobilePhone;
		
	private String emailAddress;
	
	private Parent father;
	
	private Parent mother;
	
	private Parent guardian;
	
	private String type;
	

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getLrn() {
		return lrn;
	}

	public void setLrn(String lrn) {
		this.lrn = lrn;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Parent getFather() {
		return father;
	}

	public void setFather(Parent father) {
		this.father = father;
	}

	public Parent getMother() {
		return mother;
	}

	public void setMother(Parent mother) {
		this.mother = mother;
	}

	public Parent getGuardian() {
		return guardian;
	}

	public void setGuardian(Parent guardian) {
		this.guardian = guardian;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getType() {
		return type;
	} 

	public void setType(String type) {
		this.type = type;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress; 
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", lrn=" + lrn + ", firstName=" + firstName + ", lastName="
				+ lastName + ", middleName=" + middleName + ", gender=" + gender + ", nationality=" + nationality
				+ ", gradeLevel=" + gradeLevel + ", address=" + address + ", birthDate=" + birthDate + ", age=" + age
				+ ", mobilePhone=" + mobilePhone + ", emailAddress=" + emailAddress + ", father=" + father + ", mother="
				+ mother + ", guardian=" + guardian + ", type=" + type + "]";
	}
	
	

}
