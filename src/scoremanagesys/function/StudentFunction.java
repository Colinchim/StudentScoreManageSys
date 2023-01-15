package scoremanagesys.function;

import scoremanagesys.function.MysqlConnector;

import java.sql.*;
import java.util.Scanner;

public class StudentFunction {
    private MysqlConnector mysqlConnector = null;
    private String userId;
    private Scanner input = new Scanner(System.in);
    //查询
    private String query1 = "SELECT * FROM " + " COURSE ";
    private String query2 = "SELECT * FROM " + " chenghji " + " WHERE Sno = ";
    private String query3 = "SELECT * FROM " + " Student_jidian " + " WHERE Sno = ";
    private String query4 = "SELECT * FROM " + " student " + " WHERE Sno = ";

    private Connection con=null;
    private Statement stmt=null;

    public ResultSet getRs() {
        return rs;
    }

    private ResultSet rs = null;
    private String[] res = null;

    public StudentFunction(MysqlConnector mysqlConnector, String userId) {
        this.mysqlConnector = mysqlConnector;
        this.con = mysqlConnector.getCon();
        this.userId = userId;
    }

    public void CourseInfo() throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery(query1);

    }

    public void SelfMark() throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery(query2+userId);
    }

    public void CreditInfo() throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery(query3+userId);
    }

    public void selfInfo() throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery(query4+userId);
    }
}