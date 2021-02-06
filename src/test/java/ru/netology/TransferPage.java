package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement heading = $(Selectors.byText("Пополнение карты"));
    private static SelenideElement amount = $("[data-test-id='amount'] .input__control");
    private static SelenideElement from = $("[data-test-id='from'] .input__control");
    private static SelenideElement actionTransfer = $("[data-test-id='action-transfer'] .button__text");

    public TransferPage() {
        amount.shouldBe(Condition.visible);
    }

    public DashboardPage validTransferToFirst(DataHelper.CardInfo cardInfo, int cash) {
        amount.setValue(String.valueOf(cash));
        from.setValue(cardInfo.getCardNumber());
        actionTransfer.click();
        return new DashboardPage();
    }
}
