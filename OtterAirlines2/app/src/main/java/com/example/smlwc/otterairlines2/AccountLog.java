package com.example.smlwc.otterairlines2;

import android.content.Context;

import com.example.smlwc.otterairlines2.Database.AccountLogHelper;

import java.util.List;

/**
 * This is the file that we use to read/write to the DB.
 * This creates an instance of the helper and we send our requests here.
 * Any data massaging happens here.
 */

public class AccountLog {
    private static AccountLog sAccountLog;
    private Context mContext;
    private AccountLogHelper mAccountLogHelper;

    public static AccountLog get(Context context){
        if(sAccountLog == null){
            sAccountLog = new AccountLog(context);
        }
        return sAccountLog;
    }

    private AccountLog(Context context){
        mContext = context.getApplicationContext();
        mAccountLogHelper = new AccountLogHelper(mContext);
    }

    public long addLog(AccountLogItem log){
        return mAccountLogHelper.addLogItem(log);
    }

    public AccountLogItem getLog(AccountLogItem accountLogItem) {
        return mAccountLogHelper.getLogItem(accountLogItem.getUsername());
    }

    public String getLogString(){
        StringBuilder sb = new StringBuilder();
        List<AccountLogItem> logs = mAccountLogHelper.getLogs();


        if (logs == null){
            return "Gym Logs\n";
        }

        sb.append("Gym Logs\n");

        for(AccountLogItem log : logs){
            sb.append(log.toString());
        }

        return sb.toString();
    }

}
