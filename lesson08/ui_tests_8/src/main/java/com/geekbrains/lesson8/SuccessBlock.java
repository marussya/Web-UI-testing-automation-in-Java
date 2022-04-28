package com.geekbrains.lesson8;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SuccessBlock {
    private SelenideElement sumElement = $(By.xpath("//span[@class='ajax_block_products_total']"));

    private SelenideElement susseccIcon = $(By.xpath("//i[@class='icon-ok']"));

    @Step("Проверить корректность суммы заказа")
    public void checkSuccessWithSum(String summ) {
        Assertions.assertAll(
                () -> sumElement.shouldHave(Condition.text(summ)),
                () -> susseccIcon.shouldBe(Condition.visible)
        );
    }
}
