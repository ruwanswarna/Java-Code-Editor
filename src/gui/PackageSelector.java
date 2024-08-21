package gui;

import javax.swing.JList;

/*
 * Implements the UI to insert or change the package to create a new java file
 * inherits from SelectorDialog Class
 */
public class PackageSelector extends SelectorDialog {
	// private static String selectedFolder = null;
	public PackageSelector() {
		super("Select package", "Choose a folder:");
		
		String selectedPackage = DialogCreator.getPackageList();
		String[] str = action.PathSelectAction.returnFolders(DialogCreator.getProjectName());
		JList<String> list = new JList<>(str);
		list.setSelectedValue(selectedPackage, true);
		list.setBounds(5, 5, 200, 300);
		jPanel.add(list);

		okButton.addActionListener((e) -> {
			DialogCreator.setPackageList((String)list.getSelectedValue());
			jDialog.dispose();
		});

		jDialog.setVisible(true);
	}

}
