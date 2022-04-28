package com.geekbrains.lesson8;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class TShirtsPage {
    private ElementsCollection sizesList = $$(By.xpath("//span[.='Size']/ancestor::div[@class='layered_filter']//a"));


    @Step("Выбрать размер одежды в фильтре")
    public TShirtsPage selectSizeInFilters(String size) {
        sizesList.findBy(Condition.text(size)).click();
        return this;
    }

    private ElementsCollection productsList = $$(By.xpath("//ul[contains(@class, 'product_list')]/li"));

    private SelenideElement addToCartButton = $(By.xpath("//span[.='Add to cart']"));

    @Step("Навести курсор мыши на продукт и добавить его в корзину")
    public SuccessBlock hoverAndClickAddToCartProductByName(String productName) {
        productsList.findBy(Condition.text(productName)).hover();
        addToCartButton.click();
        return page(SuccessBlock.class);
    }
}
