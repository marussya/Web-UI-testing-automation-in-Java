package ru.gb.qa.lesson06;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class TShirtsPage extends BaseView {
    public TShirtsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[.='Size']/ancestor::div[@class='layered_filter']//a")
    private List<WebElement> sizesList;

    @Step("Выбрать размер одежды в фильтре")
    public TShirtsPage selectSizeInFilters(String size) {
        sizesList.stream().filter(s -> s.getText().contains(size)).findFirst().get().click();
        return this;
    }

    @FindBy(xpath = "//ul[contains(@class, 'product_list')]/li")
    private List<WebElement> productsList;

    @FindBy(xpath = "//span[.='Add to cart']")
    private WebElement addToCartButton;

    @Step("Навести курсор мыши на продукт и добавить его в корзину")
    public SuccessBlock hoverAndClickAddToCartProductByName(String productName) {
        actions.moveToElement(productsList.stream().filter(s -> s.getText().contains(productName)).findFirst().get())
                .build().perform();
        addToCartButton.click();
        return new SuccessBlock(driver);
    }

}
