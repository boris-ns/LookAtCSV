package view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JToolBar;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final int WIDTH  = 640;
	private static final int HEIGHT = 480;

	private HashMap<String, JMenuItem> menuItems;
	
	private JMenuBar menuBar;
	private JToolBar toolbar;
	private JTable dataTable;

	public MainFrame() {
		menuItems = new HashMap<String, JMenuItem>();

		setTitle("Look At CSV");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setResizable(true);
		setLayout(new BorderLayout());
		
		initMenu();
		initToolbar();
		
		dataTable = new JTable();
		add(dataTable, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	private void initMenu() {
		menuBar = new JMenuBar();
		
		// File menu
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		addMenuItemToMenu(fileMenu, "New");
		addMenuItemToMenu(fileMenu, "Open");
		addMenuItemToMenu(fileMenu, "Save");
		addMenuItemToMenu(fileMenu, "Save as");
		addMenuItemToMenu(fileMenu, "Exit");
		
		// Edit menu
		JMenu editMenu = new JMenu("Edit");
		menuBar.add(editMenu);
		
		addMenuItemToMenu(editMenu, "Add row");
		addMenuItemToMenu(editMenu, "Add column");
		addMenuItemToMenu(editMenu, "Delete row");
		addMenuItemToMenu(editMenu, "Delete column");
		
		// Help menu
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		addMenuItemToMenu(helpMenu, "About");
		addMenuItemToMenu(helpMenu, "How to use");
		
		setJMenuBar(menuBar);
	}
	
	// TODO: create other buttons
	private void initToolbar() {
		toolbar = new JToolBar();
		add(toolbar, BorderLayout.NORTH);
		
		addButtonToToolbar(toolbar, "New row");
		addButtonToToolbar(toolbar, "Delete row");
		addButtonToToolbar(toolbar, "Save");
	}

	/**
	 * Creates JMenuItem and adds it to the parentMenu. Also
	 * adds that item to the HashMap of JMenuItems.
	 */
	private void addMenuItemToMenu(JMenu parentMenu, String itemName) {
		JMenuItem item = new JMenuItem(itemName);
		parentMenu.add(item);
		menuItems.put(itemName, item);
	}
	
	/**
	 * Creates new JButton and adds it to the toolbar
	 */
	// TODO: change this so insted of text there will be icon on the new button
	private void addButtonToToolbar(JToolBar toolbar, String btnName) {
		JButton button = new JButton(btnName);
		toolbar.add(button);
	}
	
	/**
	 * Adds action listener to the JMenuItem that is specified
	 * the name (menuItem, represents key in the HashMap of JMenuItems)
	 */
	public void setMenuItemListener(ActionListener al, String menuItem) {
		JMenuItem item = menuItems.get(menuItem);
		
		if (item == null) {
			System.out.println("JMenuItem with key " + menuItem + " doesn't exist.");
			return;
		}
		
		item.addActionListener(al);
	}
	
	/**
	 * Adds action listener to the button in the specified position (indexAt)
	 */
	public void setToolbarBtnListener(ActionListener al, int indexAt) {
		JButton button = (JButton) toolbar.getComponentAtIndex(indexAt);
		
		if (button == null) {
			System.out.println("JButton at index " + indexAt + " doesn't exist.");
			return;
		}
		
		button.addActionListener(al);
	}
}
