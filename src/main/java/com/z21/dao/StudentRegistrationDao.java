package com.z21.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.z21.be.models.school.Student;
import com.z21.be.models.school.accounts.StudentAccount; 
import com.z21.mongo.MongoManager;
import com.z21.services.util.EmailUtil;
 
public class StudentRegistrationDao extends AbstractDao{
	
	private String emailSender;
	
	private String emailTitle;
	
	private String emailMessage;
	
	private String emailPass;
	
	public StudentRegistrationDao() {
		
	
		System.out.println("Initialize UserAuthenticationDao");
		this.collection = "students";
	}
	
	public Student getStudent(String schoolCode, Long studentId) {
		
		Student students = null;
		
		MongoManager mongo = getMongoManager(schoolCode);
		DBCollection collection = mongo.getDB().getCollection(this.collection);
		
	    BasicDBObject whereQuery = new BasicDBObject();
	    whereQuery.put("studentId", studentId);
		
		DBCursor cursor = collection.find(whereQuery);
		
		 
		
		
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			
			students = convertToStudent(obj);

			break;
		}
		
		return students;
	} 
	
	
	public StudentAccount getStudentAccount(String schoolCode, Long studentId) {
		
		StudentAccount acc = new StudentAccount();
		
		MongoManager mongo = getMongoManager(schoolCode);
		DBCollection collection = mongo.getDB().getCollection("studentaccounts");
		
	    BasicDBObject whereQuery = new BasicDBObject();
	    whereQuery.put("studentId", studentId);
		
		DBCursor cursor = collection.find(whereQuery);
		
		 
		
		
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			
			if(obj != null) {
				acc.setAccountStatus(getString(obj , "accountStatus") );
				acc.setStudentId(getLong(obj , "studentId") );
				acc.setNotes(getString(obj , "notes") );
			}

			break;
		}
		
		return acc;
	} 
	
	
	
	
	public StudentAccount registerStudent(String schoolCode,  Student student) {
		
		MongoManager mongo = getMongoManager(schoolCode);
		DBCollection collection = mongo.getDB().getCollection(this.collection);	
				
		Long id = generateId(collection.getCount());
		student.setStudentId(id);
		
		BasicDBObject object = createStudent(student);
		WriteResult result = collection.insert(object);
		System.out.println(result.getUpsertedId());
		System.out.println(result.getN());
		System.out.println(result.isUpdateOfExisting());
		
	
		mongo.close(); 
		
		StudentAccount  account = createAccount(schoolCode, student );
		System.out.println("account "+account);
		
		return account;	
	}
	

	
	
	 
	private StudentAccount createAccount(String schoolCode, Student student) {
		StudentAccount acc = new StudentAccount();
		
		acc.setStudentId(student.getStudentId()); 
		acc.setAccountStatus(StudentAccount.STATUS_PENDING);
		acc.setDateCreated(new Date() );
		
		
		
		MongoManager mongo = getMongoManager(schoolCode);
		DBCollection collection = mongo.getDB().getCollection("studentaccounts");	
		Long id = generateId(collection.getCount());
		acc.setStudentId(id);
		
		WriteResult result = collection.insert(createDBObject(acc));
		
		
		return acc;
	}
	
	private BasicDBObject createStudent( Student student ) {
		
		BasicDBObject obj = new BasicDBObject();
		obj.append("studentId", student.getStudentId());		
		obj.append("address", student.getAddress());
		obj.append("age", student.getAge());
		obj.append("birthDate", student.getBirthDate());
		obj.append("firstName", student.getFirstName());
		obj.append("gender", student.getGender());
		obj.append("gradeLevel", student.getGradeLevel());
		obj.append("lastName", student.getLastName());
		obj.append("lrn", student.getLrn());
		obj.append("middleName", student.getMiddleName());
		obj.append("mobilePhone", student.getMobilePhone());
		obj.append("emailAddress", student.getEmailAddress());
		obj.append("type", student.getType());
		
		return obj;
		
	}
	
	
	private Long generateId(Long count) {
		count++;
		String id = "2020";
		if(count < 10) {
			id = id + "00"+count;
		}else if(count > 9 && count < 100 ) {
			id = id +"0"+count;
		}
		return new Long(id);
	}


	
	public List<Student> findStudents(String schoolCode, String key, String filter) {
		
		List<Student> students = new ArrayList<Student>();
		
		MongoManager mongo = getMongoManager(schoolCode);
		DBCollection collection = mongo.getDB().getCollection(this.collection);
		
		
		DBCursor cursor = null;
		
		if(key != null && filter != null) {
		    BasicDBObject whereQuery = new BasicDBObject();
		    whereQuery.put(key, filter);
		     cursor  = collection.find(whereQuery);
		}else {
			 cursor = collection.find();
		}
			
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			
			students.add(convertToStudent(obj));
		}
		
		return students;
	} 
	
	
	private Student convertToStudent(DBObject obj) {
		
		Student student = new Student();
		
		student.setStudentId(getLong(obj, "studentId"));
		student.setAddress(getString(obj, "address"));
		student.setAge(getLong(obj, "age"));
		student.setBirthDate(getString(obj, "birthDate"));
		student.setFirstName(getString(obj, "firstName"));
		student.setGender(getString(obj, "gender"));
		student.setGradeLevel(getString(obj, "gradeLevel"));
		student.setLastName(getString(obj, "lastName"));
		student.setLrn(getString(obj, "lrn"));
		student.setMiddleName(getString(obj, "middleName"));
		student.setNationality(getString(obj, "nationality"));
		student.setType(getString(obj, "type"));
		student.setEmailAddress(getString(obj, "emailAddress"));
		student.setMobilePhone(getString(obj, "mobilePhone"));
		
		return student;
			
		
	}
	
	
	public String sendEmail(String scode, Long studentId) {
		
		Student student = this.getStudent(scode, studentId);	
		StudentAccount acc =this.getStudentAccount(scode, studentId);
		
		System.out.println("Send email to "+student.getEmailAddress());
			
		String message =emailMessage.replaceAll("FULLNAME", student.getLastName()+", "+student.getFirstName()+" "+student.getMiddleName());
		 message =message.replaceAll("STATUS", acc.getAccountStatus());
		 message =message.replaceAll("STUDENTID", studentId+"");

		
		EmailUtil util = new EmailUtil(this.emailSender, this.emailPass);
		
		System.out.println("Mssage "+message);
		
		util.send(student.getEmailAddress(),emailTitle ,message);  
		
		return "Sent to "+student.getEmailAddress();
	}
	

	
	public String sendUserSearchEmail(String scode, String email) {
		
		List<Student> students = findStudents(scode, "emailAddress", email);
		
		String accs = "";
		
		if(students != null) {
			for(int i = 0 ; i < students.size(); i++) {
				Student student = students.get(i);
				
				StudentAccount acc =this.getStudentAccount(scode, student.getStudentId());
				
				String fullname = student.getLastName()+", "+student.getFirstName()+" "+student.getMiddleName();
				System.out.println("Send account email to "+student.getEmailAddress());
					
				String message = "Hi "+fullname+", \n    Your account number is: "+student.getStudentId()+".    \nStatus: "+acc.getAccountStatus()+" \n\n You may check your enrollment status here..";

	
				EmailUtil util = new EmailUtil(this.emailSender, this.emailPass);
				
				System.out.println("Mssage "+message);
				
				accs = accs + "\n " +(i+1)+".) "+fullname ;
				
				
				util.send(student.getEmailAddress(),"Student Registration status inquiry." ,message);  
			}
		}
		
		
		accs = "Email sent to "+email+"  for " + accs;
		return accs;
	}
 
	
	public String getEmailSender() {
		return emailSender;
	}

	public void setEmailSender(String emailSender) {
		this.emailSender = emailSender;
	}

	public String getEmailTitle() {
		return emailTitle;
	}

	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}

	public String getEmailMessage() {
		return emailMessage;
	}

	public void setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
	}

	public String getEmailPass() {
		return emailPass;
	}

	public void setEmailPass(String emailPass) {
		this.emailPass = emailPass;
	}
	
	
	
	
}


