package gui;

/*
 * Implements the UI to create a new java package 
 * inherits from DialogCreator Class
 */
public class PackageCreator extends DialogCreator {

	/*
	 * public PackageCreator(){
	 * 
	 * super("New Package","Package","Create New Java Package.",null,null);
	 * sourceFolderBtn.addActionListener((e)->{new SourceSelector();});
	 * buttonFinish.addActionListener((e)->{createPackage();});
	 * buttonCancel.addActionListener((e)->jDialog.dispose());
	 * jDialog.setVisible(true);
	 * 
	 * }
	 */
	public PackageCreator(String[] str) {

		super("New Package", "Package", "Create New Java Package.", str[0], null);
		sourceFolderBtn.addActionListener((e) -> {
			new SourceSelector();
		});
		buttonFinish.addActionListener((e) -> {
			createPackage();
		});
		buttonCancel.addActionListener((e) -> jDialog.dispose());
		jDialog.setVisible(true);

	}

	public void createPackage() {
		
		MainGUI.tabbedPane.createTab(DialogCreator.directory + "\\" + sourceFolderTextField.getText(), null, nameTextField.getText(), false, "package");
		jDialog.dispose();
	}
}
