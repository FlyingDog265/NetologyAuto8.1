package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.element;

public class DashboardPage {
    SelenideElement heading = element("[data-test-id=dashboard]");

    public void checkIfVisible() {
        heading.shouldBe(visible);
    }
}