package view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import common.Constants;
import utils.ImageLoader;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final int WIDTH  = 1024;
	private static final int HEIGHT = 768;

	private HashMap<String, JMenuItem> menuItems;
	
	private JMenuBar menuBar;
	private JToolBar toolbar;
	private JScrollPane tableScroller;
	private JTable dataTable;
	private JButton btnAddRow, btnDeleteRow, btnSave;

	private StatusBar statusBar;

	public MainFrame() {
		menuItems = new HashMap<String, JMenuItem>();

		setTitle(Constants.APP_NAME);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setResizable(true);
		setLayout(new BorderLayout());

		statusBar = new StatusBar("Ready");
		add(statusBar, BorderLayout.SOUTH);

		initMenu();
		initToolbar();
		
		dataTable = new JTable();
		tableScroller = new JScrollPane(dataTable);
		add(tableScroller, BorderLayout.CENTER);

		enableCommands(false);

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

		btnAddRow = new JButton();
		btnDeleteRow = new JButton();
		btnSave = new JButton();

		addButtonToToolbar(toolbar, btnAddRow, "Add row", "res/icons/row_add.png");
		addButtonToToolbar(toolbar, btnDeleteRow, "Delete row", "res/icons/row_delete.png");
		addButtonToToolbar(toolbar, btnSave, "Save", "res/icons/save.png");
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
	private void addButtonToToolbar(JToolBar toolbar, JButton btn, String tooltip, String iconPath) {
		btn.setIcon(new ImageIcon(ImageLoader.loadImage(iconPath)));
		btn.setToolTipText(tooltip);
		toolbar.add(btn);
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
	
	public void setTableModel(TableModel model) {
		dataTable.setModel(model);
	}
	
	public void addDataToTable(String[] header, Object[][] data) {
		dataTable.setModel(new DefaultTableModel(data, header));
	}
	
	public void addRowToTable(Object[] row) {
		DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
		model.addRow(row);
	}
	
	public void addColumnToTable(String columnName) {
		DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
		model.addColumn(columnName);
	}
	
	public void removeRow(int rowIndex) {
		DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
		model.removeRow(rowIndex);
	}
	
	public void clearTableSelection() {
		dataTable.clearSelection();
	}

	public void setStatusBarText(String text) {
		statusBar.setStatusText(text);
	}

	public void enableCommands(boolean lock) {
		menuItems.get("Save").setEnabled(lock);
		menuItems.get("Save as").setEnabled(lock);
		menuItems.get("Add row").setEnabled(lock);
		menuItems.get("Add column").setEnabled(lock);
		menuItems.get("Delete row").setEnabled(lock);
		menuItems.get("Delete column").setEnabled(lock);

		btnAddRow.setEnabled(lock);
		btnDeleteRow.setEnabled(lock);
		btnSave.setEnabled(lock);
	}

	public void scrollToBottom() {
		JScrollBar scrolLBar = tableScroller.getVerticalScrollBar();
		scrolLBar.setValue(scrolLBar.getMaximum());
	}

	public int[] getSelectedRows() {
		return dataTable.getSelectedRows();
	}
}
