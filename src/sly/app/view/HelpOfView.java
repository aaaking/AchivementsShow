package sly.app.view;

import java.awt.Font;

import javax.swing.JTextArea;

public class HelpOfView extends JTextArea {
	public HelpOfView() {
		// TODO Auto-generated constructor stub
		super("This would\nhelp you know more about this app");
		this.setOpaque(true);
		this.setFont(new Font(Font.DIALOG,Font.BOLD,50));
		this.setLineWrap(true);
		this.setEditable(false);
	}
}
