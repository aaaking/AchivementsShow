package sly.app.network;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

import sly.app.windows.WindowOfApp;

public class WindowOfNetwork extends JFrame {
	private WindowOfApp windowOfApp;
	private JScrollPaneOfWindow jScrollPaneOfWindow;

	public WindowOfNetwork(WindowOfApp windowOfApp) {
		windowOfApp.setWindowOfNetwork(this);
		this.setTitle("Gene Network");
		this.setSize(800, 600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.windowOfApp = windowOfApp;
		this.setBackground(Color.red);
		this.setLayout(null);
		jScrollPaneOfWindow = new JScrollPaneOfWindow(windowOfApp, new JPanelOfJScroll(windowOfApp));
		// jScrollPaneOfWindow.setBounds(0,0,this.getWidth()-jScrollPaneOfWindow.getVerticalScrollBar().getWidth()-this.getInsets().left-this.getInsets().right,this.getHeight()-jScrollPaneOfWindow.getHorizontalScrollBar().getHeight()-this.getInsets().top-this.getInsets().bottom);
		this.add(jScrollPaneOfWindow);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public void repaint(long time, int x, int y, int width, int height) {
		super.repaint(time, x, y, width, height);
		jScrollPaneOfWindow.setSize(this.getWidth() - this.getInsets().left - this.getInsets().right, this.getHeight() - this.getInsets().top - this.getInsets().bottom);
		// jScrollPaneOfWindow.setBounds(0,0,this.getWidth()-this.getInsets().left-this.getInsets().right,this.getHeight()-this.getInsets().top-this.getInsets().bottom);
	}

	public WindowOfApp getWindowOfApp() {
		return windowOfApp;
	}

	public void setWindowOfApp(WindowOfApp windowOfApp) {
		this.windowOfApp = windowOfApp;
	}

	public JScrollPaneOfWindow getjScrollPaneOfWindow() {
		return jScrollPaneOfWindow;
	}

	public void setjScrollPaneOfWindow(JScrollPaneOfWindow jScrollPaneOfWindow) {
		this.jScrollPaneOfWindow = jScrollPaneOfWindow;
	}
}
