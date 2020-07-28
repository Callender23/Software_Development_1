package gui;

import java.awt.TextArea;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class ServerSide extends JFrame {
	private JPanel panel;
	private JFrame frame;


	
	public ServerSide() throws IOException {
		
		// Making GUI panel titled Server
		panel = new JPanel();
		frame = new JFrame();
		frame.setSize(600,450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(panel);
		frame.setTitle("Server");
		
		TextArea ta = new TextArea(300,300);
		add(new JScrollPane(ta));
		
		// Set TextArea to be read only by using setEditable method
		ta.setEditable(false);
		
		frame.add(ta);
		panel.setLayout(null);
		frame.setVisible(true);
		
		// Created a server socket
		ServerSocket s1 = new ServerSocket(1342);
		
		// Let user know server started by printing in out to the TextArea
		ta.append("Server started at " + new Date() + '\n');
		
		// Accepting incoming request
		Socket s =  s1.accept();
		
		
		// Creating data input and output streams
		DataInputStream server = new DataInputStream(s.getInputStream());
		DataOutputStream serverOut = new DataOutputStream(s.getOutputStream());
		int number = server.readInt();
	

		// Output the number received from client to server TextArea using append method
		ta.append("Number received from client to check prime number is: " + number + '\n');

		// Wrote the result(yes or no) using writeUTF using our helper method prime which check to see if input number is a prime number
		// method return a string yes or no
		serverOut.writeUTF(Prime(number));
		
		// flush out the data stream
		serverOut.flush();
		
		// close the socket
		
		s1.close();
		
		// display to the user that server is disconnected and will need to be run again.
		ta.append("Server is now disconnected. Please run Server again");

	}
	
	public static void main(String[] args) throws IOException {
		
		new ServerSide();
	}
	
	// helper method to check to see if given number is prime
	public static String Prime(int number) {
		
		// if number is less then 2 then number is automatically prime
		if(number < 2) {
			return "No";
		}
		
		// declare variable i and set to 2 as our one exception to the prime rule
		int i = 2;
		
		while(i < number) {
			
			// while number is less then 2 and an even number(modulo by 2) then we return no. Even numbers are not prime.
			// 2 is the only even number that can be prime
			if(number % i == 0) {
				return "No";
			}
			i++;
		}
		
		// For all other cases we return yes
		return "Yes";
		
	}

}
