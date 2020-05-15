package com.z21.services;

import java.util.List;

import com.z21.be.models.school.Student;
import com.z21.be.models.school.accounts.StudentAccount;
import com.z21.dao.StudentRegistrationDao;



public class StudentRegistrationService {
	
	private StudentRegistrationDao studentRegistrationDao;
	
	public StudentAccount registerStudent(String schoolCode,  Student student) {
		
		return studentRegistrationDao.registerStudent(schoolCode, student);
	}
	
	public List<Student> findStudents(String schoolCode, String key, String filter) {
		return studentRegistrationDao.findStudents(schoolCode, key, filter); 
	}

	public StudentRegistrationDao getStudentRegistrationDao() {
		return studentRegistrationDao;
	} 

	public void setStudentRegistrationDao(StudentRegistrationDao studentRegistrationDao) {
		this.studentRegistrationDao = studentRegistrationDao;
	}
	
	public Student getStudent(String schoolCode, Long studentId) {
		return studentRegistrationDao.getStudent(schoolCode, studentId);
	}
	 
    public StudentAccount getStudentAccount(String schoolCode, Long studentId) {
    	return studentRegistrationDao.getStudentAccount(schoolCode, studentId);
    }
	
	public String setEmail(String scode, Long studentId) {
		return studentRegistrationDao.sendEmail(scode,studentId );
	}
 
	
	public String sendUserSearchEmail(String scode, String email) {
		return studentRegistrationDao.sendUserSearchEmail(scode,email );
	}
 
 
	
}
 