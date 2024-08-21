package action;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;

import gui.DialogCreator;

/*
 * Implement functions to break down the selected path from JTree into subsections like
 * java project name, list of packages, java file name and return these as Strings
 * */
public class PathSelectAction {
	private String projectName;
	private String packageList;
	private static ArrayList<String> packages = new ArrayList<>();

	public PathSelectAction(String jTreeSelectedPath) {

		if (jTreeSelectedPath == null) {
			return;
		}

		String str = DialogCreator.directory.getPath() + "\\";
		str = jTreeSelectedPath.replace(str, "");
		try {
			projectName = str.substring(0, str.indexOf("\\"));
		} catch (IndexOutOfBoundsException e) {

			projectName = str;
		}

		if (str.contains("\\src\\")) {
			str = str.replace(projectName + "\\src\\", "");
			
			if (str.contains(".java") && str.contains("\\")) {
				str = str.substring(0, str.lastIndexOf("\\"));
				packageList = str.replace("\\", ".");
			}
			else {
				packageList = "";
			}
			
		}

	}
	

	public String[] returnPath() {

		return new String[] { projectName, packageList };
	}
	
	public static String returnNode(String path) {

		path = path.substring(path.lastIndexOf("\\") + 1);
		return path;
	}

	public static String[] returnSourceFolders() {

		String[] directories = DialogCreator.directory.list(new FilenameFilter() {
			@Override
			public boolean accept(File current, String name) {
				return new File(current, name).isDirectory();
			}
		});
		return directories;
	}

	public static String[] returnFolders(String selectedSourceFolder) {
		File file = new File(DialogCreator.directory, selectedSourceFolder);
		packages.removeAll(packages);
		getSubDirectories(new File(file, "src"),"");
		Collections.sort(packages);
		String[] _packages = packages.toArray(new String[packages.size()]);
		
		return _packages;
	}
	public static void getSubDirectories(File parentDirectory,String parentPackage) {
		
		File file = new File(parentDirectory, parentPackage);
		String[] directories = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File current, String name) {
				return new File(current, name).isDirectory();
			}
		});
		
		
		if(directories != null) {
			for (String currentDirectory : directories) {
				getSubDirectories(file, currentDirectory);
				if(parentPackage =="") {
					packages.add(currentDirectory);
				}
				else {
					packages.add(parentPackage+"."+ currentDirectory);
				}
				
			}
			
		}
		
	}

}
