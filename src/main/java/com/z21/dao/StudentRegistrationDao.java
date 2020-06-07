package com.z21.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.z21.be.models.school.ReferenceDocument;
import com.z21.be.models.school.Student;
import com.z21.be.models.school.accounts.StudentAccount;
import com.z21.dao.util.EmailHandlerThread;
import com.z21.fe.models.resp.EnrolleesResp;
import com.z21.mongo.MongoManager;
 
public class StudentRegistrationDao extends AbstractDao{
	
	private String emailSender;
	
	private String emailTitle;
	
	private String emailMessage;
	
	private String emailPass;
	
	public StudentRegistrationDao() {
		
	
		System.out.println("Initialize UserAuthenticationDao");
		this.collection = "students";
	}
	
	public void saveReferenceDocument(String scode, ReferenceDocument ref ) {
			
		MongoManager mongo = getMongoManager();
		DBCollection collection = mongo.getDB().getCollection(scode+"_"+"studentdocuments");	
		//Long id = generateId(collection.getCount());
		//acc.setStudentId(id);		
		//WriteResult result = collection.insert(createDBObject(ref));	
		
	    BasicDBObject whereQuery = new BasicDBObject();
	    whereQuery.put("studentId", ref.getStudentId());
		
		
		WriteResult result = collection.update(whereQuery, createDBObject(ref), true, false);
		
		mongo.close();
		
	}
	
	
	public List<ReferenceDocument> getReferenceDocuments(String scode, Long studentId ) {
		
		
		List<ReferenceDocument>  documents = new ArrayList<ReferenceDocument>();
		
		MongoManager mongo = getMongoManager();
		DBCollection collection = mongo.getDB().getCollection(scode+"_"+"studentdocuments");	
		
	    BasicDBObject whereQuery = new BasicDBObject();
	    whereQuery.put("studentId", studentId);
		
		DBCursor cursor = collection.find(whereQuery);
		
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			
			ReferenceDocument doc = new ReferenceDocument();
			doc.setDocName(getString(obj, "docName"));
			doc.setPath(getString(obj, "path"));
			doc.setStudentId(getLong(obj, "studentId"));
			documents.add(doc);
			
		}		
		mongo.close();
		
