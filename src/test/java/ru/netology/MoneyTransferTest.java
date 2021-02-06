package ru.netology;

import com.codeborne.selenide.Condition;
import lombok.val;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @BeforeEach
    void openLink() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldTransferMoneyFromSecondToFirst() {
        val LoginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = LoginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val firstCardBalanceBefore = DashboardPage.getFirstCardBalance();
        val secondCardBalanceBefore = DashboardPage.getSecondCardBalance();
        Random random = new Random();
        val cash = random.nextInt(10000);
        val transferPage = DashboardPage.clickToReplenishFirstCard();
        val transferInfo = DataHelper.getSecondCardInfo();
        transferPage.validTransferToFirst(transferInfo, cash);
        val firstCardBalanceAfter = DataHelper.getBalanceAfterReplenishment(firstCardBalanceBefore, cash);
        val secondCardBalanceAfter = DataHelper.getBalanceAfterWritingOff(secondCardBalanceBefore, cash);
        val firstCardCurrentBalance = DashboardPage.getFirstCardBalance();
        val secondCardCurrentBalance = DashboardPage.getSecondCardBalance();
        assertEquals(firstCardBalanceAfter, firstCardCurrentBalance);
        assertEquals(secondCardBalanceAfter, secondCardCurrentBalance);
    }
}
