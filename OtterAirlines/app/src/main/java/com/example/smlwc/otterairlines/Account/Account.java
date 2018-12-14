package com.example.smlwc.otterairlines.Account;

import android.content.Context;

public class Account {
    private static Account sAccount;
    private Context mContext;
    private AccountHelper mAccountHelper;

    public static Account get(Context context) {
        if (sAccount == null) {
            sAccount = new Account(context);
        }

        return sAccount;
    }

    private Account(Context context) {
        mContext = context;
        mAccountHelper = new AccountHelper(mContext);
    }

    public long addAccountItem(AccountItem accountItem) { return mAccountHelper.addAccountItem(accountItem); }
}
