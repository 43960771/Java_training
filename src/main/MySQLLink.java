package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//数据库连接
public class MySQLLink {

    public static Connection getConnection() {
        Connection conn = null;
        //加载驱动
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //尝试连接数据库
            try {
                conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library?useSSL=true&characterEncoding=utf-8&user=root&password=20001004");
                System.out.println("连接数据库成功");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return conn;
    }
}
