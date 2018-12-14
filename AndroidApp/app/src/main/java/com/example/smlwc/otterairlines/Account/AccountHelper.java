package com.example.smlwc.otterairlines.Account;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class AccountHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    public static final String DATABASE_NAME = "account.db";
    private SQLiteDatabase db;
    private Context mContext;

    public AccountHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create ACCOUNT table
        db.execSQL("create table " + AccountTable.NAME +
                "(" + " _id integer primary key autoincrement, " +
                AccountTable.Cols.UUID +
                AccountTable.Cols.USERNAME +
                AccountTable.Cols.PASSWORD +
                AccountTable.Cols.DATE +
                ")"
        );

        // Make a bunch of default accounts
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(new Account("alice5", "csumb100"));
        accounts.add(new Account("brian77", "123ABC"));
        accounts.add(new Account("chris21", "CHRIS21"));
        accounts.add(new Account("admin2", "admin2", true));

        // Fill table with default accounts
        for (Account account : accounts) {
            insertAccount(account);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertAccount(Account account) {
        // Get writable database
        try {
            db = this.getWritableDatabase();
        }
        catch (Exception e) {
            Log.d("yolo", e.getMessage());
        }

        // Insert account content values
//        return db.insert(AccountTable.NAME, null, account.getContentValues());
        return 0;
    }

    public Account getAccount(String username, String password) {
//        String whereClause = AccountTable.Cols.USERNAME + " = ? ";
//        String[] whereArgs = {username};

        try {
            db = this.getWritableDatabase();
//            AccountCursor cursor = new AccountCursor(db.query(AccountTable.NAME, null, whereClause, whereArgs, null, null, null));
            Log.d("yolo", "You got it");
        }
        catch (Exception e) {
            Log.d("yolo", e.getMessage());
        }

//        AccountCursor cursor = new AccountCursor(db.query(AccountTable.NAME, null, whereClause, whereArgs, null, null, null));

//        if (cursor.getCount() == 0) {
//            return null;
//        }
//        else {
//            cursor.moveToFirst();
//            Account account = cursor.getAccount();
//
//            if (account.getPassword() != password) {
//                return null;
//            }
//            else {
//                return account;
//            }
//        }

        return null;
    }
}
