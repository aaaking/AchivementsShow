import java.awt.BorderLayout;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.util.ArrayList;

//import User;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class RightPanelForAll extends JScrollPane implements
        java.io.Serializable {
    private static final long serialVersionUID = 24365878803L;
    private static RightPanelForAll isMe;
    User user = User.createUser();
    ArrayList<User> users = user.getUsers();
    Paper paper = Paper.createPaper();
    ArrayList<Paper> papers = paper.getmPapers();

    // <font color=颜色代码 size=字号 face=字体〉文字说明〈/font〉
    private RightPanelForAll() {
        JTextArea newLabel = new JTextArea();
        newLabel.setLineWrap(true);
        newLabel.setEditable(false);
        int size = users.size();
        int userRows = size / 16;

        String head = "学生\n";
        StringBuilder strMsg = new StringBuilder(head);
        for (User u : users) {
            strMsg.append(u.getName() + "    ");
        }
        strMsg.append("\n\n");

        strMsg.append("论文\n");
        int paperRows = (int) (papers.size() * 1);
        int i = 1;
        for (Paper p : papers) {
            strMsg.append("[" + (i++) + "] " + p.getmContent() + "\n");
        }
        strMsg.append("\n\n");
        strMsg.append("专利\n");
        strMsg.append("\n\n");
        strMsg.append("奖励\n");
        strMsg.append("\n\n");
        strMsg.append("竞赛\n");

        newLabel.setText(strMsg.toString());
        // setBounds(5, 0, 1000, 650);//32*(userRows+6+paperRows)
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        setViewportView(newLabel);
    }

    public static RightPanelForAll createRightPanelForAll() {
        if (isMe == null) {
            isMe = new RightPanelForAll();
        }
        return isMe;
    }

    public void updateUsers() {
        // user = User.createUser();
        // users = user.getUsers();
        isMe = new RightPanelForAll();
    }

    public void updatePapers() {
        // paper = Paper.createPaper();
        // papers = paper.getmPapers();
        isMe = new RightPanelForAll();
    }
}
