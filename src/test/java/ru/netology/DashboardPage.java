package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $(Selectors.byText("Ваши карты"));
    private static SelenideElement actionDepositOne = $$("[data-test-id=action-deposit]").first();
    private static SelenideElement actionDepositTwo = $$("[data-test-id=action-deposit]").last();
    private static ElementsCollection cards = $$(".list__item");
    private static String balanceStart = "баланс: ";
    private static String balanceFinish = " р.";

    public DashboardPage() {
        actionDepositOne.shouldBe(Condition.visible);
    }

    public static TransferPage clickToReplenishFirstCard() {
        actionDepositOne.click();
        return new TransferPage();
    }

    public static TransferPage clickToReplenishSecondCard() {
        actionDepositOne.click();
        return new TransferPage();
    }

    public static int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public static int getSecondCardBalance() {
        val text = cards.last().text();
        return extractBalance(text);
    }

    private static int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
