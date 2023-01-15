package scoremanagesys.ui;

import scoremanagesys.common.UserType;
import scoremanagesys.function.ChangePassword;
import scoremanagesys.function.manager.AddStudent;
import scoremanagesys.function.manager.AddTeacher;
import scoremanagesys.function.manager.ManagerFunction;
import scoremanagesys.function.MysqlConnector;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


class ManagerFrame extends JFrame {


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
    private JButton bt1;//添加教师
    private JButton bt2;//添加学生
    private JButton bt3;//删除教师
    private JButton bt4;//删除学生
    private JButton bt5;//修改教师
    private JButton bt6;//修改学生
    private JButton bt7;//查询教师
    private JButton bt8;//查询学生

    private MysqlConnector mysqlConnector;
    private ManagerFunction managerFunction;
    private String userId;
    private JPanel btnPanel;


    private JTextField SnoFid;
    private JTextField TnoFld;




    public ManagerFrame(String userId) {
        super("管理员端");        //JFrame的标题名称
        this.setSize(640, 600);        //控制窗体大小
        //设置默认窗体在屏幕中央
        int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((x - this.getWidth()) / 2, (y-this.getHeight())/ 2);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        SnoFid = new JTextField();
        TnoFld = new JTextField();

        this.userId = userId;
        scpDemo = new JScrollPane();
        mysqlConnector = new MysqlConnector();
        managerFunction = new ManagerFunction(mysqlConnector, userId);

        bt1 = new JButton("添加教师信息");
        bt2 = new JButton("添加学生信息");
        bt3 = new JButton("删除教师信息");
        bt4 = new JButton("删除学生信息");
        bt5 = new JButton("修改教师密码");
        bt6 = new JButton("修改学生密码");
        bt7 = new JButton("查询教师信息");
        bt8 = new JButton("查询学生信息");

        //增加
        bt1.setBounds(100, 240, 100, 30);
        bt2.setBounds(380, 240, 100, 30);
        //修改
        bt5.setBounds(100, 360, 100, 30);
        bt6.setBounds(380, 360, 100, 30);
        //删除和查询教师
        bt3.setBounds(100, 480, 100, 30);
        bt7.setBounds(380, 480, 100, 30);
        TnoFld.setBounds(400, 480, 100, 30);
        //删除和查询学生
        bt4.setBounds(100, 720, 100, 30);
        bt8.setBounds(380, 720, 100, 30);
        SnoFid.setBounds(400, 7200, 100, 30);

        scpDemo.setBounds(10, 50, 580, 400);    //设置滚动框大小

        JLabel jLabel1 = new JLabel("输入查询/删除的学生学号:");
        jLabel1.setBounds(24, 72, 10, 17);

        JLabel jLabel2 = new JLabel("输入查询/删除的教师工号:");
        jLabel2.setBounds(24, 72, 10, 17);

        btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(3,4,5,5));

        btnPanel.add(bt1);
        btnPanel.add(bt2);
        btnPanel.add(bt5);
        btnPanel.add(bt6);
        btnPanel.add(bt3);
        btnPanel.add(bt7);
        btnPanel.add(jLabel2);
        btnPanel.add(TnoFld);
        btnPanel.add(bt4);
        btnPanel.add(bt8);
        btnPanel.add(jLabel1);
        btnPanel.add(SnoFid);

        this.add(this.scpDemo);
        this.add(btnPanel, BorderLayout.SOUTH);





        /********按钮“添加教师信息”的响应*******/
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTeacher(managerFunction);
            }
        });

        /******按钮 “添加学生信息”的响应*****/
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStudent(managerFunction);
            }
        });

        /******按钮 “删除教师信息”的响应*****/
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int select = JOptionPane.showConfirmDialog(ManagerFrame.this,
                        "确定删除教师吗？", "删除教师",
                        JOptionPane.YES_NO_OPTION);
                if (select == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(ManagerFrame.this, "删除成功!");
                    String Tno = TnoFld.getText();
                    TnoFld.setText("");
                    try {
                        managerFunction.delTeacher(Tno);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                } else {
                    JOptionPane.showMessageDialog(ManagerFrame.this, "取消删除");
                }
            }
        });

        /******按钮 “删除学生信息”的响应*****/
        this.bt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int select = JOptionPane.showConfirmDialog(ManagerFrame.this,
                        "确定删除学生吗？", "删除学生",
                        JOptionPane.YES_NO_OPTION);
                if (select == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(ManagerFrame.this, "删除成功!");
                        String Cno = SnoFid.getText();
                        SnoFid.setText("");
                    try {
                        managerFunction.delStudent(Cno);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                } else {
                    JOptionPane.showMessageDialog(ManagerFrame.this, "取消删除");
                }

            }
        });

        /******按钮 “修改教师账号”的响应*****/
        this.bt5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePassword(UserType.USERTYPE_MANAGER, UserType.USERTYPE_TEACHER, TnoFld.getText());
            }
        });

        /******按钮 “修改学生账号”的响应*****/
        bt6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePassword(UserType.USERTYPE_MANAGER, UserType.USERTYPE_STUDENT, SnoFid.getText());
            }
        });

        /******按钮 “查询教师信息”的响应*****/
        bt7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String Tno = TnoFld.getText();
                    TnoFld.setText("");
                    managerFunction.teacherInfo(Tno);
                    ResultSet rs =managerFunction.getRs();
                    int count = 0;
                    while (rs.next()) {
                        count++;
                    }
                    managerFunction.teacherInfo(Tno);
                    rs = managerFunction.getRs();
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

        /******按钮 “查询学生信息”的响应*****/
        bt8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String Sno = SnoFid.getText();
                    managerFunction.studentInfo(Sno);
                    SnoFid.setText("");
                    ResultSet rs = managerFunction.getRs();
                    int count = 0;
                    while (rs.next()) {
                        count++;
                    }
                    managerFunction.studentInfo(Sno);
                    rs = managerFunction.getRs();
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
    }
}
