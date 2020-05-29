package com.z21.dao.util;

import com.z21.services.util.EmailUtil;

public class EmailHandlerThread extends Thread {


	
	private EmailUtil util;
	
	private String emailTitle;
	
	private String recipientEmail;
	
	private String messageHtml;
	
	private boolean triggered = false;

	
	public EmailHandlerThread(String sender, String pass, String emailTitle, String reciever, String htmlMessage) {
		util = new EmailUtil(sender, pass);
		this.emailTitle = emailTitle;
		this.recipientEmail = reciever;
		this.messageHtml = htmlMessage;
		
	}
	
	
	public void run(){  
				
		System.out.println("thread is running...");  
		
		if(!triggered) {
			System.out.println("Sending Email to "+recipientEmail);  
			System.out.println("emailTitle "+emailTitle);
			System.out.println("Message "+messageHtml); 
					
			util.send(recipientEmail, emailTitle, messageHtml);
			triggered = true;
		}
		
		if(util.isSent()) {
			
			System.out.println("Stoping Thread");  

			this.yield();
		}
	} 
	
	
}
