package com.qucai.sample.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
    private static String driver = "com.mysql.jdbc.Driver";
//    private static String url = "jdbc:mysql://39.97.176.13:3306/gognzi?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull&useSSL=true";
//    private static String url = "jdbc:mysql://localhost:3306/sample?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull&useSSL=true";
    private static String url = "jdbc:mysql://47.93.14.1:3306/sample?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull&useSSL=true";
    private static String userName = "root";
//    private static String userPassword = "Gf2021";
    private static String userPassword = "Spear19830805";

    public static Connection getConnection() {
        Connection conn = null;//定义一个conn变量，不能定义为static的类型的   否则全局共享
        
        try {

            Class.forName(driver);
            try {
                conn = DriverManager.getConnection(url, userName, userPassword);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
//                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return conn;
    }

    public static void closeDB(Connection conn, PreparedStatement ptmt,
                               ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (ptmt != null) {
            try {
                ptmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}