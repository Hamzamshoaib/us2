package edu.unsw.ass1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RandomFunc {
//change the code
	/*public static void main(String[] args){
		ArrayList<AuctionItems> something = new ArrayList<AuctionItems>(); 
		something = randElements();
		for (AuctionItems e : something) {
			System.out.println(e.title);			
		}
	}*/
	
	public static ArrayList<AuctionItems> randElements() {
		ArrayList<AuctionItems> randValues = new ArrayList<AuctionItems>();
		ArrayList<AuctionItems> sendValues = new ArrayList<AuctionItems>();
		randValues = ReadXMLFile.printElements();
		ArrayList <Integer> shuffleNum = new ArrayList<Integer>();
		for (int i = 0; i < randValues.size(); i++) {
			shuffleNum.add(i);
		//	System.out.println(shuffleNum.get(i));
		}
		Collections.shuffle(shuffleNum);
		int[] randNum = new int[10];
		//System.out.println(randNum.length);
		Random number = new Random();
		for (int i = 0; i < randNum.length; i++) {
			int n = number.nextInt(randValues.size());
			randNum[i] = n;
		}
		for (int i = 0; i < 10; i++) {
			int n = shuffleNum.get(i);
			//System.out.println(n);
			sendValues.add(randValues.get(n));
			//System.out.println(randValues.get(i));
		}
		//System.out.println(randValues.size());*/
		return sendValues;

	}

}