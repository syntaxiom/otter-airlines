package com.example.smlwc.otterairlines3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.smlwc.otterairlines3.Account.AccountManager;
import com.example.smlwc.otterairlines3.Reservation.Reservation;
import com.example.smlwc.otterairlines3.Reservation.ReservationManager;

public class InspectActivity extends AppCompatActivity {

    // UI components
    Toolbar toolbar;
    FloatingActionButton fab;
    TextView detailsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspect);

        // Set title on startup
        setTitle("Flight details");

        // Get UI components
        fab = findViewById(R.id.fab);
        toolbar = findViewById(R.id.toolbar);
        detailsTextView = findViewById(R.id.detailsTextView);

        // Set toolbar
        setSupportActionBar(toolbar);

        // Set details text view
        detailsTextView.setText(ReservationManager.getFlight(ReservationManager.getCurrentReservation().getDepartureCity(), ReservationManager.getCurrentReservation().getArrivalCity())
                + "\n" + ReservationManager.getCurrentReservation().toString());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start building the alert
                AlertDialog.Builder builder = new AlertDialog.Builder(InspectActivity.this);
                builder.setTitle("Confirm Delete");
                builder.setCancelable(true);

                // Ask the user to confirm their decision to delete
                builder.setMessage("Are you sure you want to cancel this flight?");

                // The user can accept
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ReservationManager.cancelFlight(ReservationManager.getCurrentReservation());
                        dialog.cancel();

                        if (AccountManager.getCurrentAccount().isAdmin()) {
                            startFlightsActivity();
                        }
                        else {
                            startReservationsActivity();
                        }
                    }
                });

                // The user can cancel
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                // Show the alert
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    private void startReservationsActivity() {
        Intent intent = new Intent(InspectActivity.this, ReservationsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void startFlightsActivity() {
        Intent intent = new Intent(InspectActivity.this, FlightsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
