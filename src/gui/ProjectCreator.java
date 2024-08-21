package gui;

/*
 * Implements the UI to create a new java Project
 * inherits from DialogCreator Class
 */
public class ProjectCreator extends DialogCreator {

	public ProjectCreator() {

		super("New Project", "Java Project", "Create New Java Project.", null, null);
		buttonFinish.addActionListener((e) -> {
			createProject();
		});
		buttonCancel.addActionListener((e) -> jDialog.dispose());
		jDialog.setVisible(true);

	}

	public void createProject() {

		MainGUI.tabbedPane.createTab(null, null, nameTextField.getText(), false, "project"); // this is to create a
																								// project so type is
																								// project
		jDialog.dispose();
	}
}
