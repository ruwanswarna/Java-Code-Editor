package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.JSplitPane;


public class MainGUI extends JFrame implements ActionListener {

	private JMenu menuFile, menuEdit, menuRun, menuWindow, menuAbout;
	private JMenuItem openProject, save, saveAs, saveAll, exit, undo,
								redo, copy, cut, paste, findReplace, run, debug, preferences,
								aboutUS,aboutSoftware, javaProject, newClass, newInterface, 
								newEnum,newPackage;
		 
	private JPanel bottomPanel, explorerPanel, bodyPanel, consolePanel, editorPanel,codingPanel;
	private final JTabbedPane explorerTabbedPane;
	private JTabbedPane consoleTabbedPane;
	private final JTabbedPane editorTabbedPane;
	private final JScrollPane editorScrollPane,consoleScrollPane,explorerScrollPane;
	private final JTextArea textAreaConsole, textAreaNumbers, textAreaEditor;
	private JButton saveButton, saveAllButton, runButton, newPackageButton, newClassButton,
						newInterfaceButton, newEnumButton;
	private JLabel label1;
	private JTree tree;
	private JPopupMenu consolePopupMenu;
	private JPopupMenu editorPopupMenu;
	private JPopupMenu explorerPopupMenu;
	private JMenu newMenu; 
	private JProgressBar progressBar;
	private final JSplitPane mainSplitPane, secondarySplitPane;
	
	

	public MainGUI() {
		
		ImageIcon logo = new ImageIcon("src/icons/logo.png");
			
		//Frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(629,744);
		setResizable(true);
		setTitle("Java Code Editor");
		setLocationRelativeTo(null);
		setIconImage(logo.getImage());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		//MenuBar
		menuBarInvoker();
		
		//ToolBar
		toolBarInvoker();
    
		
		//Project Explorer Section	
		explorerPanel = new JPanel();
		explorerPanel.setLayout(new BorderLayout());
		explorerPanel.setBackground(new Color(255, 255, 255));
			
		explorerTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		explorerTabbedPane.setFocusable(false);
		explorerPanel.add(explorerTabbedPane);
		
		explorerScrollPane = new JScrollPane();
		explorerScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		explorerScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		explorerTabbedPane.addTab("Project Explorer", null, explorerScrollPane, null);
		
		//jtree invoker
		jTreeInvoker();
		
		
		//jtree-> explorerPopupMenu
		explorerPopupMenuInvoker();
		
		
		//Body Section
		bodyPanel = new JPanel();
		bodyPanel.setBackground(new Color(255, 255, 255));
		bodyPanel.setLayout(new BorderLayout(0, 0));
		
		
		//Body Section -> Editor Section
		editorPanel = new JPanel();
		editorPanel.setBackground(new Color(255, 255, 255));
		editorPanel.setPreferredSize(new Dimension(0,400));
		editorPanel.setLayout(new BorderLayout(0, 0));
		bodyPanel.add(editorPanel, BorderLayout.NORTH);
		
		
		editorTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		editorTabbedPane.setFocusable(false);
		editorPanel.add(editorTabbedPane, BorderLayout.CENTER);
			
		editorScrollPane = new JScrollPane();
		editorScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		editorTabbedPane.addTab("Editor", null, editorScrollPane, null);
		
		codingPanel = new JPanel();
		editorScrollPane.setViewportView(codingPanel);
		codingPanel.setLayout(new BorderLayout(0, 0));
		
		textAreaNumbers = new JTextArea();
		textAreaNumbers.setFont(new Font("Consolas", Font.BOLD, 16));
		textAreaNumbers.setBackground(new Color(128, 128, 255));
		textAreaNumbers.setEditable(false);
		codingPanel.add(textAreaNumbers, BorderLayout.WEST);
		textAreaNumbers.setText("  1\n  2\n  3\n  4\n  5\n  6\n 10\n100");
		
		textAreaEditor = new JTextArea();
		textAreaEditor.setTabSize(4);
		textAreaEditor.setFont(new Font("Consolas", Font.PLAIN, 16));
		textAreaEditor.setText("class Main{\n\tpublic static void main(String[] args){\n\t\tSystem.out.println(\"Hello World\");\n\t}\n}");
		codingPanel.add(textAreaEditor, BorderLayout.CENTER);
		
		//textAreaEditor-> editorPopupMenu
		editorPopupMenuInvoker();
		
		//Body Section -> Console Section	
		consolePanel = new JPanel();
		bodyPanel.add(consolePanel, BorderLayout.CENTER);
		consolePanel.setLayout(new GridLayout(0, 1, 0, 0));
			
		consoleTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		consoleTabbedPane.setFocusable(false);
		consolePanel.add(consoleTabbedPane);
		
		consoleScrollPane = new JScrollPane();
		consoleScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		consoleScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		consoleTabbedPane.addTab("Console", null, consoleScrollPane, null);
		
		textAreaConsole = new JTextArea();
		textAreaConsole.setText("Program Output Goes Here");
		textAreaConsole.setEditable(false);
		textAreaConsole.setForeground(new Color(0, 255, 0));
		textAreaConsole.setBackground(new Color(0, 0, 0));
		consoleScrollPane.setViewportView(textAreaConsole);
		
		//textAreaConsole->consolePopupMenu
		consolePopupMenuInvoker();
		
		//SplitPanes
		mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,explorerPanel,bodyPanel);
		getContentPane().add(mainSplitPane, BorderLayout.CENTER);
		
		secondarySplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,editorPanel,consolePanel);
		bodyPanel.add(secondarySplitPane, BorderLayout.CENTER);
		
		 //Bottom Panel
        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(255, 255, 255));
        bottomPanel.setLayout(new BorderLayout(0, 0));
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        
        //Bottom Panel -> Progress Bar
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        bottomPanel.add(progressBar, BorderLayout.NORTH);
        
        //Bottom Panel -> Status Label
        label1 = new JLabel("Ready");
        label1.setForeground(new Color(0, 0, 0));
        label1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        bottomPanel.add(label1, BorderLayout.SOUTH);
		
		//BottomBar
		bottomPanel = new JPanel();
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		label1 = new JLabel("Line : xx  Column : yy Count: zz");
		bottomPanel.add(label1, BorderLayout.WEST);
				
		progressBar = new JProgressBar(0,100);
		bottomPanel.add(progressBar, BorderLayout.EAST);
		
		
			
		setVisible(true);

		}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void jTreeInvoker() {
		 String directory ="C:\\\\Users\\\\ruwan\\\\OneDrive\\\\Documents\\\\JCE-workspace";
		 FileSystemModel fileSystemModel;
		 fileSystemModel = new FileSystemModel(new File(directory));
		 tree = new JTree(fileSystemModel);
		 tree.setEditable(false);
		 
		 tree.addTreeSelectionListener(new TreeSelectionListener() {
		      public void valueChanged(TreeSelectionEvent event) {
		        File file = (File) tree.getLastSelectedPathComponent();
		        System.out.println(file.getPath());
		      }
		    });
		 MouseListener ml = new MouseAdapter() {
		     public void mousePressed(MouseEvent e) {
		         if(SwingUtilities.isRightMouseButton(e)){
		         int selRow = tree.getRowForLocation(e.getX(), e.getY());
		         TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
		                 tree.setSelectionPath(selPath); 
		                 if (selRow>-1){
		                    tree.setSelectionRow(selRow); 
		                 }
		         }
		     }
		 };
		 tree.addMouseListener(ml);
		 explorerScrollPane.setViewportView(tree);
	}
	public void explorerPopupMenuInvoker() {
		
		newMenu=new JMenu("New");
		JMenuItem javaProjectItem = new JMenuItem("Java Project");
		JMenuItem packageItem =  new JMenuItem("Package");
		JMenuItem classItem = new JMenuItem("Class");
		JMenuItem interfaceItem = new JMenuItem("Interface");
		JMenuItem enumItem = new JMenuItem("Enum");
		
		newMenu.add(javaProjectItem);
		newMenu.add(packageItem);
		newMenu.add(classItem);
		newMenu.add(interfaceItem);
		newMenu.add(enumItem);
		explorerPopupMenu= new JPopupMenu();
		JMenuItem deleteItem = new JMenuItem("Delete");
		JMenuItem refreshItem = new JMenuItem("Refresh");
		JMenuItem closeProjectItem = new JMenuItem("Close Project");
		
		explorerPopupMenu.add(newMenu);
		explorerPopupMenu.add(deleteItem);
		explorerPopupMenu.add(refreshItem);
		explorerPopupMenu.add(closeProjectItem);
		
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    explorerPopupMenu.show(tree, e.getX(), e.getY());
                }
            }
        });
        
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    explorerPopupMenu.show(tree, e.getX(), e.getY());
                }
            }
        });
        
        javaProjectItem.addActionListener((e)->new ProjectCreator()); 
        packageItem.addActionListener((e)->new PackageCreator());
        classItem.addActionListener((e)->new ClassCreator());
        interfaceItem.addActionListener((e)->new InterfaceCreator());
        enumItem.addActionListener((e)->new EnumCreator());
        deleteItem.addActionListener((e)->{});
        refreshItem.addActionListener((e)->{jTreeInvoker();explorerPopupMenuInvoker();});
        closeProjectItem.addActionListener((e)->{});
        
	}
	public void editorPopupMenuInvoker() {
		editorPopupMenu = new JPopupMenu();
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
        
        undoTypingItem.addActionListener((e)->{});
        redoItem.addActionListener((e)->{});
        saveItem.addActionListener((e)->{});
        copyItem.addActionListener((e)->{});
        cutItem.addActionListener((e)->{});
        pasteItem.addActionListener((e)->{});
        selectAllItem.addActionListener((e)->{});
        
        
			
	}
	public void consolePopupMenuInvoker() {
		consolePopupMenu = new JPopupMenu();
		JMenuItem copy_Item = new JMenuItem("Copy");
		JMenuItem cut_Item = new JMenuItem("Cut");
		JMenuItem select_All_Item = new JMenuItem("Select All");
		JMenuItem clear_Item = new JMenuItem("Clear");
		
        consolePopupMenu.add(copy_Item);
        consolePopupMenu.add(cut_Item);
        consolePopupMenu.add(select_All_Item);
        consolePopupMenu.add(clear_Item);
        textAreaConsole.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    consolePopupMenu.show(textAreaConsole, e.getX(), e.getY());
                }
            }
        });
        
        textAreaConsole.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    consolePopupMenu.show(textAreaConsole, e.getX(), e.getY());
                }
            }
        });
        
        copy_Item.addActionListener((e)->{});
        cut_Item.addActionListener((e)->{});
        select_All_Item.addActionListener((e)->{});
        clear_Item.addActionListener((e)->{});
		
		
	}
	public void menuBarInvoker() {
		// Set the Menus
	    menuFile = new JMenu("File");
	    menuEdit = new JMenu("Edit");
	    menuRun = new JMenu("Run");
	    menuWindow = new JMenu("Window");
	    menuAbout = new JMenu("About");
	        
	    //Set Menu Items
	    newMenu = new JMenu("New");
	    openProject = new JMenuItem("Open Project");
	    save = new JMenuItem("Save");
	    saveAs = new JMenuItem("Save As");
	    saveAll = new JMenuItem("Save All");
	    exit = new JMenuItem("Exit");
	        
	    undo = new JMenuItem("Undo");
	    redo = new JMenuItem("Redo");
	    copy = new JMenuItem("Copy");
	    cut = new JMenuItem("Cut");
	    paste = new JMenuItem("Paste");
	    findReplace = new JMenuItem("Find/Replace");
	        
	    run = new JMenuItem("Run");
	    debug = new JMenuItem("Debug");
	        
	    preferences = new JMenuItem("Preferences");
	        
	    aboutUS = new JMenuItem("About US");
	    aboutSoftware = new JMenuItem("About Software");
	        
	    javaProject= new JMenuItem("Java Project");
	    newClass = new JMenuItem("Class");
	    newInterface = new JMenuItem("Interface");
	    newEnum = new JMenuItem("Enum");
	    newPackage = new JMenuItem("Package");
	        
	    newMenu.add(javaProject);
	    newMenu.add(newPackage);
	    newMenu.add(newClass);
	    newMenu.add(newInterface);
	    newMenu.add(newEnum);
	        
	        
	    menuFile.add(newMenu);
	    menuFile.add(openProject);
	    menuFile.add(save);
	    menuFile.add(saveAs);
	    menuFile.add(saveAll);
	    menuFile.add(exit);
	        
	    menuEdit.add(undo);
	    menuEdit.add(redo);
	    menuEdit.add(copy);
	    menuEdit.add(cut);
	    menuEdit.add(paste);
	    menuEdit.add(findReplace);
	        
	    menuRun.add(run);
	    menuRun.add(debug);
	        
	    menuWindow.add(preferences);
	        
	    menuAbout.add(aboutUS); 
	    menuAbout.add(aboutSoftware); 
	    
	    openProject.addActionListener(this);
        save.addActionListener(this);
        saveAs.addActionListener(this);
        saveAll.addActionListener(this);
        exit.addActionListener((e)->System.exit(0));
        undo.addActionListener(this);
        redo.addActionListener(this);
        copy.addActionListener(this);
        cut.addActionListener(this);
        paste.addActionListener(this);
        findReplace.addActionListener(this);
        run.addActionListener(this);
        debug.addActionListener(this);
        preferences.addActionListener((e)->new Preferences());
        aboutUS.addActionListener((e)->new AboutUS());
        aboutSoftware.addActionListener((e)->new AboutSoftware());
        javaProject.addActionListener((e)->new ProjectCreator());
        newPackage.addActionListener((e)->new PackageCreator());
        newClass.addActionListener((e)->new ClassCreator());
        newInterface.addActionListener((e)->new InterfaceCreator());
        newEnum.addActionListener((e)->new EnumCreator());
          
	    
	    JMenuBar menuBar = new JMenuBar();
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuRun);
		menuBar.add(menuWindow);
		menuBar.add(menuAbout);
		setJMenuBar(menuBar);
			
	}
	
	public void toolBarInvoker() {
		
		ImageIcon saveButtonIcon = new ImageIcon("src/icons/save.png");
		ImageIcon saveAllButtonIcon = new ImageIcon("src/icons/saveAll.png");
		ImageIcon runButtonIcon = new ImageIcon("src/icons/run.png");
		ImageIcon newPackageButtonIcon = new ImageIcon("src/icons/package.png");
		ImageIcon newClassButtonIcon = new ImageIcon("src/icons/class.png");
		ImageIcon newInterfaceButtonIcon = new ImageIcon("src/icons/interface.png");
		ImageIcon newEnumButtonIcon = new ImageIcon("src/icons/enum.png");
		
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		getContentPane().add(toolBar,BorderLayout.NORTH);
		
		//ToolBar Buttons
		saveButton = new JButton();
		saveAllButton = new JButton();
		runButton = new JButton();
		newPackageButton = new JButton();
		newClassButton = new JButton();
		newInterfaceButton = new JButton();
		newEnumButton = new JButton();
		
		saveButton.setMargin(new Insets(-3, -3, -3, -3));
		saveAllButton.setMargin(new Insets(-3, -3, -3, -3));
		runButton.setMargin(new Insets(-3, -3, -3, -3));
		newPackageButton.setMargin(new Insets(-3, -3, -3, -3));
		newClassButton.setMargin(new Insets(-3, -3, -3, -3));
		newInterfaceButton.setMargin(new Insets(-3, -3, -3, -3));
		newEnumButton.setMargin(new Insets(-3, -3, -3, -3));
		
		saveButton.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 2));
		saveAllButton.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 2));
		runButton.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 2));
		newPackageButton.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 2));
		newClassButton.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 2));
		newInterfaceButton.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 2));
		newEnumButton.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 2));
		
		
		saveButton.setIcon(saveButtonIcon);
		saveAllButton.setIcon(saveAllButtonIcon);
		runButton.setIcon(runButtonIcon);
		newPackageButton.setIcon(newPackageButtonIcon);
		newClassButton.setIcon(newClassButtonIcon);
		newInterfaceButton.setIcon(newInterfaceButtonIcon);
		newEnumButton.setIcon(newEnumButtonIcon);
		
		saveButton.setToolTipText("Save");
		saveAllButton.setToolTipText("Save All");
		runButton.setToolTipText("Run");
		newPackageButton.setToolTipText("New Package");
		newClassButton.setToolTipText("New Class");
		newInterfaceButton.setToolTipText("New Interface");
		newEnumButton.setToolTipText("New Enum");
		
		saveButton.setFocusable(false);
		saveAllButton.setFocusable(false);
		runButton.setFocusable(false);
		newPackageButton.setFocusable(false);
		newClassButton.setFocusable(false);
		newInterfaceButton.setFocusable(false);
		newEnumButton.setFocusable(false);
		
		saveButton.addActionListener(this);
		saveAllButton.addActionListener(this);
		runButton.addActionListener(this);
		newPackageButton.addActionListener((e)->new PackageCreator());
		newClassButton.addActionListener((e)->new ClassCreator());
		newInterfaceButton.addActionListener((e)->new InterfaceCreator());
		newEnumButton.addActionListener((e)->new EnumCreator());
		
		
		toolBar.add(saveButton);
		toolBar.add(saveAllButton);
		toolBar.add(runButton);
		toolBar.add(newPackageButton);
		toolBar.add(newClassButton);
		toolBar.add(newInterfaceButton);
		toolBar.add(newEnumButton);
		
	}

}