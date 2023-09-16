package gui;


public class ProjectCreator extends DialogCreator {
	
	public ProjectCreator(){
		
		super("New Project","Java Project","Create New Java Project.");
		buttonFinish.addActionListener((e)->{createProject();});
		buttonCancel.addActionListener((e)->jDialog.dispose());
		jDialog.setVisible(true);
		
	}
	public void createProject() {
		
	}
}
