package com.z21.services;

import java.util.List;

import com.z21.be.models.school.ReferenceDocument;
import com.z21.be.models.school.Student;
import com.z21.be.models.school.accounts.StudentAccount;
import com.z21.dao.StudentRegistrationDao;
import com.z21.fe.models.resp.EnrolleesResp;



public class StudentRegistrationService {
	
	private StudentRegistrationDao studentRegistrationDao;
	
	public StudentAccount registerStudent(String schoolCode,  Student student, String preferredPayment, String notes) {
		 
		return studentRegistrationDao.registerStudent(schoolCode, student, preferredPayment, notes);
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
	
    
	public StudentAccount updateStudentAccount(String schooCode, StudentAccount acc) {
		return studentRegistrationDao.updateStudentAccount(schooCode, acc);
	}

    
	public String setEmail(String scode, Long studentId) {
		return studentRegistrationDao.sendEmail(scode,studentId );
	}
 
	
	public String sendUserSearchEmail(String scode, String email) {
		return studentRegistrationDao.sendUserSearchEmail(scode,email );
	}
 
	
	public List<EnrolleesResp> getEnrollees(String schoolCode) {
		return studentRegistrationDao.getEnrollees(schoolCode);
	}
	
	
	public List<ReferenceDocument> getReferenceDocuments(String scode, Long studentId ) {
		return studentRegistrationDao.getReferenceDocuments(scode, studentId);
	}
	
	public void saveReferenceDocument(String scode, ReferenceDocument ref ) {
		studentRegistrationDao.saveReferenceDocument(scode, ref);
	}
	
}
 