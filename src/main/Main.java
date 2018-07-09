package main;

import controller.MainController;
import view.MainFrame;

public class Main {

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		MainController controller = new MainController(frame);
	}
}
