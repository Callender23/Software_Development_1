import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.logging.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

/*
 * Author: Jeffrey Callender
 * Date: 5/22/2020
 * Text Analyzer
 * 
 * For This project I use the help of Jsoup a java library that works with real world HTML
 * and provides API for fecting URLs. This includes things suchs as parseing HTML files, extracting
 * data and so on. I found using this to be the easiest way to handle the removal of the html tags and 
 * overall general parsing.
 */

public class ReadFile {

	public static void main (String[] args) throws Exception{
		
																			
		Scanner Sc = new Scanner(System.in);
		
																/* I Implemented a try and catch statement to test my code and throw exception.*/
	try {
		System.out.print("Enter a URL: ");	
		String url = Sc.nextLine();											/* URL string takes in input from console via scanner object.*/
																/* I'm including the URL in here but this program will work with any website
																   that has text within their p tags. 
															           URL: https://www.gutenberg.org/files/1065/1065-h/1065-h.htm.*/
		Document doc = Jsoup.connect(url).get();									/* Using Jsoup library we connect to our url string using the .connect().*/
		Elements paragraphs = doc.select("p");										/* Using the Jsoup library we are able to select the <p> tags within the HTML URL and
																   effectively isolate the rest of the text from the URL that not needed */
		
		
		
		ArrayList<String> arraylist = new ArrayList<String>();		
		
		for(Element p: paragraphs) 											/*Enhance for loop going through p tags text and adding text to arraylist as one giant string. */
		{
			arraylist.add(p.text());
		}	
		
		String str = arraylist.toString();										/* Declare String variable str to hold string from arraylist. Used toString to convert the arraylist to a string.*/					
		str = str.replaceAll("[^a-zA-Z0-9]", " ");									/* Stripped String of all non-alphabetical character using replace all.*/
		str =str.toLowerCase();												/* Made entire String lowercase to prevent Upper case words from being treated as different from lower case.*/
        
        int count = 0;
		String [] words = str.split("\\s+");						        			/* Declared String array to hold the string from the arraylist*/
		HashMap<String, Integer> mappy = new HashMap<String, Integer>();						/* I used a Hashmap to store and sort my words here over other methods due to its fast O(1) runtimes for many operation.*/
																
		
		for(String Raven: words) 											/* Enhance for loop looping through all the words in our words array that contain our string.*/
		{
			if(mappy.containsKey(Raven)) 										/* If statement checking our hashmap if it find a word that as occure already in the map.*/
			{
				count = mappy.get(Raven);									/* If map does find more then one occurrence of word we place word in map and update the count.*/
				mappy.put(Raven, count + 1);	
			}
			else 
			{
				mappy.put(Raven, 1);										/* If Hashmap does not find more then a single occurence then we just put the word in the Hashmap and give value  of 1.*/
			}
		}
		
		LinkedHashMap<String, Integer> descendingMap = new LinkedHashMap<>();					 	/* Declared a LinedHashMap in ordered to help sort the values in our HashMap(mappy) in descending order.
																   LinkedHashMap preserve the order in which elements are inserted which is important for this to work.*/
		mappy.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))  			/* Here Im doing the sorting of the HashMap in a descending order. By using the comparingByvalues(method) and calling the reverseOrder method(). After that
		 														   I use the forEachOrdered which performs an action, in this case the action is putting the values of both keys and values to the LinkedHashMap we created in								
		 														   a descending order*/ 								
		 																							
		 																								
			.forEachOrdered(x -> descendingMap.put(x.getKey(),x.getValue()));
		
		System.out.println("Frequency of Words: " +descendingMap);							/* At last we print out the content within our descending LinkedHashMap. */
		
		
	}
	
	catch (IOException ex) {																	
																/* Using level.SEVERE to ensure that we caputure any serious level of faliure.*/
		Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE,null, ex);
		}
	}
	
	
}
