package scoremanagesys.function;

import java.io.PrintStream;
import java.sql.*;
import java.util.Scanner;

public class TeacherFunction {

    private Scanner input = new Scanner(System.in);
    private String query4 = "SELECT * FROM " +  " 课程平均成绩 " + " WHERE Cno = ";
    private String query2 = "SELECT * FROM "+ " 教师课表 " + " WHERE Tno = ";
    private String query3 = "SELECT * FROM " + " sj " + " WHERE Cno = ";
    private String query1 = "SELECT * FROM " + " teacher " + " WHERE Tno = ";
    private String userId;
    private MysqlConnector mysqlConnector;
    private Connection con;
    private Statement stmt = null;
    private ResultSet rs = null;

    public TeacherFunction(MysqlConnector mysqlConnector, String userId) {
        this.mysqlConnector = mysqlConnector;
        this.con = mysqlConnector.getCon();
        this.userId = userId;
    }

    //教师信息
    public void teacherInfo() throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery(query1 + userId);
    }

    //教师授课信息
    public void teachClassInfo() throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery(query2+ userId);
    }
    //所有学生成绩
    public void allStudentScore(String Cno) throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery(query3+Cno);
    }

    //单个学生成绩
    public void oneStudentScore() throws SQLException {
        stmt = con.createStatement();
    }

    //平均分
    public void avgScore(String Cno) throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery(query4+Cno);
    }

    //新增成绩
    public void changeScore(String msg) throws SQLException {
        String[] in = msg.split(" ");
        stmt = con.createStatement();
        String insertSQL = "insert into sc(Cno,Sno,Cterm,SCmark) values (?,?,?,?)";
        PreparedStatement stat = con.prepareStatement(insertSQL);

        stat.setObject(1,in[0]);

        stat.setObject(2,in[1]);

        stat.setObject(3,in[2]);

        stat.setObject(4,in[3]);
        stat.executeUpdate();
    }

    public ResultSet getRs() {
        return rs;
    }
}