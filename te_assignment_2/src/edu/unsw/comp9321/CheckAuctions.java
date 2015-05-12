package edu.unsw.comp9321;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CheckAuctions implements Runnable {
	ArrayList<Integer> ignored = new ArrayList<Integer>();

	@Override
	public void run() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime()));
		
		//For Item_DB whose time is before now
		BiddingController bc = new BiddingController();
		Email email = new Email();
		for(int i : bc.getDoneAuctions()){
			if (!ignored.contains(i)){
				//send winning/losing emails
				email.sendWinnerLoserEmails(i);
				ignored.add(i);
			}
		}
			
			//delete the item from Items
			//delte all bids in BiddingPrice
		
	}

}
