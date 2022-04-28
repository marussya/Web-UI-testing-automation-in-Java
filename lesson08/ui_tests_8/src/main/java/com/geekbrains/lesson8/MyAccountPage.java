package com.geekbrains.lesson8;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class MyAccountPage {
    private SelenideElement womenButton = $(By.xpath("//a[@title='Women']"));

    @Step("Навести кнопку мыши на раздел 'Женщины'")
    public WomenSuggestBlock hoverWomenButton() {
        womenButton.hover();
        return page(WomenSuggestBlock.class);
    }
}
