package ru.netology.data;

import java.util.Random;

public class DataGenerator {
    public static int generateRandomCash() {
        Random random = new Random();
        int cash = random.nextInt(10000);
        return cash;
    }
}