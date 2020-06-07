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

	
	protected String database;
	
	private String defaultHost = "ds249269.mlab.com";

		

	
	private int defaultPort = 49269;
	

	private String user = "heroku_r9h85zmp";
	
	private String password = "3tr0t4kf11ru7nnh98tc6vns20";
	

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
=======
	private String dbname = "heroku_r9h85zmp";
	
	private String dbuser;
	
	private String dbpass;
	

	

	public String getDbuser() {
		return dbuser;
	}

	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}

	public String getDbpass() {
		return dbpass;
>>>>>>> b_mvp_1
	}

	public void setDbpass(String dbpass) {
		this.dbpass = dbpass;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
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

	public MongoManager getMongoManager() {
<<<<<<< HEAD
		return new MongoManager(database, defaultHost, defaultPort, user, password);
=======
		
		return new MongoManager(dbname, defaultHost, defaultPort);
>>>>>>> b_mvp_1
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
			
			//System.out.print("JSON "+jsonString);
			
			 dbObject = (BasicDBObject) JSON.parse(jsonString);
		
	      } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	      }
		
		
		return dbObject;
		
	}
}
