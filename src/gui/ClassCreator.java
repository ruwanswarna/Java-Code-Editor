package gui;

/*
 * Implements the UI to create a new java class 
 * inherits from DialogCreator Class
 */
public class ClassCreator extends DialogCreator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClassCreator(String[] str) {
		super("New Class", "Java Class", "Create New Java Class", str[0], str[1]);
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
			createClass();
		});
		buttonCancel.addActionListener((e) -> jDialog.dispose());
		jDialog.setVisible(true);

	}

	/*
	 * public ClassCreator(){
	 * 
	 * super("New Class","Java Class","Create New Java Class",null,null);
	 * sourceFolderBtn.addActionListener((e)->{new SourceSelector();});
	 * packageBtn.addActionListener((e)->{new PackageSelector();});
	 * buttonFinish.addActionListener((e)->{createClass();});
	 * buttonCancel.addActionListener((e)->jDialog.dispose());
	 * jDialog.setVisible(true);
	 * 
	 * }
	 */
	public void createClass() {

		MainGUI.tabbedPane.createTab(DialogCreator.directory + "\\" + sourceFolderTextField.getText(),
				packageTextField.getText(), nameTextField.getText(), checkBox.isSelected(), "class"); // this is to
																										// create a
																										// class so type
																										// is class
		jDialog.dispose();

	}
}
