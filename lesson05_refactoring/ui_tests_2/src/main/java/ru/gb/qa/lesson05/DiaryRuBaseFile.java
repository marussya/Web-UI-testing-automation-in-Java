package ru.gb.qa.lesson05;

import io.github.bonigarcia.wdm.WebDriverManager;
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

public class DiaryRuBaseFile {
    public static void main(String[] args) throws InterruptedException {
        //регистрируем вебдрайвер менеджер
        WebDriverManager.chromedriver().setup();
        //создаем экземпляр драйвера
        WebDriver driver = new ChromeDriver();
        //переходим на страницу
        driver.get("https://diary.ru/");
        //1 способ: настраиваем ожидание для прогрузки страницы
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

/*
//процедура авторизации
        //Регистрируем экземпляр класса вебэлемент для дальнейшего к нему обращения
        WebElement loginButton = driver.findElement(By.xpath("//a[.='Вход']"));
        loginButton.click();
        //более упрощенный вариант который
        // используется если не нужно сохранять элемент, чтобы он хранился в переменной
        driver.findElement(By.xpath("//a[.='Вход']")).click();
        //открывается страница авторизации
        //если нужна цель именно авторизоваться, то лучше сразу перейти на эту страницу

        //ищем поле логина
        driver.findElement(By.id("loginform-username")).sendKeys("ferretester");
        driver.findElement(By.id("loginform-password")).sendKeys("3IorE0wSa0");
        Thread.sleep(5000);
        //обход капчи (легальный)
        //переключерие внутрь айфрейма и клик по кнопке капчи
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title=\"reCAPTCHA\"]")));
        driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']")).click();
        Thread.sleep(5000);
        //Обратное переключение на основную страницу и клик на кнопку войти
        driver.switchTo().parentFrame();
        driver.findElement(By.id("login_btn")).click();
        Thread.sleep(5000);
*/
        //2 способ как обойти капчу при авторизации с помощью куки
        //создаем переменную, содержащую нужную нам куку
        Cookie authCookie = new Cookie("_identity_", "4f8583d438962c0b3012ec8f9917d398e50a9cc3418" +
                "aacf0b31d31626b4a2121a%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A52%3A%22%5" +
                "B3565696%2C%22WYa2l_RjvVXWcpwdosPQoqHRoR3euQYJ%22%2C2592000%5D%22%3B%7D");
        //подвешиваем куку в браузер
        driver.manage().addCookie(authCookie);
        //обновляем страничку
        driver.navigate().refresh();
        //после этого мы должны оказаться в авторизованной зоне
        Thread.sleep(1000);

        //Переход на страницу создания новой записи
        driver.findElement(By.xpath("//a[@title='Новая запись']")).click();
        //создаем уникальное имя записи
        String postName = "Post" + new Random().nextInt(1000);
        //делаем ожидание прогрузки элементов
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postTitle")));

        //находим заголовок
        driver.findElement(By.id("postTitle")).sendKeys(postName);
        //находим поле сообщения (это айфрейм, переключаемся в него)
        driver.switchTo().frame(driver.findElement(By.id("message_ifr")));
        // добавляем любой текст
        driver.findElement(By.id("tinymce")).sendKeys("ourPostText");
        //переключаемся обратно в родительский фрейм
        driver.switchTo().parentFrame();
        //ищем кнопку опубликовать и нажимаем на нее
        driver.findElement(By.id("rewrite")).click();
/*
        //первый способ: ищем последний пост либо передавая ранее заданное значение
        driver.findElement(By.xpath(String.format("//a[.='%s']",postName))).click();
*/
        //второй способ (более каноничный): получаем список однотипных элементов
        List<WebElement> posts = driver.findElements(By.xpath("//a[@class='title']"));
        //создаем стрим постов, фильтруем по содержимому(тексту), получаем первый в списке, кликаем на него
        posts.stream().filter(p -> p.getText().contains(postName)).findFirst().get().click();

        Thread.sleep(5000);

        driver.quit();
    }
}
