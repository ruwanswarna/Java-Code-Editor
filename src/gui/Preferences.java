package gui;

import java.awt.Font;
import java.io.FileWriter;

/*
 * Implements the UI to change software preferences 
 * inherits from DialogCreator Class
 */
public class Preferences extends DialogCreator {

	public Preferences() {

		super("Preferences");
		buttonSave.addActionListener((e) -> savePreferences());
		buttonCancel.addActionListener((e) -> jDialog.dispose());
		jDialog.setVisible(true);

	}

	public void savePreferences() {
		try {

			FileWriter writer = new FileWriter("C:\\ProgramData\\Java Code Editor\\config.txt");
			writer.write(directory.getPath());
			writer.write("\n");
			writer.write(font_type);
			writer.write("\n");
			writer.write(String.valueOf(font_size));
			writer.write("\n");
			writer.write(String.valueOf(font_style));
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		jDialog.dispose();
	}

	public static Font returnFont() {
		return new Font(font_type, font_style, font_size);
	}

}
