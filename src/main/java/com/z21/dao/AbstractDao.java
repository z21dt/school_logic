package com.z21.dao;

import com.mongodb.DBObject;
import com.z21.mongo.MongoManager;

public abstract class AbstractDao {
	
	protected String collection;
	
	protected String database;
	
	private String defaultHost = "localhost";
	
	private int defaultPort = 27017;
	

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
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
		return new MongoManager(database, defaultHost, defaultPort);
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
		

	
	
}
