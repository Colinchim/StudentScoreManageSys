package scoremanagesys.ui;

import scoremanagesys.function.ChangePassword;
import scoremanagesys.function.MysqlConnector;
import scoremanagesys.function.StudentFunction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


class StudentFrame extends JFrame {

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


    private JScrollPane scpDemo;
    private JTableHeader jth;
    private JTable tabDemo;
    private JButton bt1;
    private JButton bt2;
    private JButton bt3;
    private JButton bt4;
    private JButton bt5;
    private JPanel btnPanel;
    private JTextField jTextField;
    private MysqlConnector mysqlConnector;
    private StudentFunction studentFunction;
    private String userId;



    public StudentFrame(String userId) {
        super("学生端");        //JFrame的标题名称
        this.setVisible(true);
        this.setSize(800, 600);        //控制窗体大小
        //设置默认窗体在屏幕中央
        int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((x - this.getWidth()) / 2, (y-this.getHeight())/ 2);

        this.userId = userId;
        scpDemo = new JScrollPane();
        mysqlConnector = new MysqlConnector();
        studentFunction = new StudentFunction(mysqlConnector, this.userId);
        jTextField = new JTextField("");
        jTextField.setBounds(95, 30, 150, 22);
        jTextField.requestFocusInWindow();//用户名获得焦点
        bt1 = new JButton("查询课程信息");
        bt2 = new JButton("查询成绩信息");
        bt3 = new JButton("查询绩点信息");
        bt4 = new JButton("查询个人信息");
        bt5 = new JButton("修改个人密码");
        btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        this.btnPanel.setBorder(new EmptyBorder(8, 8, 4, 8));
        bt1.setBounds(100, 480, 100, 30);
        bt2.setBounds(380, 480, 100, 30);
        bt3.setBounds(100, 480, 100, 30);
        bt4.setBounds(380, 480, 100, 30);
        bt5.setBounds(380, 480, 100, 30);
        scpDemo.setBounds(10, 50, 580, 400);    //设置滚动框大小


        /********按钮“查询课程信息”的响应*******/
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    studentFunction.CourseInfo();
                    ResultSet rs = studentFunction.getRs();
                    int count = 0;
                    while (rs.next()) {
                        count++;
                    }
                    studentFunction.CourseInfo();
                    rs = studentFunction.getRs();
                    // 将查询获得的记录数据，转换成适合生成JTable的数据形式
                    Object[][] info = new Object[count][6];
                    String[] title = {"课程学分", "课程学时", "课程名称", "课程号", "开设学期", "授课教师"};
                    count = 0;
                    while (rs.next()) {
                        info[count][0] = rs.getString(1);
                        info[count][1] = rs.getString(2);
                        info[count][2] = rs.getString(3);
                        info[count][3] = rs.getString(4);
                        info[count][4] = rs.getString(5);
                        info[count][5] = rs.getString(6);
                        count++;
                    }
                    // 创建JTable
                    tabDemo = new JTable(info, title);
                    // 显示表头
                    jth = tabDemo.getTableHeader();
                    // 将JTable加入到带滚动条的面板中
                    scpDemo.getViewport().add(tabDemo);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        /******按钮 “查询成绩信息”的响应*****/
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    studentFunction.SelfMark();
                    ResultSet rs = studentFunction.getRs();
                    int count = 0;
                    while (rs.next()) {
                        count++;
                    }
                    studentFunction.SelfMark();
                    rs = studentFunction.getRs();
                    // 将查询获得的记录数据，转换成适合生成JTable的数据形式
                    Object[][] info = new Object[count][6];
                    String[] title = {"学生姓名", "学生学号", "课程名称", "课程成绩"};
                    count = 0;
                    while (rs.next()) {
                        info[count][0] = rs.getString(1);
                        info[count][1] = rs.getString(2);
                        info[count][2] = rs.getString(3);
                        info[count][3] = rs.getString(4);
                        count++;
                    }
                    // 创建JTable
                    tabDemo = new JTable(info, title);
                    // 显示表头
                    jth = tabDemo.getTableHeader();
                    // 将JTable加入到带滚动条的面板中
                    scpDemo.getViewport().add(tabDemo);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        /******按钮 “查询绩点信息”的响应*****/
        this.bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    studentFunction.CreditInfo();
                    ResultSet rs = studentFunction.getRs();
                    int count = 0;
                    while (rs.next()) {
                        count++;
                    }
                    studentFunction.CreditInfo();
                    rs = studentFunction.getRs();
                    // 将查询获得的记录数据，转换成适合生成JTable的数据形式
                    Object[][] info = new Object[count][6];
                    String[] title = {"姓名", "学号", "平均学分绩点"};
                    count = 0;
                    while (rs.next()) {
                        info[count][0] = rs.getString(2);
                        info[count][1] = rs.getString(3);
                        info[count][2] = rs.getString(1);
                        count++;
                    }
                    // 创建JTable
                    tabDemo = new JTable(info, title);
                    // 显示表头
                    jth = tabDemo.getTableHeader();
                    // 将JTable加入到带滚动条的面板中
                    scpDemo.getViewport().add(tabDemo);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        /******按钮 “查询个人信息”的响应*****/
        this.bt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    studentFunction.selfInfo();
                    ResultSet rs = studentFunction.getRs();
                    int count = 0;
                    while (rs.next()) {
                        count++;
                    }
                    studentFunction.selfInfo();
                    rs = studentFunction.getRs();
                    // 将查询获得的记录数据，转换成适合生成JTable的数据形式
                    Object[][] info = new Object[count][6];
                    String[] title = {"姓名", "学号", "性别", "年龄", "专业", "班级"};
                    count = 0;
                    while (rs.next()) {
                        info[count][0] = rs.getString(3);
                        info[count][1] = rs.getString(4);
                        info[count][2] = rs.getString(6);
                        info[count][3] = rs.getString(1);
                        info[count][4] = rs.getString(7);
                        info[count][5] = rs.getString(6);
                        count++;
                    }
                    // 创建JTable
                    tabDemo = new JTable(info, title);
                    // 显示表头
                    jth = tabDemo.getTableHeader();
                    // 将JTable加入到带滚动条的面板中
                    scpDemo.getViewport().add(tabDemo);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        /******按钮 “修改个人密码”的响应*****/
        this.bt5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePassword("1", userId );
            }
        });
        /******* 将组件加入到窗体中******/
        this.add(this.scpDemo);
        this.add(btnPanel, BorderLayout.NORTH);
        bt1.setLayout(new FlowLayout());
        bt2.setLayout(new FlowLayout());
        bt3.setLayout(new FlowLayout());
        bt4.setLayout(new FlowLayout());
        bt5.setLayout(new FlowLayout());
        btnPanel.add(bt1, FlowLayout.LEFT);
        btnPanel.add(bt2, FlowLayout.LEFT);
        btnPanel.add(bt3, FlowLayout.LEFT);
        btnPanel.add(bt4, FlowLayout.LEFT);
        btnPanel.add(bt5, FlowLayout.LEFT);
        //btnPanel.add(jTextField, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}


