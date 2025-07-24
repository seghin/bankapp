package me.rob.bankapp.utils;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class PasswordUtils {

    public static String hash(String input) {

        try {

            var hashedInput = new StringBuilder();

            var md = MessageDigest.getInstance("SHA-256");

            byte[] hashBytes = md.digest(input.getBytes());
            
            for (byte b : hashBytes) hashedInput.append(String.format("%02x", b));

            return hashedInput.toString();

        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    public static boolean isValid(String password) {

        if (password == null || password.isBlank()) throw new IllegalArgumentException("Password cannot be empty.");

        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-\\[\\]{};':\"\\\\|,.<>/?]).{8,}$";

        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(password);

        return matcher.matches();
    }

    public static boolean verify(String input, String password) { 
        
        if (input == null || input.isBlank()) throw new IllegalArgumentException("Password cannot be empty.");
        
        return hash(input).equals(password); 
    }
}