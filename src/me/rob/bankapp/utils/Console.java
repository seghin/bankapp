package me.rob.bankapp.utils;

import java.math.BigDecimal;

import java.util.Scanner;
import java.util.function.Supplier;
import java.util.InputMismatchException;

public final class Console {

    private static final Scanner scanner = new Scanner(System.in);

    private static void delay(int ms) {

        if (ms < 0) throw new IllegalArgumentException("Delay duration must be non-negative.");    

        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public static void prompt(String message, boolean endl) {

        if (message == null || message.isBlank()) return;

        for (int i = 0; i < message.length(); i++) { 
                
            System.out.print(message.charAt(i));
                
            delay(75);
        }

        System.out.print(endl ? "\n" : "");
    }

    public static <T> T getInput(String message, boolean endl, Supplier<T> supplier) {

        while (true) {

            prompt(message, endl);

            try {

                T input = supplier.get();

                scanner.nextLine(); 

                return input;

            } catch (InputMismatchException ignored) {

                prompt("Invalid number. Please try again.\n", true);

                scanner.nextLine();
            }
        }
    }

    public static String getString(String message, boolean endl) {

        prompt(message, endl);

        return scanner.nextLine();
    }

    public static int getInteger(String message, boolean endl) { return getInput(message, endl, scanner::nextInt); }

    public static BigDecimal getBigDecimal(String message, boolean endl) { return getInput(message, endl, scanner::nextBigDecimal); }
}