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

public class AddTeacher extends JFrame {

    private static final long serialVersionUID = -768631070458723803L;

    private JTextField ageFld;
    private JTextField nameFld;
    private JTextField idFld;
    private JTextField sexFld;
    private JTextField phoneFld;
    private JTextField titleFld;
    private JButton ok;
    private ManagerFunction managerFunction;

    public String[] getInfo() {
        return info;
    }

    private String[] info = new String[6];


    //教师和学生端
    public AddTeacher(ManagerFunction managerFunction){
        this.managerFunction = managerFunction;
        this.init();
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
//年龄丶姓名丶工号丶性别丶电话号码丶职称
    public void init(){
        this.setTitle("请输入教师信息");//设置标题
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 387)/2,
                (screenSize.height - 267)/2,
                290, 267);
        getContentPane().setLayout(null);
        setResizable(false);



        ageFld = new JTextField();
        nameFld = new JTextField();
        idFld = new JTextField();
        sexFld = new JTextField();
        phoneFld = new JTextField();
        titleFld = new JTextField();

        ageFld.setBounds(65, 34, 150, 22);
        nameFld.setBounds(65, 59, 150, 22);
        idFld.setBounds(65, 84, 150, 22);
        sexFld.setBounds(65, 109, 150, 22);
        phoneFld.setBounds(65, 134, 150, 22);
        titleFld.setBounds(65, 159, 150, 22);

        getContentPane().add(ageFld);
        getContentPane().add(nameFld);
        getContentPane().add(idFld);
        getContentPane().add(sexFld);
        getContentPane().add(phoneFld);
        getContentPane().add(titleFld);


        JLabel jLabel1 = new JLabel("年龄:");
        jLabel1.setBounds(24, 34, 50, 17);
        getContentPane().add(jLabel1);

        JLabel jLabel2 = new JLabel("姓名:");
        jLabel2.setBounds(24, 59, 50, 17);
        getContentPane().add(jLabel2);

        JLabel jLabel3 = new JLabel("工号:");
        jLabel3.setBounds(24, 84, 50, 17);
        getContentPane().add(jLabel3);

        JLabel jLabel4 = new JLabel("性别:");
        jLabel4.setBounds(24, 109, 50, 17);
        getContentPane().add(jLabel4);

        JLabel jLabel5 = new JLabel("电话:");
        jLabel5.setBounds(24, 134, 50, 17);
        getContentPane().add(jLabel5);

        JLabel jLabel6 = new JLabel("职称:");
        jLabel6.setBounds(24, 159, 50, 17);
        getContentPane().add(jLabel6);


        //按钮
        ok = new JButton("确认");
        ok.setBounds(110, 200, 60, 28);
        getContentPane().add(ok);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info[0] = ageFld.getText();
                info[1] = nameFld.getText();
                info[2] = idFld.getText();
                info[3] = sexFld.getText();
                info[4] = phoneFld.getText();
                info[5] = titleFld.getText();

                try {
                    managerFunction.addTeacher(info);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(AddTeacher.this, "添加成功!");
                AddTeacher.this.dispose();
            }
        });


    }
}
