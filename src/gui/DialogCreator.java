package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class DialogCreator extends JFrame implements ActionListener {
	
	protected JButton buttonFinish, buttonCancel;
	protected JDialog jDialog;
	
	public DialogCreator() {
		
	}
	public DialogCreator(String title,String top_label, String bottom_label) {
		 jDialog = new JDialog(this , title, true);  
	     jDialog.setLayout(new BorderLayout());  
	     jDialog.setSize(450,600);  
	     jDialog.setLocationRelativeTo(null);
	     jDialog.setResizable(false);
		
	     //set top label
		 JLabel topLabel = new JLabel(top_label);
		 topLabel.setFont(new Font("MV Boli", Font.PLAIN,28));
		 topLabel.setBounds(5,10,200,40);
		 JLabel bottomLabel = new JLabel(bottom_label);
		 bottomLabel.setFont(new Font("Calibri", Font.PLAIN,18));
		 bottomLabel.setBounds(5,60,250,20);
		 
		 //set bottom label
		 JLabel sourceFolderLabel = new JLabel("Source folder:");
		 JLabel packageLabel = new JLabel("Package:");
		 JLabel nameLabel = new JLabel("Name:");
		 
		 //set labels, text fields, buttons 
         sourceFolderLabel.setBounds(5,20,100,20);
         packageLabel.setBounds(5,60,100,20);
         nameLabel.setBounds(5,120,100,20);
		 
         JTextField sourceFolderTextField = new JTextField();
         JTextField packageTextField = new JTextField();
         JTextField nameTextField = new JTextField();
         
         sourceFolderTextField.setBounds(100,20,200,20);
         packageTextField.setBounds(100,60,200,20);
         nameTextField.setBounds(100,120,200,20);
         
         JButton sourceFolderBtn = new JButton("Browse");
         JButton packageBtn = new JButton("Browse");
         
         
         sourceFolderBtn.setBounds(330,20,85,20);
         packageBtn.setBounds(330,60,85,20);

         
         sourceFolderBtn.setFocusable(false);
         packageBtn.setFocusable(false);
         
         //set auto generated main method
         JLabel setMainMethodLabel = new JLabel("public static void main(String[] args){}");
         setMainMethodLabel.setBounds(5,200,220,30);
         
         JCheckBox checkBox = new JCheckBox();	
         checkBox.setBounds(235,200,30,30);
         
         
         JSeparator jSeparator = new JSeparator();
         jSeparator.setOrientation(JSeparator.HORIZONTAL);
         jSeparator.setBounds(5,100,425,10);
         
		//create two panels
	     JPanel topPanel = new JPanel();
	     JPanel bottomPanel = new JPanel();
	     
	     topPanel.setBackground(Color.lightGray);
	     topPanel.setLayout(null);
	     topPanel.setPreferredSize(new Dimension(400,80));
	     
	     topPanel.add(topLabel);
	     topPanel.add(bottomLabel);
	     
	     //finish button and cancel button
	     buttonFinish = new JButton("Finish");
	     buttonCancel = new JButton("Cancel");
	     
	     buttonFinish.setBounds(210,440,100,30);
	     buttonFinish.setFocusable(false);
	     buttonCancel.setBounds(320,440,100,30);
	     buttonCancel.setFocusable(false);
	     
	    
	    
		
	     
	     bottomPanel.setLayout(null);
	     bottomPanel.add(sourceFolderLabel);
	     bottomPanel.add(sourceFolderTextField);
	     bottomPanel.add(packageLabel);
	     bottomPanel.add(packageTextField);
	     bottomPanel.add(nameLabel);
	     bottomPanel.add(nameTextField);
	     bottomPanel.add(buttonFinish);
	     bottomPanel.add(buttonCancel);
	     bottomPanel.add(sourceFolderBtn);
	     bottomPanel.add(packageBtn);
	     bottomPanel.add(setMainMethodLabel);
	     bottomPanel.add(checkBox);
	     bottomPanel.add(jSeparator);
	     
	     if(title=="New Package") {
        	 packageLabel.setVisible(false);
	    	 packageTextField.setVisible(false);
	    	 packageBtn.setVisible(false);
	     }
         if(title!="New Class") {
        	 setMainMethodLabel.setVisible(false);
        	 checkBox.setVisible(false);
         }

	     jDialog.add(topPanel, BorderLayout.NORTH);
	     jDialog.add(bottomPanel, BorderLayout.CENTER);
	     
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
