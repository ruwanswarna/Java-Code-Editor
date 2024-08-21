package gui;

/*
 * Implements the UI to create a new java Interface 
 * inherits from DialogCreator Class
 */
public class InterfaceCreator extends DialogCreator {

	public InterfaceCreator(String[] str) {
		super("New Interface", "Interface", "Create New Java Interface.", str[0], str[1]);
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
			createInterface();
		});
		buttonCancel.addActionListener((e) -> jDialog.dispose());
		jDialog.setVisible(true);
	}

	/*
	 * public InterfaceCreator(){
	 * 
	 * super("New Interface","Interface","Create New Java Interface.",null,null);
	 * sourceFolderBtn.addActionListener((e)->{new SourceSelector();});
	 * packageBtn.addActionListener((e)->{new PackageSelector();});
	 * buttonFinish.addActionListener((e)->{createInterface();});
	 * buttonCancel.addActionListener((e)->jDialog.dispose());
	 * jDialog.setVisible(true);
	 * 
	 * 
	 * }
	 */
	public void createInterface() {

		MainGUI.tabbedPane.createTab(DialogCreator.directory + "\\" + sourceFolderTextField.getText(),
				packageTextField.getText(), nameTextField.getText(), false, "interface"); // this is to create an
																							// interface so type is
																							// interface
		jDialog.dispose();
	}
}
