package scoremanagesys.ui;

import scoremanagesys.function.ChangePassword;
import scoremanagesys.function.MysqlConnector;
import scoremanagesys.function.TeacherFunction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;


class TeacherFrame extends JFrame {


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
    private JButton bt6;
    private JButton bt7;

    private MysqlConnector mysqlConnector;
    private TeacherFunction teacherFunction;
    private String userId;
    private JPanel btnPanel;
    private JPanel btnPanel1;

    private JTextField CnoFid;
    private JTextField newStudent;



    public TeacherFrame(String userId) {
        super("教师端");        //JFrame的标题名称
        this.setSize(640, 600);        //控制窗体大小
        //设置默认窗体在屏幕中央
        int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((x - this.getWidth()) / 2, (y-this.getHeight())/ 2);

        CnoFid = new JTextField("请输入课程号                   ");
        CnoFid.setForeground(Color.gray);
        CnoFid.setSize(150, 22);
        newStudent = new JTextField("请按顺序输入：课程号 学号 学期 分数，按空格隔开");
        newStudent.setForeground(Color.gray);
        newStudent.setSize(450, 22);


        this.userId = userId;
        scpDemo = new JScrollPane();
        mysqlConnector = new MysqlConnector();
        teacherFunction = new TeacherFunction(mysqlConnector, userId);
        bt1 = new JButton("查询教师信息");
        bt2 = new JButton("查询任课信息");
        bt3 = new JButton("查询学生成绩");
        bt4 = new JButton("查询平均成绩");
        bt5 = new JButton("修改学生成绩");
        bt6 = new JButton("修改个人密码");
        bt7 = new JButton("新增学生成绩");

        btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        btnPanel.setBorder(new EmptyBorder(8, 8, 4, 8));
        btnPanel1 = new JPanel();
        btnPanel1.setLayout(new GridLayout(3,2, 30,1));
        btnPanel1.setBorder(new EmptyBorder(8, 8, 15, 8));

        bt1.setBounds(100, 480, 100, 30);
        bt2.setBounds(380, 480, 100, 30);
        bt5.setBounds(380, 240, 100, 30);
        bt6.setBounds(380, 240, 100, 30);

        bt3.setBounds(65, 34, 150, 22);//
        bt4.setBounds(65, 34, 150, 22);//
        bt7.setBounds(65, 34, 150, 22);//
        scpDemo.setBounds(10, 50, 580, 400);    //设置滚动框大小


        /**文本框监听光标**/
        CnoFid.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                //得到焦点时，当前文本框的提示文字和创建该对象时的提示文字一样，说明用户正要键入内容
                if (CnoFid.getText().equals("请输入课程号                   ")) {
                    CnoFid.setText("");     //将提示文字清空
                    CnoFid.setForeground(Color.black);  //设置用户输入的字体颜色为黑色
                }
            }
            public void focusLost(FocusEvent e) {
                //失去焦点时，用户尚未在文本框内输入任何内容，所以依旧显示提示文字
                if (CnoFid.getText().equals("")){
                    CnoFid.setForeground(Color.gray); //将提示文字设置为灰色
                    CnoFid.setText("请输入课程号                   ");     //显示提示文字
                }
            }
        });

        newStudent.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                //得到焦点时，当前文本框的提示文字和创建该对象时的提示文字一样，说明用户正要键入内容
                if (newStudent.getText().equals("请按顺序输入：课程号 学号 学期 分数，按空格隔开")) {
                    newStudent.setText("");     //将提示文字清空
                    newStudent.setForeground(Color.black);  //设置用户输入的字体颜色为黑色
                }
            }
            public void focusLost(FocusEvent e) {
                //失去焦点时，用户尚未在文本框内输入任何内容，所以依旧显示提示文字
                if (newStudent.getText().equals("")){
                    newStudent.setForeground(Color.gray); //将提示文字设置为灰色
                    newStudent.setText("请按顺序输入：课程号 学号 学期 分数，按空格隔开");     //显示提示文字
                }
            }
        });


        /********按钮“查询教师信息”的响应*******/
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    teacherFunction.teacherInfo();
                    ResultSet rs =teacherFunction.getRs();
                    int count = 0;
                    while (rs.next()) {
                        count++;
                    }
                    teacherFunction.teacherInfo();
                    rs = teacherFunction.getRs();
                    // 将查询获得的记录数据，转换成适合生成JTable的数据形式
                    Object[][] info = new Object[count][6];
                    String[] title = {"年龄", "姓名", "工号", "性别", "电话", "职称"};
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

        /******按钮 “查询任课信息”的响应*****/
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    teacherFunction.teachClassInfo();
                    ResultSet rs = teacherFunction.getRs();
                    int count = 0;
                    while (rs.next()) {
                        count++;
                    }
                    teacherFunction.teachClassInfo();
                    rs = teacherFunction.getRs();
                    // 将查询获得的记录数据，转换成适合生成JTable的数据形式
                    Object[][] info = new Object[count][6];
                    String[] title = {"教师姓名", "工号", "课程名称", "课程号"};
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

        /******按钮 “查询学生成绩”的响应*****/
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String Cno = CnoFid.getText();
                    teacherFunction.allStudentScore(Cno);
                    ResultSet rs = teacherFunction.getRs();
                    int count = 0;
                    while (rs.next()) {
                        count++;
                    }
                    teacherFunction.allStudentScore(Cno);
                    rs = teacherFunction.getRs();
                    // 将查询获得的记录数据，转换成适合生成JTable的数据形式
                    Object[][] info = new Object[count][6];
                    String[] title = {"姓名", "分数"};
                    count = 0;
                    while (rs.next()) {
                        info[count][0] = rs.getString(1);
                        info[count][1] = rs.getString(5);
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

        /******按钮 “查询平均成绩”的响应*****/
        this.bt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String Cno = CnoFid.getText();
                    teacherFunction.avgScore(Cno);
                    ResultSet rs = teacherFunction.getRs();
                    int count = 0;
                    while (rs.next()) {
                        count++;
                    }
                    teacherFunction.avgScore(Cno);
                    rs = teacherFunction.getRs();
                    // 将查询获得的记录数据，转换成适合生成JTable的数据形式
                    Object[][] info = new Object[count][6];
                    String[] title = {"课程名", "平均成绩"};
                    count = 0;
                    while (rs.next()) {
                        info[count][0] = rs.getString(3);
                        info[count][1] = rs.getString(2);
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
        bt6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePassword("2", userId);
            }
        });

        /******按钮 “新增学生成绩”的响应*****/
        bt7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    JOptionPane.showMessageDialog(TeacherFrame.this, "添加成功!");
                    teacherFunction.changeScore(newStudent.getText());
                    newStudent.setText("");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        /******* 将组件加入到窗体中******/
        this.add(this.scpDemo);
        this.add(btnPanel, BorderLayout.NORTH);
        this.add(btnPanel1, BorderLayout.SOUTH);


        btnPanel.add(bt1, FlowLayout.LEFT);
        btnPanel.add(bt2, FlowLayout.LEFT);
        btnPanel.add(bt6, FlowLayout.LEFT);

        btnPanel1.add(bt3);
        btnPanel1.add(bt7);
        btnPanel1.add(bt4);
        btnPanel1.add(newStudent);
        btnPanel1.add(CnoFid);



        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
