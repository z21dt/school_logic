package com.z21.mongo;

import java.util.Arrays;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MongoManager {
		
	private String database;
	
	private String host;
	
	private int port;
	
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
	
	
	private MongoClient mongoClient;
	
	private MongoManager() {
		// do nothing;
		
	}
	
	public MongoManager(String database, String host, int port) {
		
		System.out.println("Mongo Manager "+host+":"+port+"/"+database);
		this.database = database;
		this.host = host;
		this.port = port;
		
		//mongoClient = new MongoClient(host, port);
		
		
		 String user ="heroku_r9h85zmp"; // the user name
		// String database = "admin"; // the name of the database in which the user is defined
		 char[] password ="3tr0t4kf11ru7nnh98tc6vns20".toCharArray(); // the password as a character array
		 // ...

		 MongoCredential credential = MongoCredential.createCredential(user, "admin", password);

		// MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).build();
		 MongoClientOptions options = MongoClientOptions.builder().sslEnabled(false).build();
		 mongoClient = new MongoClient(new ServerAddress("localhost", 27017),
		                                           Arrays.asList(credential),
		                                           options);
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
