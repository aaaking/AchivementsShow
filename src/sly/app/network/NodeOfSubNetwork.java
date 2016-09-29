package sly.app.network;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.event.MouseInputListener;

import sly.app.windows.WindowOfApp;

public class NodeOfSubNetwork extends JButton {
	static private NodeOfSubNetwork currentNode=null;
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
	private int id;
	NodeOfSubNetwork next;
	MouseListenerOfNodeOfSubNetwork mouseListenerOfNodeOfSubNetwork=null;
	
	public class MouseListenerOfNodeOfSubNetwork implements MouseInputListener {
		NodeOfSubNetwork non=null;
		Color oldC=null;
		int xChanged=0;
		int yChanged=0;
		int xInit=0;
		int yInit=0;
		int xSum=0;
		int ySum=0;
		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			non=(NodeOfSubNetwork)e.getSource();
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
			non=(NodeOfSubNetwork)e.getSource();
			if(non.getX()+NodeOfSubNetwork.getWidthOfNodesToDisplay()>NodeOfSubNetwork.getWidthOfWorldToDisplay()&&e.getX()>0) {
				return;
			}
			else if(non.getY()+NodeOfSubNetwork.getHeightOfNodesToDisplay()>NodeOfSubNetwork.getHeightOfWorldToDisplay()&&e.getY()>0) {
				return;
			}
			non.setBounds(non.getX()+e.getX()-xInit,non.getY()+e.getY()-yInit,non.getWidth(),non.getHeight());
			non.setxWorld((int)(non.getX()/rato));
			non.setyWorld((int)(non.getY()/rato));
			windowOfApp.getWindowOfSubNetwork().repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}
	}
	
	public NodeOfSubNetwork() {
		if(mouseListenerOfNodeOfSubNetwork==null)
			mouseListenerOfNodeOfSubNetwork=new MouseListenerOfNodeOfSubNetwork();
		this.addMouseListener(mouseListenerOfNodeOfSubNetwork);
		this.addMouseMotionListener(mouseListenerOfNodeOfSubNetwork);
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
	public static NodeOfSubNetwork getCurrentNode() {
		return currentNode;
	}
	public static void setCurrentNode(NodeOfSubNetwork currentNode) {
		NodeOfSubNetwork.currentNode = currentNode;
	}
	public static Color getColorOfCurrentNode() {
		return colorOfCurrentNode;
	}
	public static void setColorOfCurrentNode(Color colorOfCurrentNode) {
		NodeOfSubNetwork.colorOfCurrentNode = colorOfCurrentNode;
	}
	public static Color getColorOfCurrentNodes() {
		return colorOfCurrentNodes;
	}
	public static void setColorOfCurrentNodes(Color colorOfCurrentNodes) {
		NodeOfSubNetwork.colorOfCurrentNodes = colorOfCurrentNodes;
	}
	public static Color getColorOfCurrentEdges() {
		return colorOfCurrentEdges;
	}
	public static void setColorOfCurrentEdges(Color colorOfCurrentEdges) {
		NodeOfSubNetwork.colorOfCurrentEdges = colorOfCurrentEdges;
	}
	public static int getNumOfNodesARow() {
		return numOfNodesARow;
	}
	public static void setNumOfNodesARow(int numOfNodesARow) {
		NodeOfSubNetwork.numOfNodesARow = numOfNodesARow;
	}
	public static WindowOfApp getWindowOfApp() {
		return windowOfApp;
	}
	public static void setWindowOfApp(WindowOfApp windowOfApp) {
		NodeOfSubNetwork.windowOfApp = windowOfApp;
	}
	public static int getNumOfNodes() {
		return numOfNodes;
	}
	public static void setNumOfNodes(int numOfNodes) {
		NodeOfSubNetwork.numOfNodes = numOfNodes;
	}
	public static double getRato() {
		return rato;
	}
	public static void setRato(double rato) {
		NodeOfSubNetwork.rato = rato;
	}
	public static int getWidthOfNodes() {
		return widthOfNodes;
	}
	public static void setWidthOfNodes(int widthOfNodes) {
		NodeOfSubNetwork.widthOfNodes = widthOfNodes;
	}
	public static int getHeightOfNodes() {
		return heightOfNodes;
	}
	public static void setHeightOfNodes(int heightOfNodes) {
		NodeOfSubNetwork.heightOfNodes = heightOfNodes;
	}
	public static int getBlank() {
		return blank;
	}
	public static void setBlank(int blank) {
		NodeOfSubNetwork.blank = blank;
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
	public static int gethValueWorld() {
		return hValueWorld;
	}
	public static void sethValueWorld(int hValueWorld) {
		NodeOfSubNetwork.hValueWorld = hValueWorld;
	}
	public static int getvValueWorld() {
		return vValueWorld;
	}
	public static void setvValueWorld(int vValueWorld) {
		NodeOfSubNetwork.vValueWorld = vValueWorld;
	}
	public static int gethMaxWorld() {
		return hMaxWorld;
	}
	public static void sethMaxWorld(int hMaxWorld) {
		NodeOfSubNetwork.hMaxWorld = hMaxWorld;
	}
	public static int getvMaxWorld() {
		return vMaxWorld;
	}
	public static void setvMaxWorld(int vMaxWorld) {
		NodeOfSubNetwork.vMaxWorld = vMaxWorld;
	}
	public static int getWidthOfWorld() {
		return widthOfWorld;
	}
	public static void setWidthOfWorld(int widthOfWorld) {
		NodeOfSubNetwork.widthOfWorld = widthOfWorld;
	}
	public static int getHeightOfWorld() {
		return heightOfWorld;
	}
	public static void setHeightOfWorld(int heightOfWorld) {
		NodeOfSubNetwork.heightOfWorld = heightOfWorld;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public NodeOfSubNetwork getNext() {
		return next;
	}
	public void setNext(NodeOfSubNetwork next) {
		this.next = next;
	}
	public static int getWidthOfNodesToDisplay() {
		return (int)(widthOfNodes*rato);
	}
	public static int getBlankToDisplay() {
		return (int)(blank*rato);
	}
	public static int getHeightOfNodesToDisplay() {
		return (int)(heightOfNodes*rato);
	}
	public static int getWidthOfWorldToDisplay() {
		return (int)(widthOfWorld*rato);
	}
	public static int getHeightOfWorldToDisplay() {
		return (int)(heightOfWorld*rato);
	}
	public static int getHValueToDisplay() {
		return (int)(hValueWorld*rato);
	}
	public static int getVValueToDisplay() {
		return (int)(vValueWorld*rato);
	}
	public static int getHMaxToDisplay()
	{
		return (int)(hMaxWorld*rato);
	}
	public static int getVMaxToDisplay() {
		return (int)(vMaxWorld*rato);
	}

	public static Color getColorOfRegulatingEdges() {
		return colorOfRegulatingEdges;
	}

	public static void setColorOfRegulatingEdges(Color colorOfRegulatingEdges) {
		NodeOfSubNetwork.colorOfRegulatingEdges = colorOfRegulatingEdges;
	}

	public static Color getColorOfRegulatedEdges() {
		return colorOfRegulatedEdges;
	}

	public static void setColorOfRegulatedEdges(Color colorOfRegulatedEdges) {
		NodeOfSubNetwork.colorOfRegulatedEdges = colorOfRegulatedEdges;
	}

	public static Color getColorOfUnknownEdges() {
		return colorOfUnknownEdges;
	}

	public static void setColorOfUnknownEdges(Color colorOfUnknownEdges) {
		NodeOfSubNetwork.colorOfUnknownEdges = colorOfUnknownEdges;
	}

	public static Color getColorOfDoubleRegulatingEdges() {
		return colorOfDoubleRegulatingEdges;
	}

	public static void setColorOfDoubleRegulatingEdges(Color colorOfDoubleRegulatingEdges) {
		NodeOfSubNetwork.colorOfDoubleRegulatingEdges = colorOfDoubleRegulatingEdges;
	}
}