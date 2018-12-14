package com.example.smlwc.otterairlines.Account;

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
}
