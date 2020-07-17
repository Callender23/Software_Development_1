package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

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
 * Updated For 7/13/2020
 * Added a database to the word occurrence application. Instead of the words frequency being store and read to our HashMap it will be done using MySQL database.
 * The HashMap will only be use as a temporary place to store the words until it is passed to the database using query commands.
 * With the added database this application will be using JDBC to support query commands for inserting and reading of our created table called word.
 * A table word was created in MySQL but could have been done in here as well in case we are inserting into a table that does not exist. 
 *
 * Update For 7/16/2020
 * Java doc was added to word occurrences application.
 */

public class URL_GUI implements  ActionListener {
	
	private JPanel panel;
	private JFrame frame;
	private JButton button;
	private JLabel label;
	private TextField inputUrl = new TextField();
	
	/**
	 * constructor that makes a new GUI
	 */
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

	/**
	 * main method
	 * @param args
	 * @throws Exception
	 */
	public static void main (String[] args) throws Exception{
		
		new URL_GUI();
	}

	/**
	 * Method takes in an ActionEvent e performs an action.
	 * Method establishes a connection to a URL using Jsoup extended library.
	 * Method then parses p tags from HTML and puts most recurring words in local MySQL database along with their frequency count
	 * @param ActionEvent e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {	
		String real = inputUrl.getText();
		
		try {
			Document doc = Jsoup.connect(real).get();
			Elements paragraphs = doc.select("p");
			
			ArrayList<String> arraylist = new ArrayList<String>();
			for(Element p: paragraphs) 											/*Enhance for loop going through p tags text and adding text to arraylist as one giant string. */
				{
					arraylist.add(p.text());
				}	
			
			String str = arraylist.toString();										/* Declare String variable str to hold string from arraylist. Used toString to convert the arraylist to a string.*/					
			str = str.replaceAll("[^a-zA-Z0-9]", " ");									/* Stripped String of all non-alphabetical character using replace all.*/
			str =str.toLowerCase();
			
			int count = 0;
			Connection con = getConnection();
			PreparedStatement wordKeeper;
			String [] words = str.split("\\s+");	
																					/* Declared String array to hold the string from the arraylist*/
			HashMap<String, Integer> mappy = new HashMap<String, Integer>();
			for(String Raven: words) 											/* Enhance for loop looping through all the words in our words array that contain our string.*/
				{
					if(mappy.containsKey(Raven)) 										/* If statement checking our hashmap if it find a word that as occure already in the map.*/
					{
						count = mappy.get(Raven);
																							/* If map does find more then one occurrence of word we place word in map and update the count.*/
						mappy.put(Raven, count + 1);
						// we have preparedStatment object wordkeeper that we set equal to our insert statement that insert the values from words array and count to our database column ourwords and wordCount
						wordKeeper = con.prepareStatement("INSERT INTO words (ourwords,wordCount) VALUES('"+Raven+"','"+count+"') ON DUPLICATE KEY UPDATE wordCount = VALUES (wordCount) + 1");
						// statements are executed with executeUpdate()
						wordKeeper.executeUpdate();	
						
					}
					else 
					{
						mappy.put(Raven, 1);										/* If Hashmap does not find more then a single occurence then we just put the word in the Hashmap and give value  of 1.*/
					}
					
				}
			
				// we call helper method getData() to display the top 20 frequently occurring words.
				getData();
			
			
			
		} catch (IOException e1) {
			Logger.getLogger(URL_GUI.class.getName()).log(Level.SEVERE,null, e1);
			e1.printStackTrace();
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		
	}
	
	/*Class getConnection throws an exception and connects to MySQL*/
	/**
	 * method establishes connection to a local instant of MySQL database
	 * @return connection object
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception{
		
		try {
			 String driver = "com.mysql.cj.jdbc.Driver";
			 String url = "jdbc:mysql://localhost:3306/word_occurences";
			 String username = "root";
			 String password = "TheBest@23";
			 Class.forName(driver);
			 
			 Connection com = DriverManager.getConnection(url, username, password);
			 return com;
		}catch (Exception e) {      
			System.out.println(e);
		}
		
		return null;
		
	}
	/*getData() is a helper method that helps us output the result of our query. In this particular case we are connecting to the database
	  and selecting the first 20 words that occurs the most.*/
	/**
	 * methods displays a query of top 20 recurring words in local MySQL database
	 *
	 */
	public static void getData() {
		
		
		String query ="SELECT * FROM words ORDER BY wordCount DESC LIMIT 20";
		try {
			Connection con = getConnection();
			PreparedStatement output = con.prepareStatement(query);
			
			ResultSet rs = output.executeQuery();
			System.out.println("The Top 20 most reoccuring words");
			while(rs.next()) {
		
				System.out.println("Frequency: "+(rs.getInt(2)) + " \tWord: "+ (rs.getString(1)));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}

