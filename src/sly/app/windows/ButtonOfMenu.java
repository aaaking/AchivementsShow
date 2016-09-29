package sly.app.windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ButtonOfMenu extends JButton {
private WindowOfApp windowOfApp=null;
	
	
	public void setWindowOfApp(WindowOfApp windowOfApp) {
		this.windowOfApp = windowOfApp;
	}
	
	
	class TempMouseListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			ButtonOfMenu btn=(ButtonOfMenu)e.getSource();
			btn.setFont(new Font(btn.getFont().getFontName(),Font.BOLD,16));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			ButtonOfMenu btn=(ButtonOfMenu)e.getSource();
			btn.setFont(new Font(btn.getFont().getFontName(),Font.BOLD,13));
		}}

	public ButtonOfMenu(WindowOfApp windowOfApp,String name) {
		super(new ImageIcon(new ImageIcon(windowOfApp.getClass().getResource("/images/"+name+".png")).getImage().getScaledInstance(20,30,Image.SCALE_DEFAULT)));
		this.setName(name);
		this.setText(name);
		this.setForeground(Color.WHITE);
		this.setVisible(true);
		this.addActionListener(windowOfApp.getActionListenerOfAllButtonsOfApp());
		this.windowOfApp=windowOfApp;
		this.setBorder(null);
		this.setContentAreaFilled(false);
		this.setHorizontalAlignment(JButton.LEFT);
		this.setFocusPainted(false);
		this.setFont(new Font(this.getFont().getFontName(),Font.BOLD,13));
		//this.setBorder(BorderFactory.createRaisedBevelBorder());
		this.addMouseListener(new TempMouseListener());
		
		
		
	}
	
	public WindowOfApp getWindowOfApp() {
		return windowOfApp;
	}
}
