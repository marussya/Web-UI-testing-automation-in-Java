package ru.gb.qa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AfishaTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    private final static String AFISHA_BASE_URL = "https://afisha.ru";

    @BeforeAll
    static void registerDrivwe() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get(AFISHA_BASE_URL);
    }

    @Test
    void likeRandomFilmTest() {
        List<WebElement> filmsList = driver.findElements(By.xpath("//a[contains(@href, 'movie') and @data-test='LINK ITEM-NAME ITEM-URL']/h2"));
        driver.findElement(By.xpath("//h2[.='Сегодня в кино']/ancestor::div[@data-test='PAGE-SECTION-HEADER']//button[@data-test='BUTTON CONTROL-NEXT']")).click();
        driver.findElement(By.xpath("//h2[.='Сегодня в кино']/ancestor::div[@data-test='PAGE-SECTION-HEADER']//button[@data-test='BUTTON CONTROL-NEXT']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[.='ОК']")));
        driver.findElement(By.xpath("//button[.='ОК']")).click();
        filmsList.stream().filter(f -> f.getText().contains("Анчартед")).findFirst().get().click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@data-test='PAGE-SECTION TITLE-SECTION']//button[@data-test='BUTTON FAVORITE']")));

        Boolean loginFrameAppeared = false;

        while (!loginFrameAppeared) {
            driver.findElement(By.xpath("//section[@data-test='PAGE-SECTION TITLE-SECTION']//button[@data-test='BUTTON FAVORITE']")).click();
            if (driver.findElements(By.xpath("//iframe[contains(@src,'id.rambler.ru/login-20/')]")).size() > 0) {
                loginFrameAppeared = true;
            }
        }

        driver.findElement(By.xpath("//section[@data-test='PAGE-SECTION TITLE-SECTION']//button[@data-test='BUTTON FAVORITE']")).click();

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'id.rambler.ru/login-20/')]")));

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
        Assertions.assertEquals(driver.findElement(By.id("login")).getText(), true);

        String a = "";
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
