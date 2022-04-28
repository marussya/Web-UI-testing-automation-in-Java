package com.geekbrains.lesson8;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    private SelenideElement emailField = $(By.id("email"));

    private SelenideElement submitButton = $(By.id("SubmitLogin"));

    private SelenideElement passwordField = $(By.id("passwd"));

    @Step("Логин")
    public MyAccountPage login(String email, String pass) {
        emailField.sendKeys(email);
        passwordField.sendKeys(pass);
        submitButton.click();
        return page(MyAccountPage.class);
    }
}
