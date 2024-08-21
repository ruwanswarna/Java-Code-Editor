package gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;

import action.CreatePackageAction;
import action.CreateProjectAction;
import action.CreateTabAction;

/*
 * Implements the related functions to create the welcome tab and new tabs whenever the user create a new file or
 * opens a new file 
 */
public class ClosableTabbedPane extends JTabbedPane implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int tabCount = 0;
	private static int welcomeTabIndex;
	private TabCloseUI closeUI = new TabCloseUI(this);

	public ClosableTabbedPane(JPanel editorPanel) {

		this.setFocusable(false);
		editorPanel.add(this);
		createWelcomeTab();

	}

	public ClosableTabbedPane getClosableTabbedPane() {

		return this;
	}

	public void createWelcomeTab() {
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File("src/images/topImage.jpg"));
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		Image topImage = bufferedImage.getScaledInstance(1250, 200, Image.SCALE_DEFAULT);
		// Image topImage=new ImageIcon("src/images/topImage.jpg").getImage();

		class ImagePanel extends JPanel {

			private Image img;

			public ImagePanel(String img) {
				this(new ImageIcon(img).getImage());
			}

			public ImagePanel(Image img) {
				// this.img = img;
				// Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
				// setPreferredSize(size);
				// setMinimumSize(size);
				// setMaximumSize(size);
				// setSize(size);
				// setLayout(null);
			}

			@Override
			protected void paintComponent(Graphics g) {

				super.paintComponent(g);
				g.drawImage(topImage, 0, 0, null);
			}

		}

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel topPanel = new ImagePanel(topImage);
		topPanel.setLayout(new GridBagLayout());
		topPanel.setPreferredSize(new Dimension(0, 80));

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.WHITE);
		bottomPanel.setLayout(null);

		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(bottomPanel, BorderLayout.CENTER);

		JLabel topLabel = new JLabel("Java Code Editor For Developers");
		topLabel.setForeground(Color.WHITE);
		topLabel.setFont(new Font(null, Font.PLAIN, 36));
		topLabel.setSize(600, 50);
		topPanel.add(topLabel);

		JButton createProjectLabel = new JButton("new java project");
		createProjectLabel.setBounds(0, 30, 150, 30);
		createProjectLabel.setForeground(Color.BLUE);
		createProjectLabel.setBackground(Color.WHITE);
		createProjectLabel.setFont(new Font(null, Font.PLAIN, 16));
		createProjectLabel.setBorderPainted(false);
		createProjectLabel.setFocusable(false);

		Font font = createProjectLabel.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		createProjectLabel.setFont(font.deriveFont(attributes));
		bottomPanel.add(createProjectLabel);

		JButton openProjectLabel = new JButton("open project");
		openProjectLabel.setBounds(0, 60, 125, 30);
		openProjectLabel.setForeground(Color.BLUE);
		openProjectLabel.setBackground(Color.WHITE);
		openProjectLabel.setFont(new Font(null, Font.PLAIN, 16));
		openProjectLabel.setBorderPainted(false);
		openProjectLabel.setFocusable(false);

		font = openProjectLabel.getFont();
		attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		openProjectLabel.setFont(font.deriveFont(attributes));
		bottomPanel.add(openProjectLabel);

		JScrollPane editorScrollPane = new JScrollPane();
		editorScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		editorScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.addTab("Welcome   ", editorScrollPane);
		editorScrollPane.setViewportView(panel);
		this.welcomeTabIndex = this.getSelectedIndex();

		createProjectLabel.addActionListener((e) -> new ProjectCreator());
		openProjectLabel.addActionListener((e) ->{JOptionPane.showMessageDialog(this,"This function is not available. Please create a new project.","Alert",JOptionPane.WARNING_MESSAGE);});

	}

	public void createTab(String path) { // create tab for displaying file content from disk

		tabCount++;
		if (tabCount == 1) {
			this.removeTabAt(welcomeTabIndex);
		}
		String title = new CreateTabAction(path).getTitle();
		this.addTab(title, CreateTabAction.getScrollPane());

	}

	public void createTab(String source, String _package, String title, boolean selected, String type) { // type =
																											// "class","enum","interface","package","project"

		tabCount++;
		if (tabCount == 1) {
			this.removeTabAt(welcomeTabIndex);
		}

		if (type == "project") {
			new CreateProjectAction(title);
		}
		else if (type == "package") {
			new CreatePackageAction(source,title);
		} else if (type == "class") {
			new CreateTabAction(source, _package, selected, title);
			this.addTab(title + ".java   ", CreateTabAction.getScrollPane());

		} else if (type == "interface" || type == "enum") {
			new CreateTabAction(source, _package, title, type);
			this.addTab(title + ".java   ", CreateTabAction.getScrollPane());
		}

	}

	/*
	 * private void createPackageAction(String source,String title) {
	 * 
	 * File file = new File(source + "//" + title.replace(".", "//"));
	 * file.mkdirs();
	 * 
	 * }
	 * 
	 * private void createProjectAction(String title) { String path =
	 * DialogCreator.directory.getPath(); File file1 = new File(path + "//" + title
	 * + "//src"); File file2 = new File(path + "//" + title + "//bin");
	 * file1.mkdirs(); file2.mkdirs();
	 * 
	 * }
	 */

	public void paint(Graphics g) {
		super.paint(g);
		closeUI.paint(g);
	}

	public void addTab(String title, Component component) {
		super.addTab(title + "  ", component);
		int count = this.getTabCount();
		this.setSelectedIndex(count - 1);
	}

	public String getTabTitleAt(int index) {
		return super.getTitleAt(index).trim();
	}

	private class TabCloseUI implements MouseListener, MouseMotionListener {
		private ClosableTabbedPane tabbedPane;
		private int closeX = 0, closeY = 0, meX = 0, meY = 0;
		private int selectedTab;
		private final int width = 8, height = 8;
		private Rectangle rectangle = new Rectangle(0, 0, width, height);

		private TabCloseUI() {
		}

		public TabCloseUI(ClosableTabbedPane pane) {

			tabbedPane = pane;
			tabbedPane.addMouseMotionListener(this);
			tabbedPane.addMouseListener(this);
		}

		public void mouseEntered(MouseEvent me) {
		}

		public void mouseExited(MouseEvent me) {
		}

		public void mousePressed(MouseEvent me) {
		}

		public void mouseClicked(MouseEvent me) {
		}

		public void mouseDragged(MouseEvent me) {
		}

		public void mouseReleased(MouseEvent me) {
			if (closeUnderMouse(me.getX(), me.getY())) {
				boolean isToCloseTab = tabAboutToClose(selectedTab);
				if (isToCloseTab && selectedTab > -1) {
					tabbedPane.removeTabAt(selectedTab);
					tabCount--;
					if (tabCount == 0) {
						createWelcomeTab();
					}
				}
				selectedTab = tabbedPane.getSelectedIndex();

			}
		}

		public void mouseMoved(MouseEvent me) {
			meX = me.getX();
			meY = me.getY();
			if (mouseOverTab(meX, meY)) {
				controlCursor();
				tabbedPane.repaint();
			}
		}

		private void controlCursor() {
			if (tabbedPane.getTabCount() > 0)
				if (closeUnderMouse(meX, meY)) {
					tabbedPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
					if (selectedTab > -1)
						tabbedPane.setToolTipTextAt(selectedTab, "Close " + tabbedPane.getTitleAt(selectedTab));
				} else {
					tabbedPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					if (selectedTab > -1)
						tabbedPane.setToolTipTextAt(selectedTab, "");
				}
		}

		private boolean closeUnderMouse(int x, int y) {
			rectangle.x = closeX;
			rectangle.y = closeY;
			return rectangle.contains(x, y);
		}

		public void paint(Graphics g) {

			int tabCount = tabbedPane.getTabCount();
			for (int j = 0; j < tabCount; j++)
				if (tabbedPane.getComponent(j).isShowing()) {
					int x = tabbedPane.getBoundsAt(j).x + tabbedPane.getBoundsAt(j).width - width - 5;
					int y = tabbedPane.getBoundsAt(j).y + 5;
					drawClose(g, x, y);
					break;
				}
			if (mouseOverTab(meX, meY)) {
				drawClose(g, closeX, closeY);
			}
		}

		private void drawClose(Graphics g, int x, int y) {
			if (tabbedPane != null && tabbedPane.getTabCount() > 0) {
				Graphics2D g2 = (Graphics2D) g;
				drawColored(g2, isUnderMouse(x, y) ? Color.RED : Color.WHITE, x, y);
			}
		}

		private void drawColored(Graphics2D g2, Color color, int x, int y) {
			g2.setStroke(new BasicStroke(5, BasicStroke.JOIN_ROUND, BasicStroke.CAP_ROUND));
			g2.setColor(Color.BLACK);
			g2.drawLine(x, y, x + width, y + height);
			g2.drawLine(x + width, y, x, y + height);
			g2.setColor(color);
			g2.setStroke(new BasicStroke(3, BasicStroke.JOIN_ROUND, BasicStroke.CAP_ROUND));
			g2.drawLine(x, y, x + width, y + height);
			g2.drawLine(x + width, y, x, y + height);

		}

		private boolean isUnderMouse(int x, int y) {
			if (Math.abs(x - meX) < width && Math.abs(y - meY) < height)
				return true;
			return false;
		}

		private boolean mouseOverTab(int x, int y) {
			int tabCount = tabbedPane.getTabCount();
			for (int j = 0; j < tabCount; j++)
				if (tabbedPane.getBoundsAt(j).contains(meX, meY)) {
					selectedTab = j;
					closeX = tabbedPane.getBoundsAt(j).x + tabbedPane.getBoundsAt(j).width - width - 5;
					closeY = tabbedPane.getBoundsAt(j).y + 5;
					return true;
				}
			return false;
		}

	}

	public boolean tabAboutToClose(int tabIndex) {
		String tab = getTabTitleAt(tabIndex);
		int choice = JOptionPane.showConfirmDialog(null, "Save \"" + tab + "\" before closing ?", "Save file",
				JOptionPane.INFORMATION_MESSAGE);
		
		return choice == 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
