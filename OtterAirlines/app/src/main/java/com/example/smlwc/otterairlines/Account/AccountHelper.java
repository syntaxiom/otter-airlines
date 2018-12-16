package com.example.smlwc.otterairlines.Account;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.smlwc.otterairlines.LoginActivity;

import java.util.ArrayList;

public class AccountHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    public static final String DATABASE_NAME = "accounts.db";
    private SQLiteDatabase db;

    public AccountHelper(Context context) { super(context, DATABASE_NAME, null, VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + AccountTable.NAME +
            "(" + " _id integer primary key autoincrement, " +
                AccountTable.Cols.UUID + "," +
                AccountTable.Cols.USERNAME + "," +
                AccountTable.Cols.PASSWORD + "," +
                AccountTable.Cols.DATE +
            ")");

        // Make a bunch of default accounts
        ArrayList<AccountItem> accountItemss = new ArrayList<>();
        accountItemss.add(new AccountItem("alice5", "csumb100"));
        accountItemss.add(new AccountItem("brian77", "123ABC"));
        accountItemss.add(new AccountItem("chris21", "CHRIS21"));
        accountItemss.add(new AccountItem("admin2", "admin2"));

        // Fill with default accounts
        for (AccountItem accountItem : accountItemss) {
            addAccountItem(accountItem);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addAccountItem(AccountItem accountItem) {
        db = this.getWritableDatabase();
        return db.insert(AccountTable.NAME, null, accountItem.getContentValues());
    }

    public AccountItem getAccountItem(String username, String password) {
        String whereClause = AccountTable.Cols.USERNAME + " = ? ";
        String[] whereArgs = {username};

        db = this.getWritableDatabase();

        AccountCursor accountCursor = new AccountCursor(db.query(AccountTable.NAME, null, whereClause, whereArgs, null, null, null));

        if (accountCursor.getCount() == 0) {
            Log.d("yolo", "No accounts");
            return null;
        }
        else {
            accountCursor.moveToFirst();
            AccountItem accountItem = accountCursor.getAccountItem();

            if (accountItem.isValid(password)) {
                return accountItem;
            }
            else {
                return null;
            }
        }
    }
}
