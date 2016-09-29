package sly.app.windows;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundOfWindow extends JPanel {
	private WindowOfApp windowOfApp;
	public BackgroundOfWindow(WindowOfApp windowOfApp) {
		this.setBounds(0,0,windowOfApp.getWidth()-windowOfApp.getInsets().left-windowOfApp.getInsets().right,windowOfApp.getHeight()-windowOfApp.getInsets().top-windowOfApp.getInsets().bottom);
		this.windowOfApp=windowOfApp;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.setBounds(0,0,windowOfApp.getWidth()-windowOfApp.getInsets().left-windowOfApp.getInsets().right,windowOfApp.getHeight()-windowOfApp.getInsets().top-windowOfApp.getInsets().bottom);
		java.net.URL imgURL = windowOfApp.getClass().getResource("/images/1.jpg");
		g.drawImage(new ImageIcon(imgURL).getImage(),0,0,windowOfApp.getWidth(),windowOfApp.getHeight(),null);
	}

	@Override
	public void repaint(long time, int x, int y, int width, int height) {
		super.repaint(time, x, y, width, height);
	}

	@Override
	public void update(Graphics g) {
		super.update(g);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
