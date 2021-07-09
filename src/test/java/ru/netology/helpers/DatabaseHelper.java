package ru.netology.helpers;

import java.sql.*;

public class DatabaseHelper {
    private final static String url = "jdbc:mysql://localhost:3306/app";
    private final static String user = "app";
    private final static String password = "pass";

    private DatabaseHelper() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static String getUserId() {
        String getUserId = "SELECT id FROM users WHERE login = 'vasya';";
        try (
                Statement createStmt = getConnection().createStatement()
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
                PreparedStatement prepareStmt = getConnection().prepareStatement(requestCode)
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
        try (var connect = getConnection();
             var deleteCardsStmt = connect.createStatement();
             var deleteAuthCodesStmt = connect.createStatement();
             var deleteUsersStmt = connect.createStatement()
        ) {
            deleteCardsStmt.executeUpdate(deleteCards);
            deleteAuthCodesStmt.executeUpdate(deleteAuthCodes);
            deleteUsersStmt.executeUpdate(deleteUsers);
        }
    }
}