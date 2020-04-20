package com.z21.services;

import java.util.List;

import com.z21.be.models.Student;
import com.z21.dao.StudentRegistrationDao;



public class StudentRegistrationService {
	
	private StudentRegistrationDao studentRegistrationDao;
	
	public Student registerNewStudent( Student student) {
		
		return studentRegistrationDao.registerNewStudent(student);
	}
	
	public List<Student> findStudents(String key, String filter) {
		return studentRegistrationDao.findStudents(key, filter);
	}

	public StudentRegistrationDao getStudentRegistrationDao() {
		return studentRegistrationDao;
	}

	public void setStudentRegistrationDao(StudentRegistrationDao studentRegistrationDao) {
		this.studentRegistrationDao = studentRegistrationDao;
	}
	
	
}
