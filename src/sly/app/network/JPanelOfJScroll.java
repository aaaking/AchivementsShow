package sly.app.network;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;

import sly.app.windows.WindowOfApp;

public class JPanelOfJScroll extends JPanel {
	private WindowOfApp windowOfApp;
	private NodeOfNetwork[] nodes;
	
	public JPanelOfJScroll(WindowOfApp windowOfApp) {
		this.windowOfApp=windowOfApp;
		windowOfApp.setjPanelOfJScroll(this);
		NodeOfNetwork.setWindowOfApp(windowOfApp);
		NodeOfNetwork.setNumOfNodes(1000);
		NodeOfNetwork.setEdges((new byte[1000][1000]));
		this.setOpaque(false);
		nodes=new NodeOfNetwork[1000];
		for(int i=0;i<NodeOfNetwork.getNumOfNodes();i++)
			nodes[i]=new NodeOfNetwork();
		byte[][] edges=NodeOfNetwork.getEdges();
		for(int i=0;i<NodeOfNetwork.getNumOfNodes();i++) {
			for(int j=0;j<NodeOfNetwork.getNumOfNodes();j++) {
				if(Math.random()>0.999)
					edges[i][j]=1;//有向边
				else if(Math.random()>0.999) {
					edges[i][j]=2;//无向边
					edges[j][i]=2;
				}
				else {
					if(edges[j][i]==1) {
						edges[i][j]=0;//无边
					}
				}
			}
		}
		for(int i=0;i<NodeOfNetwork.getNumOfNodes();) {
			for(int j=0;j<NodeOfNetwork.getNumOfNodesARow();j++) {
				if(i+j==NodeOfNetwork.getNumOfNodes())
					break;
				int temp=i+j;
				nodes[temp].setId(temp);
				nodes[temp].setName("name"+temp);
				nodes[temp].setText("name"+temp);
				nodes[temp].setBounds(j*(NodeOfNetwork.getWidthOfNodesToDisplay()+NodeOfNetwork.getBlankToDisplay()),i*(NodeOfNetwork.getHeightOfNodesToDisplay()+NodeOfNetwork.getBlankToDisplay())/NodeOfNetwork.getNumOfNodesARow(),NodeOfNetwork.getWidthOfNodesToDisplay(),NodeOfNetwork.getHeightOfNodesToDisplay());
				nodes[temp].setxWorld(j*(NodeOfNetwork.getWidthOfNodes()+NodeOfNetwork.getBlank()));
				nodes[temp].setyWorld(i*(NodeOfNetwork.getHeightOfNodes()+NodeOfNetwork.getBlank())/NodeOfNetwork.getNumOfNodesARow());
				this.add(nodes[temp]);
			}
			i=i+NodeOfNetwork.getNumOfNodesARow();
		}
		this.setLayout(null);
		NodeOfNetwork.setWidthOfWorld((NodeOfNetwork.getWidthOfNodes()+NodeOfNetwork.getBlank())*(NodeOfNetwork.getNumOfNodesARow()+10));
		NodeOfNetwork.setHeightOfWorld((NodeOfNetwork.getHeightOfNodes()+NodeOfNetwork.getBlank())*(NodeOfNetwork.getNumOfNodes()/NodeOfNetwork.getNumOfNodesARow()+10));
		this.setPreferredSize(new Dimension(NodeOfNetwork.getWidthOfWorldToDisplay(),NodeOfNetwork.getHeightOfNodesToDisplay()));
		this.addMouseWheelListener(new MouseWheelListenerOfJPanelOfJScroll());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Color oc=g.getColor();
		g.setColor(Color.green);
		drawLines(g);
		g.setColor(oc);
		NodeOfNetwork.sethValueWorld((int)(windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getHorizontalScrollBar().getValue()/NodeOfNetwork.getRato()));
		NodeOfNetwork.setvValueWorld((int)(windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getVerticalScrollBar().getValue()/NodeOfNetwork.getRato()));
		NodeOfNetwork.sethMaxWorld((int)(windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getHorizontalScrollBar().getMaximum()/NodeOfNetwork.getRato()));
		NodeOfNetwork.setvMaxWorld((int)(windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getVerticalScrollBar().getMaximum()/NodeOfNetwork.getRato()));
	}

