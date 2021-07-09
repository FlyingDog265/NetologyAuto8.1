package ru.netology.helpers;

import java.sql.*;

public class DatabaseHelper {
    private final static String url = "jdbc:mysql://localhost:3306/app";
    private final static String user = "app";
    private final static String password = "pass";

    private DatabaseHelper() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/app", "app", "pass");
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

    public static void clearTables() throws SQLException {
        String deleteCards = "DELETE FROM cards; ";
        String deleteAuthCodes = "DELETE FROM auth_codes; ";
        String deleteUsers = "DELETE FROM users; ";
        try (var conn = getConnection();
             var deleteCardsStmt = conn.createStatement();
             var deleteAuthCodesStmt = conn.createStatement();
             var deleteUsersStmt = conn.createStatement()
        ) {
            deleteCardsStmt.executeUpdate(deleteCards);
            deleteAuthCodesStmt.executeUpdate(deleteAuthCodes);
            deleteUsersStmt.executeUpdate(deleteUsers);
        }
    }
}