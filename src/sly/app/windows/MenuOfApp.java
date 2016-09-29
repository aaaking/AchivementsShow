package sly.app.windows;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;

public class MenuOfApp extends JPanel {
	ButtonOfMenu buttonOfOpenFile;
	ButtonOfMenu buttonOfConstructGNW;
	ButtonOfMenu buttonOfAbout;
	ButtonOfMenu buttonOfExit;
	ButtonOfMenu buttonOfExamples;
	ButtonOfMenu buttonOfHelp;
	ButtonOfMenu buttonOfSaveNetwork;
	WindowOfApp windowOfApp=null;
	
	public MenuOfApp(WindowOfApp windowOfApp) {
		this.windowOfApp=windowOfApp;
		buttonOfOpenFile=new ButtonOfMenu(windowOfApp,"Open File");
		buttonOfConstructGNW=new ButtonOfMenu(windowOfApp,"Construct GNW");
		buttonOfAbout=new ButtonOfMenu(windowOfApp,"About");
		buttonOfExit=new ButtonOfMenu(windowOfApp,"Exit");
		buttonOfExamples=new ButtonOfMenu(windowOfApp,"Examples");
		buttonOfHelp=new ButtonOfMenu(windowOfApp,"Help");
		buttonOfSaveNetwork=new ButtonOfMenu(windowOfApp,"Save Network");
		buttonOfOpenFile.setBounds(windowOfApp.getInsets().left+10,windowOfApp.getInsets().top+100,140,50);
		buttonOfConstructGNW.setBounds(windowOfApp.getInsets().left+10,windowOfApp.getInsets().top+150,140,50);
		buttonOfSaveNetwork.setBounds(windowOfApp.getInsets().left+10,windowOfApp.getInsets().top+200,140,50);
		buttonOfExamples.setBounds(windowOfApp.getInsets().left+10,windowOfApp.getInsets().top+250,140,50);
		buttonOfHelp.setBounds(windowOfApp.getInsets().left+10,windowOfApp.getInsets().top+300,140,50);
		buttonOfAbout.setBounds(windowOfApp.getInsets().left+10,windowOfApp.getInsets().top+350,140,50);
		buttonOfExit.setBounds(windowOfApp.getInsets().left+10,windowOfApp.getInsets().top+400,140,50);
		this.setBounds(windowOfApp.getInsets().left,windowOfApp.getInsets().top,150,windowOfApp.getHeight()-windowOfApp.getInsets().bottom-windowOfApp.getInsets().top);
		this.setLayout(null);
		this.add(buttonOfOpenFile);
		this.add(buttonOfConstructGNW);
		this.add(buttonOfSaveNetwork);
		this.add(buttonOfExamples);
		this.add(buttonOfHelp);
		this.add(buttonOfAbout);
		this.add(buttonOfExit);
		buttonOfSaveNetwork.setEnabled(false);
		this.setBackground(null);
		this.setOpaque(false);
		this.setVisible(true);
	}
}
