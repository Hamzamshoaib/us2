package edu.unsw.ass1;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchFile {

	/*public static void main(String[] args){
		ArrayList<AuctionItems> something = new ArrayList<AuctionItems>(); 
		something = search("MacBook");
		for (AuctionItems e : something) {
			//System.out.println(e.title);			
		//}
	}*/
	
	
	public static ArrayList<AuctionItems> search(String searchTerm){
        ArrayList<AuctionItems> results = new ArrayList<AuctionItems>();
        ArrayList<String> searching = new ArrayList<String>(Arrays.asList(searchTerm.split("\\s+")));
        ArrayList<AuctionItems> values = ReadXMLFile.printElements(); 
                       
        for (int i=0;i<values.size();i++){
                for(int j=0; j<searching.size();j++){
                        if (values.get(i).getTitle().toString().toLowerCase().contains(searching.get(j).toString().toLowerCase()) ||
                                        values.get(i).getCategory().toString().toLowerCase().contains(searching.get(j).toString().toLowerCase()) ||
                                        values.get(i).getDescription().toString().toLowerCase().contains(searching.get(j).toString().toLowerCase())){
                                results.add(values.get(i));
                        }
                }
        }
       
        //for (AuctionItems i : results){
         //       System.out.println(i.getTitle() + " something");
       // }
        return results;
}

	
	
	
}
