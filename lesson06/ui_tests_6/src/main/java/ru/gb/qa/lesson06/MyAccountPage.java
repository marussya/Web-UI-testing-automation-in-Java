package ru.gb.qa.lesson06;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BaseView {
    public MyAccountPage(WebDriver driver) {
        super(driver);
        womenSuggestBlock = new WomenSuggestBlock(driver);
    }

    @FindBy(xpath = "//a[@title='Women']")
    private WebElement womenButton;

    @Step("Навести кнопку мыши на раздел 'Женщины'")
    public WomenSuggestBlock hoverWomenButton() {
        actions.moveToElement(womenButton).build().perform();
        return womenSuggestBlock;
    }

    public WomenSuggestBlock womenSuggestBlock;
}
