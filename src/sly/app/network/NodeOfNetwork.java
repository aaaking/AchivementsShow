package sly.app.network;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.event.MouseInputListener;

import sly.app.windows.WindowOfApp;

public class NodeOfNetwork extends JButton {
	static private MouseListenerOfNodeOfNetwork mouseListenerOfNodeOfNetwork=null;
	static private NodeOfNetwork currentNode=null;
	static private Color colorOfCurrentNode=Color.red;
	static private Color colorOfCurrentNodes=Color.blue;
	static private Color colorOfCurrentEdges=Color.gray;
	static private Color colorOfRegulatingEdges=Color.cyan;
	static private Color colorOfRegulatedEdges=Color.pink;
	static private Color colorOfUnknownEdges=Color.BLACK;
	static private Color colorOfDoubleRegulatingEdges=Color.GRAY;
	static private int numOfNodesARow=30;
	static private WindowOfApp windowOfApp=null;
	static private int numOfNodes;
	static private double rato=0.3;
	static private int widthOfNodes=400;
	static private int heightOfNodes=200;
	static private int blank=200;
	private int xWorld;
	private int yWorld;
	static private int hValueWorld;
	static private int vValueWorld;
	static private int hMaxWorld;
	static private int vMaxWorld;
	static private int widthOfWorld;
	static private int heightOfWorld;
	static private byte[][] edges;
	private int id;
	
	public NodeOfNetwork() {
		super();
		if(mouseListenerOfNodeOfNetwork==null)
			mouseListenerOfNodeOfNetwork=new MouseListenerOfNodeOfNetwork();
		this.addMouseListener(mouseListenerOfNodeOfNetwork);
		this.addMouseMotionListener(mouseListenerOfNodeOfNetwork);

	}
	
	static public boolean reduce() {
		if(rato<0.1)
			return false;
		rato=rato-0.05;
		return true;
	}
	static public boolean increase() {
		if(rato>0.95)
			return false;
		rato=rato+0.05;
		return true;
	}
	
	public static WindowOfApp getWindowOfApp() {
		return windowOfApp;
	}
	public static void setWindowOfApp(WindowOfApp windowOfApp) {
		NodeOfNetwork.windowOfApp = windowOfApp;
	}
	
	public class MouseListenerOfNodeOfNetwork implements MouseInputListener {
		NodeOfNetwork non=null;
		Color oldC=null;
		int xChanged=0;
		int yChanged=0;
		int xInit=0;
		int yInit=0;
		int xSum=0;
		int ySum=0;
		@Override
		public void mouseClicked(MouseEvent e) {
			non=(NodeOfNetwork)e.getSource();
			if(currentNode==null)
				currentNode=non;
			else {
				currentNode.setBackground(oldC);
				for(int i=0;i<NodeOfNetwork.getNumOfNodes();i++) {
					if(edges[currentNode.getId()][i]!=0)
						windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getJpjs().getNodes()[i].setBackground(oldC);
				}
				for(int i=0;i<NodeOfNetwork.getNumOfNodes();i++) {
					if(edges[i][currentNode.getId()]!=0)
						windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getJpjs().getNodes()[i].setBackground(oldC);
				}
				currentNode=non;
			}
			oldC=non.getBackground();
			non.setBackground(colorOfCurrentNode);
			windowOfApp.getViewOfApp().getOptionOfNetworkInView().getSearchField().setText(non.getName());
			String str="The Genes that regulate the Gene which you select:\n";
			for(int i=0;i<NodeOfNetwork.getNumOfNodes();i++) {
				if(edges[non.getId()][i]!=0) {
					str=str+windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getJpjs().getNodes()[i].getName()+"\n";
					windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getJpjs().getNodes()[i].setBackground(colorOfCurrentNodes);
				}
			}
			str=str+"The Genes that are regulated by the Gene which you select:\n";
			for(int i=0;i<NodeOfNetwork.getNumOfNodes();i++) {
				if(edges[i][non.getId()]!=0) {
					str=str+windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getJpjs().getNodes()[i].getName()+"\n";
					windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getJpjs().getNodes()[i].setBackground(colorOfCurrentNodes);
				}
			}
			windowOfApp.getViewOfApp().getOptionOfNetworkInView().getSearchArea().setText(str);
			windowOfApp.getWindowOfNetwork().repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			non=(NodeOfNetwork)e.getSource();
			xInit=e.getX();
			yInit=e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			non=null;
			xInit=0;
			yInit=0;
			xSum=0;
			ySum=0;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			non = (NodeOfNetwork) e.getSource();
			if (non.getX() + NodeOfNetwork.getWidthOfNodesToDisplay() > NodeOfNetwork.getWidthOfWorldToDisplay() && e.getX() > 0)
				return;
			else if (non.getY() + NodeOfNetwork.getHeightOfNodesToDisplay() > NodeOfNetwork.getHeightOfWorldToDisplay() && e.getY() > 0)
				return;
			non.setBounds(non.getX() + e.getX() - xInit, non.getY() + e.getY() - yInit, non.getWidth(), non.getHeight());
			non.setxWorld((int) (non.getX() / rato));
			non.setyWorld((int) (non.getY() / rato));
			windowOfApp.getWindowOfNetwork().repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}
		
