package sly.app.view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;

import sly.app.windows.WindowOfApp;

public class ButtonOfExamples extends JButton {

	WindowOfApp windowOfApp = null;

	public WindowOfApp getWindowOfApp() {
		return windowOfApp;
	}

	public void setWindowOfApp(WindowOfApp windowOfApp) {
		this.windowOfApp = windowOfApp;
	}

	public ButtonOfExamples(WindowOfApp windowOfApp, String name) {
		this.setName(name);
		this.setText(name);
		this.setForeground(Color.red);
		this.setVisible(true);
		this.windowOfApp = windowOfApp;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	@Override
	public void repaint(long tm, int x, int y, int width, int height) {
		super.repaint(tm, x, y, width, height);
	}

}
