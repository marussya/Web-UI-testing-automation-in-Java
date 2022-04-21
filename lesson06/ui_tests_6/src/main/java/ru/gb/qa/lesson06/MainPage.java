package ru.gb.qa.lesson06;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MainPage extends BaseView {
    @FindBy(xpath = "//a[@class='login']")
    public WebElement signInButton;

    @Step("Клик на кнопку авторизации")
    public LoginPage clickSignInButton() {
        signInButton.click();
        //assertThat(driver.findElement(By.xpath("//span[@class='ajax_block_products_total']")), hasText("16$"));
        return new LoginPage(driver);
    }

    public MainPage(WebDriver driver) {
        super(driver);
    }
}
