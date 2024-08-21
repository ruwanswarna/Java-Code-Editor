package gui;

import javax.swing.JList;

/*
 * Implements the UI to insert or change the java project to create a new java file
 * inherits from SelectorDialog Class
 */
public class SourceSelector extends SelectorDialog {
	// private static String selectedSourceFolder = null;
	public SourceSelector() {
		super("Select project", "Choose a source folder:");

		String selectedProject = DialogCreator.getProjectName();
		String[] str = action.PathSelectAction.returnSourceFolders();
		JList<String> list = new JList<>(str);
		list.setSelectedValue(selectedProject, true);
		list.setBounds(5, 5, 200, 300);
		jPanel.add(list);

		okButton.addActionListener((e) -> {
			
			DialogCreator.setProjectName(list.getSelectedValue());
			jDialog.dispose();
		});
		jDialog.setVisible(true);
	}

}
