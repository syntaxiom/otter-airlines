package com.example.smlwc.otterairlines.Account;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addAccountItem(AccountItem accountItem) {
        db = this.getWritableDatabase();
        return 0;
    }
}
