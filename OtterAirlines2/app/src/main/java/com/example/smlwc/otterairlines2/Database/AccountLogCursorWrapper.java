package com.example.smlwc.otterairlines2.Database;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.example.smlwc.otterairlines2.AccountLogItem;

import java.util.Date;
import java.util.UUID;

/**
 * This supplies the mechanism to get a log item from a cursor.
 */

public class AccountLogCursorWrapper extends CursorWrapper {
    public AccountLogCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public AccountLogItem getLogItem(){

        String uuidString = getString(getColumnIndex(AccountLogSchema.AccountLogTable.Cols.UUID));

        Date date = new Date(getLong(getColumnIndex(AccountLogSchema.AccountLogTable.Cols.DATE)));

        String username = getString(getColumnIndex(AccountLogSchema.AccountLogTable.Cols.USERNAME));
        String password = getString(getColumnIndex(AccountLogSchema.AccountLogTable.Cols.PASSWORD));
        boolean admin = getLong(getColumnIndex(AccountLogSchema.AccountLogTable.Cols.ADMIN)) == 1 ? true : false;

        AccountLogItem log = new AccountLogItem(uuidString, username, password, admin);
        log.setDate(date);

        return log;
    }
}
