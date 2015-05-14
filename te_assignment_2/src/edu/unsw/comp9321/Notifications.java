package edu.unsw.comp9321;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Notifications {

	//public static void main(String[] args) {
	
	public Notifications(){
		
	}
		public String acceptOffer(int reserveprice, int offerprice, String itemname)  {
			String message = "Offer of " + offerprice + " for item " + itemname + " is below you reserve price " + reserveprice;
			String response = null;
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			Object[] options = {"I Accept",
			"Decline"};
			int n = JOptionPane.showOptionDialog(frame, message,"Accept or Reject",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,     //do not use a custom Icon
					options,  //the titles of buttons
					options[1]); //default button title
			//System.out.println(n);
			if (n == 1) {
				response = "accept";
			}
			else {
				response = "reject";
			}
			System.out.println(response);
			return response;
		}

	}

//}
