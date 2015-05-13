package edu.unsw.comp9321;
import java.util.regex.Pattern;


public class InputCheck {

	public static boolean isLetters(String letters) {
	    char[] chars = letters.toCharArray();

	    for (char c : chars) {
	        if(!Character.isLetter(c)) {
	            return false;
	        }
	    }
	    return true;
	}
	public static boolean isNum (String number) {
		int inputInt = 0;
		try
		{
		   inputInt = Integer.parseInt(number);
		} catch (Exception e)
		{
		   return false;
		}
		return true;
	}
	public static boolean isEmail (String email) {
		if (Pattern.matches( ".+@.+", email)) {
			return true;
		}
		return false;
	}
}
