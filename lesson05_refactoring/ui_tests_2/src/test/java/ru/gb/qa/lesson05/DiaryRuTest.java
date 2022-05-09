package ru.gb.qa.lesson05;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class DiaryRuTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    private final static String DIARY_BASE_URL = "https://diary.ru";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get(DIARY_BASE_URL);
    }

    @BeforeEach
    void authCookieSetup() {
        Cookie authCookie = new Cookie("_identity_", "338c818accc863fc3d86cdcde615ae438e05f9fc4c9c17bc5f140b" +
                "73ac1326fda%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A54%3A%22%5B%223565696%22%2C%22WYa" +
                "2l_RjvVXWcpwdosPQoqHRoR3euQYJ%22%2C2592000%5D%22%3B%7D");
        driver.manage().addCookie(authCookie);
        driver.navigate().refresh();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Новая запись']")));
    }

    @Test
    @DisplayName("Тест проверяет получение заголовка нового поста")
    void createNewPostTest() {
        driver.findElement(By.xpath("//a[@title='Новая запись']")).click();
        String postName = "Post" + new Random().nextInt(1000);
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postTitle")));

        driver.findElement(By.id("postTitle")).sendKeys(postName);
        driver.switchTo().frame(driver.findElement(By.id("message_ifr")));
        driver.findElement(By.id("tinymce")).sendKeys("ourPostText");
        driver.switchTo().parentFrame();
        driver.findElement(By.id("rewrite")).click();

        List<WebElement> posts = driver.findElements(By.xpath("//a[@class='title']"));
        posts.stream().filter(p -> p.getText().contains(postName)).findFirst().get().click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='post-header']//a[contains(text(), postName)]")));

        Assertions.assertEquals(driver.findElement(By.xpath("//div[@class='post-header']//a[contains(text(), postName)]")).getText().contains(postName), true);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

}
