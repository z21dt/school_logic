package com.z21.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.z21.be.models.school.Parent;
import com.z21.be.models.school.ReferenceDocument;
import com.z21.be.models.school.Student;
import com.z21.be.models.school.accounts.StudentAccount;
import com.z21.fe.models.AttachmentReq;
import com.z21.fe.models.EnrolleeReq;
import com.z21.fe.models.EnrollmentStatusReq;
import com.z21.fe.models.resp.EnrolleeListResp;
import com.z21.fe.models.resp.RefDocumentResp;
import com.z21.fe.models.resp.StudentRegistrationResp;
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
			 
		
		StudentAccount ac  = studentRegistrationService.registerStudent( e.getSchoolId(), student, e.getPaymentSelected(), e.getNotes());
		
		return studentRegistrationService.getStudent(e.getSchoolId(), ac.getStudentId());
	}
			 

	

	@RequestMapping(value = "updateStatus", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody StudentRegistrationResp updateRegistrationStatus( @RequestBody EnrollmentStatusReq req,
																			HttpServletRequest servletRequest,
																			HttpServletResponse servletResponse) {
		servletResponse.addHeader("Access-Control-Allow-Origin", "*");

		StudentRegistrationResp resp = new StudentRegistrationResp();
		
		StudentAccount acc = new StudentAccount();
		acc.setStudentId(req.getStudentId());
		//acc.setNotes(req.getNotes());
		acc.setRegistrarsNotes(req.getRegNotes());
		acc.setAccountStatus(req.getEnrollmentStatus());
		
		acc = studentRegistrationService.updateStudentAccount(req.getSchoolId() , acc);
		
		resp.setEnrollmentStatus(acc.getAccountStatus());
		resp.setNotes(acc.getNotes());
		resp.setStudentId(acc.getStudentId());	
		
		return resp;
	}
	

	@RequestMapping(value = "getEnrollees", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody EnrolleeListResp getRegistrationStatus(@RequestParam(value = "scode", required = true) String scode,																	
										HttpServletResponse servletResponse) {			
		servletResponse.addHeader("Access-Control-Allow-Origin", "*");

		EnrolleeListResp resp = new EnrolleeListResp();  
		resp.setEnrollees(studentRegistrationService.getEnrollees(scode));  
		
		return resp;
	}
	
	
	@RequestMapping(value = "getAttachmentDetails", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody RefDocumentResp getAttachments(@RequestParam(value = "scode", required = true) String scode,		
														 @RequestParam(value = "studentId", required = true) Long studentId,	
										HttpServletResponse servletResponse) {			
		servletResponse.addHeader("Access-Control-Allow-Origin", "*");

		RefDocumentResp resp = new RefDocumentResp();  
		resp.setAttachments(studentRegistrationService.getReferenceDocuments(scode, studentId));
		
		return resp;
	}
	
	@RequestMapping(value = "saveAttachmentDetails", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ReferenceDocument saveAttachment( @RequestBody AttachmentReq att) {
		
		ReferenceDocument doc= new ReferenceDocument();
		
		doc.setDocName(att.getDocName());
		doc.setPath(att.getPath());
		doc.setStudentId(att.getStudentId());
		doc.setFileName(att.getFileName());
		studentRegistrationService.saveReferenceDocument(att.getSchoolId(), doc);
		
		
		return doc;
		
	}
	
	public void saveReferenceDocument(String scode, ReferenceDocument ref ) {
		
	}
	
	
	  
	
}
