package sly.app.network;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import sly.app.windows.WindowOfApp;

public class WindowOfSubNetwork extends JFrame {
	private WindowOfApp windowOfApp;
	private JScrollPane jScrollPane;
	private JPanelOfSubNetwork jPanelOfSubNetwork;
	private NodeOfSubNetwork nodes=null;
	public WindowOfSubNetwork(WindowOfApp windowOfApp,String str) {
		windowOfApp.setWindowOfSubNetwork(this);
		NodeOfSubNetwork.setWindowOfApp(windowOfApp);
		String[] strs=str.split("\n");
		nodes=new NodeOfSubNetwork();
		nodes.setNext(null);
		for(int i=0;i<strs.length;i++) {
			for(int j=0;j<NodeOfNetwork.getNumOfNodes();j++) {
				if(strs[i].equals(windowOfApp.getjPanelOfJScroll().getNodes()[j].getName())) {
					NodeOfSubNetwork node=new NodeOfSubNetwork();
					node.setName(strs[i]);
					node.setId(windowOfApp.getjPanelOfJScroll().getNodes()[j].getId());
					node.setNext(nodes.getNext());
					nodes.setNext(node);
					break;
				}
			}
		}
		for(NodeOfSubNetwork i=nodes.next;i!=null;i=i.getNext()) {
			for(int j=0;j<NodeOfNetwork.getNumOfNodes();j++) {
				if(NodeOfNetwork.getEdges()[i.getId()][j]!=0) {
					NodeOfSubNetwork node=new NodeOfSubNetwork();
					node.setName(windowOfApp.getjPanelOfJScroll().getNodes()[j].getName());
					node.setId(windowOfApp.getjPanelOfJScroll().getNodes()[j].getId());
					node.setNext(nodes.getNext());
					nodes.setNext(node);
				}
			}
			for(int j=0;j<NodeOfNetwork.getNumOfNodes();j++) {
				if(NodeOfNetwork.getEdges()[j][i.getId()]!=0) {
					NodeOfSubNetwork node=new NodeOfSubNetwork();
					node.setName(windowOfApp.getjPanelOfJScroll().getNodes()[j].getName());
					node.setId(windowOfApp.getjPanelOfJScroll().getNodes()[j].getId());
					node.setNext(nodes.getNext());
					nodes.setNext(node);
				}
			}
		}
		for(NodeOfSubNetwork i=nodes.next;i!=null;i=i.getNext())
			NodeOfSubNetwork.setNumOfNodes(NodeOfSubNetwork.getNumOfNodes()+1);
		this.setTitle("Gene SubNetwork");
		this.setSize(800,600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.windowOfApp=windowOfApp;
		//this.setLayout(null);
		jPanelOfSubNetwork=new JPanelOfSubNetwork(windowOfApp);
		jScrollPane=new JScrollPane(jPanelOfSubNetwork);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setWheelScrollingEnabled(false);
		this.setSize(windowOfApp.getWindowOfSubNetwork().getWidth()-jScrollPane.getHorizontalScrollBar().getWidth()-windowOfApp.getWindowOfSubNetwork().getInsets().left-windowOfApp.getWindowOfSubNetwork().getInsets().right,windowOfApp.getWindowOfSubNetwork().getHeight()-jScrollPane.getVerticalScrollBar().getHeight()-windowOfApp.getWindowOfSubNetwork().getInsets().top-windowOfApp.getWindowOfSubNetwork().getInsets().bottom);
		this.add(jScrollPane);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public void repaint(long time, int x, int y, int width, int height) {
		super.repaint(time, x, y, width, height);
		jScrollPane.setSize(this.getWidth()-this.getInsets().left-this.getInsets().right,this.getHeight()-this.getInsets().top-this.getInsets().bottom);
		System.out.println("WindowOfSubNetwork repaint");
		//jScrollPaneOfWindow.setBounds(0,0,this.getWidth()-this.getInsets().left-this.getInsets().right,this.getHeight()-this.getInsets().top-this.getInsets().bottom);
	}

	public WindowOfApp getWindowOfApp() {
		return windowOfApp;
	}

	public void setWindowOfApp(WindowOfApp windowOfApp) {
		this.windowOfApp = windowOfApp;
	}

	public JScrollPane getjScrollPane() {
		return jScrollPane;
	}

	public void setjScrollPane(JScrollPane jScrollPane) {
		this.jScrollPane = jScrollPane;
	}

	public JPanelOfSubNetwork getjPanelOfSubNetwork() {
		return jPanelOfSubNetwork;
	}

	public void setjPanelOfSubNetwork(JPanelOfSubNetwork jPanelOfSubNetwork) {
		this.jPanelOfSubNetwork = jPanelOfSubNetwork;
	}

	public NodeOfSubNetwork getNodes() {
		return nodes;
	}

	public void setNodes(NodeOfSubNetwork nodes) {
		this.nodes = nodes;
	}
}
