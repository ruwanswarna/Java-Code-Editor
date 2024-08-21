package action;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import gui.DialogCreator;

/*
 * implement opening a java file from disk, saving file to disk, deleting from disk
 * and file content operations (cut, copy, paste)
 * Also getTitle() method to return file name
 * */
public class FileAction {
	private String path;

	public String openFile(String path) {
		this.path = path;
		File file = new File(path);
		FileReader reader;
		String content = "";
		try {
			String line;
			reader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(reader);
			try {
				while ((line = bufferedReader.readLine()) != null) {
					content = content + line + "\n";
				}
			} catch (IOException e) {

				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
		}

		return content;
	}
	public static String getLineNumbersContent(String content) {
		int count = getNoOfLines(content);
		int i = 1;
		content = "";
		for (; count>=0 ; count--) {
			
			content= content + i + "\n";
			i++;
		}
		
		return content;
	}
	public static int  getNoOfLines(String content) {
		int count = 0;
		for (int i = 0; i < content.length(); i++) {
			if (content.charAt(i) == '\n') {
				count++;
			}
		}
		return count;
	}

	public String getTitle() {
		
		return path.substring(path.lastIndexOf("\\") + 1) + "   ";

	}

	public void saveFile(String source, String _package, String fileName, String content) {

		if (source == null) {
			return;
		}
		System.out.println(DialogCreator.directory.getPath()+"______________");
		File fileLocation = new File(DialogCreator.directory.getPath() + "\\" 
									+ source + "\\src\\" + _package.replace(".", "\\"));
		fileLocation.mkdirs();
		File file = new File(fileLocation + "\\" + fileName);

		FileWriter writer;
		try {
			writer = new FileWriter(file);
			writer.write(content);
			writer.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void copy(String str) {
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection strse1 = new StringSelection(str);
		clip.setContents(strse1, strse1);
	}

	public void cut(String str) {

		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection strse1 = new StringSelection(str);
		clip.setContents(strse1, strse1);
	}

	public String paste() {

		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		String result = null;
		try {
			result = (String) clip.getData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return result;
	}

	public void delete(String path) {

		try {
			FileUtils.forceDelete(new File(path));
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
