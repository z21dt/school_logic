package com.z21.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.z21.be.models.SchoolInfo;
import com.z21.be.models.Student;
import com.z21.services.StudentRegistrationService;

@Controller
@RequestMapping("registration/student")
public class StudentRegistrationController {
	
	
	@Autowired
	StudentRegistrationService studentRegistrationService;

	@RequestMapping(value = "register", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Student register(@RequestBody Student student,
																HttpServletRequest servletRequest,
																HttpServletResponse servletResponse) {
				
		servletResponse.addHeader("Access-Control-Allow-Origin", "*");
		return studentRegistrationService.registerNewStudent(student);
	}
			 

	@RequestMapping(value = "registerOld", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Student registerOldStudent(
				@RequestParam(value = "lastName", required = true) String lastName,
				@RequestParam(value = "firstName", required = true) String fistName,
				@RequestParam(value = "middleName", required = false) String middleName,
				@RequestParam(value = "emailAddress", required = false) String emailAddress,
				@RequestParam(value = "birthDate", required = false) String birthDate,
				
				
																HttpServletRequest servletRequest,
																HttpServletResponse servletResponse) {
				
		servletResponse.addHeader("Access-Control-Allow-Origin", "*");
		
		Student student = new Student();
		
		student.setLastName(lastName);
		student.setFirstName(fistName);
		student.setMiddleName(middleName);
		student.setBirthDate(birthDate);
		
		
		System.out.println("New Student "+student); 
		//student.set
		
		return studentRegistrationService.registerNewStudent(student);
	}
			 

	
	@RequestMapping(value = "findStudent", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Student> find(@RequestParam(value = "searchkey", required = true) String searchKey,
											@RequestParam(value = "value", required = true) String value,
					
											HttpServletResponse servletResponse) {			
		servletResponse.addHeader("Access-Control-Allow-Origin", "*");

		return studentRegistrationService.findStudents(searchKey, value);
	}

	

}
