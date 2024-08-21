package gui;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * Implements the AboutUS UI to display the details of the creators of the software
 */
public class AboutUS extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AboutUS() {

		JLabel label1 = new JLabel("Higher Diploma in Software Engineering");
		JLabel label2 = new JLabel("NIBM");
		JLabel label3 = new JLabel("23.1F- Galle");
		JLabel label4 = new JLabel("Designed and Implemented By:");
		JLabel label5 = new JLabel("Swarna O.A.R - GAHDSE231F-035");
		JLabel label6 = new JLabel("B.G.N Ravishka - GAHDSE231F-037");
		JLabel label7 = new JLabel("K.H.J Nimsala - GAHDSE231F-022");

		label1.setFont(new Font("Arial", Font.PLAIN, 16));
		label2.setFont(new Font("Arial", Font.PLAIN, 16));
		label3.setFont(new Font("Arial", Font.PLAIN, 16));

		label1.setBounds(2, 10, 300, 20);
		label2.setBounds(120, 30, 300, 20);
		label3.setBounds(100, 50, 300, 20);
		label4.setBounds(10, 100, 300, 20);
		label5.setBounds(40, 140, 300, 20);
		label6.setBounds(40, 160, 300, 20);
		label7.setBounds(40, 180, 300, 20);

		JDialog d = new JDialog(this, "About Us", true);
		d.setLayout(null);
		d.setSize(300, 300);
		d.setLocationRelativeTo(null);
		d.setResizable(false);
		d.add(label1);
		d.add(label2);
		d.add(label3);
		d.add(label4);
		d.add(label5);
		d.add(label6);
		d.add(label7);

		d.setVisible(true);

	}
}
