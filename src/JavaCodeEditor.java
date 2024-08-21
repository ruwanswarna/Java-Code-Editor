import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import gui.DialogCreator;
import gui.MainGUI;

public class JavaCodeEditor {
	public static void main(String[] args) {
		loadPreferences();
		new MainGUI();

	}

	/* load preferences saved in config.txt file */
	public static void loadPreferences() {
		File directory =null;
		try {
			FileReader reader = new FileReader("C:\\ProgramData\\Java Code Editor\\config.txt");
			BufferedReader br = new BufferedReader(reader);
			String line;
			int i = 0;
			directory = new File("C:\\Users\\ruwan\\OneDrive\\Documents\\JCE-workspace");
			String font_type = "Consolas";
			Integer font_size = 16;
			Integer font_style = 0;
			while ((line = br.readLine()) != null) {
				switch (i) {
				case 0:
					directory = new File(line);
					break;
				case 1:
					font_type = line;
					break;
				case 2:
					font_size = Integer.valueOf(line);
					break;
				case 3:
					font_style = Integer.valueOf(line);
					break;
				}
				i++;
			}
			br.close();
			reader.close();
			DialogCreator.setPreferences(directory, font_type, font_size, font_style);
		} catch (Exception e) {
			directory.mkdirs();
			try {
				new File("C:\\ProgramData\\Java Code Editor\\config.txt").createNewFile();
				loadPreferences();

			} catch (IOException ex) {

				ex.printStackTrace();
			}
		}

	}
}
