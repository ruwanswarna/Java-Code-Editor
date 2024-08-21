package action;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import gui.Preferences;

public class CreateTabAction {
	private static JScrollPane editorScrollPane;
	private static JTextArea textAreaEditor;
	private Tab tab;
	
	
	public CreateTabAction(String path) {
		tab = new Tab();
		System.out.println(path);
		String[] arr = new PathSelectAction(path).returnPath();
		tab.setSource(arr[0]);
		tab.set_package(arr[1]);
		tab.setTitle(PathSelectAction.returnNode(path));
		System.out.println(tab.getTitle());
		
		tab.createUndoRedoObject();

		FileAction fileAction = new FileAction();
		tab.setContent(fileAction.openFile(path));
		//this.title = fileAction.getTitle();
		
		createTabContent(tab.getContent());
		if(findMainClass()) {
			tab.setMainClass(true);
		}
		System.out.println(tab.toString());
	}

	public CreateTabAction(String source, String _package, String title, String type) { // tab action for interface and
																						// enum
		tab = new Tab();
		tab.setSource(source);
		tab.set_package(_package);
		tab.setTitle(title);

		if (type == "interface") {
			tab.setContent("public interface " + title + " {\n\n}");
		} else if (type == "enum") {
			tab.setContent("public enum " + title + " {\n\n}");
		}
		createTabContent(tab.getContent());
		
		System.out.println(tab.toString());


	}

	public CreateTabAction(String source, String _package, boolean selected, String title) { //tab action for class
		
		tab = new Tab();
		tab.setSource(source);
		tab.set_package(_package);
		tab.setTitle(title);

		if (selected == true) {
			tab.setContent("public class " + title + " {\n\tpublic static void main(String[] args) {\n\t\t\n\t}\n}");
		} else {
			tab.setContent("public class " + title + " {\n\n}");
		}

		createTabContent(tab.getContent());
		
		System.out.println(tab.toString());

	}

	public void createTabContent(String content) {

		JScrollPane editorScrollPane = new JScrollPane();
		this.editorScrollPane = editorScrollPane;
		editorScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		editorScrollPane.getVerticalScrollBar().setUnitIncrement(30);

		JPanel codingPanel = new JPanel();
		editorScrollPane.setViewportView(codingPanel);
		codingPanel.setLayout(new BorderLayout(0, 0));
		JTextArea textAreaNumbers = new JTextArea();
		textAreaNumbers.setFont(Preferences.returnFont());
		textAreaNumbers.setBackground(new Color(128, 128, 255));
		textAreaNumbers.setEditable(false);
		codingPanel.add(textAreaNumbers, BorderLayout.WEST);
		textAreaNumbers.setText(FileAction.getLineNumbersContent(content));

		JTextArea textAreaEditor = new JTextArea();
		this.textAreaEditor = textAreaEditor;
		textAreaEditor.setTabSize(4);
		textAreaEditor.setFont(Preferences.returnFont());
		textAreaEditor.setText(content);
		codingPanel.add(textAreaEditor, BorderLayout.CENTER);

		JPopupMenu editorPopupMenu = new JPopupMenu();
		JMenuItem undoTypingItem = new JMenuItem("Undo Typing");
		JMenuItem redoItem = new JMenuItem("Redo");
		JMenuItem saveItem = new JMenuItem("Save");
		JMenuItem copyItem = new JMenuItem("Copy");
		JMenuItem cutItem = new JMenuItem("Cut");
		JMenuItem pasteItem = new JMenuItem("Paste");
		JMenuItem selectAllItem = new JMenuItem("Select All");

		editorPopupMenu.add(undoTypingItem);
		editorPopupMenu.add(redoItem);
		editorPopupMenu.add(saveItem);
		editorPopupMenu.add(copyItem);
		editorPopupMenu.add(cutItem);
		editorPopupMenu.add(pasteItem);
		editorPopupMenu.add(selectAllItem);

		undoTypingItem.addActionListener((e) -> {
			UR_Element element = UndoRedoAction.sendQuery(tab.getSource(),tab.get_package(),tab.getTitle(), "UNDO", null, 0, 0);
			if(element != null) { 
				textAreaEditor.select(element.getStart(), element.getEnd());
				textAreaEditor.replaceSelection("");
				System.out.println("@@@@@@"+element.getText() + "@@@@@@" + element.getStart()+"@@@@@@"+element.getEnd());
				element = null;
			}
		});
		redoItem.addActionListener((e) -> {
			UR_Element element = UndoRedoAction.sendQuery(tab.getSource(),tab.get_package(),tab.getTitle(), "REDO", null, 0, 0);
			if(element != null) { 
				textAreaEditor.setCaretPosition(element.getStart());
				textAreaEditor.insert(element.getText(),textAreaEditor.getCaretPosition());
				element=null;
			}
		});
		saveItem.addActionListener((e) -> {
			System.out.println("--------"+tab.getTitle()+"-------------");
			System.out.println(textAreaEditor.getText());
			new FileAction().saveFile(tab.getSource(), tab.get_package(), tab.getTitle(), textAreaEditor.getText());
		});
		copyItem.addActionListener((e) -> {
			new FileAction().copy(textAreaEditor.getSelectedText());
		});
		cutItem.addActionListener((e) -> {
			new FileAction().cut(textAreaEditor.getSelectedText());
			textAreaEditor.replaceSelection("");
		});
		pasteItem.addActionListener((e) -> {
			textAreaEditor.replaceSelection(new FileAction().paste());
		});
		selectAllItem.addActionListener((e) -> {
			textAreaEditor.selectAll();
		});

		textAreaEditor.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					editorPopupMenu.show(textAreaEditor, e.getX(), e.getY());
				}
			}
		});
		textAreaEditor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					editorPopupMenu.show(textAreaEditor, e.getX(), e.getY());
				}
			}
		});
		
		
		AbstractDocument document = (AbstractDocument) textAreaEditor.getDocument();
        document.setDocumentFilter(new DeletedTextFilter());
        textAreaEditor.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
            	int start = de.getOffset();
	        	int end = de.getOffset() + de.getLength();
	        	String change = textAreaEditor.getText().substring(start , end);
	        	
	        	UndoRedoAction.sendQuery(tab.getSource(),tab.get_package(),tab.getTitle(), "WRITE", change, start, end);
                //System.out.println("Inserted: " + e.getDocument().getProperty(Document.TitleProperty));
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
            	int start = de.getOffset();
            	int end = de.getOffset() + de.getLength();
            	String change = "" + de.getDocument().getProperty(Document.TitleProperty);
            	UndoRedoAction.sendQuery(tab.getSource(),tab.get_package(),tab.getTitle(), "DELETE",change,start, end);
                //System.out.println("Deleted: " + e.getDocument().getProperty(Document.TitleProperty));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        });
		
		
		// saving the file to disk
		new FileAction().saveFile(tab.getSource(), tab.get_package(), tab.getTitle(), textAreaEditor.getText());

	}
	public boolean findMainClass() {
		
		if(tab.getContent().contains("public static void main")) {
			return true;
		}
		return false;
	}

	public static JScrollPane getScrollPane() {
		return editorScrollPane;
	}

	public static JTextArea getTextArea() {
		return textAreaEditor;
	}

	public String getTitle() {
		return tab.getTitle();
	}

}
