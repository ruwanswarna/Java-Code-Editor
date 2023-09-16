package gui;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AboutSoftware extends JFrame {
	
	public AboutSoftware(){
		
		ImageIcon logo = new ImageIcon("src/icons/logo.png");
		
		JLabel label1 = new JLabel();
		label1.setIcon(logo);
		
		
		JLabel label2 = new JLabel("Java Code Editor");
		JLabel label3 = new JLabel("Coursework for");
		JLabel label4 = new JLabel("Programming Data Structures and Algorithms");
		JLabel label5 = new JLabel("Last Updated: 22/09/2023");
		
		label1.setBounds(120, 10, 60, 60);
		label2.setBounds(90, 100, 200, 20);
		label3.setBounds(93, 130, 200, 20);
		label4.setBounds(10, 150, 300, 20);
		label5.setBounds(65, 170, 200, 20);
		

		 JDialog d = new JDialog(this , "About Software", true);  
	     d.setLayout(null);  
	     d.setSize(300,300);  
	     d.setLocationRelativeTo(null);
	     d.setResizable(false);
	     
	     d.add(label1);
	     d.add(label2);
	     d.add(label3);
	     d.add(label4);
	     d.add(label5);
	     d.setVisible(true);
	}
}
