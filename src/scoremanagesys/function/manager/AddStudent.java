package scoremanagesys.function.manager;


import scoremanagesys.function.MysqlConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 */

public class AddStudent extends JFrame {

    private static final long serialVersionUID = -768631070458723803L;

    private JTextField ageFld;
    private JTextField gpaFld;
    private JTextField nameFld;
    private JTextField idFld;
    private JTextField classFld;
    private JTextField sexFld;
    private JTextField majorFld;
    private ManagerFunction managerFunction;

/*
    ageFld;
    gpaFld;
    nameFld;
    idFld;
    classFld;
    sexFld;
    majorFld;

 */
    private JButton ok;

    public String[] getInfo() {
        return info;
    }

    private String[] info = new String[7];


    //教师和学生端
    public AddStudent(ManagerFunction managerFunction){
        this.managerFunction = managerFunction;
        this.init();
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    //年龄丶绩点丶姓名丶学号丶班级丶性别丶专业
    public void init(){
        this.setTitle("请输入学生信息");//设置标题
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 387)/2,
                (screenSize.height - 267)/2,
                290, 290);
        getContentPane().setLayout(null);
        setResizable(false);


        ageFld = new JTextField();
        gpaFld = new JTextField();
        nameFld = new JTextField();
        idFld = new JTextField();
        classFld = new JTextField();
        sexFld = new JTextField();
        majorFld = new JTextField();


        ageFld.setBounds(65, 34, 150, 22);
        gpaFld.setBounds(65, 59, 150, 22);
        nameFld.setBounds(65, 84, 150, 22);
        idFld.setBounds(65, 109, 150, 22);
        classFld.setBounds(65, 134, 150, 22);
        sexFld.setBounds(65, 159, 150, 22);
        majorFld.setBounds(65, 184, 150, 22);


        getContentPane().add(ageFld);
        getContentPane().add(gpaFld);
        getContentPane().add(nameFld);
        getContentPane().add(idFld);
        getContentPane().add(classFld);
        getContentPane().add(sexFld);
        getContentPane().add(majorFld);



        JLabel jLabel1 = new JLabel("年龄");
        jLabel1.setBounds(24, 34, 50, 17);
        getContentPane().add(jLabel1);

        JLabel jLabel2 = new JLabel("绩点");
        jLabel2.setBounds(24, 59, 100, 17);
        getContentPane().add(jLabel2);

        JLabel jLabel3 = new JLabel("姓名");
        jLabel3.setBounds(24, 84, 100, 17);
        getContentPane().add(jLabel3);

        JLabel jLabel4 = new JLabel("学号");
        jLabel4.setBounds(24, 109, 100, 17);
        getContentPane().add(jLabel4);

        JLabel jLabel5 = new JLabel("班级");
        jLabel5.setBounds(24, 134, 100, 17);
        getContentPane().add(jLabel5);

        JLabel jLabel6 = new JLabel("性别");
        jLabel6.setBounds(24, 159, 100, 17);
        getContentPane().add(jLabel6);

        JLabel jLabel7 = new JLabel("专业");
        jLabel7.setBounds(24, 184, 100, 17);
        getContentPane().add(jLabel7);


        //按钮
        ok = new JButton("确认");
        ok.setBounds(110, 220, 60, 28);
        getContentPane().add(ok);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info[0] = ageFld.getText();
                info[1] = gpaFld.getText();
                info[2] = nameFld.getText();
                info[3] = idFld.getText();
                info[4] = classFld.getText();
                info[5] = sexFld.getText();
                info[6] = majorFld.getText();

                try {
                    managerFunction.addStudent(info);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                JOptionPane.showMessageDialog(AddStudent.this, "添加成功!");
                AddStudent.this.dispose();
            }
        });


    }
}