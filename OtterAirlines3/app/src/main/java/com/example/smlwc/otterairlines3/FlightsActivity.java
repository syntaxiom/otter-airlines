package com.example.smlwc.otterairlines3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smlwc.otterairlines3.Account.Account;
import com.example.smlwc.otterairlines3.Account.AccountManager;
import com.example.smlwc.otterairlines3.Reservation.Reservation;
import com.example.smlwc.otterairlines3.Reservation.ReservationManager;

import java.util.ArrayList;

public class FlightsActivity extends AppCompatActivity {

    // UI components
    Toolbar toolbar;
    FloatingActionButton fab;
    LinearLayout flightsLinearLayout;
    TextView makeNewFlightTextView;

    // Other necessary stuff
    ArrayList<Account> accounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);

        // Set title on startup
        setTitle("Flights");

        // Get UI components
        fab = findViewById(R.id.fab);
        toolbar = findViewById(R.id.toolbar);
        flightsLinearLayout = findViewById(R.id.flightsLinearLayout);
        makeNewFlightTextView = findViewById(R.id.makeNewFlightTextView);

        // Get other necessary stuff
        accounts = AccountManager.getAccounts();

        // Set toolbar
        setSupportActionBar(toolbar);

        // Fill in flights
        for (Account account : accounts) {
            for (final Reservation reservation : account.getReservations()) {
                if (!reservation.isCanceled()) {
                    // Hide make new flight text view
                    makeNewFlightTextView.setVisibility(View.INVISIBLE);

                    CardView cardView = new CardView(this);
                    TextView textView = new TextView(this);

                    // Set text view
                    textView.setTextSize(20);
                    textView.setText(reservation.getUsername() + "\n" + reservation.toString());

                    // Add to card view
                    cardView.addView(textView);

                    // Add to linear layout
                    flightsLinearLayout.addView(cardView);

                    // Card click
                    cardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ReservationManager.setCurrentReservation(reservation);
                            startInspectActivity();
                        }
                    });
                }
            }
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBookActivity();
            }
        });
    }

    private void startBookActivity() {
        Intent intent = new Intent(FlightsActivity.this, BookActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void startInspectActivity() {
        Intent intent = new Intent(FlightsActivity.this, InspectActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
