package gui;


public class InterfaceCreator  extends DialogCreator{
	
	public InterfaceCreator(){
		
		super("New Interface","Interface","Create New Java Interface.");
		buttonFinish.addActionListener((e)->{createInterface();});
		buttonCancel.addActionListener((e)->jDialog.dispose());
		jDialog.setVisible(true);

		
	}
	public void createInterface() {
		
	}
}
