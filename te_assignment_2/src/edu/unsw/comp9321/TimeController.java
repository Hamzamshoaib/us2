package edu.unsw.comp9321;

import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TimeController {
	static DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	
	public static Date addMinutes(Date date, int mins){
		final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
		long t=date.getTime();
		
		date = new Date(t + (mins * ONE_MINUTE_IN_MILLIS));
//		System.out.println(dateFormat.format(date));
		return date;
	}
	
	public static String setAuctTimer(int mins){
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(addMinutes(cal.getTime(), mins));
	}
	
	
}
