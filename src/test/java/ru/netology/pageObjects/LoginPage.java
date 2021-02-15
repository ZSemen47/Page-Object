package ru.netology.pageObjects;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement paragraph = $(Selectors.byText("Мы гарантируем безопасность ваших " +
            "данных"));
    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        $("[data-test-id='login'] .input__control").setValue(info.getLogin());
        $("[data-test-id='password'] .input__control").setValue(info.getPassword());
        $("[data-test-id='action-login'] .button__text").click();
        return new VerificationPage();
    }
}