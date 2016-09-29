package sly.app.windows;

import java.awt.Container;
import java.awt.FileDialog;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class EGBI extends JFrame {
    private static EGBI isMe = null;
    final JSplitPane mSplitPane = new JSplitPane();
    private Container mContent;
    private JPanel mLeftPanel = new JPanel();
    private JPanel mRightPanel = new JPanel();
    
    private EGBI() {
        setTitle("EGBI");
        mContent = this.getContentPane();
        setSize(800, 600);
        mSplitPane.setDividerLocation(270);
        addLeftItem(mLeftPanel);
        addRightItem(mRightPanel);
        mSplitPane.setLeftComponent(mLeftPanel);
        mSplitPane.setRightComponent(mRightPanel);
        mContent.add(mSplitPane);
        
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                isMe = null;
            }
        });
    }
    
    public static EGBI createEGBI() {
        if(isMe == null) {
            isMe = new EGBI();
        }
        return isMe;
    }

    public void addLeftItem(JPanel l) {
        l.setLayout(null);
        JButton openBtn = new JButton("Algorithm");
        final JTextField fileName = new JTextField();
        openBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isMe == null)
                    isMe = new EGBI();
                FileDialog fd=new FileDialog(isMe, "select the file you want to open",FileDialog.LOAD);
                fd.setVisible(true);
                if(fd.getFile()!=null)
                    fileName.setText(fd.getFiles()[0].getAbsolutePath());
            }
        });
        JButton openInputBtn = new JButton("InputFile");
        final JTextField inputFileName = new JTextField();
        openInputBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isMe == null)
                    isMe = new EGBI();
                FileDialog fd=new FileDialog(isMe, "select the file you want to open",FileDialog.LOAD);
                fd.setVisible(true);
                if(fd.getFile()!=null)
                    inputFileName.setText(fd.getFiles()[0].getAbsolutePath());
            }
        });
        JButton openParametersBtn = new JButton("ParatersFile");
        final JTextField parametersFileName = new JTextField();
        openParametersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isMe == null)
                    isMe = new EGBI();
                FileDialog fd=new FileDialog(isMe, "select the file you want to open",FileDialog.LOAD);
                fd.setVisible(true);
                if(fd.getFile()!=null)
                    parametersFileName.setText(fd.getFiles()[0].getAbsolutePath());
            }
        });
        JButton runBtn = new JButton("Run");
        runBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: do run paragram.
            }
        });
        runBtn.setBounds(l.getInsets().left+5,l.getInsets().top+170,100,40);
        openBtn.setBounds(l.getInsets().left+5,l.getInsets().top+20,100,40);
        fileName.setBounds(l.getInsets().left+5+105,l.getInsets().top+20,100,40);
        openInputBtn.setBounds(l.getInsets().left+5,l.getInsets().top+70,100,40);
        inputFileName.setBounds(l.getInsets().left+5+105,l.getInsets().top+70,100,40);
        openParametersBtn.setBounds(l.getInsets().left+5,l.getInsets().top+120,100,40);
        parametersFileName.setBounds(l.getInsets().left+5+105,l.getInsets().top+120,100,40);
        l.add(openBtn);
        l.add(fileName);
        l.add(openInputBtn);
        l.add(inputFileName);
        l.add(openParametersBtn);
        l.add(parametersFileName);
        l.add(runBtn);
    }
    
    public void addRightItem(JPanel r) {
        r.setLayout(null);
    }
}