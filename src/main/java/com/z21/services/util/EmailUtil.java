package com.z21.services.util;


import java.util.Properties;    
import javax.mail.*;    
import javax.mail.internet.*;


public class EmailUtil {  
	
	final String pass;
	
	final String email;
	
	private boolean emailSent = false;

	public EmailUtil(String email, String pass) {
		
		System.out.println("Email Sender "+email+"/"+pass);
		this.pass = pass;
		this.email = email;
	}

	
    public void send(String to,String sub,String msg){  
          //Get properties object    
          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          //get Session   
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
        	   return new PasswordAuthentication(email, pass);  
           	}    
          });    
          //compose message    
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject(sub);    
           message.setText(msg);    
           //send message  
           Transport.send(message);    
           System.out.println("message sent "+to);    
           this.emailSent = true;
          } catch (MessagingException e) {
        	  e.printStackTrace();
        	  throw new RuntimeException(e);
          
          }finally {
        	  this.emailSent = true;
          }
             
    }  
    
    public boolean isSent() {
    	return this.emailSent;
    }
}  
 