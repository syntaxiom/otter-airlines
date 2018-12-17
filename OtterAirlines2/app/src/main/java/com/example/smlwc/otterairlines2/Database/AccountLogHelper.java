package com.example.smlwc.otterairlines2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.smlwc.otterairlines2.AccountLogItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This class is where the database is created and accessed. We design this to be encapsulated.
 * We NEVER access this directly.
 *
 * We use the get static method to get an object ensuring that only one ever exists.
 * This, it turns out, is a singleton design pattern.
 *
 * This returns LogItems, Lists of LogItems and primitives (longs etc)
 */

public class AccountLogHelper extends SQLiteOpenHelper {

    private static final String TAG = "GYM_Log";

    private static final int VERSION            = 1;
    public static final String DATABASE_NAME    = "accounts2.db";

    private SQLiteDatabase db;

    public AccountLogHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        Log.d("yolo", Boolean.toString(this.db == null));

        db.execSQL("create table " + AccountLogSchema.AccountLogTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                                    AccountLogSchema.AccountLogTable.Cols.DATE + ","+
                                    AccountLogSchema.AccountLogTable.Cols.USERNAME + "," +
                                    AccountLogSchema.AccountLogTable.Cols.PASSWORD + "," +
                                    AccountLogSchema.AccountLogTable.Cols.UUID + ","+
                                    AccountLogSchema.AccountLogTable.Cols.ADMIN +
                                    ")"
        );

//        Log.d("yolo", Boolean.toString(this.db == null));


        // Generate default accounts
        ArrayList<AccountLogItem> accountLogItems = new ArrayList<>();
        accountLogItems.add(new AccountLogItem("alice5", "csumb100"));
        accountLogItems.add(new AccountLogItem("brian77", "123ABC"));
        accountLogItems.add(new AccountLogItem("chris21", "CHRIS21"));
        accountLogItems.add(new AccountLogItem("admin2", "admin2", true));

        // Add the accounts
        for (AccountLogItem accountLogItem : accountLogItems) {
            addLogItem(accountLogItem);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    private long insertLog(AccountLogItem log){

        ContentValues cv = getContentValues(log);

        db = this.getWritableDatabase();

        return db.insert(AccountLogSchema.AccountLogTable.NAME, null, cv);

    }

    public long addLogItem(AccountLogItem log){

        if(this.getLogItem(log.getLogID()) == null){
            return insertLog(log);
        } else {
            return updateLogItem(log);
        }
    }

    private int updateLogItem(AccountLogItem log){
        db = this.getWritableDatabase();
        ContentValues cv = AccountLogHelper.getContentValues(log);
        String whereClause = AccountLogSchema.AccountLogTable.Cols.UUID + " = ? ";
        String[] whereArgs = {log.getLogID().toString()};
        try{
            return db.update(AccountLogSchema.AccountLogTable.NAME, cv, whereClause, whereArgs);
        } catch (Exception e){
            Log.d(TAG, "something is wrong in updateLogItem");
            return -1;
        }
    }

    public AccountLogItem getLogItem(String username){

        String whereClause = AccountLogSchema.AccountLogTable.Cols.USERNAME + " = ? ";
        String[] whereArgs = {username};

        AccountLogCursorWrapper cursor = new AccountLogCursorWrapper(this.queryDB(AccountLogSchema.AccountLogTable.NAME,whereClause,whereArgs));

        try {
            if (cursor.getCount() == 0){
                Log.d(TAG, "No results from getLogItem");
                return null;
            }
            cursor.moveToFirst();
            return cursor.getLogItem();
        } finally {
            cursor.close();
        }
    }

    public AccountLogItem getLogItem(UUID logUUID){

        String whereClause = AccountLogSchema.AccountLogTable.Cols.UUID + " = ? ";
        String[] whereArgs = {logUUID.toString()};

        AccountLogCursorWrapper cursor = new AccountLogCursorWrapper(this.queryDB(AccountLogSchema.AccountLogTable.NAME,whereClause,whereArgs));

        try {
            if (cursor.getCount() == 0){
                Log.d(TAG, "No results from getLogItem");
                return null;
            }
            cursor.moveToFirst();
            return cursor.getLogItem();
        } finally {
            cursor.close();
        }
    }

    public List<AccountLogItem> getLogs(){
        List<AccountLogItem> logs = new ArrayList<>();

        AccountLogCursorWrapper cursor = new AccountLogCursorWrapper(this.queryDB(AccountLogSchema.AccountLogTable.NAME,null,null));
        try{
            if(cursor.getCount() == 0){
                Log.d(TAG, "getLogItems returned nothing...");
                return null;
            }
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                logs.add(cursor.getLogItem());
                cursor.moveToNext();
            }
        }finally {

            cursor.close();
        }

        return logs;
    }

    private Cursor queryDB(String DBName, String whereClause, String[] whereArgs){
        try {
            if (db.isOpen()) {
                Log.d("yolo", "Yeah it's open");
            }
            db = this.getWritableDatabase();

        } catch (Exception e) {
            Log.d("yolo", e.getMessage());
        }

        try{
            return db.query(AccountLogSchema.AccountLogTable.NAME,
                    null,
                    whereClause,
                    whereArgs,
                    null,
                    null,
                    null);
        }catch (Exception e){
            Log.d(TAG, "Problem in queryDB!!");
            return null;
        }
    }

    public static ContentValues getContentValues(AccountLogItem log){
        ContentValues cv = new ContentValues();
        cv.put(AccountLogSchema.AccountLogTable.Cols.DATE, log.getDate().getTime());
        cv.put(AccountLogSchema.AccountLogTable.Cols.UUID, log.getLogID().toString());
        cv.put(AccountLogSchema.AccountLogTable.Cols.USERNAME, log.getUsername());
        cv.put(AccountLogSchema.AccountLogTable.Cols.PASSWORD, log.getPassword());
        cv.put(AccountLogSchema.AccountLogTable.Cols.ADMIN, log.isAdmin() ? 1 : 0);

        return cv;
    }

}
