package scoremanagesys.function.manager;


import scoremanagesys.function.MysqlConnector;

import java.io.PrintStream;
import java.sql.*;
import java.util.Scanner;

public class ManagerFunction {


    private MysqlConnector mysqlConnector = null;
    private String userId;
    private Scanner input;
    private String query1;
    private String query2;

    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    public ManagerFunction(MysqlConnector mysqlConnector, String userId) {
        this.input = new Scanner(System.in);
        this.query1 = "SELECT * FROM  TEACHER  WHERE Tno = ";
        this.query2 = "SELECT * FROM  STUDENT  WHERE Sno = ";
        this.stmt = null;
        this.rs = null;
        this.mysqlConnector = mysqlConnector;
        this.con = mysqlConnector.getCon();
    }

    //添加教师
    public void addTeacher(String[] info) throws SQLException {
        String[] infos = info;
        String insertSQL = "insert into teacher(Tage,Tname,Tno,Tsex,Ttele,Ttitle) values (?,?,?,?,?,?)";
        PreparedStatement stat = con.prepareStatement(insertSQL);
        System.out.println("请输入教师:年龄丶姓名丶工号丶性别丶电话号码丶职称");
        stat.setObject(1,infos[0]);
        stat.setObject(2,infos[1]);
        stat.setObject(3,infos[2]);
        stat.setObject(4,infos[3]);
        stat.setObject(5,infos[4]);
        stat.setObject(6,infos[5]);
        stat.executeUpdate();
    }
    //添加学生
    public void addStudent(String[] info) throws SQLException {
        String[] infos = info;
        String insertSQL = "insert into student(Sage,SCP,Sname,Sno,ClassNo,Ssex,Smajor) values (?,?,?,?,?,?,?)";
        PreparedStatement stat = con.prepareStatement(insertSQL);
        System.out.println("请输入学生:年龄丶绩点丶姓名丶学号丶班级丶性别丶专业");
        stat.setObject(1,infos[0]);
        stat.setObject(2,infos[1]);
        stat.setObject(3,infos[2]);
        stat.setObject(4,infos[3]);
        stat.setObject(5,infos[4]);
        stat.setObject(6,infos[5]);
        stat.setObject(7,infos[6]);
        stat.executeUpdate();
    }
    //删除老师
    public void delTeacher(String teacherId) throws SQLException {
        String deleteSQL = "delete from teacher where Tno=";
        System.out.println("请输入教师工号");
        Statement stmt = con.createStatement();
        stmt.executeUpdate(deleteSQL+teacherId);
    }
    //删除学生
    public void delStudent(String studentId) throws SQLException {
        String deleteSQL = "delete from student where Sno=";
        System.out.println("请输入学生学号");
        Statement stmt = con.createStatement();
        stmt.executeUpdate(deleteSQL+studentId);
    }

    //查询教师
    public void teacherInfo(String Tno) throws SQLException {
        Statement stmt = con.createStatement();
        rs = stmt.executeQuery(query1 + Tno);
        //年龄 姓名 工号 性别 电话 职称
    }
    //查询学生
    public void studentInfo(String Sno) throws SQLException {
        Statement stmt = con.createStatement();
        this.rs = stmt.executeQuery(this.query2 + Sno);
        //年龄 姓名 学号 班级 性别

    }

    public ResultSet getRs() {
        return rs;
    }
}
