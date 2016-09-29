package sly.app.windows;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import sly.app.view.ExamplesOfView;
import sly.app.view.HelpOfView;
import sly.app.view.OptionOfNetworkInView;

public class ViewOfApp extends JPanel {
	HelpOfView helpOfView=null;
	ExamplesOfView examplesOfView=null;
	WindowOfApp windowOfApp=null;
	OptionOfNetworkInView optionOfNetworkInView=null;
	
	public OptionOfNetworkInView getOptionOfNetworkInView() {
		return optionOfNetworkInView;
	}

	public void setOptionOfNetworkInView(OptionOfNetworkInView optionOfNetworkInView) {
		this.optionOfNetworkInView = optionOfNetworkInView;
	}

	public ExamplesOfView getExamplesOfView() {
		return examplesOfView;
	}

	public void setExamplesOfView(ExamplesOfView examplesOfView) {
		this.examplesOfView = examplesOfView;
	}

	public WindowOfApp getWindowOfApp() {
		return windowOfApp;
	}

	public void setWindowOfApp(WindowOfApp windowOfApp) {
		this.windowOfApp = windowOfApp;
	}

	public HelpOfView getHelpOfView() {
		return helpOfView;
	}

	public void setHelpOfView(HelpOfView helpOfView) {
		this.helpOfView = helpOfView;
	}

	//程序的中央一大片的显示区域
	public ViewOfApp(WindowOfApp windowOfApp) {
		this.setVisible(false);
		this.setBackground(Color.white);
		this.setOpaque(false);
		this.windowOfApp=windowOfApp;
		this.setBounds(160,10,windowOfApp.getWidth()-170-windowOfApp.getInsets().left-windowOfApp.getInsets().right,windowOfApp.getHeight()-20-windowOfApp.getInsets().bottom-windowOfApp.getInsets().top);
		helpOfView=new HelpOfView();
		examplesOfView=new ExamplesOfView(windowOfApp);
		//this.setBounds(150,0,windowOfApp.getWidth()-150,windowOfApp.getHeight());
		helpOfView.setBounds(0,0,this.getWidth(),this.getHeight());
		examplesOfView.setBounds(0,150,600,200);
		//this.add(helpOfView);
		//this.add(examplesOfView);
		this.setLayout(null);
	}

	@Override
	public void repaint(long time, int x, int y, int width, int height) {
		super.repaint(time, x, y, width, height);
		if(windowOfApp!=null) {
			this.setBounds(160,10,windowOfApp.getWidth()-170-windowOfApp.getInsets().left-windowOfApp.getInsets().right,windowOfApp.getHeight()-20-windowOfApp.getInsets().bottom-windowOfApp.getInsets().top);
			helpOfView.setBounds(0,0,this.getWidth(),this.getHeight());
			examplesOfView.setBounds(0,150,600,200);
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(windowOfApp!=null) {
			this.setBounds(160,10,windowOfApp.getWidth()-170-windowOfApp.getInsets().left-windowOfApp.getInsets().right,windowOfApp.getHeight()-20-windowOfApp.getInsets().bottom-windowOfApp.getInsets().top);
			helpOfView.setBounds(0,0,this.getWidth(),this.getHeight());
			examplesOfView.setBounds(0,150,600,200);
		}
	}
}
