package com.example.cinerate.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {
    public static String hashPassword(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();

            for (byte b : hashBytes){
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
    public static boolean verifyPassword(String providedPassword, String storedHashedPassword) {
        String hashedProvidedPassword = hashPassword(providedPassword);
        return hashedProvidedPassword.equals(storedHashedPassword);
    }
}
