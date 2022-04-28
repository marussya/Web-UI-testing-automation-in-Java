package com.geekbrains.lesson8;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class WomenSuggestBlock {
    private SelenideElement tShirtsButton = $(By.xpath("//ul[contains(@class, 'sf-menu')]/li/a[@title='T-shirts']"));

    @Step("Кликнуть на кнопку 'Рубашки'")
    public TShirtsPage tShirtsButtonClick() {
        tShirtsButton.click();
        return page(TShirtsPage.class);
    }
}
