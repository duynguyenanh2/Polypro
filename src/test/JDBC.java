/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class JDBC {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String db_url = "jdbc:mysql://localhost:3306/polypro";
    private static String username = "jdbc";
    private static String password = "123456";

    static {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static PreparedStatement preparedStatement(String sql, Object... args) throws SQLException {
        Connection conn = DriverManager.getConnection(db_url, username, password);
        PreparedStatement ps = null;
        if (sql.trim().startsWith("{")) {
            ps = conn.prepareCall(sql);
        } else {
            ps = conn.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i + 1, args[i]);
        }
        return ps;
    }

    public static void executeUpdate(String sql, Object... args) {
        try {
            PreparedStatement st = preparedStatement(sql, args);
            try {
                st.executeUpdate();
            } finally {
                st.getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            PreparedStatement ps = preparedStatement(sql, args);
            return ps.executeQuery();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
