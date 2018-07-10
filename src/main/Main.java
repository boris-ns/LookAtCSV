package main;

import controller.MainController;
import model.DataModel;
import view.MainFrame;

public class Main {

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		DataModel model = new DataModel();
		MainController controller = new MainController(frame, model);
	}
}
