package ru.netology.helpers;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.*;

public class DatabaseHelper {
    private final static String url = "jdbc:mysql://localhost:3306/app";
    private final static String user = "app";
    private final static String password = "pass";

    private DatabaseHelper() {
    }

    public static String getUserId() {
        String getUserId = "SELECT id FROM users WHERE login = 'vasya';";
        try (
                Connection connect = DriverManager.getConnection(url, user, password);
                Statement createStmt = connect.createStatement()
        ) {
            try (ResultSet resultSet = createStmt.executeQuery(getUserId)) {
                if (resultSet.next()) {
                    return resultSet.getString(1);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    public static String getVerificationCode() {
        String requestCode = "SELECT code FROM auth_codes WHERE user_id = ? ORDER BY created DESC LIMIT 1;";
        try (
                Connection connect = DriverManager.getConnection(url, user, password
                );
                PreparedStatement prepareStmt = connect.prepareStatement(requestCode)
        ) {
            prepareStmt.setString(1, getUserId());
            try (ResultSet resultSet = prepareStmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString(1);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    public static void clearAuthCodesTable() {
        String deleteAuthCodeSQL = " DELETE FROM auth_codes;";
        QueryRunner runner = new QueryRunner();
        try {
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                runner.execute(conn, deleteAuthCodeSQL, new ScalarHandler<>());
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}