package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.DataModel;
import view.MainFrame;

public class MainController {

	private MainFrame view;
	private DataModel model;
	
	public MainController(MainFrame view, DataModel model) {
		this.view = view;
		this.model = model;
		
		view.addDataToTable(model.getHeader(), model.getData()); // move this later to the new listener
		addListenersForMenuItems();
		addListenersForToolbarBtns();
	}
	
	private void addListenersForMenuItems() {
		// File menu
		view.setMenuItemListener(new ExitListener(), "Exit");
		
		// Edit menu
		view.setMenuItemListener(new AddRowListener(), "Add row"); // leave as 2 instances of AddRowList. or make just 1?
		view.setMenuItemListener(new AddColumnListener(), "Add column");
		view.setMenuItemListener(new DeleteRowsListener(), "Delete row");
		
		// Help menu
		view.setMenuItemListener(new AboutListener(), "About");
		view.setMenuItemListener(new HowToUseListener(), "How to use");
	}
	
	private void addListenersForToolbarBtns() {
		view.setToolbarBtnListener(new AddRowListener(), 0);
		view.setToolbarBtnListener(new DeleteRowsListener(), 1);
		view.setToolbarBtnListener(new SaveListener(), 2);
	}
	
	class NewListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO: finish this, new dialog will ask to enter header of csv (id,name,surname)
		}
	}
	
	class OpenListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO: finish this, jfilechooser to open file, and update table
		}
	}
	
	class SaveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Operation SAVE executed.");
		}
	}
	
	class SaveAsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	class ExitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Operation EXIT executed");

			int retVal = JOptionPane.showConfirmDialog(null, "Are you sure?", 
									"Quit editor", JOptionPane.YES_NO_OPTION);
			
			if (retVal == JOptionPane.YES_OPTION)
				System.exit(0);
		}
	}
	
	class AboutListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Author: Boris Sulicenko, 2018",
										"About", JOptionPane.INFORMATION_MESSAGE);
			
			System.out.println("Operation ABOUT: activated");
		}
	}

	class HowToUseListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String message = "How to use:\n"
					+ "Use File->New to create new CSV file\n"
					+ "You have options for adding rows and columns.";	
			
			JOptionPane.showMessageDialog(null, message,
					"How to use", JOptionPane.INFORMATION_MESSAGE);
			
			System.out.println("Operation HOW TO USE: activated");
		}
	}
	
	class AddRowListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			view.addRowToTable(new Object[model.getHeader().length]);
			System.out.println("New row is added.");
		}
	}
	
	class AddColumnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String colName = JOptionPane.showInputDialog(view, "What's the name of the new column ?");
			
			// If user presses Cancel or input string is empty
			if (colName == null || colName.equals(""))
				return;
			
			view.addColumnToTable(colName);
			System.out.println("New column " + colName + " is added.");
		}
	}

	class DeleteRowsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int[] selectedRows = view.getSelectedRows();
			
			if (selectedRows.length == 0)
				return;
			
			// Removing must be from last to first, otherwise there will be exceptions
			for (int i = selectedRows.length - 1; i >= 0; --i) {
				view.removeRow(selectedRows[i]);
				System.out.println("Row " + selectedRows[i] + " deleted.");
			}
			
			view.clearTableSelection();
		}
	}
}
