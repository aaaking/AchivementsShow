package sly.app.windows;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFrame;

import sly.app.network.JPanelOfJScroll;
import sly.app.network.JPanelOfSubNetwork;
import sly.app.network.PrepareForSubNetwork;
import sly.app.network.WindowOfNetwork;
import sly.app.network.WindowOfSubNetwork;

public class WindowOfApp extends JFrame {
	private static WindowOfApp isMe;
	MenuOfApp menuOfApp=null;
	ViewOfApp viewOfApp=null;
	BackgroundOfWindow backgroundOfWindow=null;
	ActionListenerOfAllButtonsOfApp actionListenerOfAllButtonsOfApp=null;
	File openedFile=null;
	WindowOfNetwork windowOfNetwork=null;
	JPanelOfJScroll jPanelOfJScroll=null;
	PrepareForSubNetwork prepareForSubNetwork=null;
	WindowOfSubNetwork windowOfSubNetwork=null;
	JPanelOfSubNetwork jPanelOfSubNetwork=null;
	
	public static WindowOfApp createWindowOfAppInstance() {
		if(isMe == null)
			isMe = new WindowOfApp();
		return isMe;
	}

	private WindowOfApp() {
		actionListenerOfAllButtonsOfApp=new ActionListenerOfAllButtonsOfApp();
		this.setSize(800,600);
		this.setTitle("WindowOfApp");
	}

	public void launch() {
		menuOfApp=new MenuOfApp(this);
		viewOfApp=new ViewOfApp(this);
		backgroundOfWindow=new BackgroundOfWindow(this);
		this.setLayout(null);
		this.add(menuOfApp);
		this.add(viewOfApp);
		this.add(backgroundOfWindow);
		this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                isMe.dispose();
                isMe = null;
            }
        });
		//menuOfApp.setBounds(0,0,150,this.getHeight());
		//this.pack();
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public ActionListenerOfAllButtonsOfApp getActionListenerOfAllButtonsOfApp() {
		return actionListenerOfAllButtonsOfApp;
	}
	
	public ViewOfApp getViewOfApp() {
		return viewOfApp;
	}
	
	public void setOpenedFile(File openedFile) {
		this.openedFile = openedFile;
	}
	
	public WindowOfNetwork getWindowOfNetwork() {
		return windowOfNetwork;
	}
	
	public void setWindowOfNetwork(WindowOfNetwork windowOfNetwork) {
		this.windowOfNetwork = windowOfNetwork;
	}
	
	public JPanelOfJScroll getjPanelOfJScroll() {
		return jPanelOfJScroll;
	}

	public void setjPanelOfJScroll(JPanelOfJScroll jPanelOfJScroll) {
		this.jPanelOfJScroll = jPanelOfJScroll;
	}
	
	public PrepareForSubNetwork getPrepareForSubNetwork() {
		return prepareForSubNetwork;
	}

	public void setPrepareForSubNetwork(PrepareForSubNetwork prepareForSubNetwork) {
		this.prepareForSubNetwork = prepareForSubNetwork;
	}
	
	public WindowOfSubNetwork getWindowOfSubNetwork() {
		return windowOfSubNetwork;
	}

	public void setWindowOfSubNetwork(WindowOfSubNetwork windowOfSubNetwork) {
		this.windowOfSubNetwork = windowOfSubNetwork;
	}
	
	public JPanelOfSubNetwork getjPanelOfSubNetwork() {
		return jPanelOfSubNetwork;
	}

	public void setjPanelOfSubNetwork(JPanelOfSubNetwork jPanelOfSubNetwork) {
		this.jPanelOfSubNetwork = jPanelOfSubNetwork;
	}
}
