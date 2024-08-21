package action;

import java.io.File;

/*
 * create new directories for packages inside the path from String source
 * */
public class CreatePackageAction {
	public CreatePackageAction(String source,String title) {
		File file = new File(source + "//" + title.replace(".", "//"));
		file.mkdirs();
	} 
}