	private void drawLines(Graphics g) {
		Color oldC=g.getColor();
		for(int i=0;i<NodeOfNetwork.getNumOfNodes();i++) {
			for(int j=0;j<NodeOfNetwork.getNumOfNodes();j++) {
				if(NodeOfNetwork.getEdges()[i][j]!=0) {
					if(NodeOfNetwork.getCurrentNode()!=null) {
						if(i==NodeOfNetwork.getCurrentNode().getId()&&NodeOfNetwork.getEdges()[i][j]==2&&NodeOfNetwork.getEdges()[j][i]==2) {
							g.setColor(NodeOfNetwork.getColorOfUnknownEdges());
							lineUpNoO(g,i,j);
							g.setColor(oldC);
						}
						else if(i==NodeOfNetwork.getCurrentNode().getId()&&NodeOfNetwork.getEdges()[j][i]==1&&NodeOfNetwork.getEdges()[i][j]==1) {
							g.setColor(NodeOfNetwork.getColorOfDoubleRegulatingEdges());
							lineUp(g,i,j);
							g.setColor(oldC);
						}
						else if(i==NodeOfNetwork.getCurrentNode().getId()&&NodeOfNetwork.getEdges()[i][j]==1&&NodeOfNetwork.getEdges()[j][i]==0) {
							g.setColor(NodeOfNetwork.getColorOfRegulatingEdges());
							lineUp(g,i,j);
							g.setColor(oldC);
						}
						else if(j==NodeOfNetwork.getCurrentNode().getId()&&NodeOfNetwork.getEdges()[i][j]==2&&NodeOfNetwork.getEdges()[j][i]==2) {
							g.setColor(NodeOfNetwork.getColorOfUnknownEdges());
							lineUpNoO(g,i,j);
							g.setColor(oldC);
						}
						else if(j==NodeOfNetwork.getCurrentNode().getId()&&NodeOfNetwork.getEdges()[i][j]==1&&NodeOfNetwork.getEdges()[j][i]==1) {
							g.setColor(NodeOfNetwork.getColorOfDoubleRegulatingEdges());
							lineUp(g,i,j);
							g.setColor(oldC);
						}
						else if(j==NodeOfNetwork.getCurrentNode().getId()&&NodeOfNetwork.getEdges()[i][j]==1&&NodeOfNetwork.getEdges()[j][i]==0) {
							g.setColor(NodeOfNetwork.getColorOfRegulatedEdges());
							lineUp(g,i,j);
							g.setColor(oldC);
						}
						else {
							if(NodeOfNetwork.getEdges()[i][j]==1)
								lineUp(g,i,j);
							else if(NodeOfNetwork.getEdges()[i][j]==2)
								lineUpNoO(g,i,j);
							else
								System.out.println("error...........");
						}
					}
					else {
						if(NodeOfNetwork.getEdges()[i][j]==1)
							lineUp(g,i,j);
						else if(NodeOfNetwork.getEdges()[i][j]==2)
							lineUpNoO(g,i,j);
						else
							System.out.println("error...........");
					}
				}
			}
		}
	}
	
	private void lineUpNoO(Graphics g,int i,int j) {
		if(nodes[i].getX()<nodes[j].getX()) {
			//i lies left j
			g.drawLine(nodes[i].getX()+nodes[i].getWidth(),nodes[i].getY()+nodes[i].getHeight()/2,nodes[j].getX(),nodes[j].getY()+nodes[j].getHeight()/2);
			//drawTriA(g,(nodes[i].getX()+nodes[i].getWidth()+nodes[j].getX())/2,(nodes[i].getY()+nodes[i].getHeight()/2+nodes[j].getY()+nodes[j].getHeight()/2)/2,);
		}
		else {
			g.drawLine(nodes[i].getX(),nodes[i].getY()+nodes[i].getHeight()/2,nodes[j].getX()+nodes[j].getWidth(),nodes[j].getY()+nodes[j].getHeight()/2);
		}
	}
	
	private void lineUp(Graphics g,int i,int j) {
		if(nodes[i].getX()<nodes[j].getX()) {
			//i lies left j
			drawAL(nodes[i].getX()+nodes[i].getWidth(),nodes[i].getY()+nodes[i].getHeight()/2,nodes[j].getX(),nodes[j].getY()+nodes[j].getHeight()/2,(Graphics2D)g);
			//drawTriA(g,(nodes[i].getX()+nodes[i].getWidth()+nodes[j].getX())/2,(nodes[i].getY()+nodes[i].getHeight()/2+nodes[j].getY()+nodes[j].getHeight()/2)/2,);
		}
		else {
			drawAL(nodes[i].getX(),nodes[i].getY()+nodes[i].getHeight()/2,nodes[j].getX()+nodes[j].getWidth(),nodes[j].getY()+nodes[j].getHeight()/2,(Graphics2D)g);
		}
	}
	
