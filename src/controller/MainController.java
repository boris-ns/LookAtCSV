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
		view.setMenuItemListener(new ExitListener(), "Exit");
		view.setMenuItemListener(new AboutListener(), "About");
		view.setMenuItemListener(new HowToUseListener(), "How to use");
	}
	
	private void addListenersForToolbarBtns() {
		// TODO: finish this
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
		// TODO: write 'how to use' text
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "How to use\nWork in progress!",
										"How to use", JOptionPane.INFORMATION_MESSAGE);
			
			System.out.println("Operation HOW TO USE: activated");
		}
	}
}
