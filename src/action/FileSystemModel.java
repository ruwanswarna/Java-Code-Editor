package action;

import java.io.File;
import java.util.Vector;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/*
 * This class is for the related functions to populate the JTree
 */
public class FileSystemModel implements TreeModel {
	private File root;

	private Vector listeners = new Vector();

	public FileSystemModel(File rootDirectory) {
		root = rootDirectory;
	}

	public Object getRoot() {

		return root;
	}

	public Object getChild(Object parent, int index) {
		File directory = (File) parent;

		String[] children = directory.list();

		return new TreeFile(directory, children[index]);

	}

	public int getChildCount(Object parent) {
		File file = (File) parent;
		if (file.isDirectory()) {
			String[] fileList = file.list();
			if (fileList != null)
				return file.list().length;
		}
		return 0;
	}

	public boolean isLeaf(Object node) {
		File file = (File) node;
		return file.isFile();
	}

	public int getIndexOfChild(Object parent, Object child) {
		File directory = (File) parent;
		File file = (File) child;
		String[] children = directory.list();
		for (int i = 0; i < children.length; i++) {
			if (file.getName().equals(children[i])) {
				return i;
			}
		}
		return -1;

	}

	public void addTreeModelListener(TreeModelListener listener) {
		listeners.add(listener);
	}

	public void removeTreeModelListener(TreeModelListener listener) {
		listeners.remove(listener);
	}

	private class TreeFile extends File {
		public TreeFile(File parent, String child) {
			super(parent, child);

		}

		public String toString() {

			return getName();

		}
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// TODO Auto-generated method stub

	}
}