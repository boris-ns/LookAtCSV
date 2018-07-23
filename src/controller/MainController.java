package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import common.Constants;
import model.DataModel;
import utils.Writer;
import view.MainFrame;

public class MainController {

	private MainFrame view;
	private DataModel model;
	private String filePath;
	
	public MainController(MainFrame view, DataModel model) {
		this.view = view;
		this.model = model;

		addListenersForMenuItems();
		addListenersForToolbarBtns();
	}
	
	private void addListenersForMenuItems() {
		// File menu
		view.setMenuItemListener(new NewListener(), "New");
		view.setMenuItemListener(new OpenListener(), "Open");
		view.setMenuItemListener(new SaveListener(), "Save");
		view.setMenuItemListener(new SaveAsListener(), "Save as");
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

	private String openSaveAsDialog() {
		String filePath = null;
		JFileChooser chooser = new JFileChooser();
		int retVal = chooser.showSaveDialog(chooser);

		if(retVal == JFileChooser.APPROVE_OPTION) {
			filePath = chooser.getSelectedFile().getAbsolutePath();
		}

		return filePath;
	}

	class NewListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField header = new JTextField("id,username,password");

			Object[] message = new Object[] {
				"Enter CSV header", header
			};

			int option = JOptionPane.showConfirmDialog(null, message,
											"New file", JOptionPane.OK_CANCEL_OPTION);

			while ((header.getText().isEmpty()) && option != JOptionPane.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(null, "You must enter header of CSV file",
						"Error", JOptionPane.ERROR_MESSAGE);
				option = JOptionPane.showConfirmDialog(null, message,
											"New file", JOptionPane.OK_CANCEL_OPTION);
			}

			if (option == JOptionPane.OK_OPTION) {
				model.createEmptyModel(header.getText());
				view.setTableModel(model);
				model.fireTableDataChanged();
				view.enableCommands(true);
				view.setStatusBarText("Created new file.");
			}
		}
	}
	
	class OpenListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();

			int retVal = chooser.showOpenDialog(chooser);

			if (retVal == JFileChooser.APPROVE_OPTION) {
				filePath = chooser.getSelectedFile().getAbsolutePath();
				model.readData(filePath);
				view.setTableModel(model);
				view.setStatusBarText("Opened file with " + model.getDataSize() + " rows.");
				view.setTitle(Constants.APP_NAME + " [" + filePath + "]");
				view.enableCommands(true);
			}
		}
	}
	
	class SaveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (filePath == null) {
				if ((filePath = openSaveAsDialog()) == null) {
					return;
				}
			}

			Writer.writeToFile(model.getData(), filePath);
			view.setStatusBarText("File successfully saved. Saved " + model.getDataSize() + " rows.");
		}
	}
	
	class SaveAsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if ((filePath = openSaveAsDialog()) == null) {
				return;
			}

			filePath += ".csv";

			Writer.writeToFile(model.getData(), filePath);
			view.setStatusBarText("File successfully saved. Saved. " + model.getDataSize() + " rows.");
			view.setTitle(Constants.APP_NAME + " [" + filePath + "]");
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
			model.addRow();
			model.fireTableDataChanged();
			view.scrollToBottom();
			view.setStatusBarText("Added new row.");
			System.out.println("Added new row.");
		}
	}

	// TODO: Fix this, currently NOT WORKING
	class AddColumnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String colName = JOptionPane.showInputDialog(view, "What's the name of the new column ?");
			
			// If user presses Cancel or input string is empty
			if (colName == null || colName.equals(""))
				return;

			model.addColumn(colName);
			model.fireTableDataChanged();
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
				model.removeRow(i);
				model.fireTableDataChanged();
				System.out.println("Row " + selectedRows[i] + " deleted.");
			}

			if (selectedRows.length == 1)
				view.setStatusBarText("Row deleted.");
			else
				view.setStatusBarText("Multiple rows deleted.");

			view.clearTableSelection();
		}
	}
}
