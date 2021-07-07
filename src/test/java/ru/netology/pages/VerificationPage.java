package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.element;

public class VerificationPage {
    Duration duration = Duration.ofSeconds(15000);
    private final SelenideElement codeField = element("[data-test-id=code] input");
    private final SelenideElement verifyButton = element("[data-test-id=action-verify]");
    private final SelenideElement errorNotification = element("[data-test-id=error-notification]");

    public VerificationPage() {
        codeField.shouldBe(visible, duration);
    }

    public void invalidVerify(String verificationCode) {
        codeField.setValue(verificationCode);
        verifyButton.click();
        errorNotification.shouldBe(visible);
    }

    public void validVerify(String verificationCode) {
        codeField.setValue(verificationCode);
        verifyButton.click();
        new DashboardPage();
    }
}
