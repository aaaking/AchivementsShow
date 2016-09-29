package sly.app.windows;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ActionListenerOfAllButtonsOfApp implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		ButtonOfMenu btn=(ButtonOfMenu)e.getSource();
		if(btn.getName().equals("Open File")) {
			btn.getWindowOfApp().getViewOfApp().setVisible(false);
			FileDialog fd=new FileDialog(btn.getWindowOfApp(),"select the file you want to open",FileDialog.LOAD);
			fd.setVisible(true);
			if(fd.getFile()!=null)
				btn.getWindowOfApp().setOpenedFile(fd.getFiles()[0]);
		}
		else if(btn.getName().equals("Construct GNW")) {
			btn.getWindowOfApp().getViewOfApp().setVisible(false);
		}
		else if(btn.getName().equals("Help")) {
			btn.getWindowOfApp().getViewOfApp().setVisible(false);
			btn.getWindowOfApp().getViewOfApp().removeAll();
			btn.getWindowOfApp().getViewOfApp().add(btn.getWindowOfApp().getViewOfApp().getHelpOfView());
			btn.getWindowOfApp().getViewOfApp().setVisible(true);
			
		}
		else if(btn.getName().equals("Exit")) {
			btn.getWindowOfApp().getViewOfApp().setVisible(false);
			if(JOptionPane.showConfirmDialog(btn.getWindowOfApp(),"Do you want to exit?","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null)==0) {
				btn.getWindowOfApp().dispose();
			}
		}
		else if(btn.getName().equals("Examples")) {
			btn.getWindowOfApp().getViewOfApp().setVisible(false);
			btn.getWindowOfApp().getViewOfApp().removeAll();
			btn.getWindowOfApp().getViewOfApp().add(btn.getWindowOfApp().getViewOfApp().getExamplesOfView());
			btn.getWindowOfApp().getViewOfApp().setVisible(true);
			
		}
		else if(btn.getName().equals("About")) {
			btn.getWindowOfApp().getViewOfApp().setVisible(false);
			JOptionPane.showMessageDialog(btn.getWindowOfApp(),"this\n should\n be\n something about this app like version or author and so on","About",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(btn.getName().equals("Save Network")) {
			btn.getWindowOfApp().getViewOfApp().setVisible(false);
			FileDialog fd=new FileDialog(btn.getWindowOfApp(),"select the file you want to save",FileDialog.SAVE);
			fd.setVisible(true);
			if(fd.getFile()!=null)
				System.out.println("Now,I want to save the network");
		}
	}
}
