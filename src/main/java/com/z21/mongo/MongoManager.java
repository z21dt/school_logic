package com.z21.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoManager {
	
	
	private String database;
	
	private String host;
	
	private String user;
	
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
	}

	private String password;
	
	private int port;
	
	
	private MongoClient mongoClient;
	
	private MongoManager() {
		// do nothing;
		
	}
	
	public MongoManager(String database, String host, int port, String user, String password) {
		this.database = database;
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
		
		MongoClientURI uri = new MongoClientURI("mongodb://heroku_r9h85zmp:3tr0t4kf11ru7nnh98tc6vns20@ds249269.mlab.com:49269/heroku_r9h85zmp?retryWrites=false");
		mongoClient = new MongoClient(uri);
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public DB getDB() {
		return mongoClient.getDB(database);
		
	}
	
	public void close() {
		this.mongoClient.close();
	}

}
