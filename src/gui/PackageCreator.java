package gui;

public class PackageCreator extends DialogCreator {
	
	public PackageCreator(){
		
		 super("New Package","Package","Create New Java Package.");
		 buttonFinish.addActionListener((e)->{createPackage();});
		 buttonCancel.addActionListener((e)->jDialog.dispose());
		 jDialog.setVisible(true);
	     
	}
	public void createPackage() {
		
	}
}
