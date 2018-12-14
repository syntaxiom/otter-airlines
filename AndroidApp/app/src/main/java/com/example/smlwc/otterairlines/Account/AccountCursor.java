package com.example.smlwc.otterairlines.Account;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

public class AccountCursor extends CursorWrapper {

    public AccountCursor(Cursor cursor) {
        super(cursor);
    }

    public Account getAccount() {
        UUID accountUUID = UUID.fromString(getString(getColumnIndex(AccountTable.Cols.UUID)));
        String accountUsername = getString(getColumnIndex(AccountTable.Cols.USERNAME));
        String accountPassword = getString(getColumnIndex(AccountTable.Cols.PASSWORD));
        Date accountDate = new Date(getLong(getColumnIndex(AccountTable.Cols.DATE)));

        Account account = new Account(accountUUID, accountUsername, accountPassword, accountDate);

        return account;
    }
}
