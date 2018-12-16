package com.example.smlwc.otterairlines3;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TextView;

import com.example.smlwc.otterairlines3.Account.AccountManager;
import com.example.smlwc.otterairlines3.Reservation.Reservation;
import com.example.smlwc.otterairlines3.Reservation.ReservationManager;

import java.util.ArrayList;

public class ReservationsActivity extends AppCompatActivity {

    // UI components
    Toolbar toolbar;
    FloatingActionButton fab;
    TextView addNewReservationTextView;
    ScrollView reservationsScrollView;
    LinearLayout reservationsLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        // Set title on startup
        setTitle(AccountManager.getCurrentAccount().getUsername());

        // Get UI components
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        addNewReservationTextView = findViewById(R.id.addNewReservationTextView);
        reservationsScrollView = findViewById(R.id.reservationsScrollView);
        reservationsLinearLayout = findViewById(R.id.reservationsLinearLayout);

        // Set toolbar properties
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get reservations of current user
        ArrayList<Reservation> reservations = AccountManager.getCurrentAccount().getReservations();

        // Hide "add new reservation" text view if reservations aren't empty
        if (!reservations.isEmpty()) {
            addNewReservationTextView.setVisibility(View.INVISIBLE);
        }

        // Add card views per reservation
        for (Reservation reservation : reservations) {
            CardView cardView = new CardView(this);
            TextView flightTextView = new TextView(this);
            TextView departureTextView = new TextView(this);
            TextView arrivalTextView = new TextView(this);
            TextView ticketTextView = new TextView(this);
            LinearLayout linearLayout = new LinearLayout(this);

            // Set layout
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            // Set flight text view
            flightTextView.setTextSize(18);
            flightTextView.setText(ReservationManager.getFlight(reservation.getDepartureCity(), reservation.getArrivalCity()));

            // Set departure text view
            departureTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_flight_takeoff_black_18dp, 0, 0, 0);
            departureTextView.setTextSize(22);
            departureTextView.setText("   " + reservation.getDepartureCity());

            // Set arrival text view
            arrivalTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_flight_land_black_18dp, 0, 0, 0);
            arrivalTextView.setTextSize(22);
            arrivalTextView.setText("   " + reservation.getArrivalCity());

            // Set ticket text view
            ticketTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_receipt_black_18dp, 0, 0, 0);
            ticketTextView.setTextSize(22);
            ticketTextView.setText("   " + reservation.getNumberOfTickets() + " ($" + (reservation.getNumberOfTickets() * 150) + ")");

            // Add them to the linearLayout
            linearLayout.addView(flightTextView);
            linearLayout.addView(departureTextView);
            linearLayout.addView(arrivalTextView);
            linearLayout.addView(ticketTextView);

            // Add layout to card
            cardView.addView(linearLayout);

            // Add card to the layout
            reservationsLinearLayout.addView(cardView);
        }

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
