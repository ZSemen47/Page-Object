package ru.netology.test;

import lombok.val;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.data.DataHelper;
import ru.netology.pageObjects.DashboardPage;
import ru.netology.pageObjects.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @BeforeEach
    void openLink() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldTransferMoneyFromSecondToFirst() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val firstCardBalanceBefore = DashboardPage.getFirstCardBalance();
        val secondCardBalanceBefore = DashboardPage.getSecondCardBalance();
        val randomCash = DataGenerator.generateRandomCash();
        val transferPage = DashboardPage.clickToReplenishFirstCard();
        val transferInfo = DataHelper.getSecondCardInfo();
        transferPage.validTransferToFirst(transferInfo, randomCash);
        val firstCardBalanceAfter = DataHelper.getBalanceAfterReplenishment(firstCardBalanceBefore, randomCash);
        val secondCardBalanceAfter = DataHelper.getBalanceAfterWritingOff(secondCardBalanceBefore, randomCash);
        val firstCardCurrentBalance = DashboardPage.getFirstCardBalance();
        val secondCardCurrentBalance = DashboardPage.getSecondCardBalance();
        assertEquals(firstCardBalanceAfter, firstCardCurrentBalance);
        assertEquals(secondCardBalanceAfter, secondCardCurrentBalance);
    }

    @Test
    void validTransferFromFirstToSecond() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val firstCardBalanceBefore = DashboardPage.getFirstCardBalance();
        val secondCardBalanceBefore = DashboardPage.getSecondCardBalance();
        val randomCash = DataGenerator.generateRandomCash();
        val transferPage = DashboardPage.clickToReplenishSecondCard();
        val transferInfo = DataHelper.getFirstCardInfo();
        transferPage.validTransferToSecond(transferInfo, randomCash);
        val firstCardBalanceAfter = DataHelper.getBalanceAfterWritingOff(firstCardBalanceBefore, randomCash);
        val secondCardBalanceAfter = DataHelper.getBalanceAfterReplenishment(secondCardBalanceBefore, randomCash);
        val firstCardCurrentBalance = DashboardPage.getFirstCardBalance();
        val secondCardCurrentBalance = DashboardPage.getSecondCardBalance();
        assertEquals(firstCardBalanceAfter, firstCardCurrentBalance);
        assertEquals(secondCardBalanceAfter, secondCardCurrentBalance);
    }

    @Test
    void shouldBeErrorWhenTransferMoreThanBalance() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val randomCash = DataGenerator.generateRandomCash();
        val transferPage = DashboardPage.clickToReplenishFirstCard();
        val transferInfo = DataHelper.getSecondCardInfo();
        transferPage.validTransferToFirst(transferInfo, randomCash);
        transferPage.notEnoughBalance();
    }
}