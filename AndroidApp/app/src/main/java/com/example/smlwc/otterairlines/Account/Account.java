package com.example.smlwc.otterairlines.Account;

import android.content.ContentValues;

import java.util.Date;
import java.util.UUID;

public class Account {
    // Instance fields
    private UUID accountId;
    private String username;
    private String password;
    private Date dateCreated;
    private boolean admin;
    private boolean valid;

    public Account(String username, String password) {
        accountId = UUID.randomUUID();
        this.username = username;
        this.password = password;
        dateCreated = new Date();
        admin = false;
        setValid();
    }

    public Account(String username, String password, boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    //    public UUID getAccountId() {
//        return accountId;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getPassword() { return password; }
//
//    public Date getDateCreated() {
//        return dateCreated;
//    }


    public boolean isAdmin() { return admin; }

    public boolean isValid() { return valid; }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(AccountTable.Cols.UUID, accountId.toString());
        cv.put(AccountTable.Cols.USERNAME, username);
        cv.put(AccountTable.Cols.PASSWORD, password);
        cv.put(AccountTable.Cols.DATE, dateCreated.toString());
        return cv;
    }

    private void setValid() {
        // Requirements
        int letters_username_min = 3;
        int numbers_username_min = 1;
        int letters_password_min = 3;
        int numbers_password_min = 1;

        // Counters
        int usernameLetterCount = 0;
        int usernameNumberCount = 0;
        int passwordLetterCount = 0;
        int passwordNumberCount = 0;

        // Check each character in username
        for (char character : username.toCharArray()) {
            // Increment letter count if applicable
            if (Character.isLetter(character)) {
                usernameLetterCount++;
            }

            // Increment number count if applicable
            if (Character.isDigit(character)) {
                usernameNumberCount++;
            }
        }

        // Check each character in password
        for (char character : password.toCharArray()) {
            // Increment letter count if applicable
            if (Character.isLetter(character)) {
                passwordLetterCount++;
            }

            // Increment number count if applicable
            if (Character.isDigit(character)) {
                passwordNumberCount++;
            }
        }

        valid = (usernameLetterCount >= letters_username_min) &&
                (usernameNumberCount >= numbers_username_min) &&
                (passwordLetterCount >= letters_password_min) &&
                (passwordNumberCount >= numbers_password_min);
    }
}
