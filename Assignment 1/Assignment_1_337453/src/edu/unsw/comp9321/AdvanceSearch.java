package edu.unsw.comp9321;

import java.util.ArrayList;
import java.util.Arrays;

public class AdvanceSearch {

	/*public static void main(String[] args){
		ArrayList<AuctionItems> something = new ArrayList<AuctionItems>(); 
		something = search("", "", "Very nice phone" );
		for (AuctionItems e : something) {
			System.out.println(e.title + " "+ e.category + " " + e.description);
		}
	}*/
	
	
	public static ArrayList<AuctionItems> search(String heading, String category, String description){
        ArrayList<AuctionItems> results = new ArrayList<AuctionItems>();
        ArrayList<AuctionItems> values = ReadXMLFile.printElements();
        if (heading != null && !heading.isEmpty()){
        	ArrayList<String> headingList = new ArrayList<String>(Arrays.asList(heading.split("\\s+")));
        	for (int i=0;i<values.size();i++){
        		for(int j=0; j< headingList.size();j++){
        			if (values.get(i).getTitle().toString().toLowerCase().contains(headingList.get(j).toString().toLowerCase())) {
        				results.add(values.get(i));
        				values.remove(i);
        			}
        		}
        	}
        }
        if (category != null && !category.isEmpty()) {
        	
        	ArrayList<String> categoryList = new ArrayList<String>(Arrays.asList(category.split("\\s+")));
        	for (int i=0;i<values.size();i++){
        		for(int j=0; j< categoryList.size();j++){
        			if (values.get(i).getCategory().toString().toLowerCase().contains(categoryList.get(j).toString().toLowerCase())) {
        				results.add(values.get(i));
        				values.remove(i);
        			}
        		}
        	}
        }
        if (description != null && !description.isEmpty()){
        	ArrayList<String> descriptionList = new ArrayList<String>(Arrays.asList(description.split("\\s+")));
        	for (int i=0;i<values.size();i++){
        		for(int j=0; j< descriptionList.size();j++){
        			if (values.get(i).getDescription().toString().toLowerCase().contains(descriptionList.get(j).toString().toLowerCase())) {
        				results.add(values.get(i));
        				values.remove(i);
        			}
        		}
        	}
		} 
        return results;
}

	
	
	
}
