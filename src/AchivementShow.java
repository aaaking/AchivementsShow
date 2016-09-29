import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JWindow;

import sly.app.windows.ACO;
import sly.app.windows.CChi;
import sly.app.windows.EGBI;
import sly.app.windows.WindowOfApp;

public class AchivementShow extends JFrame implements java.io.Serializable {
    private static final long serialVersionUID = 3476970532L;
    private int mImageWidth = 1056;
    private int mImageHeight = 620;
    private Container mContent;
    private JPanel mLeftPanel = new JPanel();
    private JPanel mRightPanel = new JPanel();
    private RightPanelForAll mRightPanelForAll;
    private RightPanelForUser mRightPanelForUser;
    final JSplitPane mSplitPane = new JSplitPane();
    private static boolean HAS_LOGIN = false;
    JComboBox mBoxNames;
    JComboBox mTypeBox;

    public AchivementShow() {
        systemTray();
        splash();
        setTitle("吉林大学计算机学院B513实验室");
        menuBar();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });
        showFrame();
    }

    public JPanel getmRightPanel() {
        return mRightPanel;
    }

    public void setmRightPanel(JPanel mRightPanel) {
        this.mRightPanel = mRightPanel;
    }

    public RightPanelForAll getmRightPanelForAll() {
        return mRightPanelForAll;
    }

    public void setmRightPanelForAll(RightPanelForAll mRightPanelForAll) {
        this.mRightPanelForAll = mRightPanelForAll;
    }

    public RightPanelForUser getmRightPanelForUser() {
        return mRightPanelForUser;
    }

    public void setmRightPanelForUser(RightPanelForUser mRightPanelForUser) {
        this.mRightPanelForUser = mRightPanelForUser;
    }

    public JSplitPane getmSplitPane() {
        return mSplitPane;
    }

    public JPanel getmLeftPanel() {
        return mLeftPanel;
    }

    public void setmLeftPanel(JPanel mLeftPanel) {
        this.mLeftPanel = mLeftPanel;
    }

    public static void main(String[] args) {
        final AchivementShow frm = new AchivementShow();
        frm.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = frm.getWidth();
                int height = frm.getHeight();
                frm.getmSplitPane().setDividerLocation(
                        width > 1056 ? 190 : width / 5 - 20);
                frm.getmLeftPanel().resize(width > 1056 ? 190 : width / 5,
                        height - 45);
                frm.getmRightPanel()
                        .resize(width > 1056 ? width - 190 : width / 5 * 4,
                                height - 45);
                frm.getmRightPanelForAll().setBounds(5, 0,
                        width > 1056 ? width - 220 : width / 5 * 4 - 10,
                        height - 50);
                frm.getmRightPanelForUser().setBounds(5, 0,
                        width > 1056 ? width - 220 : width / 5 * 4 - 10,
                        height - 50);
                frm.getmRightPanelForAll().validate();
                frm.getmRightPanelForUser().validate();
                frm.validate();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }
        });
    }

    private void showFrame() {
        this.setVisible(true); // 显示窗体.
    }

    private void splash() {
        // Image imgSplash =
        // Toolkit.getDefaultToolkit().getImage("./images/splash.png");
        ImageIcon imgSplash = new ImageIcon(this.getClass().getResource(
                "/images/splash.png"));
        // ImageIcon image = new
        // ImageIcon(SwingResourceManager.getImage("images/logo.gif"));

        ImagePane pnlImage = new ImagePane(imgSplash.getImage());
        JWindow splashWindow = new JWindow(this);
        Container pane = splashWindow.getContentPane();
        pane.add(pnlImage, BorderLayout.CENTER);
        Dimension scmSize = Toolkit.getDefaultToolkit().getScreenSize();
        // mImageWidth = 8 * imgSplash.getWidth(this);
        // mImageHeight = 6 * imgSplash.getHeight(this);
        mSplitPane.setDividerLocation(190);
        this.setSize(mImageWidth, mImageHeight);
        this.setLocation(scmSize.width / 2 - mImageWidth / 2, scmSize.height
                / 2 - mImageHeight / 2);
        splashWindow.setSize(mImageWidth, mImageHeight);
        splashWindow.setLocation(scmSize.width / 2 - mImageWidth / 2,
                scmSize.height / 2 - mImageHeight / 2);
        splashWindow.show();
        splashWindow.toFront();
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.dispose();
    }

    private void systemTray() {
        if (SystemTray.isSupported()) { // 判断是否支持系统托盘
        // URL url = AchivementShow.class.getResource("images/tray.png"); //
        // 获取图片所在的URL
        // ImageIcon icon = new ImageIcon(url); // 实例化图像对象
        // Image image = icon.getImage(); // 获得Image对象
            // Image image =
            // Toolkit.getDefaultToolkit().getImage("./images/tray.png");
            ImageIcon image = new ImageIcon(this.getClass().getResource(
                    "/images/tray.png"));
            TrayIcon trayIcon = new TrayIcon(image.getImage()); // 创建托盘图标
            trayIcon.addMouseListener(new MouseAdapter() { // 为托盘添加鼠标适配器
                public void mouseClicked(MouseEvent e) { // 鼠标事件
                    if (e.getClickCount() == 2) { // 判断是否双击了鼠标
                        showFrame(); // 调用方法显示窗体
                    }
                }
            });
            trayIcon.setToolTip("B513"); // 添加工具提示文本
            PopupMenu popupMenu = new PopupMenu(); // 创建弹出菜单
            MenuItem exit = new MenuItem("退出"); // 创建菜单项
            exit.addActionListener(new ActionListener() { // 添加事件监听器
                public void actionPerformed(final ActionEvent arg0) {
                    System.exit(0); // 退出系统
                }
            });
            popupMenu.add(exit); // 为弹出菜单添加菜单项
            trayIcon.setPopupMenu(popupMenu); // 为托盘图标加弹出菜弹
            SystemTray systemTray = SystemTray.getSystemTray(); // 获得系统托盘对象
            try {
                systemTray.add(trayIcon); // 为系统托盘加托盘图标
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void exit() {
        Object[] options = { "确定", "隐藏" };
        JOptionPane pane2 = new JOptionPane("真想退出吗?",
                JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, null,
                options, options[1]);
        JDialog dialog = pane2.createDialog(this, "警告");
        dialog.setVisible(true);
        Object selectedValue = pane2.getValue();
        if (selectedValue == options[1]) {
            // setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 这个是关键
            this.dispose();
        } else if (selectedValue == options[0]) {
            System.exit(0);
        } else if (selectedValue == null) {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 这个是关键
            // dialog.dispose();
        }
    }

    private void addGNWAlgorithmItem(JMenu algorithmMenu) {
    	final JMenuItem manageAlgorithmItem = new JMenuItem("GNW");
    	manageAlgorithmItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				WindowOfApp.createWindowOfAppInstance().launch();
			}
    	});
    	////////////////////////////////////////////
    	final JMenuItem EGBIItem = new JMenuItem("EGBI");
    	EGBIItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                EGBI.createEGBI();
            }
        });
        ////////////////////////////////////////////
        final JMenuItem CChiItem = new JMenuItem("CChi");
        CChiItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                CChi.createCChi();
            }
        });
        ////////////////////////////////////////////
        final JMenuItem ACOItem = new JMenuItem("ACO");
        ACOItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ACO.createACO();
            }
        });
    	algorithmMenu.add(manageAlgorithmItem);
    	algorithmMenu.add(EGBIItem);
    	algorithmMenu.add(CChiItem);
    	algorithmMenu.add(ACOItem);
    }
    
    private void manageAlgorithmMenu(JMenu algorithmMenu) {
    	addGNWAlgorithmItem(algorithmMenu);
    }

    private void menuBar() {
        final JLabel welcomeLabel = new JLabel(
                "<html><font size='18' color='blue'>欢迎使用学生成就管理系统</font></html>",
                JLabel.CENTER);
        mContent = this.getContentPane();
        JMenuBar menubar = new JMenuBar();
        JMenu LoginMenu = new JMenu("系统");
        final JMenuItem userLoginMenuItem = new JMenuItem("登录");
        // ///////////////////////////////////////
        JMenu mangeMenu = new JMenu("管理");
        final JMenuItem manageAddUserItem = new JMenuItem("增加新生");
        final JMenuItem manageDeleteUserItem = new JMenuItem("删除学生");
        final JMenuItem manageUpdateUserItem = new JMenuItem("更改学生");
        final JMenuItem manageAddPaperItem = new JMenuItem("增加论文");
        final JMenuItem manageDeletePaperItem = new JMenuItem("删除论文");
        final JMenuItem manageUpdatePaperItem = new JMenuItem("更改论文");
        // ///////////////////////////////////////
        JMenu algorithmMenu = new JMenu("算法");
        manageAlgorithmMenu(algorithmMenu);
        // ///////////////////////////////////////
        JMenu HelpMenu = new JMenu("关于");
        final JMenuItem helpItem = new JMenuItem("关于");
        manageUpdateUserItem.setEnabled(HAS_LOGIN);
        manageAddPaperItem.setEnabled(HAS_LOGIN);
        manageUpdatePaperItem.setEnabled(HAS_LOGIN);
        manageDeletePaperItem.setEnabled(HAS_LOGIN);
        helpItem.setEnabled(true);
        userLoginMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JFrame frame01 = new JFrame();
                frame01.setTitle("登录");
                frame01.setBounds(450, 200, 400, 150);
                // Container content01 = frame01.getContentPane();
                JPanel panel = new JPanel();
                JPanel panel2 = new JPanel();
                JPanel panel01 = new JPanel();
                JPanel panel02 = new JPanel();
                final JTextField userName = new JTextField(20);
                final JPasswordField password = new JPasswordField(20);
                panel01.add(new JLabel("请输入用户名: "));
                panel01.add(userName);
                panel02.add(new JLabel("请输入密码:      "));
                panel02.add(password);
                JButton ok = new JButton("ok");
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (userName.getText().trim().toUpperCase()
                                .equals("B513")
                                && password.getText().trim().toUpperCase()
                                        .equals("B513")) {
                            HAS_LOGIN = true;
                            mContent.remove(welcomeLabel);
                            JOptionPane.showMessageDialog(null, "登陆成功",
                                    "success", JOptionPane.INFORMATION_MESSAGE);
                            frame01.dispose();
                            userLoginMenuItem.setEnabled(false);
                            manageAddUserItem.setEnabled(HAS_LOGIN);
                            manageDeleteUserItem.setEnabled(HAS_LOGIN);
                            manageUpdateUserItem.setEnabled(HAS_LOGIN);
                            manageAddPaperItem.setEnabled(HAS_LOGIN);
                            manageDeletePaperItem.setEnabled(HAS_LOGIN);
                            manageUpdatePaperItem.setEnabled(HAS_LOGIN);
                            helpItem.setEnabled(true);
                            setLeftPanel();
                            setRightPanel();
                            mContent.add(mSplitPane);
                            mContent.validate();
                        } else {
                            JOptionPane.showMessageDialog(null, "输入违法",
                                    "error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                JButton cancel = new JButton("cancel");
                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame01.dispose();
                    }
                });
                panel.add(panel01);
                panel.add(panel02);
                panel2.add(ok);
                panel2.add(cancel);
                frame01.add(panel, BorderLayout.CENTER);
                frame01.add(panel2, BorderLayout.SOUTH);
                frame01.setVisible(true);
            }
        });
        JMenuItem exitLoginMenu = new JMenuItem("退出");
        exitLoginMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        // 系统
        LoginMenu.add(userLoginMenuItem);
        LoginMenu.add(exitLoginMenu);
        // 管理
        manageAddUserItem.setEnabled(HAS_LOGIN);
        manageAddUserItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final User singleUser = User.createUser();
                final ArrayList<User> users = singleUser.getUsers();
                final JDialog addUserDialog = new JDialog();
                Container addUserDialogPane = addUserDialog.getContentPane();
                addUserDialogPane.setLayout(null);
                JLabel newNameLabel = new JLabel("新生姓名");
                newNameLabel.setBounds(0, 0, 64, 32);
                addUserDialogPane.add(newNameLabel);

                final JTextField newNameField = new JTextField(10);
                newNameField.setBounds(74, 0, 64, 32);
                addUserDialogPane.add(newNameField);

                JLabel newNumberLabel = new JLabel("新生学号");
                newNumberLabel.setBounds(0, 42, 64, 32);
                addUserDialogPane.add(newNumberLabel);

                final JTextField newNumberField = new JTextField(15);
                newNumberField.setBounds(74, 42, 64, 32);
                addUserDialogPane.add(newNumberField);

                JButton okBt = new JButton("添加");
                okBt.setBounds(0, 84, 64, 32);
                addUserDialogPane.add(okBt);
                okBt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = newNameField.getText().trim();
                        String number = newNumberField.getText().trim();
                        if (name == null || name.equals("") || number == null
                                || number.equals("")) {
                            JOptionPane.showMessageDialog(null, "输入违法",
                                    "error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            boolean noOverLapping = true;
                            for (User u : users) {
                                if (name.equals(u.getName())) {
                                    JOptionPane.showMessageDialog(null,
                                            "该学生名已存在", "error",
                                            JOptionPane.ERROR_MESSAGE);
                                    noOverLapping = false;
                                    break;
                                } else if (number.equals(u.getNumber())) {
                                    JOptionPane.showMessageDialog(null,
                                            "该学生学号已存在", "error",
                                            JOptionPane.ERROR_MESSAGE);
                                    noOverLapping = false;
                                    break;
                                }
                            }
                            if (noOverLapping) {
                                manageAddUserItem.setEnabled(true);
                                manageDeleteUserItem.setEnabled(true);
                                manageUpdateUserItem.setEnabled(true);
                                manageAddPaperItem.setEnabled(true);
                                manageDeletePaperItem.setEnabled(true);
                                manageUpdatePaperItem.setEnabled(true);
                                helpItem.setEnabled(true);
                                User user = new User(name, number);
                                singleUser.addUser(user);
                                JOptionPane.showMessageDialog(null, "添加成功",
                                        "success",
                                        JOptionPane.INFORMATION_MESSAGE);
                                mRightPanelForAll = RightPanelForAll
                                        .createRightPanelForAll();
                                mRightPanelForAll.updateUsers();
                                mRightPanelForUser = RightPanelForUser
                                        .createRightPanelForUser();
                                mRightPanelForUser.updateUsers();
                                updateBoxNames();
                                addUserDialog.dispose();
                            }
                        }
                    }
                });

                JButton cancelBt = new JButton("取消");
                cancelBt.setBounds(74, 84, 64, 32);
                addUserDialogPane.add(cancelBt);
                cancelBt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manageAddUserItem.setEnabled(true);
                        manageDeleteUserItem.setEnabled(true);
                        manageUpdateUserItem.setEnabled(true);
                        manageAddPaperItem.setEnabled(true);
                        manageDeletePaperItem.setEnabled(true);
                        manageUpdatePaperItem.setEnabled(true);
                        helpItem.setEnabled(true);
                        addUserDialog.dispose();
                    }
                });
                addUserDialog.setSize(158, 166);
                addUserDialog.setTitle("添加");
                addUserDialog.setLocation(mImageWidth / 2, mImageHeight / 2);
                addUserDialog.setVisible(true);
                manageAddUserItem.setEnabled(false);
                manageDeleteUserItem.setEnabled(false);
                manageUpdateUserItem.setEnabled(false);
                manageAddPaperItem.setEnabled(false);
                manageDeletePaperItem.setEnabled(false);
                manageUpdatePaperItem.setEnabled(false);
                helpItem.setEnabled(true);
                addUserDialog.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        manageAddUserItem.setEnabled(true);
                        manageDeleteUserItem.setEnabled(true);
                        manageUpdateUserItem.setEnabled(true);
                        manageAddPaperItem.setEnabled(true);
                        manageDeletePaperItem.setEnabled(true);
                        manageUpdatePaperItem.setEnabled(true);
                        helpItem.setEnabled(true);
                    }
                });
            }
        });
        manageDeleteUserItem.setEnabled(HAS_LOGIN);
        manageDeleteUserItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final User singleUser = User.createUser();
                final ArrayList<User> users = singleUser.getUsers();
                final JDialog deleteUserDialog = new JDialog();
                Container deleteUserDialogPane = deleteUserDialog
                        .getContentPane();
                deleteUserDialogPane.setLayout(null);
                JLabel newNameLabel = new JLabel("选择姓名");
                newNameLabel.setBounds(0, 0, 64, 32);
                deleteUserDialogPane.add(newNameLabel);

                String[] namesString = new String[users.size()];
                int i = 0;
                for (User u : users) {
                    namesString[i] = u.getName();
                    i++;
                }
                final JComboBox namesBox = new JComboBox(namesString);
                namesBox.setBounds(74, 0, 64, 32);
                deleteUserDialogPane.add(namesBox);

                JButton okBt = new JButton("删除");
                okBt.setBounds(0, 42, 64, 32);
                deleteUserDialogPane.add(okBt);
                okBt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object[] options = { "确定", "取消" };
                        JOptionPane pane2 = new JOptionPane("真要删除吗?",
                                JOptionPane.QUESTION_MESSAGE,
                                JOptionPane.YES_NO_OPTION, null, options,
                                options[1]);
                        JDialog dialog = pane2.createDialog(deleteUserDialog,
                                "警告");
                        dialog.setVisible(true);
                        Object selectedValue = pane2.getValue();
                        if (selectedValue == options[0]) {
                            manageAddUserItem.setEnabled(true);
                            manageDeleteUserItem.setEnabled(true);
                            manageUpdateUserItem.setEnabled(true);
                            manageAddPaperItem.setEnabled(true);
                            manageDeletePaperItem.setEnabled(true);
                            manageUpdatePaperItem.setEnabled(true);
                            helpItem.setEnabled(true);
                            JOptionPane.showMessageDialog(null, "删除成功",
                                    "success", JOptionPane.INFORMATION_MESSAGE);
                            User userDelete = users.get(namesBox
                                    .getSelectedIndex());
                            singleUser.deleteUser(userDelete);
                            mRightPanelForAll = RightPanelForAll
                                    .createRightPanelForAll();
                            mRightPanelForAll.updateUsers();
                            mRightPanelForUser = RightPanelForUser
                                    .createRightPanelForUser();
                            mRightPanelForUser.updateUsers();
                            updateBoxNames();
                            deleteUserDialog.dispose();
                        } else if (selectedValue == options[1]) {
                        } else if (selectedValue == null) {
                        }
                    }
                });

                JButton cancelBt = new JButton("取消");
                cancelBt.setBounds(74, 42, 64, 32);
                deleteUserDialogPane.add(cancelBt);
                cancelBt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manageAddUserItem.setEnabled(true);
                        manageDeleteUserItem.setEnabled(true);
                        manageUpdateUserItem.setEnabled(true);
                        manageAddPaperItem.setEnabled(true);
                        manageDeletePaperItem.setEnabled(true);
                        manageUpdatePaperItem.setEnabled(true);
                        helpItem.setEnabled(true);
                        deleteUserDialog.dispose();
                    }
                });

                deleteUserDialog.setSize(158, 166);
                deleteUserDialog.setTitle("删除");
                deleteUserDialog.setLocation(mImageWidth / 2, mImageHeight / 2);
                deleteUserDialog.setVisible(true);
                manageAddUserItem.setEnabled(false);
                manageDeleteUserItem.setEnabled(false);
                manageUpdateUserItem.setEnabled(false);
                manageAddPaperItem.setEnabled(false);
                manageDeletePaperItem.setEnabled(false);
                manageUpdatePaperItem.setEnabled(false);
                helpItem.setEnabled(true);
                deleteUserDialog.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        manageAddUserItem.setEnabled(true);
                        manageDeleteUserItem.setEnabled(true);
                        manageUpdateUserItem.setEnabled(true);
                        manageAddPaperItem.setEnabled(true);
                        manageDeletePaperItem.setEnabled(true);
                        manageUpdatePaperItem.setEnabled(true);
                        helpItem.setEnabled(true);
                    }
                });
            }
        });
        manageUpdateUserItem.setEnabled(HAS_LOGIN);
        manageUpdateUserItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final User singleUser = User.createUser();
                final ArrayList<User> users = singleUser.getUsers();
                final JDialog updateUserDialog = new JDialog();
                Container updateUserDialogPane = updateUserDialog
                        .getContentPane();
                updateUserDialogPane.setLayout(null);
                JLabel newNameLabel = new JLabel("选择学生");
                newNameLabel.setBounds(0, 0, 64, 32);
                updateUserDialogPane.add(newNameLabel);

                String[] namesString = new String[users.size()];
                int i = 0;
                for (User u : users) {
                    namesString[i] = u.getName();
                    i++;
                }
                final JComboBox namesBox = new JComboBox(namesString);
                namesBox.setBounds(74, 0, 79, 32);
                updateUserDialogPane.add(namesBox);

                JLabel updateNameLabel = new JLabel("修改姓名");
                updateNameLabel.setBounds(0, 42, 64, 32);
                updateUserDialogPane.add(updateNameLabel);

                final JTextField updateNameField = new JTextField();
                updateNameField.setBounds(74, 42, 79, 32);
                updateUserDialogPane.add(updateNameField);

                JLabel updateNumberLabel = new JLabel("修改学号");
                updateNumberLabel.setBounds(0, 84, 64, 32);
                updateUserDialogPane.add(updateNumberLabel);

                final JTextField updateNumberField = new JTextField();
                updateNumberField.setBounds(74, 84, 79, 32);
                updateUserDialogPane.add(updateNumberField);
                if (users.size() > 0) {
                    User uu = users.get(0);
                    updateNameField.setText(uu.getName());
                    updateNumberField.setText(uu.getNumber());
                }
                namesBox.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        int index = namesBox.getSelectedIndex();
                        User uu = users.get(index);
                        updateNameField.setText(uu.getName());
                        updateNumberField.setText(uu.getNumber());
                    }
                });

                JButton okBt = new JButton("确定");
                okBt.setBounds(0, 126, 64, 32);
                updateUserDialogPane.add(okBt);
                okBt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int index = namesBox.getSelectedIndex();
                        String name = updateNameField.getText().trim();
                        String number = updateNumberField.getText().trim();
                        Object[] options = { "确定", "取消" };
                        JOptionPane pane2 = new JOptionPane("确定修改吗?",
                                JOptionPane.QUESTION_MESSAGE,
                                JOptionPane.YES_NO_OPTION, null, options,
                                options[1]);
                        JDialog dialog = pane2.createDialog(updateUserDialog,
                                "警告");
                        dialog.setVisible(true);
                        Object selectedValue = pane2.getValue();
                        if (selectedValue == options[0]) {
                            if ((name == null || name.equals(""))
                                    || (number == null || number.equals(""))) {
                                JOptionPane.showMessageDialog(null, "无输入",
                                        "error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                boolean noOverLapping = true;
                                int i = 0;
                                for (User u : users) {
                                    if (i == index) {
                                        i++;
                                        continue;
                                    }
                                    i++;
                                    if (name.equals(u.getName())) {
                                        JOptionPane.showMessageDialog(null,
                                                "该学生名已存在", "error",
                                                JOptionPane.ERROR_MESSAGE);
                                        noOverLapping = false;
                                        break;
                                    } else if (number.equals(u.getNumber())) {
                                        JOptionPane.showMessageDialog(null,
                                                "该学生学号已存在", "error",
                                                JOptionPane.ERROR_MESSAGE);
                                        noOverLapping = false;
                                        break;
                                    }
                                }
                                if (noOverLapping) {
                                    // manageAddUserItem.setEnabled(true);
                                    // manageDeleteUserItem.setEnabled(true);
                                    // manageUpdateUserItem.setEnabled(true);
                                    // manageAddPaperItem.setEnabled(true);
                                    // manageUpdatePaperItem.setEnabled(true);
                                    helpItem.setEnabled(true);
                                    singleUser.updateUser(index, name, number);
                                    JOptionPane.showMessageDialog(null, "修改成功",
                                            "success",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    mRightPanelForAll = RightPanelForAll
                                            .createRightPanelForAll();
                                    mRightPanelForAll.updateUsers();
                                    mRightPanelForUser = RightPanelForUser
                                            .createRightPanelForUser();
                                    mRightPanelForUser.updateUsers();
                                    updateBoxNames();
                                    // addUserDialog.dispose();
                                }
                            }
                        } else if (selectedValue == options[1]) {
                        } else if (selectedValue == null) {
                        }
                    }
                });

                JButton cancelBt = new JButton("取消");
                cancelBt.setBounds(74, 126, 64, 32);
                updateUserDialogPane.add(cancelBt);
                cancelBt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manageAddUserItem.setEnabled(true);
                        manageDeleteUserItem.setEnabled(true);
                        manageUpdateUserItem.setEnabled(true);
                        manageAddPaperItem.setEnabled(true);
                        manageDeletePaperItem.setEnabled(true);
                        manageUpdatePaperItem.setEnabled(true);
                        helpItem.setEnabled(true);
                        updateUserDialog.dispose();
                    }
                });

                updateUserDialog.setSize(175, 250);
                updateUserDialog.setLocation(mImageWidth / 2, mImageHeight / 2);
                updateUserDialog.setTitle("修改");
                updateUserDialog.setVisible(true);
                manageAddUserItem.setEnabled(false);
                manageDeleteUserItem.setEnabled(false);
                manageUpdateUserItem.setEnabled(false);
                manageAddPaperItem.setEnabled(false);
                manageDeletePaperItem.setEnabled(false);
                manageUpdatePaperItem.setEnabled(false);
                helpItem.setEnabled(true);
                updateUserDialog.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        manageAddUserItem.setEnabled(true);
                        manageDeleteUserItem.setEnabled(true);
                        manageUpdateUserItem.setEnabled(true);
                        manageAddPaperItem.setEnabled(true);
                        manageDeletePaperItem.setEnabled(true);
                        manageUpdatePaperItem.setEnabled(true);
                        helpItem.setEnabled(true);
                    }
                });
            }
        });
        manageAddPaperItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final Paper singlePaper = Paper.createPaper();
                final ArrayList<Paper> papers = singlePaper.getmPapers();
                final JDialog addPaperDialog = new JDialog();
                Container addPaperDialogPane = addPaperDialog.getContentPane();
                addPaperDialogPane.setLayout(null);
                final JLabel title = new JLabel("论文题目");
                title.setBounds(0, 0, 64, 32);
                addPaperDialogPane.add(title);
                final JLabel authors = new JLabel("论文作者");
                authors.setBounds(0, 42, 64, 32);
                addPaperDialogPane.add(authors);
                final JLabel firstAuthor = new JLabel("第一作者");
                firstAuthor.setBounds(0, 84, 64, 32);
                addPaperDialogPane.add(firstAuthor);
                final JLabel content = new JLabel("完整引用");
                content.setBounds(0, 126, 64, 32);
                addPaperDialogPane.add(content);
                final JTextField titleField = new JTextField(100);
                titleField.setBounds(74, 0, 500, 32);
                titleField.setToolTipText("请输入论文题目");
                addPaperDialogPane.add(titleField);
                final JTextField authorsField = new JTextField(100);
                authorsField.setBounds(74, 42, 500, 32);
                authorsField
                        .setToolTipText("作者必须只包含汉字,字母和英文逗号三种符号,且作者必须以英文逗号分割");
                addPaperDialogPane.add(authorsField);
                final JTextField firstAuthorField = new JTextField(100);
                firstAuthorField.setBounds(74, 84, 500, 32);
                addPaperDialogPane.add(firstAuthorField);
                final JTextField contentField = new JTextField(260);
                contentField.setBounds(74, 126, 500, 32);
                contentField.setToolTipText("论文的完整论文引用");
                addPaperDialogPane.add(contentField);

                JButton okBt = new JButton("确定");
                okBt.setBounds(0, 168, 64, 32);
                addPaperDialogPane.add(okBt);
                okBt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String paperTitle = titleField.getText().trim();
                        String paperAuthors = authorsField.getText().trim();
                        String paperFirstAuthor = firstAuthorField.getText()
                                .trim();
                        String paperContent = contentField.getText().trim();
                        if (paperTitle.equals("") || paperAuthors.equals("")
                                || paperFirstAuthor.equals("")
                                || paperContent.equals("")
                                || !checkAuthors(paperAuthors)) {
                            JOptionPane.showMessageDialog(null, "输入错误",
                                    "error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (hasTheSamePaper(paperTitle)) {
                            JOptionPane.showMessageDialog(null, "有相同题目的论文!",
                                    "error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        String[] authorsArr = paperAuthors.split(",");
                        ArrayList<User> zuozhes = new ArrayList<User>();
                        User first_zuozhe = new User(authorsArr[0].trim(), "1");
                        for (String s : authorsArr) {
                            User uuu = new User(s.trim(), "1");
                            zuozhes.add(uuu);
                        }
                        Paper p = new Paper(zuozhes, first_zuozhe,
                                paperContent, "EI", paperTitle);
                        papers.add(p);
                        mRightPanelForAll = RightPanelForAll
                                .createRightPanelForAll();
                        mRightPanelForAll.updatePapers();
                        mRightPanelForUser = RightPanelForUser
                                .createRightPanelForUser();
                        mRightPanelForUser.updatePapers();
                        JOptionPane.showMessageDialog(null, "添加论文成功!",
                                "success", JOptionPane.INFORMATION_MESSAGE);
                        manageAddUserItem.setEnabled(true);
                        manageDeleteUserItem.setEnabled(true);
                        manageUpdateUserItem.setEnabled(true);
                        manageAddPaperItem.setEnabled(true);
                        manageDeletePaperItem.setEnabled(true);
                        manageUpdatePaperItem.setEnabled(true);
                        helpItem.setEnabled(true);
                        addPaperDialog.dispose();
                    }
                });

                JButton cancelBt = new JButton("取消");
                cancelBt.setBounds(74, 168, 64, 32);
                addPaperDialogPane.add(cancelBt);
                cancelBt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manageAddUserItem.setEnabled(true);
                        manageDeleteUserItem.setEnabled(true);
                        manageUpdateUserItem.setEnabled(true);
                        manageAddPaperItem.setEnabled(true);
                        manageDeletePaperItem.setEnabled(true);
                        manageUpdatePaperItem.setEnabled(true);
                        helpItem.setEnabled(true);
                        addPaperDialog.dispose();
                    }
                });

                addPaperDialog.setSize(610, 240);
                addPaperDialog.setLocation(mImageWidth / 2, mImageHeight / 2);
                addPaperDialog.setTitle("增加论文");
                addPaperDialog.setVisible(true);
                manageAddUserItem.setEnabled(false);
                manageDeleteUserItem.setEnabled(false);
                manageUpdateUserItem.setEnabled(false);
                manageAddPaperItem.setEnabled(false);
                manageDeletePaperItem.setEnabled(false);
                manageUpdatePaperItem.setEnabled(false);
                helpItem.setEnabled(true);
                addPaperDialog.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        manageAddUserItem.setEnabled(true);
                        manageDeleteUserItem.setEnabled(true);
                        manageUpdateUserItem.setEnabled(true);
                        manageAddPaperItem.setEnabled(true);
                        manageDeletePaperItem.setEnabled(true);
                        manageUpdatePaperItem.setEnabled(true);
                        helpItem.setEnabled(true);
                    }
                });
            }
        });
        manageDeletePaperItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final Paper singlePaper = Paper.createPaper();
                final ArrayList<Paper> papers = singlePaper.getmPapers();
                final JDialog deletePaperDialog = new JDialog();
                Container deletePaperDialogPane = deletePaperDialog
                        .getContentPane();
                deletePaperDialogPane.setLayout(null);
                JLabel newNameLabel = new JLabel("选择论文");
                newNameLabel.setBounds(0, 0, 64, 32);
                deletePaperDialogPane.add(newNameLabel);

                String[] paperssString = new String[papers.size()];
                int i = 0;
                for (Paper u : papers) {
                    paperssString[i] = u.getmContent();
                    i++;
                }
                final JComboBox papersBox = new JComboBox(paperssString);
                papersBox.setBounds(74, 0, 1000, 32);
                deletePaperDialogPane.add(papersBox);

                JButton okBt = new JButton("删除");
                okBt.setBounds(0, 42, 64, 32);
                deletePaperDialogPane.add(okBt);
                okBt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object[] options = { "确定", "取消" };
                        JOptionPane pane2 = new JOptionPane("真要删除吗?",
                                JOptionPane.QUESTION_MESSAGE,
                                JOptionPane.YES_NO_OPTION, null, options,
                                options[1]);
                        JDialog dialog = pane2.createDialog(deletePaperDialog,
                                "警告");
                        dialog.setVisible(true);
                        Object selectedValue = pane2.getValue();
                        if (selectedValue == options[0]) {
                            // manageAddUserItem.setEnabled(true);
                            // manageDeleteUserItem.setEnabled(true);
                            // manageUpdateUserItem.setEnabled(true);
                            // manageAddPaperItem.setEnabled(true);
                            // manageDeletePaperItem.setEnabled(true);
                            // manageUpdatePaperItem.setEnabled(true);
                            // helpItem.setEnabled(true);
                            JOptionPane.showMessageDialog(null, "删除成功",
                                    "success", JOptionPane.INFORMATION_MESSAGE);
                            Paper paperDelete = papers.get(papersBox
                                    .getSelectedIndex());
                            singlePaper.deletePaper(paperDelete);
                            mRightPanelForAll = RightPanelForAll
                                    .createRightPanelForAll();
                            mRightPanelForAll.updateUsers();
                            mRightPanelForUser = RightPanelForUser
                                    .createRightPanelForUser();
                            mRightPanelForUser.updateUsers();
                            updateBoxPapers();
                            deletePaperDialog.dispose();
                        } else if (selectedValue == options[1]) {
                        } else if (selectedValue == null) {
                        }
                    }
                });

                JButton cancelBt = new JButton("取消");
                cancelBt.setBounds(74, 42, 64, 32);
                deletePaperDialogPane.add(cancelBt);
                cancelBt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manageAddUserItem.setEnabled(true);
                        manageDeleteUserItem.setEnabled(true);
                        manageUpdateUserItem.setEnabled(true);
                        manageAddPaperItem.setEnabled(true);
                        manageDeletePaperItem.setEnabled(true);
                        manageUpdatePaperItem.setEnabled(true);
                        helpItem.setEnabled(true);
                        deletePaperDialog.dispose();
                    }
                });

                deletePaperDialog.setSize(1110, 166);
                deletePaperDialog.setTitle("删除论文");
                deletePaperDialog
                        .setLocation(mImageWidth / 8, mImageHeight / 2);
                deletePaperDialog.setVisible(true);
                manageAddUserItem.setEnabled(false);
                manageDeleteUserItem.setEnabled(false);
                manageUpdateUserItem.setEnabled(false);
                manageAddPaperItem.setEnabled(false);
                manageDeletePaperItem.setEnabled(false);
                manageUpdatePaperItem.setEnabled(false);
                helpItem.setEnabled(true);
                deletePaperDialog.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        manageAddUserItem.setEnabled(true);
                        manageDeleteUserItem.setEnabled(true);
                        manageUpdateUserItem.setEnabled(true);
                        manageAddPaperItem.setEnabled(true);
                        manageDeletePaperItem.setEnabled(true);
                        manageUpdatePaperItem.setEnabled(true);
                        helpItem.setEnabled(true);
                    }
                });
            }
        });
        manageUpdatePaperItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final Paper singlePaper = Paper.createPaper();
                final ArrayList<Paper> papers = singlePaper.getmPapers();
                final JDialog updatePaperDialog = new JDialog();
                Container updatePaperDialogPane = updatePaperDialog
                        .getContentPane();
                updatePaperDialogPane.setLayout(null);
                JLabel newNameLabel = new JLabel("选择论文");
                newNameLabel.setBounds(0, 0, 64, 32);
                updatePaperDialogPane.add(newNameLabel);

                String[] papersString = new String[papers.size()];
                int i = 0;
                for (Paper u : papers) {
                    papersString[i] = u.getmContent();
                    i++;
                }
                final JComboBox papersBox = new JComboBox(papersString);
                papersBox.setBounds(74, 0, 1000, 32);
                updatePaperDialogPane.add(papersBox);

                final JLabel title = new JLabel("论文题目");
                title.setBounds(0, 42, 64, 32);
                updatePaperDialogPane.add(title);
                final JLabel authors = new JLabel("论文作者");
                authors.setBounds(0, 84, 64, 32);
                updatePaperDialogPane.add(authors);
                final JLabel firstAuthor = new JLabel("第一作者");
                firstAuthor.setBounds(0, 126, 64, 32);
                updatePaperDialogPane.add(firstAuthor);
                final JLabel content = new JLabel("完整引用");
                content.setBounds(0, 168, 64, 32);
                updatePaperDialogPane.add(content);
                final JTextField titleField = new JTextField(500);
                titleField.setBounds(74, 42, 1000, 32);
                titleField.setText(papers.get(0).getmTitle());
                titleField.setToolTipText("修改论文题目");
                updatePaperDialogPane.add(titleField);
                final JTextField authorsField = new JTextField(500);
                authorsField.setBounds(74, 84, 1000, 32);
                authorsField.setText(authorsListToString(papers.get(0)
                        .getmAuthors()));
                authorsField
                        .setToolTipText("i cannot do it, this does not work");
                updatePaperDialogPane.add(authorsField);
                final JTextField firstAuthorField = new JTextField(500);
                firstAuthorField.setBounds(74, 126, 1000, 32);
                firstAuthorField.setText(papers.get(0).getmFirstAuthor()
                        .getName());
                firstAuthorField
                        .setToolTipText("i cannot do it, first author cannot be updated");
                updatePaperDialogPane.add(firstAuthorField);
                final JTextField contentField = new JTextField(500);
                contentField.setBounds(74, 168, 1000, 32);
                contentField.setText(papers.get(0).getmContent());
                contentField.setToolTipText("修改论文的完整论文引用");
                updatePaperDialogPane.add(contentField);
                papersBox.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        int index = papersBox.getSelectedIndex();
                        titleField.setText(papers.get(index).getmTitle());
                        authorsField.setText(authorsListToString(papers.get(
                                index).getmAuthors()));
                        firstAuthorField.setText(papers.get(index)
                                .getmFirstAuthor().getName());
                        contentField.setText(papers.get(index).getmContent());
                    }
                });

                JButton okBt = new JButton("修改");
                okBt.setBounds(0, 210, 64, 32);
                updatePaperDialogPane.add(okBt);
                okBt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int index = papersBox.getSelectedIndex();
                        String title = titleField.getText().trim();
                        String content = contentField.getText().trim();
                        Object[] options = { "确定", "取消" };
                        JOptionPane pane2 = new JOptionPane("确定修改吗?",
                                JOptionPane.QUESTION_MESSAGE,
                                JOptionPane.YES_NO_OPTION, null, options,
                                options[1]);
                        JDialog dialog = pane2.createDialog(updatePaperDialog,
                                "警告");
                        dialog.setVisible(true);
                        Object selectedValue = pane2.getValue();
                        if (selectedValue == options[0]) {
                            if ((title == null || title.equals(""))
                                    || (content == null || content.equals(""))) {
                                JOptionPane.showMessageDialog(null, "输入非法",
                                        "error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                boolean noOverLapping = true;
                                int i = 0;
                                for (Paper u : papers) {
                                    if (i == index) {
                                        i++;
                                        continue;
                                    }
                                    i++;
                                    if (title.equals(u.getmTitle())) {
                                        JOptionPane.showMessageDialog(null,
                                                "该论文已存在", "error",
                                                JOptionPane.ERROR_MESSAGE);
                                        noOverLapping = false;
                                        break;
                                    } else if (content.equals(u.getmContent())) {
                                        JOptionPane.showMessageDialog(null,
                                                "该论文已存在", "error",
                                                JOptionPane.ERROR_MESSAGE);
                                        noOverLapping = false;
                                        break;
                                    }
                                }
                                if (noOverLapping) {
                                    helpItem.setEnabled(true);
                                    singlePaper.updatePaper(index, title,
                                            content);
                                    JOptionPane.showMessageDialog(null, "修改成功",
                                            "success",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    mRightPanelForAll = RightPanelForAll
                                            .createRightPanelForAll();
                                    mRightPanelForAll.updateUsers();
                                    mRightPanelForUser = RightPanelForUser
                                            .createRightPanelForUser();
                                    mRightPanelForUser.updateUsers();
                                    updateBoxPapers();
                                }
                            }
                        } else if (selectedValue == options[1]) {
                        } else if (selectedValue == null) {
                        }
                    }
                });

                JButton cancelBt = new JButton("取消");
                cancelBt.setBounds(74, 210, 64, 32);
                updatePaperDialogPane.add(cancelBt);
                cancelBt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manageAddUserItem.setEnabled(true);
                        manageDeleteUserItem.setEnabled(true);
                        manageUpdateUserItem.setEnabled(true);
                        manageAddPaperItem.setEnabled(true);
                        manageDeletePaperItem.setEnabled(true);
                        manageUpdatePaperItem.setEnabled(true);
                        helpItem.setEnabled(true);
                        updatePaperDialog.dispose();
                    }
                });

                updatePaperDialog.setSize(1110, 282);
                updatePaperDialog
                        .setLocation(mImageWidth / 8, mImageHeight / 2);
                updatePaperDialog.setTitle("修改论文");
                updatePaperDialog.setVisible(true);
                manageAddUserItem.setEnabled(false);
                manageDeleteUserItem.setEnabled(false);
                manageUpdateUserItem.setEnabled(false);
                manageAddPaperItem.setEnabled(false);
                manageDeletePaperItem.setEnabled(false);
                manageUpdatePaperItem.setEnabled(false);
                helpItem.setEnabled(true);
                updatePaperDialog.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        manageAddUserItem.setEnabled(true);
                        manageDeleteUserItem.setEnabled(true);
                        manageUpdateUserItem.setEnabled(true);
                        manageAddPaperItem.setEnabled(true);
                        manageDeletePaperItem.setEnabled(true);
                        manageUpdatePaperItem.setEnabled(true);
                        helpItem.setEnabled(true);
                    }
                });
            }
        });
        mangeMenu.add(manageAddUserItem);
        mangeMenu.add(manageDeleteUserItem);
        mangeMenu.add(manageUpdateUserItem);
        mangeMenu.add(manageAddPaperItem);
        mangeMenu.add(manageDeletePaperItem);
        mangeMenu.add(manageUpdatePaperItem);
        // 关于
        helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JDialog aboutDialog = new JDialog();
                Container aboutDialogPane = aboutDialog.getContentPane();
                aboutDialogPane.setLayout(null);
                JLabel aboutLabel = new JLabel();
                StringBuilder sb = new StringBuilder(
                        "<html><body><i><font color=blue>声明</font></i><br>");
                sb.append("20150320。<br>周智慧<br><br>");
                sb.append("<html><body><i><font color=blue>link</font></i><br>");
                sb.append("<a href=\"http://www.jlu.edu.cn/newjlu/\" runat=\"server\">查看</a>");
                sb.append("</body></html>");
                aboutLabel.setText(sb.toString());
                aboutLabel.setBounds(20, 0, 100, 190);
                aboutDialogPane.add(aboutLabel);

                JLabel l = new URLLabel("http://www.jlu.edu.cn/newjlu/", "主页");// http://www.jlu.edu.cn/newjlu/
                l.setBounds(20, 180, 64, 32);
                aboutDialogPane.add(l);

                aboutDialog.setTitle("关于");
                aboutDialog.setSize(300, 300);
                aboutDialog.setLocation(mImageWidth / 2, mImageHeight / 2);
                aboutDialog.setVisible(true);
            }
        });
        HelpMenu.add(helpItem);
        //
        menubar.add(LoginMenu);
        menubar.add(mangeMenu);
        menubar.add(algorithmMenu);
        menubar.add(HelpMenu);
        mContent.add(menubar, BorderLayout.NORTH);
        mContent.add(welcomeLabel, BorderLayout.CENTER);
    }

    private void updateBoxNames() {
        User singleUser = User.createUser();
        ArrayList<User> users = singleUser.getUsers();
        String[] namesString = new String[users.size() + 1];
        int i = 1;
        namesString[0] = "不限";
        for (User u : users) {
            namesString[i] = u.getName();
            i++;
        }
        mLeftPanel.remove(mBoxNames);
        mLeftPanel.repaint();
        mBoxNames = new JComboBox(namesString);
        mBoxNames.setVisible(mTypeBox.getSelectedIndex() == 1);
        mBoxNames.setBounds(10 + 64, mImageHeight * 4 / 3 / 5 + 32 + 28 + 32,
                64, 32);
        mLeftPanel.add(mBoxNames);
        mLeftPanel.validate();
        mContent.validate();
    }

    private void updateBoxPapers() {
        // TODO: update BoxPapers
    }

    private void setLeftPanel() {
        // BoxLayout la = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
        mLeftPanel.setLayout(null);
        mLeftPanel.setBackground(Color.white);
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon(this.getClass().getResource(
                "/images/leftBg.png")));
        imageLabel.setBounds(0, 0, mImageWidth / 5, mImageHeight * 4 / 3 / 5);
        mLeftPanel.add(imageLabel);
        JLabel searchLabel = new JLabel("查询");
        searchLabel.setBounds(10, imageLabel.getHeight() + 18, 64, 32);
        mLeftPanel.add(searchLabel);
        JLabel search = new JLabel("查询类型");
        search.setBounds(10, imageLabel.getHeight() + searchLabel.getHeight()
                + 18, 64, 32);
        mLeftPanel.add(search);
        mTypeBox = new JComboBox(new String[] { "不限", "学生", "论文", "专利", "奖励",
                "竞赛" });
        mTypeBox.setBounds(10 + search.getWidth(), imageLabel.getHeight()
                + searchLabel.getHeight() + 18, 64, 32);
        mLeftPanel.add(mTypeBox);

        final JLabel name = new JLabel("学生姓名");
        name.setBounds(10, imageLabel.getHeight() + searchLabel.getHeight()
                + 28 + search.getHeight(), 64, 32);
        mLeftPanel.add(name);
        name.setVisible(false);
        User singleUser = User.createUser();
        ArrayList<User> users = singleUser.getUsers();
        String[] namesString = new String[users.size() + 1];
        int i = 1;
        namesString[0] = "不限";
        for (User u : users) {
            namesString[i] = u.getName();
            i++;
        }
        mBoxNames = new JComboBox(namesString);
        mBoxNames.setBounds(10 + search.getWidth(), imageLabel.getHeight()
                + searchLabel.getHeight() + 28 + search.getHeight(), 64, 32);
        mLeftPanel.add(mBoxNames);
        mBoxNames.setVisible(false);

        mTypeBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (mTypeBox.getSelectedIndex()) {
                case 0:
                    name.setVisible(false);
                    mBoxNames.setVisible(false);
                    break;
                case 1:
                    name.setVisible(true);
                    mBoxNames.setVisible(true);
                    break;
                case 2:
                    name.setVisible(false);
                    mBoxNames.setVisible(false);
                    break;
                case 3:
                    name.setVisible(false);
                    mBoxNames.setVisible(false);
                    break;
                case 4:
                    name.setVisible(false);
                    mBoxNames.setVisible(false);
                    break;
                case 5:
                    name.setVisible(false);
                    mBoxNames.setVisible(false);
                    break;
                }
            }
        });

        JButton searchBt = new JButton("查询");
        searchBt.setBounds(10, imageLabel.getHeight() + searchLabel.getHeight()
                + 48 + search.getHeight() + mTypeBox.getHeight(), 64, 32);
        searchBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                switch (mTypeBox.getSelectedIndex()) {
                case 0:
                    setRightPanelForAll();
                    break;
                case 1:
                    int index = mBoxNames.getSelectedIndex();
                    if (index == 0) {
                        setRightPanelForUser();
                    } else if (index > 0) {
                        setRightPanelForUser(index - 1);
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                }
            }
        });
        mLeftPanel.add(searchBt);
        mSplitPane.setLeftComponent(mLeftPanel);
    }

    private void setRightPanel() {
        mSplitPane.setRightComponent(mRightPanel);
    }

    private void setRightPanelForAll() {
        mRightPanel.removeAll();
        mContent.repaint();
        mRightPanelForAll = RightPanelForAll.createRightPanelForAll();
        mRightPanel.setLayout(null);
        mRightPanelForAll.setBounds(5, 0, mRightPanel.getWidth(),
                mRightPanel.getHeight());
        mRightPanel.add(mRightPanelForAll);
        mContent.validate();
    }

    private void setRightPanelForUser() {
        mRightPanel.removeAll();
        mContent.repaint();
        mRightPanelForUser = RightPanelForUser.createRightPanelForUser();
        mRightPanel.setLayout(null);
        mRightPanelForUser.setBounds(5, 0, mRightPanel.getWidth(),
                mRightPanel.getHeight());
        mRightPanel.add(mRightPanelForUser);
        mContent.validate();
    }

    private void setRightPanelForUser(int index) {
        mRightPanel.removeAll();
        mContent.repaint();
        mRightPanelForUser = RightPanelForUser.createRightPanelForUser(index);
        mRightPanel.setLayout(null);
        mRightPanelForUser.setBounds(5, 0, mRightPanel.getWidth(),
                mRightPanel.getHeight());
        mRightPanel.add(mRightPanelForUser);
        mContent.validate();
    }

    private boolean checkAuthors(String authors) {
        String[] sA = authors.split(",");
        if (sA.length <= 0 || authors.contains("，") || authors.contains("。")
                || authors.contains("；") || authors.contains("、")
                || authors.contains("/") || authors.contains("“")
                || authors.contains("”")) {
            return false;
        }
        for (int i = 0; i < sA.length; i++) {
            String sTemp = sA[i].trim();
            if (sTemp.equals("")
                    || !(sTemp.getBytes().length == sTemp.length() || sTemp
                            .getBytes().length == 2 * sTemp.length())) {
                return false;
            }
            if (sTemp.matches(".*[\\d+].*")) {
                return false;
            }
        }
        return true;
    }

    private boolean hasTheSamePaper(String title) {
        boolean ret = false;
        Paper singlePaper = Paper.createPaper();
        ArrayList<Paper> papers = singlePaper.getmPapers();
        for (Paper p : papers) {
            if (p.getmTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    private String authorsListToString(ArrayList<User> list) {
        StringBuilder sb = new StringBuilder();
        for (User u : list) {
            sb.append(u.getName() + ", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

}

class ImagePane extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Image imgSplash;

    public ImagePane(Image image) {
        MediaTracker mt = new MediaTracker(this);
        mt.addImage(image, 0);
        try {
            mt.waitForID(0);
        } catch (Exception e) {
            e.printStackTrace();

        }
        this.imgSplash = image;
    }

    public void paint(Graphics g) {
        g.drawImage(imgSplash, 0, 0, this);
    }

    public Dimension getPreferredSize() {
        return new Dimension(imgSplash.getWidth(this),
                imgSplash.getHeight(this));
    }

}