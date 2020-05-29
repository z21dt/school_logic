package com.z21.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.z21.be.models.school.Parent;
import com.z21.be.models.school.Student;
import com.z21.be.models.school.accounts.StudentAccount;
import com.z21.fe.models.EnrolleeReq;
import com.z21.services.StudentRegistrationService;

@Controller
@RequestMapping("enrollment/student")
public class SimplifiedEnrollmentController {	
	
	@Autowired
	StudentRegistrationService studentRegistrationService;
	

	@RequestMapping(value = "enroll", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Student register(@RequestBody EnrolleeReq e,
																HttpServletRequest servletRequest,
																HttpServletResponse servletResponse) {
				
		
		System.out.println("POST");
		servletResponse.addHeader("Access-Control-Allow-Origin", "*");
		
		
		System.out.println("EnrolleeReq-> "+e);
		
		Student student = new Student();
		
		student.setLastName(e.getLastName());
		student.setFirstName(e.getFirstName());
		student.setMiddleName(e.getMiddleName());
		student.setBirthDate(e.getBirthDay());
		student.setGradeLevel(e.getGradeLevel());
		student.setMobilePhone(e.getContactNumber());
		student.setType(e.getStudentType());
		student.setAddress(e.getAddress());
		student.setEmailAddress(e.getEmailAddress());
		
		
		Parent guardian = new Parent(); 
		guardian.setLastname(e.getLastName());
		guardian.setContactNumber(e.getContactNumber());
		guardian.setType(3);
			
		student.setGuardian(guardian);
			 
		
		StudentAccount ac  = studentRegistrationService.registerStudent( e.getSchoolId(), student, e.getPaymentSelected());
		
		return studentRegistrationService.getStudent(e.getSchoolId(), ac.getStudentId());
	}
			 

	
}
