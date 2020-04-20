package com.z21.dao;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.z21.be.models.Student;
import com.z21.mongo.MongoManager;

public class StudentRegistrationDao extends AbstractDao{
	
	public StudentRegistrationDao() {
		
		System.out.println("Initialize UserAuthenticationDao");
		this.collection = "students";
		this.setDatabase("schooldb");
	}
	
	public Student registerNewStudent( Student student) {
		
		MongoManager mongo = getMongoManager();
		DBCollection collection = mongo.getDB().getCollection(this.collection);
		
		
		//collection.insert(createStudentObject(student) );
		WriteResult result = collection.insert(createStudentObject(student));
			
		System.out.println(result.getUpsertedId());
		System.out.println(result.getN());
		System.out.println(result.isUpdateOfExisting());
		mongo.close(); 
		return student;	
	}
	
	

	private DBObject createStudentObject(Student user) {
		BasicDBObject dbObject = new BasicDBObject();
		
	      ObjectMapper mapper = new ObjectMapper();
	      //Converting the Object to JSONString
	      try {
			String jsonString = mapper.writeValueAsString(user);
			
			System.out.print("JSON "+jsonString);
			
			 dbObject = (BasicDBObject) JSON.parse(jsonString);
		
	      } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	      }
		
		
		return dbObject;
		
	}
	
	
	public List<Student> findStudents(String key, String filter) {
		
		List<Student> students = new ArrayList<Student>();
		
		MongoManager mongo = getMongoManager();
		DBCollection collection = mongo.getDB().getCollection(this.collection);
		DBCursor cursor = collection.find();
		
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			
			students.add(convertToStudent(obj));
		}
		
		return students;
	}
	
	
	
	private Student convertToStudent(DBObject obj) {
		
		Student student = new Student();
		
		student.setAddress(getString(obj, "address"));
		student.setAge(getLong(obj, "age"));
		student.setBirthDate(getString(obj, "birthDate"));
		student.setFirstName(getString(obj, "firstName"));
		student.setGender(getString(obj, "gender"));
		student.setGradeLevel(getString(obj, "gradeLevel"));
		student.setLastName(getString(obj, "lastName"));
		student.setLrn(getString(obj, "lrn"));
		student.setMiddleName(getString(obj, "middleName"));
		student.setNationality(getString(obj, "nationalit"));
		
		return student;
			
		
	}

	
}


