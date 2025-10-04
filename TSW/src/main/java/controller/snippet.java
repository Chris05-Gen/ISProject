package controller;

import java.security.MessageDigest;

public class snippet {
    public static void main(String[] args) {
        String password = "pwd456";

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes("UTF-8"));

            StringBuilder hex = new StringBuilder();
            for (byte b : hash) {
                hex.append(String.format("%02x", b));
            }

            System.out.println("Password hashata: " + hex.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

