package edu.unsw.ass1;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXMLFile {
//change the code
	/*public static void main(String[] args){
		ArrayList<AuctionItems> something = new ArrayList<AuctionItems>(); 
		something = printElements();
		for (AuctionItems e : something) {
			System.out.println(e.title);			
		}
	}*/
	
	public static ArrayList<AuctionItems> printElements() {
		String id, title, category, description, picture, currency, dateString, 
		address, city, state, country, postCode ;
		ArrayList<AuctionItems> tagValues = new ArrayList<AuctionItems>();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse("WebContent/database.xml");

			doc.getDocumentElement().normalize();


			NodeList nList = doc.getElementsByTagName("AuctionItem");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);


				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					
					id = eElement.getElementsByTagName("ID").item(0).getTextContent();
					title = eElement.getElementsByTagName("Title").item(0).getTextContent();
					category = eElement.getElementsByTagName("Category").item(0).getTextContent();
					description = eElement.getElementsByTagName("Description").item(0).getTextContent();
					picture = eElement.getElementsByTagName("Picture").item(0).getTextContent();
					currency = eElement.getAttribute("currency"); //need to fix the currency, it is printing empty
					dateString = eElement.getElementsByTagName("EndTime").item(0).getTextContent();
					address = eElement.getElementsByTagName("streetAddress").item(0).getTextContent();
					city = eElement.getElementsByTagName("city").item(0).getTextContent();
					state = eElement.getElementsByTagName("state").item(0).getTextContent();
					country = eElement.getElementsByTagName("country").item(0).getTextContent();
					postCode = eElement.getElementsByTagName("postalCode").item(0).getTextContent();
					//System.out.println(currency);
					
					AuctionItems item = new AuctionItems(id, title, category, description, picture, currency, dateString,
							address, city, state, country, postCode);
					tagValues.add(item);
					

				}	
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tagValues;

	}

}
