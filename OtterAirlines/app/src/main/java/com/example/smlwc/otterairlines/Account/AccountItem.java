package com.example.smlwc.otterairlines.Account;

import android.content.ContentValues;

import java.util.Date;
import java.util.UUID;

public class AccountItem {
    private UUID accountUUID;
    private String username;
    private String password;
    private Date date;

    public AccountItem(String username, String password) {
        accountUUID = UUID.randomUUID();
        this.username = username;
        this.password = password;
        date = new Date();
    }

    public AccountItem(UUID accountUUID, String username, String password, Date date) {
        this.accountUUID = accountUUID;
        this.username = username;
        this.password = password;
        this.date = date;
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(AccountTable.Cols.UUID, accountUUID.toString());
        cv.put(AccountTable.Cols.USERNAME, username);
        cv.put(AccountTable.Cols.PASSWORD, password);
        cv.put(AccountTable.Cols.DATE, date.getTime());
        return cv;
    }

    public boolean isValid(String password) {
        return this.password == password;
    }
}
