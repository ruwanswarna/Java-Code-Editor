package gui;


public class EnumCreator extends DialogCreator {
	
		public EnumCreator(){
			
			super("New Enum","Enum","Create New Java Enum Type.");
			buttonFinish.addActionListener((e)->{createEnum();});
			buttonCancel.addActionListener((e)->jDialog.dispose());
			jDialog.setVisible(true);

		}
		public void createEnum() {
			
		}

}
