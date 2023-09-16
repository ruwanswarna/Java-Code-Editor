package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Preferences extends JFrame {
	
	public Preferences() {
		
		 JPanel topPanel = new JPanel();
	     JPanel bottomPanel = new JPanel();
	     topPanel.setBackground(Color.lightGray);
	     topPanel.setPreferredSize(new Dimension(400,80));
		
		 JDialog d = new JDialog(this , "Preferences", true);  
	     d.setLayout(new BorderLayout());  
	     d.setSize(400,600);  
	     d.setLocationRelativeTo(null);
	     d.setResizable(false);
	     
	     d.add(topPanel, BorderLayout.NORTH);
	     d.add(bottomPanel, BorderLayout.CENTER);
	     d.setVisible(true);
        
	}
}
