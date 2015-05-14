package edu.unsw.comp9321;

import java.util.ArrayList;

public class CheckAuctions implements Runnable {
	ArrayList<Integer> ignored = new ArrayList<Integer>();

	@Override
	public void run() {
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		Calendar cal = Calendar.getInstance();
//		System.out.println(dateFormat.format(cal.getTime()));
		
		//For Item_DB whose time is before now
		BiddingController bc = new BiddingController();
		Email email = new Email();
		for(int i : bc.getDoneAuctions()){
			//if highestbid = -1
			if(!ignored.contains(i) && bc.currentWinningBid(i) == -1){
				email.itemNotSold(i);
				bc.deleteItem(i);
			} else if (!ignored.contains(i) && !bc.bidLessThanReserved(i)){
				//send winning/losing emails
				email.endAuctionEmails(i);
				bc.deleteItem(i);
//				ignored.add(i);
			} else if (!ignored.contains(i) && bc.bidLessThanReserved(i)){
				email.lessThanReserved(i);
				ignored.add(i);
			}
		}
	}

}
