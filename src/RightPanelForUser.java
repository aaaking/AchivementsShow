import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class RightPanelForUser extends JScrollPane implements
        java.io.Serializable {
    private static final long serialVersionUID = 25479075332L;
    private static RightPanelForUser isMe;
    User user = User.createUser();
    ArrayList<User> users = user.getUsers();
    Paper paper = Paper.createPaper();
    ArrayList<Paper> papers = paper.getmPapers();

    private RightPanelForUser() {
        JTextArea newLabel = new JTextArea();
        newLabel.setLineWrap(true);
        newLabel.setEditable(false);
        StringBuilder sb = new StringBuilder();

        for (User u : users) {
            int i = 1;
            sb.append(u.getName() + "\n");
            for (Paper p : papers) {
                if (p.getmFirstAuthor().getName().equals(u.getName())) {
                    sb.append("[" + (i++) + "] " + p.getmContent() + "\n");
                }
            }
            sb.append("\n");
        }

        newLabel.setText(sb.toString());
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        setViewportView(newLabel);
    }

    public static RightPanelForUser createRightPanelForUser() {
        if (null == isMe) {
            isMe = new RightPanelForUser();
        }
        return isMe;
    }

    private RightPanelForUser(int index) {
        int i = 1;
        int j = 1;
        ArrayList<Paper> maybeNotFirstAuthorPapers = new ArrayList<Paper>();
        JTextArea newLabel = new JTextArea();
        newLabel.setLineWrap(true);
        newLabel.setEditable(false);
        StringBuilder sb = new StringBuilder();
        sb.append("基本信息\n");
        User u = users.get(index);
        sb.append("姓名: " + u.getName() + "\n");
        sb.append("学号: " + u.getNumber() + "\n\n");

        sb.append("一作\n");
        for (Paper p : papers) {
            if (p.getmFirstAuthor().getName().equals(u.getName())) {
                sb.append("[" + (i++) + "] " + p.getmContent() + "\n");
            } else {
                maybeNotFirstAuthorPapers.add(p);
            }
        }

        sb.append("\n非一作\n");
        for (Paper p : maybeNotFirstAuthorPapers) {
            if (contain(p.getmAuthors(), u)) {
                sb.append("[" + (j++) + "] " + p.getmContent() + "\n");
            }
        }

        newLabel.setText(sb.toString());
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        setViewportView(newLabel);
    }

    public static RightPanelForUser createRightPanelForUser(int index) {
        // if (null == isMe2) {
        // isMe2 = new RightPanelForUser(index);
        // }
        // return isMe2;
        return new RightPanelForUser(index);
    }

    public void updateUsers() {
        // user = User.createUser();
        // users = user.getUsers();
        isMe = new RightPanelForUser();
    }

    public void updatePapers() {
        // paper = Paper.createPaper();
        // papers = paper.getmPapers();
        isMe = new RightPanelForUser();
    }

    private boolean contain(ArrayList<User> list, User u) {
        String name = u.getName();
        for (User uu : list) {
            if (uu.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
