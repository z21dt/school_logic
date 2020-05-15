package com.z21.dao;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.z21.be.models.common.SchoolConfig;
import com.z21.mongo.MongoManager;

public class SchoolConfigurationDao  extends AbstractDao{
	
	private String database = "common";

	
	public SchoolConfigurationDao() {
		
		System.out.println("Initialize UserAuthenticationDao");
		this.collection = "schools";
		
	}
	
	public SchoolConfig createSchoolInfo(SchoolConfig school) {
		
		MongoManager mongo = getMongoManager(database);
		DBCollection collection = mongo.getDB().getCollection(this.collection);
		
		
		//collection.insert(createStudentObject(student) );
		WriteResult result = collection.insert(createDBObject(school));
			
		System.out.println(result.getUpsertedId());
		System.out.println(result.getN());
		System.out.println(result.isUpdateOfExisting()); 
		mongo.close(); 
		
		return school;
	}

	
	
	public SchoolConfig getSchoolInfo(String schoolCode) {
		
		List<SchoolConfig> schools = new ArrayList<SchoolConfig>();
		
		MongoManager mongo = getMongoManager(database);
		DBCollection collection = mongo.getDB().getCollection(this.collection);
		
		 BasicDBObject whereQuery = new BasicDBObject();
		 whereQuery.put("schoolCode", schoolCode);
		
		DBCursor cursor = collection.find(whereQuery);
		
		SchoolConfig school = null;
		
		while(cursor.hasNext()){
			school = convertToSchool(cursor.next());
			break;
			
			//schools.add(convertToStudent(obj));
		}
		
		return school;
	} 
	
	private SchoolConfig convertToSchool(DBObject obj) {
		
		SchoolConfig school = new SchoolConfig();
		
		school.setSchoolCode(getString(obj,"schoolCode"));
		school.setSchoolName(getString(obj,"schoolName"));
		school.setAddress(getString(obj,"address"));
		school.setEmailAddress(getString(obj,"emailAddress"));
		school.setFooterBgColor(getString(obj,"footerBgColor"));
		school.setHeaderBgColor(getString(obj,"headerBgColor"));
		school.setHeaderTextColor(getString(obj,"headerTextColor"));
		school.setLogo(getString(obj,"logo"));
		school.setSchoolDescription(getString(obj,"schoolDescription"));
		school.setContactInfo(getString(obj,"contactInfo"));
		school.setFooterTextColor(getString(obj,"footerTextColor"));		
		school.setUid(obj.get("_id").toString() );
		school.setContactNumber(getString(obj,"contactNumber"));
		
	/*	
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
	*/	
		return school;
			
		
	}
	
	
	public SchoolConfig updateSchoolInfo(SchoolConfig school) {
		

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.append("schoolCode", school.getSchoolCode());
		
		MongoManager mongo = getMongoManager(database);
		DBCollection collection = mongo.getDB().getCollection(this.collection);
		collection.update(searchQuery, createDBObject(school));

		
		
		return getSchoolInfo(school.getSchoolCode());
		//return school;
		
	}

	
}
