package com.example.smlwc.otterairlines3.Account;

import java.util.HashMap;

public class AccountManager {
    private static HashMap<String, Account> accounts = new HashMap<String, Account>(){{
        put("alice5", new Account("alice5", "csumb100", false));
        put("brian77", new Account("brian77", "123ABC", false));
        put("chris21", new Account("chris21", "CHRIS21", false));
        put("admin2", new Account("admin2", "admin2", true));
    }};

    private static Account currentAccount;

    public static void addAccount(String username, Account account) {
        accounts.put(username, account);
    }

    public static Account getAccount(String username) {
        return accounts.get(username);
    }

    public static Account getCurrentAccount() {
        return currentAccount;
    }

    public static void setCurrentAccount(Account currentAccount) {
        AccountManager.currentAccount = currentAccount;
    }
}
