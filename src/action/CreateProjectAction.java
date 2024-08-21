package action;

import java.io.File;

import gui.DialogCreator;

/*
 * create new directories for a new java project inside applications workspace saved in DialogCreator.directory file
 * */
public class CreateProjectAction {
	public CreateProjectAction(String title) {
		String path = DialogCreator.directory.getPath();
		File file1 = new File(path + "//" + title + "//src");
		File file2 = new File(path + "//" + title + "//bin");
		file1.mkdirs();
		file2.mkdirs();
	}
	
}