		return documents;
		
	}
	
	
	public Student getStudent(String schoolCode, Long studentId) {
		
		Student students = null;
		
		MongoManager mongo = getMongoManager();
		DBCollection collection = mongo.getDB().getCollection(schoolCode+"_"+this.collection);
		
	    BasicDBObject whereQuery = new BasicDBObject();
	    whereQuery.put("studentId", studentId);
		
		DBCursor cursor = collection.find(whereQuery);
			 	
		
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			
			students = convertToStudent(obj);

			break;
		}
		mongo.close();
		return students;
	} 
	
	
	public List<EnrolleesResp> getEnrollees(String schoolCode) {
		List<EnrolleesResp> enrollees = new ArrayList<EnrolleesResp>();
		
		MongoManager mongo = getMongoManager();
		DBCollection collection = mongo.getDB().getCollection(schoolCode+"_"+this.collection);
		
		DBCursor cursor = collection.find();
			 	
		
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			
			EnrolleesResp enrollee = new EnrolleesResp();
			
			enrollee.setGradeLevel(getString(obj, "gradeLevel"));
			
			String name = getString(obj, "firstName")+" "+getString(obj, "middleName")+" "+getString(obj, "lastName");
			enrollee.setStudentName(name);			
			enrollee.setStudentType(getString(obj, "type"));
			enrollee.setStudentId(getLong(obj, "studentId"));

			
	
			StudentAccount acc  = getStudentAccount(schoolCode, getLong(obj, "studentId"));
			
			enrollee.setStatus(acc.getAccountStatus());
			enrollee.setNotes(acc.getNotes());
			enrollee.setPreferredPayment(acc.getPreferredPayment());	
			enrollee.setRegNotes(acc.getRegistrarsNotes());

			
			enrollees.add(enrollee);
			
		}
		mongo.close();
		
		return enrollees;
		
	}
	
	public StudentAccount getStudentAccount(String schoolCode, Long studentId) {
		
		StudentAccount acc = new StudentAccount();
		
		MongoManager mongo = getMongoManager();
		DBCollection collection = mongo.getDB().getCollection(schoolCode+"_"+"studentaccounts");
		
	    BasicDBObject whereQuery = new BasicDBObject();
	    whereQuery.put("studentId", studentId);
		
		DBCursor cursor = collection.find(whereQuery);
				
		
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			
			if(obj != null) {
				
				acc.setAccountStatus(getString(obj , "accountStatus") );
				acc.setStudentId(getLong(obj , "studentId") );
				acc.setNotes(getString(obj , "notes") );
				acc.setPreferredPayment(getString(obj, "preferredPayment"));
				acc.setRegistrarsNotes(getString(obj, "registrarsNotes"));
				
				Long dateLong = (Long)obj.get("dateCreated");
				if( dateLong != null) {
					acc.setDateCreated(new Date(dateLong) );

				}
				
			}

			break;
		}
		
		mongo.close();

		return acc;
	} 
	
	
	
	
	public StudentAccount registerStudent(String schoolCode,  Student student, String preferredPayment, String notes) {
		
		MongoManager mongo = getMongoManager();
		DBCollection collection = mongo.getDB().getCollection(schoolCode+"_"+this.collection);	
				
		Long id = generateId(collection.getCount());
		student.setStudentId(id);
		
		BasicDBObject object = createStudent(student);
		WriteResult result = collection.insert(object);
		System.out.println(result.getUpsertedId());
		System.out.println(result.getN());
		System.out.println(result.isUpdateOfExisting());		
	
		mongo.close(); 
		
		
		StudentAccount  account = createAccount(schoolCode, student, preferredPayment, notes );
		System.out.println("account "+account);
		
		this.sendEmail(schoolCode, id);
		
		String regMessage = "New Enrollee: "+student.getLastName()+", "+student.getFirstName()+"\n"+
							"Student Type: "+student.getType()+"\n"+
							"Email: "+student.getEmailAddress()+"\n"+
							"Contract # "+student.getMobilePhone()+"\n"+						
							"Preferred Payment Mode: "+preferredPayment;
							
		
		  
		
		emailRegistrar(  schoolCode, "Ping! New Registration: "+student.getLastName()+", "+student.getFirstName(), regMessage );
		

		return account;	
	}
	

	private StudentAccount createAccount(String schoolCode, Student student, String preferredPayment, String notes) {
		StudentAccount acc = new StudentAccount();
		
		acc.setStudentId(student.getStudentId()); 
		acc.setAccountStatus(StudentAccount.STATUS_PENDING);
		acc.setDateCreated(new Date() );
		acc.setPreferredPayment(preferredPayment); 
		acc.setNotes(notes);
		
		
		MongoManager mongo = getMongoManager();
		DBCollection collection = mongo.getDB().getCollection(schoolCode+"_"+"studentaccounts");	
		//Long id = generateId(collection.getCount());
		//acc.setStudentId(id);
		
		WriteResult result = collection.insert(createDBObject(acc));
		
		mongo.close();

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
		
		System.out.println("Find student "+schoolCode+" ("+this.collection+") : "+key+" -> "+filter);
		
		MongoManager mongo = getMongoManager();
		DBCollection collection = mongo.getDB().getCollection(schoolCode+"_"+this.collection);
		
		
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
		
		mongo.close();
		
		
		

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

	
	private void emailRegistrar( String schoolCode, String title, String message) {
		
		//todo get schoolEmail;
		
		String email = "slra.tagum@gmail.com";
		
		System.out.println("Email the registrar "+email);
		
		EmailHandlerThread emailThread = new EmailHandlerThread(this.emailSender, this.emailPass, title ,email, message);

		System.out.println("To Registrat Mssage "+message);
		
		//util.send(student.getEmailAddress(),emailTitle ,message);  
		emailThread.start();
		
	}
	
	public String sendEmail(String scode, Long studentId) {
		
		Student student = this.getStudent(scode, studentId);	
		StudentAccount acc =this.getStudentAccount(scode, studentId); 
		
		System.out.println("Send email to "+student.getEmailAddress());
			
		String message =emailMessage.replaceAll("FULLNAME", student.getLastName()+", "+student.getFirstName()+" "+student.getMiddleName());
		 message =message.replaceAll("STATUS", acc.getAccountStatus());
		 message =message.replaceAll("STUDENTID", studentId+"");

		
		//EmailUtil util = new EmailUtil(this.emailSender, this.emailPass);
		EmailHandlerThread emailThread = new EmailHandlerThread(this.emailSender, this.emailPass, emailTitle, student.getEmailAddress(), message);

		System.out.println("Mssage "+message);
		
		//util.send(student.getEmailAddress(),emailTitle ,message);  
		emailThread.start();
		
		return "Sent to "+student.getEmailAddress();
	}
	

	
	
	
	
	public String sendUserSearchEmail(String scode, String email) {
		
		
		System.out.println("Send user search email. "+scode+" --> "+email);
		
		
		List<Student> students = findStudents(scode, "emailAddress", email);
		
		System.out.println("Students: "+students);
		
		String accs = "";
		
		if(students != null) {
			for(int i = 0 ; i < students.size(); i++) {
				Student student = students.get(i);
				
				StudentAccount acc =this.getStudentAccount(scode, student.getStudentId());
				
				String fullname = student.getLastName()+", "+student.getFirstName()+" "+student.getMiddleName();
				System.out.println("Send account email to "+student.getEmailAddress());
					
				String message = "Hi "+fullname+", \n    Your registration code is: "+student.getStudentId()+".    \nStatus: "+acc.getAccountStatus()+" \n\n You may check your enrollment status <a href=\"http://localhost:4200/slra/registration/checkStatus?regcode"+student.getStudentId()+"\" >here</a>..";

	
				//EmailUtil util = new EmailUtil(this.emailSender, this.emailPass);
				
				System.out.println("Mssage "+message);
				
				accs = accs + "\n " +(i+1)+".) "+fullname ;
				 
				
				//util.send(student.getEmailAddress(),"Student Registration status inquiry." ,message);  
				
				EmailHandlerThread emailThread = new EmailHandlerThread(this.emailSender, this.emailPass, "Student Registration status inquiry - "+fullname, student.getEmailAddress(), message);
				emailThread.start();
			}
		}
		
		
		accs = "Email sent to "+email+"  for " + accs;
		return accs;
	}
 
	
	
	public StudentAccount updateStudentAccount(String schooCode, StudentAccount acc) {
		
		System.out.println("Update student Account Status "+acc);
		
		StudentAccount db_acc = getStudentAccount(schooCode, acc.getStudentId());
		
		db_acc.setAccountStatus(acc.getAccountStatus());
		db_acc.setRegistrarsNotes(acc.getRegistrarsNotes());
		
		MongoManager mongo = getMongoManager();
		DBCollection collection = mongo.getDB().getCollection(schooCode+"_"+"studentaccounts");
		
		
	    BasicDBObject whereQuery = new BasicDBObject();
	    whereQuery.put("studentId", acc.getStudentId());
	    
	    
	    collection.update(whereQuery, createDBObject(db_acc), false, false);

	    mongo.close();
		
		return db_acc;
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


