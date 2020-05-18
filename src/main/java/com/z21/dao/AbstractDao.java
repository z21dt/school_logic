package com.z21.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.z21.be.models.school.Student;
import com.z21.mongo.MongoManager;

public abstract class AbstractDao {
	
	protected String collection;
	
	private int defaultPort;
	private String defaultHost;
	private String mongoUser;
	private String mongoPass;
//	private String defaultHost = "ds249269.mlab.com";
//	
//	private String user = "heroku_r9h85zmp";
//	
//	private String password = "3tr0t4kf11ru7nnh98tc6vns20";
	

	public String getMongoUser() {
		return mongoUser;
	}

	public void setMongoUser(String mongoUser) {
		this.mongoUser = mongoUser;
	}

	public String getMongoPass() {
		return mongoPass;
	}

	public void setMongoPass(String mongoPass) {
		this.mongoPass = mongoPass;
	}

	public String getDefaultHost() {
		return defaultHost;
	}

	public void setDefaultHost(String defaultHost) {
		this.defaultHost = defaultHost;
	}

	public int getDefaultPort() {
		return defaultPort;
	}

	public void setDefaultPort(int defaultPort) {
		this.defaultPort = defaultPort;
	}
	
	
	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public MongoManager getMongoManager(String database) {
		return new MongoManager(database, defaultHost, defaultPort, mongoUser, mongoPass);
	}

	

	public String getString(DBObject obj , String key) {
		return (String)obj.get(key);
	}
	
	public Long getLong(DBObject obj , String key) {
		
		Object value = obj.get(key);
		if(value != null && value instanceof Number) {
			return new Long(value+"");
		}
		return null;
		
	}
		

	

	public DBObject createDBObject(Object obj) {
		BasicDBObject dbObject = new BasicDBObject();
		
	      ObjectMapper mapper = new ObjectMapper();
	      //Converting the Object to JSONString
	      try {
			String jsonString = mapper.writeValueAsString(obj);
			
			System.out.print("JSON "+jsonString);
			
			 dbObject = (BasicDBObject) JSON.parse(jsonString);
		
	      } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	      }
		
		
		return dbObject;
		
	}
}
