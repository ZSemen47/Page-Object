package ru.netology.data;

import java.util.Random;

public class DataGenerator {
    public static int generateRandomCash() {
        Random random = new Random();
        int cash = random.nextInt(10000);
        return cash;
    }

    public static int generateInvalidCash() {
        Random random = new Random();
        int min = 11000;
        int max = 20000;
        int diff = max - min;
        int invalidCash = random.nextInt(diff);
        return invalidCash;
    }
}