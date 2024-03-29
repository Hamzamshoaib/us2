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
	    	  return false;
	      }
	 
		return true;
	}
	
	public Boolean newHighBid(int Item_ID){
		UserController uc = new UserController();
		BiddingController bc = new BiddingController();
		String user = bc.winningBidder(Item_ID);
		int highestBid = bc.currentWinningBid(Item_ID);
		String to = uc.getEmail(user);
		String itemName = uc.getItemName(Item_ID);
	      try {
		         // Create a default MimeMessage object.
		         Message message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         message.setRecipients(Message.RecipientType.TO,
		         InternetAddress.parse(to));
		         // Set Subject: header field
		         message.setSubject("Congratulations " + user + "! You have the highest bid at the moment!");
		         // Now set the actual message
		         message.setText("Hi!\n\n"
		         		+ "You have the highest bid for " + itemName + " at $" + highestBid + "."
		         		+ "\n\n"
		         		+ "Daily Auction Deals Team :)");

		         // Send message
		         Transport.send(message);

		         System.out.println("Sent New High Bid Mail successfully...");

	      } catch (MessagingException e) {
	    	  return false;
	      }		
	      return true; 
	}
	
	public Boolean sendLostBidEmail(int Item_ID){
		UserController uc = new UserController();
		BiddingController bc = new BiddingController();
		
		String user = bc.secondHighestBidder(Item_ID);
		if (user == null){
			System.out.println("He was the only bidder");
			return false;
		}
		
		int highestBid = bc.currentWinningBid(Item_ID);
		String to = uc.getEmail(user);
		String itemName = uc.getItemName(Item_ID);
	      try {
		         // Create a default MimeMessage object.
		         Message message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         message.setRecipients(Message.RecipientType.TO,
		         InternetAddress.parse(to));
		         // Set Subject: header field
		         message.setSubject("Oh no, " + user + "! You've been outbid!");
		         // Now set the actual message
		         message.setText("Hi!\n\n"
		         		+ "Unfortunately you have been outbid for your " + itemName + ".\n\n"
		         		+ "The new highest bid is: $" + highestBid + "."
		         		+ "\n\n"
		         		+ "Daily Auction Deals Team :)");
		         
		         // Send message
			         Transport.send(message);
	
			         System.out.println("Sent Lost Bid Mail successfully...");

	      } catch (MessagingException e) {
	    	  return false;
	      }		
	      return true;  
	}
	
	public Boolean emailWinner(int Item_ID){
		UserController uc = new UserController();
		BiddingController bc = new BiddingController();
		String user = bc.winningBidder(Item_ID);
		int highestBid = bc.currentWinningBid(Item_ID);
		String to = uc.getEmail(user);
		String itemName = uc.getItemName(Item_ID);
	      try {
		         // Create a default MimeMessage object.
		         Message message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         message.setRecipients(Message.RecipientType.TO,
		         InternetAddress.parse(to));
		         // Set Subject: header field
		         message.setSubject("Congratulations " + user + "! You have won an item!");
		         // Now set the actual message
		         message.setText("Hi!\n\n"
		         		+ "You have won a brand new " + itemName + " for $" + highestBid + "! :)"
		         		+ "\n\n"
		         		+ "Daily Auction Deals Team :)");

		         // Send message
		         Transport.send(message);

		         System.out.println("Sent winner email successfully...");

	      } catch (MessagingException e) {
	    	  return false;
	      }		
	      return true; 
	}
	
	public Boolean emailOwner(int Item_ID){
		UserController uc = new UserController();
		BiddingController bc = new BiddingController();
		String user = uc.getItemOwner(Item_ID);
		int highestBid = bc.currentWinningBid(Item_ID);
		String to = uc.getEmail(user);
		String itemName = uc.getItemName(Item_ID);
	      try {
		         // Create a default MimeMessage object.
		         Message message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         message.setRecipients(Message.RecipientType.TO,
		         InternetAddress.parse(to));
		         // Set Subject: header field
		         message.setSubject("Congratulations " + user + "! Your item has been sold!");
		         // Now set the actual message
		         message.setText("Hi!\n\n"
		         		+ "You have sold your item " + itemName + " for $" + highestBid + "! :)"
		         		+ "\n\n"
		         		+ "Daily Auction Deals Team :)");

		         // Send message
		         Transport.send(message);

		         System.out.println("Sent owner email successfully...");

	      } catch (MessagingException e) {
	    	  return false;
	      }		
	      return true; 
	}
	
	public Boolean lessThanReserved(int Item_ID){
		UserController uc = new UserController();
		BiddingController bc = new BiddingController();
		String user = uc.getItemOwner(Item_ID);
		int highestBid = bc.currentWinningBid(Item_ID);
		String to = uc.getEmail(user);
		String itemName = uc.getItemName(Item_ID);
	      try {
		         // Create a default MimeMessage object.
		         Message message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         message.setRecipients(Message.RecipientType.TO,
		         InternetAddress.parse(to));
		         // Set Subject: header field
		         message.setSubject(user + ", your item has not reached its reserve price");
		         // Now set the actual message
		         message.setText("Hi!\n\n"
		         		+ "Your item, " + itemName + " has an offer of $" + highestBid 
		         		+ " which is below your reserve price of $" + bc.getReservePrice(Item_ID) + ".\n\n"
		         		+ "Log into your account to accept or reject this offer."
		         		+ "\n\n"
		         		+ "Daily Auction Deals Team :)");

		         // Send message
		         Transport.send(message);

		         System.out.println("Sent reserved price notification email successfully...");

	      } catch (MessagingException e) {
	    	  return false;
	      }		
	      return true; 
	}
	
	public Boolean itemNotSold(int Item_ID){
		UserController uc = new UserController();
		String user = uc.getItemOwner(Item_ID);
		String to = uc.getEmail(user);
		String itemName = uc.getItemName(Item_ID);
	      try {
		         // Create a default MimeMessage object.
		         Message message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         message.setRecipients(Message.RecipientType.TO,
		         InternetAddress.parse(to));
		         // Set Subject: header field
		         message.setSubject(user + ", your item wasn't sold :(");
		         // Now set the actual message
		         message.setText("Hi!\n\n"
		         		+ "Your item, " + itemName + ", has not had any bids and was not sold. :( \n\n" 
		         		+ "Log into your account and re-add if you want."
		         		+ "\n\n"
		         		+ "Daily Auction Deals Team :)");

		         // Send message
		         Transport.send(message);

		         System.out.println("Sent item not sold notification email successfully...");

	      } catch (MessagingException e) {
	    	  return false;
	      }		
	      return true; 
	}
	
	public void newBidEmails(int Item_ID){
		newHighBid(Item_ID);
		sendLostBidEmail(Item_ID);
	}
	
	public void endAuctionEmails(int Item_ID){
		emailWinner(Item_ID);
		emailOwner(Item_ID);
	}
}
