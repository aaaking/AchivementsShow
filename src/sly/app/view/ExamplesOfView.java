package sly.app.view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import sly.app.network.WindowOfNetwork;
import sly.app.windows.WindowOfApp;

public class ExamplesOfView extends JPanel {
	WindowOfApp windowOfApp = null;
	ButtonOfExamples button0;
	ButtonOfExamples button1;
	ButtonOfExamples button2;
	ButtonOfExamples button3;
	ButtonOfExamples button4;
	ButtonOfExamples button5;
	private ActionListenerOfButtonsOfExamples aloboe = null;
	
	public ExamplesOfView(WindowOfApp windowOfApp) {
		this.windowOfApp = windowOfApp;
		aloboe = new ActionListenerOfButtonsOfExamples();
		button0 = new ButtonOfExamples(windowOfApp, "Brief Network");
		button1 = new ButtonOfExamples(windowOfApp, "one");
		button2 = new ButtonOfExamples(windowOfApp, "two");
		button3 = new ButtonOfExamples(windowOfApp, "three");
		button4 = new ButtonOfExamples(windowOfApp, "four");
		button5 = new ButtonOfExamples(windowOfApp, "five");
		button0.setBounds(0, 0, 200, 100);
		button1.setBounds(200, 0, 200, 100);
		button2.setBounds(400, 0, 200, 100);
		button3.setBounds(0, 100, 200, 100);
		button4.setBounds(200, 100, 200, 100);
		button5.setBounds(400, 100, 200, 100);
		this.setLayout(null);
		this.add(button0);
		this.add(button1);
		this.add(button2);
		this.add(button3);
		this.add(button4);
		this.add(button5);
		button0.addActionListener(aloboe);
		this.setBackground(null);
		this.setOpaque(false);
		this.setVisible(true);
	}

	private class ActionListenerOfButtonsOfExamples implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (windowOfApp.getWindowOfNetwork() == null)
				new WindowOfNetwork(windowOfApp);
			else
				windowOfApp.getWindowOfNetwork().setVisible(true);
			windowOfApp.getViewOfApp().setOptionOfNetworkInView(new OptionOfNetworkInView(windowOfApp));
		}

	}
}
