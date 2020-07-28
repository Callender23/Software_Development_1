package gui;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ClientSide extends JFrame implements ActionListener {
	private JPanel panel;
	private JFrame frame;
	private JButton b;
	private JTextField tf, tf1;
	private JLabel l, l2;
	
	public ClientSide() throws UnknownHostException, IOException {
		
		// making new JPanel and JFrame
		panel = new JPanel();
		frame = new JFrame();
		l = new JLabel("Enter a number to check prime:");
		l2 = new JLabel("Is the number prime?");
		Dimension size = l.getPreferredSize();
		Dimension size2 = l2.getPreferredSize();
		
		l.setBounds(50, 50, size.width, size.height);
		l2.setBounds(150, 150, size2.width, size2.height);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		frame.setTitle("Client");
		b = new JButton("Enter");
		tf = new JTextField(17);
		tf1 = new JTextField(10);
		
		// add button and textfield to panel
		b.addActionListener(this);
		
		
		panel.add(l);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.add(tf);
		panel.add(b);
	
		panel.add(l2);
		panel.add(tf1);
	
	
		
		// add panel to the frame
		frame.add(panel);
		

		// setting size of the frame
		frame.setSize(500,450);
		

		frame.setVisible(true);
		


	}
	public static void main(String[] args) throws IOException {

		new ClientSide();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			
			// get input from textfield
			int input = Integer.parseInt((tf.getText()));
			Socket s = new Socket("localhost",1342);
			
			DataInputStream server = new DataInputStream(s.getInputStream());
			DataOutputStream serverOut = new DataOutputStream(s.getOutputStream());
			
	
			serverOut.writeInt(input);
			String answer = server.readUTF();
			
			// output answer to second textfield called tf1
			tf1.setText(answer);
		
			serverOut.flush();
			serverOut.close();
			
			s.close();
		
		}catch(IOException ex){
			 ex.printStackTrace();
		}
	}

}