		public Color getOldC() {
			return oldC;
		}

		public void setOldC(Color oldC) {
			this.oldC = oldC;
		}
	}
	
	public static byte[][] getEdges() {
		return edges;
	}

	public static void setEdges(byte[][] edges) {
		NodeOfNetwork.edges = edges;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getWidthOfNodes() {
		return widthOfNodes;
	}

	public static void setWidthOfNodes(int widthOfNodes) {
		NodeOfNetwork.widthOfNodes = widthOfNodes;
	}

	public static int getHeightOfNodes() {
		return heightOfNodes;
	}

	public static void setHeightOfNodes(int heightOfNodes) {
		NodeOfNetwork.heightOfNodes = heightOfNodes;
	}

	public static int getBlank() {
		return blank;
	}

	public static void setBlank(int blank) {
		NodeOfNetwork.blank = blank;
	}

	public static int getNumOfNodes() {
		return numOfNodes;
	}

	public static void setNumOfNodes(int numOfNodes) {
		NodeOfNetwork.numOfNodes = numOfNodes;
	}

	public static double getRato() {
		return rato;
	}

	public static void setRato(double rato) {
		NodeOfNetwork.rato = rato;
	}

	public static int getWidthOfNodesToDisplay() {
		return (int) (widthOfNodes * rato);
	}

	public static int getHeightOfNodesToDisplay() {
		return (int) (heightOfNodes * rato);
	}

	public static int getBlankToDisplay() {
		return (int) (blank * rato);
	}

	public int getxWorld() {
		return xWorld;
	}

	public void setxWorld(int xWorld) {
		this.xWorld = xWorld;
	}

	public int getyWorld() {
		return yWorld;
	}

	public void setyWorld(int yWorld) {
		this.yWorld = yWorld;
	}

	static public int gethValueWorld() {
		return hValueWorld;
	}

	static public void sethValueWorld(int n) {
		hValueWorld = n;
	}

	static public int getvValueWorld() {
		return vValueWorld;
	}

	static public void setvValueWorld(int n) {
		vValueWorld = n;
	}

	static public int getHValueToDisplay() {
		return (int) (hValueWorld * rato);
	}

	static public int getVValueToDisplay() {
		return (int) (vValueWorld * rato);
	}

	public static int gethMaxWorld() {
		return hMaxWorld;
	}

	public static void sethMaxWorld(int hMaxWorld) {
		NodeOfNetwork.hMaxWorld = hMaxWorld;
	}

	public static int getvMaxWorld() {
		return vMaxWorld;
	}

	public static void setvMaxWorld(int vMaxWorld) {
		NodeOfNetwork.vMaxWorld = vMaxWorld;
	}

	public static int getHMaxToDisplay() {
		return (int) (NodeOfNetwork.hMaxWorld * NodeOfNetwork.rato);
	}

	public static int getVMaxToDisplay() {
		return (int) (NodeOfNetwork.vMaxWorld * NodeOfNetwork.rato);
	}

	public static int getNumOfNodesARow() {
		return numOfNodesARow;
	}

	public static void setNumOfNodesARow(int numOfNodesARow) {
		NodeOfNetwork.numOfNodesARow = numOfNodesARow;
	}

	public static MouseListenerOfNodeOfNetwork getMouseListenerOfNodeOfNetwork() {
		return mouseListenerOfNodeOfNetwork;
	}

	public static void setMouseListenerOfNodeOfNetwork(MouseListenerOfNodeOfNetwork mouseListenerOfNodeOfNetwork) {
		NodeOfNetwork.mouseListenerOfNodeOfNetwork = mouseListenerOfNodeOfNetwork;
	}

	public static NodeOfNetwork getCurrentNode() {
		return currentNode;
	}

	public static void setCurrentNode(NodeOfNetwork currentNode) {
		NodeOfNetwork.currentNode = currentNode;
	}

	public static Color getColorOfCurrentNode() {
		return colorOfCurrentNode;
	}

	public static void setColorOfCurrentNode(Color colorOfCurrentNode) {
		NodeOfNetwork.colorOfCurrentNode = colorOfCurrentNode;
	}

	public static Color getColorOfCurrentNodes() {
		return colorOfCurrentNodes;
	}

	public static void setColorOfCurrentNodes(Color colorOfCurrentNodes) {
		NodeOfNetwork.colorOfCurrentNodes = colorOfCurrentNodes;
	}

	public static Color getColorOfCurrentEdges() {
		return colorOfCurrentEdges;
	}

	public static void setColorOfCurrentEdges(Color colorOfCurrentEdges) {
		NodeOfNetwork.colorOfCurrentEdges = colorOfCurrentEdges;
	}

	public static int getWidthOfWorld() {
		return widthOfWorld;
	}

	public static void setWidthOfWorld(int widthOfWorld) {
		NodeOfNetwork.widthOfWorld = widthOfWorld;
	}

	public static int getHeightOfWorld() {
		return heightOfWorld;
	}

	public static void setHeightOfWorld(int heightOfWorld) {
		NodeOfNetwork.heightOfWorld = heightOfWorld;
	}

	public static int getWidthOfWorldToDisplay() {
		return (int) (widthOfWorld * rato);
	}

	public static int getHeightOfWorldToDisplay() {
		return (int) (heightOfWorld * rato);
	}

	public static Color getColorOfRegulatingEdges() {
		return colorOfRegulatingEdges;
	}

	public static void setColorOfRegulatingEdges(Color colorOfRegulatingEdges) {
		NodeOfNetwork.colorOfRegulatingEdges = colorOfRegulatingEdges;
	}

	public static Color getColorOfRegulatedEdges() {
		return colorOfRegulatedEdges;
	}

	public static void setColorOfRegulatedEdges(Color colorOfRegulatedEdges) {
		NodeOfNetwork.colorOfRegulatedEdges = colorOfRegulatedEdges;
	}

	public static Color getColorOfUnknownEdges() {
		return colorOfUnknownEdges;
	}

	public static void setColorOfUnknownEdges(Color colorOfUnknownEdges) {
		NodeOfNetwork.colorOfUnknownEdges = colorOfUnknownEdges;
	}

	public static Color getColorOfDoubleRegulatingEdges() {
		return colorOfDoubleRegulatingEdges;
	}

	public static void setColorOfDoubleRegulatingEdges(Color colorOfDoubleRegulatingEdges) {
		NodeOfNetwork.colorOfDoubleRegulatingEdges = colorOfDoubleRegulatingEdges;
	}
}
