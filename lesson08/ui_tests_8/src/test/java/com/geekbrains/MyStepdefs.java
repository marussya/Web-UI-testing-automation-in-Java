package com.geekbrains;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.geekbrains.lesson8.*;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.open;

public class MyStepdefs {
    @Given("Пользователь авторизован на сайте")
    public void userAuthorized() {
        Configuration.timeout = 10000;
        //Configuration.headless = true;
        open("http://automationpractice.com/");
        new MainPage().clickSignInButton()
                .login("spartalex93@test.test", "123456");
    }

    @When("Я навожу мышь на раздел Женщины")
    public void hoverWomanButton() {
        new MyAccountPage().hoverWomenButton();
    }

    @And("^Я кликаю .* на кнопку Рубашки")
    public void clickTShirtsButton() {
        new WomenSuggestBlock().tShirtsButtonClick();
    }

    @And("Выбираю в фильтре размер S")
    public void checkSSize() {
        new TShirtsPage().selectSizeInFilters("S");
    }

    @And("Добавляю в корзину продукт Faded")
    public void addToCart() {
        new TShirtsPage().hoverAndClickAddToCartProductByName("Faded");
    }

    @Then("Проверяем корректность суммы заказа")
    public void checkSumm() {
        new SuccessBlock().checkSuccessWithSum("$16.51");
    }

    @And("Выбираю в фильтре размер {string}")
    public void chooseTheFilterSizeSize(String size) {
        new TShirtsPage().selectSizeInFilters(size);
    }

    @After(value = "@close")
    public void quitBrower() {
        Selenide.closeWebDriver();
    }
}
