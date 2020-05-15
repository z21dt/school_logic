package com.z21.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoManager {
		
	private String database;
	
	private String host;
	
	private int port;
	
	
	private MongoClient mongoClient;
	
	private MongoManager() {
		// do nothing;
		
	}
	
	public MongoManager(String database, String host, int port) {
		
		System.out.println("Mongo Manager "+host+":"+port+"/"+database);
		this.database = database;
		this.host = host;
		this.port = port;
		
		mongoClient = new MongoClient(host, port);
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
