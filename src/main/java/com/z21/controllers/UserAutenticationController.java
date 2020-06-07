package com.z21.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.z21.be.models.common.User;
import com.z21.fe.models.UserLoginReq;
import com.z21.fe.models.UserLoginResp;
import com.z21.services.UserAuthenticationService;


@Controller
@RequestMapping("authentication/user")
public class UserAutenticationController {
	
	
	@Autowired  
	UserAuthenticationService userAuthenticationService;
	
	
	@RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody UserLoginResp login(@RequestBody UserLoginReq request,
												  	 			HttpServletResponse servletResponse) {
	
		UserLoginResp resp = new UserLoginResp();
		if(request.getUserName()!= null && request.getPass() != null ) {

		
			User user = userAuthenticationService.authenticate(request.getUserName(), request.getPass());
			
			if(user != null) {
				user.setUserName(request.getUserName());
				resp.setStatusMessage("Login Successfull");
				resp.setResponseCode("200");
				resp.setUser(user);
				
				return resp;
			}
		resp.setStatusMessage("Invalid username or password");
		resp.setResponseCode("404");

		
		}
		return resp; 
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody UserLoginResp register(@RequestBody User user,
																HttpServletResponse servletResponse) {
		UserLoginResp resp = new UserLoginResp();
		
		
		
		User newUser = userAuthenticationService.registerNewUser(user);
		
		user.setPassword("**********");
		resp.setUser(newUser);
		
		
		return resp;
	} 
			 

	
	@RequestMapping(value = "getToken", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String register(  @RequestParam(value = "scode", required = true) String scode,
										   @RequestParam(value = "userName", required = true) String userName,
										   @RequestParam(value = "pass", required = true) String pass,
																HttpServletResponse servletResponse) {

		String token = null;	
		
		return token;
	}
			 
}
