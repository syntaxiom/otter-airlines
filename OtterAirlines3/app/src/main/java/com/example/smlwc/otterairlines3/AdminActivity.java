package com.example.smlwc.otterairlines3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {

    // UI components
    Button accountsButton;
    Button flightsButton;
    Button cancellationsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Set title on startup
        setTitle("The secret admin page...");

        // Get UI components
        accountsButton = findViewById(R.id.accountsButton);
        flightsButton = findViewById(R.id.flightsButton);
        cancellationsButton = findViewById(R.id.cancellationsButton);

        // Accounts button click
        accountsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAccountsActivity();
            }
        });

        // Flights button click
        flightsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Cancellations button click
        cancellationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCancellationsActivity();
            }
        });
    }

    private void startAccountsActivity() {
        Intent intent = new Intent(AdminActivity.this, AccountsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void startCancellationsActivity() {
        Intent intent = new Intent(AdminActivity.this, CancellationsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
