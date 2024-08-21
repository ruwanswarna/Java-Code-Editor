package action;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;

/*
 * This is to populate the PopupMenu for the currently opened java projects
 * which are associated with the Run button in the tool bar 
 * */
public class RunPopupMenuAction {
	private JPopupMenu menu;
	public RunPopupMenuAction(JToggleButton dropDownButton) {
		menu = new JPopupMenu();
		fillPopupMenu();
        menu.show(dropDownButton, 0, dropDownButton.getHeight());
	}
	public void fillPopupMenu() {
		menu.add(new JMenuItem("Black"));
        menu.add(new JMenuItem("Red"));
	}
}
