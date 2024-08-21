package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * Parent class of PackageSelector and ProjectSelector Classes
 * Implements the UIs of above classes
 */
public class SelectorDialog extends JFrame implements ActionListener {

	protected JDialog jDialog;
	protected JPanel jPanel;
	protected JButton okButton;

	public SelectorDialog(String title, String message) {

		jDialog = new JDialog(this, title, true);
		jDialog.setLayout(null);
		jDialog.setSize(335, 430);
		jDialog.setLocationRelativeTo(null);
		jDialog.setResizable(false);

		JLabel jLabel = new JLabel(message);
		jLabel.setBounds(10, 10, 200, 20);

		jPanel = new JPanel();
		jPanel.setLayout(null);
		jPanel.setBounds(10, 40, 300, 300);
		jPanel.setBackground(Color.WHITE);

		okButton = new JButton("OK");
		okButton.setBounds(120, 355, 80, 20);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(220, 355, 80, 20);

		jDialog.add(jLabel);
		jDialog.add(jPanel);
		jDialog.add(okButton);
		jDialog.add(cancelButton);

		cancelButton.addActionListener((e) -> {
			jDialog.dispose();
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
