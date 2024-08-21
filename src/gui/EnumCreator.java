package gui;

/*
 * Implements the UI to create a new java Enumeration 
 * inherits from DialogCreator Class
 */
public class EnumCreator extends DialogCreator {

	public EnumCreator(String[] str) {
		super("New Enum", "Enum", "Create New Java Enum Type.", str[0], str[1]);
		sourceFolderBtn.addActionListener((e) -> {
			new SourceSelector();
			if(!sourceFolderTextField.getText().isEmpty()) {
				packageBtn.setEnabled(true);
			}
		});
		packageBtn.addActionListener((e) -> {
			new PackageSelector();
		});
		buttonFinish.addActionListener((e) -> {
			createEnum();
		});
		buttonCancel.addActionListener((e) -> jDialog.dispose());
		jDialog.setVisible(true);
	}

	/*
	 * public EnumCreator() {
	 * 
	 * super("New Enum", "Enum", "Create New Java Enum Type.", null, null);
	 * sourceFolderBtn.addActionListener((e)->{new SourceSelector();});
	 * packageBtn.addActionListener((e)->{new PackageSelector();});
	 * buttonFinish.addActionListener((e) -> { createEnum(); });
	 * buttonCancel.addActionListener((e) -> jDialog.dispose());
	 * jDialog.setVisible(true);
	 * 
	 * }
	 */

	public void createEnum() {

		MainGUI.tabbedPane.createTab(DialogCreator.directory + "\\" + sourceFolderTextField.getText(),
				packageTextField.getText(), nameTextField.getText(), false, "enum"); // this is to create a enum so type
																						// is enum
		jDialog.dispose();
	}

}
