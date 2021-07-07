package ru.netology.helpers;

public class AuthInfoHelper {
    private final String login;
    private final String password;

    public AuthInfoHelper(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public static AuthInfoHelper getValidAuthInfo() {
        return new AuthInfoHelper("vasya", "qwerty123");
    }

    public static AuthInfoHelper getInvalidLogin() {
        return new AuthInfoHelper("notvalid", "qwerty123");
    }

    public static AuthInfoHelper getInvalidPassword() {
        return new AuthInfoHelper("vasya", "qqqqqqqq");
    }

    public static String invalidPassword() {
        return "aaaaaaa";
    }

    public String toString() {
        return "DatabaseHelper.AuthInfo(login=" + this.getLogin() + ", password=" + this.getPassword() + ")";
    }
}
