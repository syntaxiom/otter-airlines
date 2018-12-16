package com.example.smlwc.otterairlines3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.smlwc.otterairlines3.Account.AccountManager;

public class ReservationsActivity extends AppCompatActivity {

    // UI components
    Toolbar toolbar;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        // Set title on startup
        setTitle(AccountManager.getCurrentAccount().getUsername());

        // Get UI components
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);

        // Set toolbar properties
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // FAB click
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBookActivity();
            }
        });
    }

    private void startBookActivity() {
        Intent intent = new Intent(ReservationsActivity.this, BookActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
