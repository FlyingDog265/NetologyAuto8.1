package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.element;
import static java.time.Duration.ofSeconds;

public class DashboardPage {
    Duration duration = ofSeconds(15000);
    SelenideElement heading = element("[data-test-id=dashboard]");

    public void checkIfVisible() {
        heading.shouldBe(visible, duration);
    }
}

