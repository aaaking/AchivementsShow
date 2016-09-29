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

public class JPanelOfSubNetwork extends JPanel {
	private WindowOfApp windowOfApp;

	public JPanelOfSubNetwork(WindowOfApp windowOfApp) {
		this.windowOfApp=windowOfApp;
		windowOfApp.setjPanelOfSubNetwork(this);
		NodeOfSubNetwork k=windowOfApp.getWindowOfSubNetwork().getNodes().getNext();
		for(int i=0;i<NodeOfSubNetwork.getNumOfNodes();) {
			if(k==null)
				break;
			for(int j=0;j<NodeOfSubNetwork.getNumOfNodesARow();j++) {
				if(k==null)
					break;
				k.setText(k.getName());
				k.setBounds(j*(NodeOfSubNetwork.getWidthOfNodesToDisplay()+NodeOfSubNetwork.getBlankToDisplay()),i*(NodeOfSubNetwork.getHeightOfNodesToDisplay()+NodeOfSubNetwork.getBlankToDisplay())/NodeOfSubNetwork.getNumOfNodesARow(),NodeOfSubNetwork.getWidthOfNodesToDisplay(),NodeOfSubNetwork.getHeightOfNodesToDisplay());
				k.setxWorld(j*(NodeOfSubNetwork.getWidthOfNodes()+NodeOfSubNetwork.getBlank()));
				k.setyWorld(i*(NodeOfSubNetwork.getHeightOfNodes()+NodeOfSubNetwork.getBlank())/NodeOfSubNetwork.getNumOfNodesARow());
				this.add(k);
				k=k.getNext();
			}
			i=i+NodeOfSubNetwork.getNumOfNodesARow();
		}
		this.setLayout(null);
		NodeOfSubNetwork.setWidthOfWorld((NodeOfSubNetwork.getWidthOfNodes()+NodeOfSubNetwork.getBlank())*(NodeOfSubNetwork.getNumOfNodesARow()+10));
		NodeOfSubNetwork.setHeightOfWorld((NodeOfSubNetwork.getHeightOfNodes()+NodeOfSubNetwork.getBlank())*(NodeOfSubNetwork.getNumOfNodes()/NodeOfSubNetwork.getNumOfNodesARow()+10));
		this.setPreferredSize(new Dimension(NodeOfSubNetwork.getWidthOfWorldToDisplay(),NodeOfSubNetwork.getHeightOfNodesToDisplay()));
		this.addMouseWheelListener(new MouseWheelListenerOfJPanelOfSubNetwork());
	}
	
	private class MouseWheelListenerOfJPanelOfSubNetwork implements MouseWheelListener {
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			int wr=e.getWheelRotation();
			if(wr>0) {
				if(NodeOfSubNetwork.reduce()) {
					for(NodeOfSubNetwork i=windowOfApp.getWindowOfSubNetwork().getNodes().getNext();i!=null;i=i.getNext()) {
						i.setBounds((int)(i.getxWorld()*NodeOfSubNetwork.getRato()),(int)(i.getyWorld()*NodeOfSubNetwork.getRato()),NodeOfSubNetwork.getWidthOfNodesToDisplay(),NodeOfSubNetwork.getHeightOfNodesToDisplay());
						setPreferredSize(new Dimension(NodeOfSubNetwork.getWidthOfWorldToDisplay(),NodeOfSubNetwork.getHeightOfWorldToDisplay()));
						windowOfApp.getWindowOfSubNetwork().getjScrollPane().getHorizontalScrollBar().setValue(NodeOfSubNetwork.getHValueToDisplay());
						windowOfApp.getWindowOfSubNetwork().getjScrollPane().getVerticalScrollBar().setValue(NodeOfSubNetwork.getVValueToDisplay());
						windowOfApp.getWindowOfSubNetwork().getjScrollPane().getHorizontalScrollBar().setMaximum(NodeOfSubNetwork.getHMaxToDisplay());
						windowOfApp.getWindowOfSubNetwork().getjScrollPane().getVerticalScrollBar().setMaximum(NodeOfSubNetwork.getVMaxToDisplay());
					}
				}
			}
			else {
				if(NodeOfSubNetwork.increase()) {
					for(NodeOfSubNetwork i=windowOfApp.getWindowOfSubNetwork().getNodes().getNext();i!=null;i=i.getNext()) {
						i.setBounds((int)(i.getxWorld()*NodeOfSubNetwork.getRato()),(int)(i.getyWorld()*NodeOfSubNetwork.getRato()),NodeOfSubNetwork.getWidthOfNodesToDisplay(),NodeOfSubNetwork.getHeightOfNodesToDisplay());
						setPreferredSize(new Dimension(NodeOfSubNetwork.getWidthOfWorldToDisplay(),NodeOfSubNetwork.getHeightOfWorldToDisplay()));
						windowOfApp.getWindowOfSubNetwork().getjScrollPane().getHorizontalScrollBar().setValue(NodeOfSubNetwork.getHValueToDisplay());
						windowOfApp.getWindowOfSubNetwork().getjScrollPane().getVerticalScrollBar().setValue(NodeOfSubNetwork.getVValueToDisplay());
						windowOfApp.getWindowOfSubNetwork().getjScrollPane().getHorizontalScrollBar().setMaximum(NodeOfSubNetwork.getHMaxToDisplay());
						windowOfApp.getWindowOfSubNetwork().getjScrollPane().getVerticalScrollBar().setMaximum(NodeOfSubNetwork.getVMaxToDisplay());
					}
				}
			}
			windowOfApp.getWindowOfNetwork().repaint();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Color oc=g.getColor();
		g.setColor(Color.green);
		drawLines(g);
		g.setColor(oc);
		System.out.println("JPanelOfJScroll paintComponent");
		NodeOfSubNetwork.sethValueWorld((int)(windowOfApp.getWindowOfSubNetwork().getjScrollPane().getHorizontalScrollBar().getValue()/NodeOfSubNetwork.getRato()));
		NodeOfSubNetwork.setvValueWorld((int)(windowOfApp.getWindowOfSubNetwork().getjScrollPane().getVerticalScrollBar().getValue()/NodeOfSubNetwork.getRato()));
		NodeOfSubNetwork.sethMaxWorld((int)(windowOfApp.getWindowOfSubNetwork().getjScrollPane().getHorizontalScrollBar().getMaximum()/NodeOfSubNetwork.getRato()));
		NodeOfSubNetwork.setvMaxWorld((int)(windowOfApp.getWindowOfSubNetwork().getjScrollPane().getVerticalScrollBar().getMaximum()/NodeOfSubNetwork.getRato()));
	}

