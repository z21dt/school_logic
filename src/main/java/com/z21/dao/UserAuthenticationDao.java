package com.z21.dao;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.z21.be.models.User;
import com.z21.mongo.MongoManager;

public class UserAuthenticationDao extends AbstractDao{
	
	

	public UserAuthenticationDao() {
		
		System.out.println("Initialize UserAuthenticationDao");
		this.collection = "user_profile";
		this.setDatabase("admin");
	}
	
	public User registerNewUser(User user) {
		
		MongoManager mongo = getMongoManager();
		DBCollection collection = mongo.getDB().getCollection(this.collection);
		WriteResult result = collection.insert(createUserObject(user));
			
		System.out.println(result.getUpsertedId());
		System.out.println(result.getN());
		System.out.println(result.isUpdateOfExisting());

		 user.setUserId(new Long(result.getN()));
		 
		 mongo.close();
		 
		 return user;
	} 

	
	private DBObject createUserObject(User user) {
		
		
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
		
		docBuilder.append("userId", user.getUserId());
		docBuilder.append("userName", user.getUserName());
		docBuilder.append("password", user.getPassword());
		docBuilder.append("email", user.getEmail());
		docBuilder.append("contactNumber", user.getContactNumber());
		docBuilder.append("fullName", user.getFullName());

		return docBuilder.get();
		
	}
	
	
	public User authenticate(String userName, String password) {
		User user = null;
		
		MongoManager mongo = getMongoManager();
		
		DBObject query = BasicDBObjectBuilder.start().add("userName", userName).get();
		DBObject result = mongo.getDB().getCollection(this.collection).findOne(query);
		
		if(result != null) {
			 
			String pass  = getString(result, "password") ;
			if(password.equals(pass)) {
			
				user = new User();
				user.setContactNumber(getString(result, "contactNumber") );
				user.setEmail(getString(result, "email") );
				user.setFullName(getString(result, "fullName") );
				user.setPassword("*********");
				user.setUserName(getString(result, "userName") );
				user.setUserId(getLong(result, "userId"));
			}
			 
		}
		return user;
		
	}
	
	
}
