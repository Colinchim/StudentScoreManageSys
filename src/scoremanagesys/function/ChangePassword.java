package scoremanagesys.function;


import scoremanagesys.common.UserType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 */

public class ChangePassword extends JFrame {

    private static final long serialVersionUID = -768631070458723803L;
    private JPasswordField pwdFld;
    private JPasswordField pwd2Fld;
    private JTextField userIdFld;
    private JButton ok;
    private JButton reset;
    private JButton cancel;
    private MysqlConnector mysqlConnector = new MysqlConnector();
    private String userType;
    private String userId;
    private String managerType;

    //教师和学生端
    public ChangePassword(String userType, String userId){
        this.managerType = "4";
        this.userType = userType;
        this.userId = userId;
        this.init();
        setVisible(true);

    }
    //管理员端
    public ChangePassword(String managerType, String userType, String userId){
        this.managerType = managerType;
        this.userType = userType;
        this.userId = userId;
        this.init();
        setVisible(true);

    }

    public void init(){
        this.setTitle("修改密码");//设置标题
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 387)/2,
                (screenSize.height - 267)/2,
                387, 267);
        getContentPane().setLayout(null);
        setResizable(false);

        JLabel label = new JLabel("账号:"); //label显示
        label.setBounds(24, 36, 59, 17);
        getContentPane().add(label);

        userIdFld = new JTextField(); //用户名
        userIdFld.setBounds(130, 34, 150, 22);
        getContentPane().add(userIdFld);

        JLabel label5 = new JLabel("*新密码:");
        label5.setBounds(24, 72, 100, 17);
        getContentPane().add(label5);

        JLabel label3 = new JLabel("*确认新密码:");
        label3.setBounds(24, 107, 100, 17);
        getContentPane().add(label3);


        pwdFld = new JPasswordField();//密码框
        pwdFld.setBounds(130, 70, 150, 22);
        getContentPane().add(pwdFld);

        pwd2Fld = new JPasswordField();
        pwd2Fld.setBounds(130, 105, 150, 22);
        getContentPane().add(pwd2Fld);

        //按钮
        ok = new JButton("确认");
        ok.setBounds(27, 176, 60, 28);
        getContentPane().add(ok);

        reset = new JButton("重填");
        reset.setBounds(123, 176, 60, 28);
        getContentPane().add(reset);

        cancel = new JButton("取消");
        cancel.setBounds(268, 176, 60, 28);
        getContentPane().add(cancel);


        //取消按钮监听事件处理
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                ChangePassword.this.dispose();
            }
        });
        //关闭窗口
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                ChangePassword.this.dispose();
            }
        });

        // 重置按钮监听事件处理
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                userIdFld.setText("");
                pwdFld.setText("");
                pwd2Fld.setText("");
                userIdFld.requestFocusInWindow();//用户名获得焦点
            }
        });

        //确认按钮监听事件处理
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {

                if (managerType.equals(UserType.USERTYPE_MANAGER)) {
                    changePwd();
                } else {
                    if (!userIdFld.getText().equals(userId)) {
                        JOptionPane.showMessageDialog(ChangePassword.this, "只能修改自己的密码!");
                        userIdFld.setText("");
                    } else {
                        changePwd();
                    }
                }
            }
        });
    }

    public void changePwd() {
        if (pwdFld.getPassword().length==0 || pwd2Fld.getPassword().length==0) {
            JOptionPane.showMessageDialog(ChangePassword.this, "带 “ * ” 为必填内容!");
            //判断用户名和密码是否为空
        } else if (!new String(pwdFld.getPassword()).equals(new String(pwd2Fld.getPassword()))) {
            JOptionPane.showMessageDialog(ChangePassword.this, "两次输入密码不一致!");
            pwdFld.setText("");
            pwd2Fld.setText("");
            pwdFld.requestFocusInWindow();
            //判断两次密码是否一致
        } else {
            JOptionPane.showMessageDialog(ChangePassword.this, "修改成功!");
            String userId_0 = userIdFld.getText();
            String password_1 = String.valueOf(pwd2Fld.getPassword());
            mysqlConnector.changePwd(userType, userId_0, password_1);

        }
    }
}