	private void drawLines(Graphics g) {
		Color oldC=g.getColor();
		NodeOfSubNetwork nodeA=windowOfApp.getWindowOfSubNetwork().getNodes().getNext();
		if(nodeA==null)
			return;
		NodeOfSubNetwork nodeB=nodeA.getNext();
		while(nodeA!=null) {
			nodeB=nodeA.getNext();
			while(nodeB!=null) {
				if(NodeOfNetwork.getEdges()[nodeA.getId()][nodeB.getId()]==1&&NodeOfNetwork.getEdges()[nodeB.getId()][nodeA.getId()]==1) {
					lineUp(g,nodeA,nodeB);
					lineUp(g,nodeB,nodeA);
				}
				else if(NodeOfNetwork.getEdges()[nodeA.getId()][nodeB.getId()]==1&&NodeOfNetwork.getEdges()[nodeB.getId()][nodeA.getId()]==0) {
					lineUp(g,nodeA,nodeB);
				}
				else if(NodeOfNetwork.getEdges()[nodeA.getId()][nodeB.getId()]==0&&NodeOfNetwork.getEdges()[nodeB.getId()][nodeA.getId()]==1) {
					lineUp(g,nodeB,nodeA);
				}
				else if(NodeOfNetwork.getEdges()[nodeA.getId()][nodeB.getId()]==2&&NodeOfNetwork.getEdges()[nodeB.getId()][nodeA.getId()]==2) {
					lineUpNoO(g,nodeA,nodeB);
				}
				else if(NodeOfNetwork.getEdges()[nodeA.getId()][nodeB.getId()]==0&&NodeOfNetwork.getEdges()[nodeB.getId()][nodeA.getId()]==0) {
					
				}
				else {
					System.out.println("error......................................");
				}
				nodeB=nodeB.getNext();
			}
			nodeA=nodeA.getNext();
		}
	}
	
	private void lineUpNoO(Graphics g,NodeOfSubNetwork a,NodeOfSubNetwork b) {
		if(a.getX()<b.getX()) {
			//i lies left j
			g.drawLine(a.getX()+a.getWidth(),a.getY()+a.getHeight()/2,b.getX(),b.getY()+b.getHeight()/2);
			//drawTriA(g,(nodes[i].getX()+nodes[i].getWidth()+nodes[j].getX())/2,(nodes[i].getY()+nodes[i].getHeight()/2+nodes[j].getY()+nodes[j].getHeight()/2)/2,);
		}
		else {
			g.drawLine(a.getX(),a.getY()+a.getHeight()/2,b.getX()+b.getWidth(),b.getY()+b.getHeight()/2);
		}
	}
	
	private void lineUp(Graphics g,NodeOfSubNetwork a,NodeOfSubNetwork b) {
		if(a.getX()<b.getX()) {
			//i lies left j
			drawAL(a.getX()+a.getWidth(),a.getY()+a.getHeight()/2,b.getX(),b.getY()+b.getHeight()/2,(Graphics2D)g);
			//drawTriA(g,(nodes[i].getX()+nodes[i].getWidth()+nodes[j].getX())/2,(nodes[i].getY()+nodes[i].getHeight()/2+nodes[j].getY()+nodes[j].getHeight()/2)/2,);
		}
		else {
			drawAL(a.getX(),a.getY()+a.getHeight()/2,b.getX()+b.getWidth(),b.getY()+b.getHeight()/2,(Graphics2D)g);
		}
		
	}
	
	public static void drawAL(int sx, int sy, int ex, int ey,Graphics2D  g2) {  
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
	 // 计算  
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
}
