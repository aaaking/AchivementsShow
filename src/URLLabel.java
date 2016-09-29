import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ToolTipManager;

public class URLLabel extends JLabel implements MouseListener {
    private static final long serialVersionUID = 2194613519655846206L;

    public static void main(String[] args) {
        ToolTipManager.sharedInstance().setInitialDelay(0);
        JFrame f = new JFrame("URLLabel test");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel l = new URLLabel(
                "http://zhidao.baidu.com/question/130767272.html", "�ٶ�֪��");
        f.add(l, "South");
        f.setSize(345, 123);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public URLLabel(String ustr, String ttt) {
        this.ustr = ustr;
        this.setText(ttt);
        this.setForeground(Color.blue);// ����������ɫ
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// ���������ʽ
        this.setToolTipText(ttt);// ������ʾ����
        this.addMouseListener(this);
    }

    // ���ʱ��Ĭ����������ָ����ҳ�档
    public void mouseClicked(MouseEvent e) {
        try {
            Desktop.getDesktop().browse(new URI(ustr));
        } catch (Exception e1) {
            e.paramString();
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    private String ustr;
}