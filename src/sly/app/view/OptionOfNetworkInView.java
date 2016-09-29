package sly.app.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sly.app.network.NodeOfNetwork;
import sly.app.network.PrepareForSubNetwork;
import sly.app.windows.WindowOfApp;

public class OptionOfNetworkInView extends JPanel {
	WindowOfApp windowOfApp;
	JButton searchButton;
	JTextField searchField;
	JTextArea searchArea;
	JButton showWindowOfNetwork;
	JButton createASubNetworkWithAGene;

	public OptionOfNetworkInView(WindowOfApp windowOfApp) {
		super();
		this.windowOfApp = windowOfApp;
		windowOfApp.getViewOfApp().setVisible(false);
		windowOfApp.getViewOfApp().removeAll();
		windowOfApp.getViewOfApp().add(this);
		this.setLayout(null);
		searchButton = new JButton("search");
		searchField = new JTextField("name of the Gene you want to search");
		searchArea = new JTextArea(10, 10);
		showWindowOfNetwork = new JButton("Show Window Of the full Network");
		createASubNetworkWithAGene = new JButton("Creat a sub network surrounds some selected Gene");
		this.add(searchButton);
		this.add(searchArea);
		this.add(searchField);
		this.add(showWindowOfNetwork);
		this.add(createASubNetworkWithAGene);
		this.setBounds(0, 0, windowOfApp.getViewOfApp().getWidth(), windowOfApp.getViewOfApp().getHeight());

		searchField.setBounds(0, 0, windowOfApp.getViewOfApp().getWidth(), 50);
		searchButton.setBounds(0, 50, windowOfApp.getViewOfApp().getWidth(), 30);
		showWindowOfNetwork.setBounds(0, 80, windowOfApp.getViewOfApp().getWidth(), 30);
		createASubNetworkWithAGene.setBounds(0, 110, windowOfApp.getViewOfApp().getWidth(), 30);
		searchArea.setBounds(0, 140, windowOfApp.getViewOfApp().getWidth(), windowOfApp.getViewOfApp().getHeight() - 110);

		TempActionListener t = new TempActionListener();
		searchButton.addActionListener(t);
		showWindowOfNetwork.addActionListener(t);
		createASubNetworkWithAGene.addActionListener(t);
		windowOfApp.getViewOfApp().setVisible(true);
		windowOfApp.getViewOfApp().repaint();
	}

	public WindowOfApp getWindowOfApp() {
		return windowOfApp;
	}

	public void setWindowOfApp(WindowOfApp windowOfApp) {
		this.windowOfApp = windowOfApp;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}

	public JTextField getSearchField() {
		return searchField;
	}

	public void setSearchField(JTextField searchField) {
		this.searchField = searchField;
	}

	public JTextArea getSearchArea() {
		return searchArea;
	}

	public void setSearchArea(JTextArea searchArea) {
		this.searchArea = searchArea;
	}

	private class TempActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton jb = (JButton) e.getSource();
			if (jb.getText().equals("search")) {
				int k = 0;
				for (; k < NodeOfNetwork.getNumOfNodes(); k++) {
					if (searchField.getText().equals(windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getJpjs().getNodes()[k].getName()))
						break;
				}
				if (k < NodeOfNetwork.getNumOfNodes()) {
					NodeOfNetwork non = windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getJpjs().getNodes()[k];
					// windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getHorizontalScrollBar().setValue(non.getX());
					// windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getVerticalScrollBar().setValue(non.getY());
					if (NodeOfNetwork.getCurrentNode() != null) {
						NodeOfNetwork.getCurrentNode().setBackground(NodeOfNetwork.getMouseListenerOfNodeOfNetwork().getOldC());
						for (int i = 0; i < NodeOfNetwork.getNumOfNodes(); i++) {
							if (NodeOfNetwork.getEdges()[non.getId()][i] != 0) {
								windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getJpjs().getNodes()[i].setBackground(NodeOfNetwork.getMouseListenerOfNodeOfNetwork().getOldC());
							}
						}
						for (int i = 0; i < NodeOfNetwork.getNumOfNodes(); i++) {
							if (NodeOfNetwork.getEdges()[i][non.getId()] != 0) {
								windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getJpjs().getNodes()[i].setBackground(NodeOfNetwork.getMouseListenerOfNodeOfNetwork().getOldC());
							}
						}
						NodeOfNetwork.setCurrentNode(non);
					} else {
						NodeOfNetwork.getMouseListenerOfNodeOfNetwork().setOldC(non.getBackground());
					}
					NodeOfNetwork.setCurrentNode(non);
					non.setBackground(NodeOfNetwork.getColorOfCurrentNode());
					windowOfApp.getViewOfApp().getOptionOfNetworkInView().getSearchField().setText(non.getName());
					String str = "The Genes that regulate the Gene which you select:\n";
					for (int i = 0; i < NodeOfNetwork.getNumOfNodes(); i++) {
						if (NodeOfNetwork.getEdges()[non.getId()][i] != 0) {
							str = str + windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getJpjs().getNodes()[i].getName() + "\n";
							windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getJpjs().getNodes()[i].setBackground(NodeOfNetwork.getColorOfCurrentNodes());
						}
					}
					str = str + "The Genes that are regulated by the Gene which you select:\n";
					for (int i = 0; i < NodeOfNetwork.getNumOfNodes(); i++) {
						if (NodeOfNetwork.getEdges()[i][non.getId()] != 0) {
							str = str + windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getJpjs().getNodes()[i].getName() + "\n";
							windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getJpjs().getNodes()[i].setBackground(NodeOfNetwork.getColorOfCurrentNodes());
						}
					}
					windowOfApp.getViewOfApp().getOptionOfNetworkInView().getSearchArea().setText(str);
				} else {
					windowOfApp.getViewOfApp().getOptionOfNetworkInView().getSearchArea().setText("the Gene you search doesn't exist");
				}
				windowOfApp.getWindowOfNetwork().setVisible(true);
				// windowOfApp.getWindowOfNetwork().repaint();
			} else if (jb.getText().equals("Show Window Of the full Network")) {
				windowOfApp.getWindowOfNetwork().setVisible(true);
			} else if (jb.getText().equals("Creat a sub network surrounds some selected Gene")) {
				windowOfApp.setPrepareForSubNetwork(new PrepareForSubNetwork(windowOfApp));
			}
		}
	}
}
