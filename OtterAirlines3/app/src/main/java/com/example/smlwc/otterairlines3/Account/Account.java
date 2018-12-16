package com.example.smlwc.otterairlines3.Account;

import java.util.Date;

public class Account {
    private String username;
    private String password;
    private boolean admin;
    private boolean validInfo;
    private Date date;

    public Account(String username, String password, boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
        validInfo = isAccountInfoValid();
        date = new Date();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isValidInfo() {
        return validInfo;
    }

    public Date getDate() {
        return date;
    }

    private boolean isAccountInfoValid() {
        // Requirements
        int minLettersUsername = 3;
        int minDigitsUsername = 1;
        int minLettersPassword = 3;
        int minDigitsPassword = 1;

        // Counts
        int letterCountUsername = 0;
        int digitCountUsername = 0;
        int letterCountPassword = 0;
        int digitCountPassword = 0;

        for (char c : username.toCharArray()) {
            if (Character.isLetter(c)) {
                letterCountUsername += 1;
            }
            if (Character.isDigit(c)) {
                digitCountUsername += 1;
            }
        }

        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                letterCountPassword += 1;
            }
            if (Character.isDigit(c)) {
                digitCountPassword += 1;
            }
        }

        return  (letterCountUsername >= minLettersUsername) &&
                (digitCountUsername >= minDigitsUsername) &&
                (letterCountPassword >= minLettersPassword) &&
                (digitCountPassword >= minDigitsPassword);
    }
}
