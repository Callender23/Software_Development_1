package gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import org.junit.platform.commons.util.StringUtils;




/*
 * Author: Jeffrey Callender
 * Date: 5/22/2020
 * Text Analyzer
 * 
 * To run this file you will need to have the Jsoup filed downloaded to use the extended library.
 * I have the file in the repository to enable to run on an IED like esclipe or netbeans but I also
 * included a executable jar file that has the needed libraries within it so nothing else is requires on the
 * user end.
 *
 * For This project I use the help of Jsoup a java library that works with real world HTML
 * and provides API for fetching URLs. This includes things such as parsing HTML files, extracting
 * data and so on. I found using this to be the easiest way to handle the removal of the HTML tags and 
 * overall general parsing.
 * 
 * Updated For 6/21/2020
 * Added a simple GUI that simply takes the URL link that will return the top occurring words within a website's p tags.
 * In the future more features will be added to the program such as outputting the result to the GUI instead of the console and
 * finding the occurrences of a specific word specified by the user input into the textfield.
 *
 * Updated For 6/21/2020
 * A Junit test was added to the file and a simple test case was made to test the functionality of word occurrences
 *
 */


public class URL_GUI implements  ActionListener {
	
	private JPanel panel;
	private JFrame frame;
	private JButton button;
	private JLabel label;
	private TextField inputUrl = new TextField();
	HashMap<String, Integer> mappy = new HashMap<String, Integer>();		// added HashMap up here in order for it to be accessible everywhere	
	public URL_GUI() {
		
		panel = new JPanel();
		frame = new JFrame();
		frame.setSize(350,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(panel);
		
		panel.setLayout(null);
		frame.setTitle("GUI for word occurences");
		
		label = new JLabel("Enter a URL:");
		label.setBounds(10,20,80,25);
		panel.add(label);
		
		inputUrl.setBounds(100,20,165,25);
		panel.add(inputUrl);
		
		button = new JButton("Enter");
		button.setBounds(10,80,80,25);
		button.addActionListener(this);
		
		panel.add(button);
		
		
		frame.setVisible(true);
		
	}

	public static void main (String[] args) throws Exception{
		new URL_GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		
		try {
			returningMap();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
	}
	
	public HashMap returningMap() throws Exception {
		try {
			String real = "http://127.0.0.1:5500/index.html";					// hardcoded the url input to avoid inputing it over and over again and make my life easier.
			Document doc = Jsoup.connect(real).get();
			Elements paragraphs = doc.select("p");
			
			ArrayList<String> arraylist = new ArrayList<String>();
			for(Element p: paragraphs) 											/*Enhance for loop going through p tags text and adding text to arraylist as one giant string. */
				{
					arraylist.add(p.text());
				}	
			
			String str = arraylist.toString();										/* Declare String variable str to hold string from arraylist. Used toString to convert the arraylist to a string.*/																							/* Stripped String of all non-alphabetical character using replace all.*/
		
			str =str.toLowerCase();
			str = str.replaceAll("[^a-zA-Z0-9]", " ");
			str = str.trim();														// remove leading and trailing white spaces
			String [] words = str.split("\\W+");
	
			int count = 0;
																								/* Declared String array to hold the string from the arraylist*/
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
			
			System.out.println("Frequency of words: "+descendingMap);							/* At last we print out the content within our descending LinkedHashMap. */
			
		
			
		} catch (IOException e1) {
			Logger.getLogger(URL_GUI.class.getName()).log(Level.SEVERE,null, e1);
			e1.printStackTrace();
		}
		
		return mappy;
	}
}

//http://127.0.0.1:5500/index.html

