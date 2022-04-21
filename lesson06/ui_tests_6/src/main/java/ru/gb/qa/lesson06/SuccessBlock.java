package ru.gb.qa.lesson06;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

public class SuccessBlock extends BaseView {
    public SuccessBlock(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[@class='ajax_block_products_total']")
    private WebElement sumElement;

    private final static String successIconXpathLocator = "//i[@class='icon-ok']";

    @FindBy(xpath = successIconXpathLocator)
    private WebElement successIcon;

    @Step("Проверить корректность суммы заказа")
    public void checkSuccessWithSum(String summ) {
        webDriverWait.until(ExpectedConditions.visibilityOf(successIcon));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(successIconXpathLocator)));

        Assertions.assertAll(
                () -> assertThat(driver.findElement(By.xpath("//span[@class='ajax_block_products_total']")), hasText(summ)),
                () -> assertThat(driver.findElement(By.xpath("//i[@class='icon-ok']")), isDisplayed())
        );
    }
}
