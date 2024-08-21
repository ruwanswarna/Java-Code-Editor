package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
/*
 * Parent class of ClassCreator, EnumCreator,InterfaceCreator, PackageCreator, ProjectCreator and Preferences
 * Classes
 * Implements the UIs of above classes
 */
public class DialogCreator extends JFrame implements ActionListener, DocumentListener {

	private JTextField locationTextField;
	protected JButton buttonFinish, buttonSave, buttonCancel, sourceFolderBtn, packageBtn;
	protected JDialog jDialog;
	protected static String font_type;
	protected static int font_style;
	protected static int font_size;
	public static File directory;
	protected static JTextField sourceFolderTextField, packageTextField, nameTextField;
	protected JCheckBox checkBox;
	private static String projectName, packageList;

	public DialogCreator(String title) {
		// String
		// directory="C:\\\\Users\\\\ruwan\\\\OneDrive\\\\Documents\\\\JCE-workspace";

		jDialog = new JDialog(this, title, true);
		jDialog.setLayout(new BorderLayout());
		jDialog.setSize(450, 600);
		jDialog.setLocationRelativeTo(null);
		jDialog.setResizable(false);

		JLabel location = new JLabel("workspace directory:");
		location.setBounds(10, 10, 150, 20);

		JButton browseButton = new JButton("Browse");
		browseButton.setBounds(340, 10, 80, 20);
		browseButton.setFocusable(false);
		browseButton.addActionListener((e) -> {
			setDirectory();
		});

		locationTextField = new JTextField();
		locationTextField.setBounds(10, 40, 320, 20);
		locationTextField.setText(directory.getPath());

		JLabel selectFontLabel = new JLabel("Select Font: ");
		selectFontLabel.setBounds(10, 100, 100, 20);

		JLabel selectSizeLabel = new JLabel("Select Size: ");
		selectSizeLabel.setBounds(10, 160, 100, 20);

		JLabel selectStyleLabel = new JLabel("Select Style: ");
		selectStyleLabel.setBounds(10, 220, 100, 20);

		JLabel preview = new JLabel("ABCD abcd");
		preview.setBounds(180, 300, 150, 50);
		preview.setFont(new Font(font_type, font_style, font_size));

		String font[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		JComboBox fontType = new JComboBox(font);
		fontType.setBounds(140, 100, 190, 20);
		fontType.setSelectedItem(font_type);
		fontType.addActionListener((e) -> {
			font_type = String.valueOf(fontType.getSelectedItem());
			preview.setFont(new Font(font_type, font_style, font_size));

		});

		Integer[] size = { 6, 8, 10, 12, 14, 16, 20, 24, 28, 32 };
		JComboBox fontSize = new JComboBox(size);
		fontSize.setBounds(140, 160, 100, 20);
		fontSize.setSelectedItem(font_size);
		fontSize.addActionListener((e) -> {
			font_size = Integer.parseInt(String.valueOf(fontSize.getSelectedItem()));
			preview.setFont(new Font(font_type, font_style, font_size));
		});

		String[] style = { "Regular", "Bold", "Italic", "Bold Italic" };
		JComboBox fontStyle = new JComboBox(style);
		fontStyle.setBounds(140, 220, 100, 20);
		fontStyle.setSelectedItem(font_style);
		fontStyle.addActionListener((e) -> {
			font_style = fontStyle.getSelectedIndex();
			preview.setFont(new Font(font_type, font_style, font_size));
		});

		// save button and cancel button
		buttonSave = new JButton("Save");
		buttonCancel = new JButton("Cancel");

		buttonSave.setBounds(210, 440, 100, 30);
		buttonSave.setFocusable(false);
		buttonCancel.setBounds(320, 440, 100, 30);
		buttonCancel.setFocusable(false);

		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		topPanel.setBackground(Color.lightGray);
		topPanel.setPreferredSize(new Dimension(450, 80));

		bottomPanel.setLayout(null);

		bottomPanel.add(location);
		bottomPanel.add(locationTextField);
		bottomPanel.add(browseButton);
		bottomPanel.add(selectFontLabel);
		bottomPanel.add(selectSizeLabel);
		bottomPanel.add(selectStyleLabel);
		bottomPanel.add(fontType);
		bottomPanel.add(fontSize);
		bottomPanel.add(fontStyle);
		bottomPanel.add(preview);

		bottomPanel.add(buttonSave);
		bottomPanel.add(buttonCancel);

		jDialog.add(topPanel, BorderLayout.NORTH);
		jDialog.add(bottomPanel, BorderLayout.CENTER);

	}

	public void setDirectory() {
		JFileChooser directoryChooser = new JFileChooser();
		directoryChooser.setCurrentDirectory(new java.io.File("."));
		directoryChooser.setDialogTitle("Select Directory");
		directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		directoryChooser.setAcceptAllFileFilterUsed(false);

		if (directoryChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

			directory = directoryChooser.getSelectedFile();
			locationTextField.setText(directory.getPath());
		}

	}

	public static void setPreferences(File dir, String fontType, int fontSize, int fontStyle) {
		directory = dir;
		font_type = fontType;
		font_size = fontSize;
		font_style = fontStyle;
	}

	public DialogCreator(String title, String top_label, String bottom_label, String projectName, String packageList) {
		this.projectName = projectName;
		this.packageList = packageList;

		jDialog = new JDialog(this, title, true);
		jDialog.setLayout(new BorderLayout());
		jDialog.setSize(450, 600);
		jDialog.setLocationRelativeTo(null);
		jDialog.setResizable(false);

		// set top label
		JLabel topLabel = new JLabel(top_label);
		topLabel.setFont(new Font("MV Boli", Font.PLAIN, 28));
		topLabel.setBounds(5, 10, 200, 40);
		JLabel bottomLabel = new JLabel(bottom_label);
		bottomLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		bottomLabel.setBounds(5, 60, 250, 20);

		// set bottom label
		JLabel sourceFolderLabel = new JLabel("Source folder:");
		JLabel packageLabel = new JLabel("Package:");
		JLabel nameLabel = new JLabel("Name:");

		// set labels, text fields, buttons
		sourceFolderLabel.setBounds(5, 20, 100, 20);
		packageLabel.setBounds(5, 60, 100, 20);
		nameLabel.setBounds(5, 120, 100, 20);

		sourceFolderTextField = new JTextField();
		packageTextField = new JTextField();
		nameTextField = new JTextField();

		sourceFolderTextField.setBounds(100, 20, 200, 20);
		packageTextField.setBounds(100, 60, 200, 20);
		nameTextField.setBounds(100, 120, 200, 20);
		
		sourceFolderTextField.setEditable(false);
		packageTextField.setEditable(false);
		
		sourceFolderTextField.setBackground(Color.WHITE);
		packageTextField.setBackground(Color.WHITE);

		if (projectName != null) {
			projectName += "\\src";
		}
		sourceFolderTextField.setText(projectName);
		packageTextField.setText(packageList);

		sourceFolderBtn = new JButton("Browse");
		packageBtn = new JButton("Browse");

		sourceFolderBtn.setBounds(330, 20, 85, 20);
		packageBtn.setBounds(330, 60, 85, 20);

		sourceFolderBtn.setFocusable(false);
		packageBtn.setFocusable(false);
		

		// set auto generated main method
		JLabel setMainMethodLabel = new JLabel("public static void main(String[] args){}");
		setMainMethodLabel.setBounds(5, 200, 220, 30);

		checkBox = new JCheckBox();
		checkBox.setBounds(235, 200, 30, 30);

		JSeparator jSeparator = new JSeparator();
		jSeparator.setOrientation(JSeparator.HORIZONTAL);
		jSeparator.setBounds(5, 100, 425, 10);

		// create two panels
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();

		topPanel.setBackground(Color.lightGray);
		topPanel.setLayout(null);
		topPanel.setPreferredSize(new Dimension(400, 80));

		topPanel.add(topLabel);
		topPanel.add(bottomLabel);

		// finish button and cancel button
		buttonFinish = new JButton("Finish");
		buttonCancel = new JButton("Cancel");

		buttonFinish.setBounds(210, 440, 100, 30);
		buttonFinish.setFocusable(false);
		buttonFinish.setEnabled(false);
		buttonCancel.setBounds(320, 440, 100, 30);
		buttonCancel.setFocusable(false);

		Document document = nameTextField.getDocument();
		document.addDocumentListener(this);

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

		if (title == "New Package") {
			packageLabel.setVisible(false);
			packageTextField.setVisible(false);
			packageBtn.setVisible(false);
		}
		if (title == "New Project") {
			sourceFolderLabel.setVisible(false);
			sourceFolderTextField.setVisible(false);
			sourceFolderBtn.setVisible(false);

			packageLabel.setVisible(false);
			packageTextField.setVisible(false);
			packageBtn.setVisible(false);
		}
		if (title != "New Class") {
			setMainMethodLabel.setVisible(false);
			checkBox.setVisible(false);
		}
		if(sourceFolderTextField.getText().isEmpty()) {
			packageBtn.setEnabled(false);
		}
		
		
		jDialog.add(topPanel, BorderLayout.NORTH);
		jDialog.add(bottomPanel, BorderLayout.CENTER);

	}

	public static String getProjectName() {
		projectName = sourceFolderTextField.getText();
		return projectName.replace("\\src", "");
	}

	public static String getPackageList() {
		return packageList;
	}

	public static void setProjectName(String projectName) {

		sourceFolderTextField.setText(projectName += "\\src");
	}

	public static void setPackageList(String packageList) {
		packageTextField.setText(packageList);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		disableIfEmpty(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {

		disableIfEmpty(e);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		disableIfEmpty(e);
	}

	public void disableIfEmpty(DocumentEvent e) {

		buttonFinish.setEnabled(e.getDocument().getLength() > 0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
