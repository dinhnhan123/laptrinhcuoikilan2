package DBConnect;

import java.sql.*;

public class DBConnect {
    String url = "jdbc:mysql://localhost:3306/shop";
    String user = "root";
    String password = "";

    Connection conn;
    static DBConnect install;


    public   DBConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password); // mở kết nối với sql
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConnect getInstall() {
        if (install == null) install = new DBConnect();
        return install;
    }

    public Statement get() {
        if (conn == null) return null;
        try {
            return conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            return null;
        }

    }

    public static void main(String[] args) {
//        DBConnect.getInstall().get();

        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("select *from products");
                rs.last();
                System.out.println(rs.getRow());
                rs.absolute(3);
                System.out.println(rs.getInt(1)+"---"+rs.getString(2));
//                rs.beforeFirst();
//                while (rs.next()){
//                    System.out.println(rs.getString(1)+"---"+rs.getString(2));
//                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("không có kết nối ");
        }
    }
}


