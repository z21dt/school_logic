package com.z21.fe.models;

import com.z21.be.models.User;

public class UserLoginResp extends AbstractResp{

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
