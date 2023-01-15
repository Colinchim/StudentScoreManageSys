package scoremanagesys.function;

import scoremanagesys.common.UserType;

import java.sql.*;

public class MysqlConnector {

    private String url   = "jdbc:mysql://81.68.102.48:3306/ScoreManageSys";
    private String query = null;
    private Connection con=null;
    private Statement stmt=null;
    private ResultSet rs=null;
    private String table = null;

    public Connection getCon() {
        return con;
    }

    public MysqlConnector() {
        try {
            //装载mysql Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //尝试获得连接对象，如果连接失败，则抛出异常
            con = DriverManager.getConnection (url, "ScoreManageSys", "bMFb.8nYPpY_dXLBpA%%Kcn");

        }
        catch (SQLException ex) {System.out.println ("发生SQL异常");}
        catch (ClassNotFoundException  ex) {ex.printStackTrace ();}
    }

    //检查用户是否合法
    public boolean CheckUser(String userType, String userId, String pwd) {
        checkUserType(userType);
        query = "SELECT * FROM " + table + " Where id = " + userId;
        //System.out.println(query);
        try {
            //创建一个Statement对象
            stmt = con.createStatement();
            // 提交一个SQL查询请求，返回结果集，被rs所引用
            rs = stmt.executeQuery (query);
            boolean is = rs.next();
            if (is) {
                if (rs.getString(2).equals(pwd)) {
                    System.out.println("登陆成功");
                    return true;
                } else if (!rs.getString(2).equals(pwd)) {
                    System.out.println("密码错误！");
                    return false;
                }
            } else {
                System.out.println("账号不存在");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    //修改密码
    public void changePwd(String userType, String userId, String pwd) {
        checkUserType(userType);
        query = " update " +  table  + " set " + table + ".pwd = " + pwd
                + " where " + table +".id" + " = " + userId;
        try {
            stmt = con.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void CloseConnect() { //关闭资源
        try{
            if (rs!=null) rs.close();
            if (stmt!=null) stmt.close();
            if (con!=null) con.close();
        }
        catch (SQLException ex) {}
        System.exit(-1);
    }

    //判断用户类型
    public void checkUserType(String userType) {
        switch (userType) {
            case UserType.USERTYPE_STUDENT: {
                table = "学生账号密码";
                break;
            }
            case UserType.USERTYPE_TEACHER: {
                table = "教师账号";
                break;
            }
            case UserType.USERTYPE_MANAGER: {
                table = "管理员账户";
            }
        }
    }

}
