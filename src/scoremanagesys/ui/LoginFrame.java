package scoremanagesys.ui;



import scoremanagesys.common.UserType;
import scoremanagesys.function.MysqlConnector;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 *
 *
 */

public class LoginFrame extends JFrame {

    {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static final long serialVersionUID = -3426717670093483287L;

    private JTextField userId;
    private JPasswordField pwdFld;
    private String userType = null;
    private MysqlConnector mysqlConnector = new MysqlConnector();

    public LoginFrame(){
        this.init();
        setVisible(true);
    }

    public void init(){
        this.setTitle("用户登录");
        this.setSize(330, 230);
        //设置默认窗体在屏幕中央
        int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((x - this.getWidth()) / 2, (y-this.getHeight())/ 2);
        this.setResizable(false);

        //登录信息面板
        JPanel mainPanel = new JPanel();
        Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        mainPanel.setBorder(BorderFactory.createTitledBorder(border, "输入登录信息", TitledBorder.CENTER,TitledBorder.TOP));
        this.add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(null);

        JLabel userIdLbl = new JLabel("账号:");
        userIdLbl.setBounds(50, 30, 40, 22);
        mainPanel.add(userIdLbl);
        userId = new JTextField();
        userId.setBounds(95, 30, 150, 22);
        userId.requestFocusInWindow();//用户名获得焦点
        mainPanel.add(userId);

        JLabel pwdLbl = new JLabel("密码:");
        pwdLbl.setBounds(50, 60, 40, 22);
        mainPanel.add(pwdLbl);
        pwdFld = new JPasswordField();
        pwdFld.setBounds(95, 60, 150, 22);
        mainPanel.add(pwdFld);

        //按钮面板放置在JFrame的南边
        JPanel btnPanel = new JPanel();
        this.add(btnPanel, BorderLayout.SOUTH);
        btnPanel.setLayout(new FlowLayout());
        btnPanel.setBorder(new EmptyBorder(2, 8, 4, 8));

        JButton registerBtn = new JButton("退出");
        btnPanel.add(registerBtn, FlowLayout.LEFT);
        JButton submitBtn = new JButton("登录");
        btnPanel.add(submitBtn, FlowLayout.LEFT);




        JPanel btnPanel1 = new JPanel();
        this.add(btnPanel1, BorderLayout.NORTH);
        btnPanel1.setLayout(new BorderLayout());
        btnPanel1.setBorder(new EmptyBorder(2, 8, 4, 8));

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton studentBtn = new JRadioButton("学生");
        JRadioButton teacherBtn = new JRadioButton("老师");
        JRadioButton managerBtn = new JRadioButton("管理员");

        buttonGroup.add(studentBtn);
        buttonGroup.add(teacherBtn);
        buttonGroup.add(managerBtn);

        btnPanel1.add(studentBtn, BorderLayout.WEST);
        btnPanel1.add(teacherBtn, BorderLayout.CENTER);
        btnPanel1.add(managerBtn, BorderLayout.LINE_END);



        //"登录"
        submitBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (studentBtn.isSelected()) {
                    userType = UserType.USERTYPE_STUDENT;
                } else if (teacherBtn.isSelected()) {
                    userType = UserType.USERTYPE_TEACHER;
                } else if (managerBtn.isSelected()) {
                    userType = UserType.USERTYPE_MANAGER;
                }
                login();
            }
        });

        //退出
        registerBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
    }

    /** 登录 */
    private void login() {
        if (userId.getText().length() == 0
                || pwdFld.getPassword().length == 0) {
            JOptionPane.showMessageDialog(LoginFrame.this,
                    "账号和密码是必填的",
                    "输入有误",JOptionPane.ERROR_MESSAGE);
            userId.requestFocusInWindow();
            return;
        }

        String userId_0 = userId.getText();
        String password_1 = String.valueOf(pwdFld.getPassword());
        if(mysqlConnector.CheckUser(userType, userId_0, password_1)){
            //获取当前用户
            JOptionPane.showMessageDialog(LoginFrame.this,
                    "登陆成功！","登录",JOptionPane.INFORMATION_MESSAGE);
            LoginFrame.this.dispose();
            if (userType.equals(UserType.USERTYPE_STUDENT)) {
                new StudentFrame(userId_0);
            } else if (userType.equals(UserType.USERTYPE_TEACHER)) {
                new TeacherFrame(userId_0);
            } else if (userType.equals(UserType.USERTYPE_MANAGER)) {
                new ManagerFrame(userId_0);
            }
        }else{ //登录失败
                JOptionPane.showMessageDialog(LoginFrame.this,
                        "账号或密码错误！",
                        "登录失败",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**退出**/
    private void exit() {
        int select = JOptionPane.showConfirmDialog(LoginFrame.this,
                "确定退出吗？", "退出系统",
                JOptionPane.YES_NO_OPTION);
        if (select == JOptionPane.YES_OPTION) {
            try {
                mysqlConnector.CloseConnect();
            } finally {
                System.exit(0);
            }
        } else {
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
