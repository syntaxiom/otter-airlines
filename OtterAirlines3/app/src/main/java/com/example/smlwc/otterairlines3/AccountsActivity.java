package com.example.smlwc.otterairlines3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smlwc.otterairlines3.Account.Account;
import com.example.smlwc.otterairlines3.Account.AccountManager;

import java.util.ArrayList;

public class AccountsActivity extends AppCompatActivity {

    // UI components
    LinearLayout accountsLinearLayout;

    // Other necessary stuff
    ArrayList<Account> accounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        // Set title on startup
        setTitle("Accounts");

        // Get UI components
        accountsLinearLayout = findViewById(R.id.accountsLinearLayout);

        // Get other necessary stuff
        accounts = AccountManager.getAccounts();

        // Fill layout with accounts info
        for (Account account : accounts) {
            CardView cardView = new CardView(this);
            TextView textView = new TextView(this);

            // Set text view properties
            textView.setTextSize(20);
            textView.setText(account.toString());

            // Add text to card
            cardView.addView(textView);

            // Add card to layout
            accountsLinearLayout.addView(cardView);
        }
    }
}