	public static void drawAL(int sx, int sy, int ex, int ey,Graphics2D  g2)   {  
        double H = 10; // 箭头高度  
        double L = 4; // 底边的一半  
        int x3 = 0;  
        int y3 = 0;  
        int x4 = 0;  
        int y4 = 0;  
        double awrad = Math.atan(L / H); // 箭头角度  
        double arraow_len = Math.sqrt(L * L + H * H); // 箭头的长度  
        double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);  
        double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);  
        double x_3 = ex - arrXY_1[0]; // (x3,y3)是第一端点  
        double y_3 = ey - arrXY_1[1];  
        double x_4 = ex - arrXY_2[0]; // (x4,y4)是第二端点
        double y_4 = ey - arrXY_2[1];  
        Double X3 = new Double(x_3);  
        x3 = X3.intValue();  
        Double Y3 = new Double(y_3);  
        y3 = Y3.intValue();  
        Double X4 = new Double(x_4);  
        x4 = X4.intValue();  
        Double Y4 = new Double(y_4);  
        y4 = Y4.intValue();  
        // 画线  
        g2.drawLine(sx, sy, ex, ey);  
        //  
        GeneralPath triangle = new GeneralPath();  
        triangle.moveTo(ex, ey);  
        triangle.lineTo(x3, y3);  
        triangle.lineTo(x4, y4);  
        triangle.closePath();  
        //实心箭头  
        g2.fill(triangle);  
        //非实心箭头  
        //g2.draw(triangle);  
    }
	
	public static double[] rotateVec(int px, int py, double ang, boolean isChLen, double newLen) {  
        double mathstr[] = new double[2];  
        // 矢量旋转函数，参数含义分别是x分量、y分量、旋转角、是否改变长度、新长度  
        double vx = px * Math.cos(ang) - py * Math.sin(ang);  
        double vy = px * Math.sin(ang) + py * Math.cos(ang);  
        if (isChLen) {
            double d = Math.sqrt(vx * vx + vy * vy);  
            vx = vx / d * newLen;  
            vy = vy / d * newLen;  
            mathstr[0] = vx;  
            mathstr[1] = vy;  
        }  
        return mathstr;  
    }
	
	public WindowOfApp getWindowOfApp() {
		return windowOfApp;
	}
	public void setWindowOfApp(WindowOfApp windowOfApp) {
		this.windowOfApp = windowOfApp;
	}
	public NodeOfNetwork[] getNodes() {
		return nodes;
	}
	public void setNodes(NodeOfNetwork[] nodes) {
		this.nodes = nodes;
	}
	
	class MouseWheelListenerOfJPanelOfJScroll implements MouseWheelListener {
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			int wr=e.getWheelRotation();
			if(wr>0) {
				if(NodeOfNetwork.reduce()) {
					for(int i=0;i<NodeOfNetwork.getNumOfNodes();i++) {
						nodes[i].setBounds((int)(nodes[i].getxWorld()*NodeOfNetwork.getRato()),(int)(nodes[i].getyWorld()*NodeOfNetwork.getRato()),NodeOfNetwork.getWidthOfNodesToDisplay(),NodeOfNetwork.getHeightOfNodesToDisplay());
						setPreferredSize(new Dimension(NodeOfNetwork.getWidthOfWorldToDisplay(),NodeOfNetwork.getHeightOfWorldToDisplay()));
						windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getHorizontalScrollBar().setValue(NodeOfNetwork.getHValueToDisplay());
						windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getVerticalScrollBar().setValue(NodeOfNetwork.getVValueToDisplay());
						windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getHorizontalScrollBar().setMaximum(NodeOfNetwork.getHMaxToDisplay());
						windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getVerticalScrollBar().setMaximum(NodeOfNetwork.getVMaxToDisplay());
					}
				}
			}
			else {
				if(NodeOfNetwork.increase()) {
					for(int i=0;i<NodeOfNetwork.getNumOfNodes();i++) {
						nodes[i].setBounds((int)(nodes[i].getxWorld()*NodeOfNetwork.getRato()),(int)(nodes[i].getyWorld()*NodeOfNetwork.getRato()),NodeOfNetwork.getWidthOfNodesToDisplay(),NodeOfNetwork.getHeightOfNodesToDisplay());
						setPreferredSize(new Dimension(NodeOfNetwork.getWidthOfWorldToDisplay(),NodeOfNetwork.getHeightOfWorldToDisplay()));
						windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getHorizontalScrollBar().setValue(NodeOfNetwork.getHValueToDisplay());
						windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getVerticalScrollBar().setValue(NodeOfNetwork.getVValueToDisplay());
						windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getHorizontalScrollBar().setMaximum(NodeOfNetwork.getHMaxToDisplay());
						windowOfApp.getWindowOfNetwork().getjScrollPaneOfWindow().getVerticalScrollBar().setMaximum(NodeOfNetwork.getVMaxToDisplay());
					}
				}
			}
			windowOfApp.getWindowOfNetwork().repaint();
		}
	}
}
