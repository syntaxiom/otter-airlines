package com.example.smlwc.otterairlines.Account;

import android.content.Context;

public class AccountLog {
    private static AccountLog sAccountLog;
    private Context mContext;
    private AccountHelper mAccountHelper;

    public static AccountLog get(Context context) {
        if (sAccountLog == null) {
            sAccountLog = new AccountLog(context);
        }

        return sAccountLog;
    }

    private AccountLog(Context context) {
        mContext = context;
        mAccountHelper = new AccountHelper(mContext);
    }

    public long addAccountItem(AccountItem accountItem) { return mAccountHelper.addAccountItem(accountItem); }

    public AccountItem getAccountItem(String username, String password) { return mAccountHelper.getAccountItem(username, password); }
}
