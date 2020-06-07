package com.z21.controllers;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import com.z21.be.models.school.Student;
import com.z21.be.models.school.accounts.StudentAccount;
import com.z21.fe.models.resp.StudentRegistrationResp;
import com.z21.services.StudentRegistrationService;
import com.z21.services.util.EmailUtil;

@RestController
@RequestMapping("registration/student")
public class StudentRegistrationController {
	
	@Autowired
	StudentRegistrationService studentRegistrationService;
	
	@RequestMapping(value = "register_ret", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody StudentRegistrationResp registerRet(
				@RequestParam(value = "scode", required = true) String scode, 		
				@RequestParam(value = "lastName", required = true) String lastName, 
				@RequestParam(value = "firstName", required = true) String firstName,
				@RequestParam(value = "middleName", required = false) String middleName,
				@RequestParam(value = "emailAddress", required = true) String emailAddress,
				@RequestParam(value = "birthDate", required = true) String birthDate,
				@RequestParam(value = "mobilePhone", required = true) String mobilePhone,	
				
															HttpServletRequest servletRequest,
															HttpServletResponse servletResponse) {
				
		servletResponse.addHeader("Access-Control-Allow-Origin", "*");
		
		StudentRegistrationResp resp = new StudentRegistrationResp();
		
		Student student =new Student();
		student.setType("Returning");
		student.setLastName(lastName);
		student.setFirstName(firstName);
		student.setMiddleName(middleName);
		student.setEmailAddress(emailAddress); 
		student.setBirthDate(birthDate);
		student.setMobilePhone(mobilePhone);
		
		StudentAccount studentAcc = studentRegistrationService.registerStudent(scode, student , "otc" , null);
		
		resp.setEnrollmentStatus(studentAcc.getAccountStatus());
		resp.setEmailAddress(emailAddress);
		resp.setStudentId(studentAcc.getStudentId()); 
		resp.setStudentName(lastName+" , "+firstName+" "+middleName);
		
		return  resp;
	}   
			

	@RequestMapping(value = "register_new", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody StudentRegistrationResp registerNew(
				@RequestParam(value = "scode", required = true) String scode, 		
				@RequestParam(value = "lastName", required = true) String lastName, 
				@RequestParam(value = "firstName", required = true) String firstName,
				@RequestParam(value = "middleName", required = false) String middleName,
				@RequestParam(value = "emailAddress", required = true) String emailAddress,
				@RequestParam(value = "birthDate", required = true) String birthDate,
				@RequestParam(value = "mobilePhone", required = true) String mpbilePhon,		
				@RequestParam(value = "lrn", required = false) String lrn,
				@RequestParam(value = "gender", required = false) String gender,
				@RequestParam(value = "gradeLevel", required = false) String gradeLevel,
				@RequestParam(value = "nationality", required = false) String nationality,
				@RequestParam(value = "address", required = false) String address,

															HttpServletRequest servletRequest,
															HttpServletResponse servletResponse) {
				
		servletResponse.addHeader("Access-Control-Allow-Origin", "*");
		
		StudentRegistrationResp resp = new StudentRegistrationResp();
		
		resp.setStatusMessage("Success");
		resp.setEnrollmentStatus("Pending");
		resp.setEmailAddress(emailAddress);
		resp.setStudentId(202001L);
		resp.setStudentName(lastName+" , "+firstName+" "+middleName);
		//student.set
		
		return  resp;
	}
			
	@RequestMapping(value = "register_parent", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody StudentRegistrationResp registerParent(
				@RequestParam(value = "scode", required = true) String scode, 
				@RequestParam(value = "studentId", required = true) Long studentId, 
				@RequestParam(value = "lastName", required = true) String lastName, 
				@RequestParam(value = "firstName", required = true) String firstName,
				@RequestParam(value = "middleName", required = false) String middleName,
				@RequestParam(value = "emailAddress", required = true) String emailAddress,
				@RequestParam(value = "occupation", required = true) String occupation,
				@RequestParam(value = "mobilePhone", required = true) String mpbilePhon,		
				@RequestParam(value = "address", required = false) String address,
				@RequestParam(value = "type", required = true) String type, 


															HttpServletRequest servletRequest,
															HttpServletResponse servletResponse) {
				
		servletResponse.addHeader("Access-Control-Allow-Origin", "*");
		
		StudentRegistrationResp resp = new StudentRegistrationResp();
	
		resp.setStatusMessage("Success");
		resp.setEnrollmentStatus("Pending");
		resp.setStudentId(202001L);

		return  resp;
	}
			
		
	

	
	@RequestMapping(value="uploadFile",method=RequestMethod.POST)  
	public  @ResponseBody  String 	uploadFile(	@RequestParam MultipartFile file,
																@RequestParam(value = "docDescr", required = false) String docDescr, 
																@RequestParam(value = "studentId", required = false) Long studentId, 
																@RequestParam(value = "scode", required = false) Long scode, 
			
											HttpServletResponse servletResponse,


			HttpSession session
			){  
				//StudentRegistrationResp resp = new StudentRegistrationResp();

		
				System.out.println("docDescr "+docDescr);
				System.out.println("studentId "+studentId);
				System.out.println("scode "+scode);
		
				
				System.out.println("File "+file);
				System.out.println("File "+file.getOriginalFilename());
				
				
				
				servletResponse.addHeader("Access-Control-Allow-Origin", "*");

		        String path=session.getServletContext().getRealPath("/");  
		        String filename=file.getOriginalFilename();  
		          
		        System.out.println(path+" "+filename);  
		    try{  
		        byte barr[]=file.getBytes(); 
		          
		        BufferedOutputStream bout=new BufferedOutputStream(  
		                 new FileOutputStream(path+"/"+filename));  
		        bout.write(barr);  
		        bout.flush();  
		        bout.close();  
		        
		                
	        }catch(Exception e){
	        	System.out.println(e);
	        	return "Error";
	        }  
	        
	        return "Success";
	        
	    }  	
		
	

	
	@RequestMapping(value="upload_document",method=RequestMethod.POST)  
	public  @ResponseBody  StudentRegistrationResp upload(	@RequestParam CommonsMultipartFile file,
											@RequestParam(value = "documentType", required = false) String documentType, 
											@RequestParam(value = "studentId", required = false) Long studentId, 
											@RequestParam(value = "scode", required = false) Long scode, 
											HttpServletResponse servletResponse,


			HttpSession session
			){  
				servletResponse.addHeader("Access-Control-Allow-Origin", "*");

			
				StudentRegistrationResp resp = new StudentRegistrationResp();
				
		        String path=session.getServletContext().getRealPath("/");  
		        String filename=file.getOriginalFilename();  
		          
		        System.out.println(path+" "+filename);  
		        try{  
		        byte barr[]=file.getBytes();  
		          
		        BufferedOutputStream bout=new BufferedOutputStream(  
		                 new FileOutputStream(path+"/"+filename));  
		        bout.write(barr);  
		        bout.flush();  
		        bout.close();  
		        
		        
		        
		          
	        }catch(Exception e){System.out.println(e);}  
	        
	        return resp;
	        
	    }  	
		

	@RequestMapping(value = "register", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Student register(@RequestBody Student student,
																HttpServletRequest servletRequest,
																HttpServletResponse servletResponse) {
				
		
		System.out.println("POST");
		servletResponse.addHeader("Access-Control-Allow-Origin", "*");
		StudentAccount ac  = studentRegistrationService.registerStudent("slra", student, "otc", null);
		
		return studentRegistrationService.getStudent("slra", ac.getStudentId());  
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
				
		
		System.out.println("Register Old Student");
		servletResponse.addHeader("Access-Control-Allow-Origin", "*");
		
		Student student = new Student();  
		
		student.setLastName(lastName);
		student.setFirstName(fistName); 
		student.setMiddleName(middleName);
		student.setBirthDate(birthDate); 
		
		
		System.out.println("New Student "+student); 
		//student.set
		
		StudentAccount ac  = studentRegistrationService.registerStudent("slra", student, "otc", null);
		
		return studentRegistrationService.getStudent("slra", ac.getStudentId());
	}
			 
	
	@RequestMapping(value = "findStudent", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Student> find(@RequestParam(value = "searchkey", required = true) String searchKey,
											@RequestParam(value = "value", required = true) String value,
					
											HttpServletResponse servletResponse) {			
		servletResponse.addHeader("Access-Control-Allow-Origin", "*");

		return studentRegistrationService.findStudents("slra", searchKey, value);
	}
	
	
	 
	@RequestMapping(value = "getStudent", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Student getStudent(@RequestParam(value = "scode", required = true) String scode,
											@RequestParam(value = "studentId", required = true) Long studentId,
					
											HttpServletResponse servletResponse) {			
		servletResponse.addHeader("Access-Control-Allow-Origin", "*");

		return studentRegistrationService.getStudent(scode, studentId);
	}
	
	
	@RequestMapping(value = "sendConfirmationEmail", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String sendEmail(@RequestParam(value = "scode", required = true) String scode,
										@RequestParam(value = "studentId", required = true) Long studentId,		
										HttpServletResponse servletResponse) {			

		servletResponse.addHeader("Access-Control-Allow-Origin", "*");

		return studentRegistrationService.setEmail(scode, studentId);
	}
	 

	@RequestMapping(value = "sendUserSearchEmail", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String sendUserSearchEmail(@RequestParam(value = "scode", required = true) String scode,
										@RequestParam(value = "emailAddress", required = true) String  emailAddress,		
										HttpServletResponse servletResponse) {			

		servletResponse.addHeader("Access-Control-Allow-Origin", "*");

		
		System.out.println("Send user Search email.");
		
		return studentRegistrationService.sendUserSearchEmail(scode, emailAddress); 
	}
	
	
	

	@RequestMapping(value = "getRegistrationStatus", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody StudentRegistrationResp getRegistrationStatus(@RequestParam(value = "scode", required = true) String scode,
																		@RequestParam(value = "studentId", required = true) Long  studentId,		
										HttpServletResponse servletResponse) {			
		servletResponse.addHeader("Access-Control-Allow-Origin", "*");

		StudentRegistrationResp resp = new StudentRegistrationResp();
		
		StudentAccount acc = studentRegistrationService.getStudentAccount(scode, studentId); 
		Student student = studentRegistrationService.getStudent(scode, studentId);
		
		
		
		if(acc != null) {
			resp.setEnrollmentStatus(acc.getAccountStatus());
			resp.setStudentId(studentId);
			resp.setStudentName(student.getFirstName()+" "+student.getMiddleName()+" "+ student.getLastName());
			resp.setStudentType(student.getType());
			resp.setEmailAddress(student.getEmailAddress());
				
		
			resp.setNotes(acc.getNotes());
			
			resp.setStatusMessage(acc.getRegistrarsNotes());
			
			/*if(acc.getAccountStatus() != null && acc.getAccountStatus().equals("Pending")) {
				resp.setStatusMessage("Please upload a picture of your prof of payment.");
			}
			*/ 
		   
			resp.setPaymentSelected(acc.getPreferredPayment());
		}
		
		return resp;
	}
	
	
	

	@RequestMapping(value = "updateRegistrationStatus", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody StudentRegistrationResp updateRegistrationStatus(   @RequestParam(value = "scode", required = true) String scode,
																			 @RequestParam(value = "studentId", required = true) String  emailAddress,	
																			 @RequestParam(value = "status", required = true) String  status,	
																			 @RequestParam(value = "token", required = true) String  token,	

										HttpServletResponse servletResponse) {			
		servletResponse.addHeader("Access-Control-Allow-Origin", "*");

		StudentRegistrationResp resp = new StudentRegistrationResp();
		//studentRegistrationService.updateStudentAccount(schooCode, acc)
		
		return resp;
	}
	
	
	  
}
