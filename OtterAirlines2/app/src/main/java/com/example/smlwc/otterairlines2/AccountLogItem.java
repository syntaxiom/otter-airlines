package com.example.smlwc.otterairlines2;

import java.util.Date;
import java.util.UUID;

public class AccountLogItem {

    private UUID logID;
    private String username;
    private String password;
    private boolean admin;
    private Date date;

    public AccountLogItem(String uuidString, String username, String password, boolean admin) {
        this.logID = UUID.fromString(uuidString);
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public AccountLogItem(String username, String password) {
        logID = UUID.randomUUID();
        this.username = username;
        this.password = password;
        admin = false;
        date = new Date();
    }

    public AccountLogItem(String username, String password, boolean admin) {
        logID = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.admin = admin;
        date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public UUID getLogID() {
        return logID;
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

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
