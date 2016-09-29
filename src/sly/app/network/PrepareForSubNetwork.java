package sly.app.network;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sly.app.windows.WindowOfApp;

public class PrepareForSubNetwork extends JFrame {
	private WindowOfApp windowOfApp=null;
	private JTextField nameOfANode=null;
	private JTextArea namesOfNodes=null;
	private JScrollPane jScrollOfNames=null;
	private JButton createSubNetwork=null;
	public PrepareForSubNetwork(WindowOfApp windowOfApp) {
		this.setLayout(null);
		this.windowOfApp=windowOfApp;
		windowOfApp.setPrepareForSubNetwork(this);
		this.setBounds(200,100,400,300);
		this.setResizable(false);
		this.setVisible(true);
		this.setTitle("PrepareForSubNetwork");
		nameOfANode=new JTextField("type the name of gene you want to add");
		namesOfNodes=new JTextArea();
		jScrollOfNames=new JScrollPane(namesOfNodes);
		createSubNetwork=new JButton("create sub network by the nodes I have typed and the nodes have edges with them");
		createSubNetwork.setToolTipText("create sub network by the nodes I have typed and the nodes have edges with them");
		this.add(jScrollOfNames);
		this.add(nameOfANode);
		this.add(createSubNetwork);
		nameOfANode.setBounds(0,0,this.getWidth(),30);
		jScrollOfNames.setBounds(0,30,this.getWidth()-this.getInsets().left-this.getInsets().right,this.getHeight()-60-this.getInsets().top-this.getInsets().bottom);
		createSubNetwork.setBounds(this.getInsets().left,this.getHeight()-30-this.getInsets().top-this.getInsets().bottom,this.getWidth()-this.getInsets().left-this.getInsets().right,30);
		//jScrollOfNames.setLayout(null);
		namesOfNodes.setBounds(0,0,jScrollOfNames.getWidth(),jScrollOfNames.getHeight());
		namesOfNodes.setEditable(false);
		nameOfANode.addActionListener(new TempActionListener());
		createSubNetwork.addActionListener(new TempActionListener2());
	}
	
	public WindowOfApp getWindowOfApp() {
		return windowOfApp;
	}
	public void setWindowOfApp(WindowOfApp windowOfApp) {
		this.windowOfApp = windowOfApp;
	}
	public JTextField getNameOfANode() {
		return nameOfANode;
	}
	public void setNameOfANode(JTextField nameOfANode) {
		this.nameOfANode = nameOfANode;
	}
	public JTextArea getNamesOfNodes() {
		return namesOfNodes;
	}
	public void setNamesOfNodes(JTextArea namesOfNodes) {
		this.namesOfNodes = namesOfNodes;
	}
	public JScrollPane getjScrollOfNames() {
		return jScrollOfNames;
	}
	public void setjScrollOfNames(JScrollPane jScrollOfNames) {
		this.jScrollOfNames = jScrollOfNames;
	}
	public JButton getCreateSubNetwork() {
		return createSubNetwork;
	}
	public void setCreateSubNetwork(JButton createSubNetwork) {
		this.createSubNetwork = createSubNetwork;
	}

	private class TempActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField source=(JTextField)e.getSource();
			namesOfNodes.append(source.getText()+"\n");
			nameOfANode.setText("");
		}
	}
	private class TempActionListener2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source=(JButton)e.getSource();
			String str=windowOfApp.getPrepareForSubNetwork().getNamesOfNodes().getText();
			windowOfApp.getPrepareForSubNetwork().setVisible(false);
			new WindowOfSubNetwork(windowOfApp,str);
		}
	}
}
