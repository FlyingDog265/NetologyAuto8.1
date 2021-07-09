package ru.netology.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.helpers.DatabaseHelper;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.LoginPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.helpers.AuthInfoHelper.*;
import static ru.netology.helpers.DatabaseHelper.getVerificationCode;
import static ru.netology.helpers.VerificationCodeHelper.getInvalidVerificationCode;

public class AuthTest {

    @BeforeEach
    public void openPage() {
        open("http://localhost:9999");
    }

    @AfterAll
    public static void cleanTables() throws SQLException {
        DatabaseHelper.clearTables();
    }

    @Test
    void shouldValidData() {
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = new DashboardPage();
        var authInfo = getValidAuthInfo();
        var verificationPage = loginPage.validAuth(authInfo);
        var codeVerify = getVerificationCode();
        verificationPage.validVerify(codeVerify);
        dashboardPage.checkIfVisible();
    }

    @Test
    void shouldNotValidLogin() {
        LoginPage loginPage = new LoginPage();
        var authInfo = getInvalidLogin();
        loginPage.invalidAuth(authInfo);
    }

    @Test
    void shouldNotValidPassword() {
        LoginPage loginPage = new LoginPage();
        var authInfo = getInvalidPassword();
        loginPage.invalidAuth(authInfo);
    }

    @Test
    void shouldNotValidAuthCode() {
        LoginPage loginPage = new LoginPage();
        var authInfo = getValidAuthInfo();
        var verificationPage = loginPage.validAuth(authInfo);
        verificationPage.invalidVerify(getInvalidVerificationCode());
    }

    @Test
    void shouldBlockWhenThreeInvalidPasswords() {
        LoginPage loginPage = new LoginPage();
        var authInfo = getInvalidPassword();
        loginPage.invalidAuth(authInfo);
        var invalidPassword = invalidPassword();
        loginPage.sendInvalidPasswordThirdTime(invalidPassword);
    }
}
