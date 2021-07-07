package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.helpers.AuthInfoHelper;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginField = $("[data-test-id=login] input");
    private final SelenideElement passwordField = $("[data-test-id=password] input");
    private final SelenideElement loginButton = $("[data-test-id=action-login]");
    private final SelenideElement errorMessage = $("[data-test-id=error-notification]");

    public void setValue(AuthInfoHelper info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
    }

    public VerificationPage validAuth(AuthInfoHelper info) {
        setValue(info);
        return new VerificationPage();
    }

    public void invalidAuth(AuthInfoHelper info) {
        setValue(info);
        errorMessage.shouldBe(visible);
    }

    public void sendInvalidPassword(String password) {
        passwordField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        passwordField.doubleClick().sendKeys(Keys.DELETE);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public void sendInvalidPasswordThirdTime(String password) {
        sendInvalidPassword(password);
        sendInvalidPassword(password);
        loginButton.shouldBe(disabled);
    }
}
