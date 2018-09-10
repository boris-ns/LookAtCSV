package view;

import javax.swing.*;

public class StatusBar extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel label;

    public StatusBar(String text) {
        label = new JLabel(text);
        add(label);
    }

    public void setStatusText(String text) {
        label.setText(text);
    }
}
