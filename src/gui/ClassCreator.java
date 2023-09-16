package gui;

public class ClassCreator extends DialogCreator{
	
	public ClassCreator(){
		
		super("New Class","Java Class","Create New Java Class");
		buttonFinish.addActionListener((e)->{createClass();});
		buttonCancel.addActionListener((e)->jDialog.dispose());
		jDialog.setVisible(true);
		 
	}
public void createClass() {
		
	}

}
