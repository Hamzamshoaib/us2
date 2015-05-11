package edu.unsw.comp9321;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Email {
	
	String from;
    final String username = "dailyauctiondeals";
    final String password = "moshe123";
    String host;
    Properties props;
    Session session;
	
	public Email(){
		from = "dailyauctiondeals@gmail.com";
		host = "smtp.gmail.com";
		
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		
		session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
		});
	}
	
	public Boolean sendVerifyEmail(String to, String IDToken){

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));
	         // Set Subject: header field
	         message.setSubject("Welcome to Daily Auction Deals! Please verify your email.");
	         // Now set the actual message
	         message.setText("Hi!\n\n"
	         		+ "Welcome to Daily Auction Deals! To use your account, please verify your email by clicking this link "
	         		+ "\n\n\thttp://localhost:8080/te_assignment_2/verification?verification_token=" + IDToken
	         		+ "\n\n"
	         		+ "Daily Auction Deals Team :)");

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully...");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
	 
		return true;
	}
	
	public Boolean sendWonBidEmail(){
		//TO-DO
		return null; 
	}
	
	public Boolean sendLostBidEmail(){
		//TO-DO
		return null; 
	}
}
