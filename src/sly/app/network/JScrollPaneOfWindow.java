package sly.app.network;

import javax.swing.JScrollPane;

import sly.app.windows.WindowOfApp;

public class JScrollPaneOfWindow extends JScrollPane {
	private WindowOfApp windowOfApp=null;
	private JPanelOfJScroll jpjs=null;
	
	public JScrollPaneOfWindow(WindowOfApp windowOfApp,JPanelOfJScroll jpjs) {
		super(jpjs);
		this.jpjs=jpjs;
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.windowOfApp=windowOfApp;
		this.setWheelScrollingEnabled(false);
		this.setSize(windowOfApp.getWindowOfNetwork().getWidth()-this.getHorizontalScrollBar().getWidth()-windowOfApp.getWindowOfNetwork().getInsets().left-windowOfApp.getWindowOfNetwork().getInsets().right,windowOfApp.getWindowOfNetwork().getHeight()-this.getVerticalScrollBar().getHeight()-windowOfApp.getWindowOfNetwork().getInsets().top-windowOfApp.getWindowOfNetwork().getInsets().bottom);
	}
	
	public WindowOfApp getWindowOfApp() {
		return windowOfApp;
	}

	public void setWindowOfApp(WindowOfApp windowOfApp) {
		this.windowOfApp = windowOfApp;
	}

	public JPanelOfJScroll getJpjs() {
		return jpjs;
	}

	public void setJpjs(JPanelOfJScroll jpjs) {
		this.jpjs = jpjs;
	}
}
