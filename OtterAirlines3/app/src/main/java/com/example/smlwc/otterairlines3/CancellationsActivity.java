package com.example.smlwc.otterairlines3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smlwc.otterairlines3.Account.Account;
import com.example.smlwc.otterairlines3.Account.AccountManager;
import com.example.smlwc.otterairlines3.Reservation.Reservation;
import com.example.smlwc.otterairlines3.Reservation.ReservationManager;

import java.util.ArrayList;

public class CancellationsActivity extends AppCompatActivity {

    // UI components
    LinearLayout cancellationsLinearLayout;

    // Other necessary stuff
    ArrayList<Account> accounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancellations);

        // Set title on startup
        setTitle("Cancellations");

        // Get UI components
        cancellationsLinearLayout = findViewById(R.id.cancellationsLinearLayout);

        // Get other necessary stuff
        accounts = AccountManager.getAccounts();

        // Fill in all the cancelled reservations
        for (Account account : accounts) {
            for (Reservation reservation : account.getReservations()) {
                if (reservation.isCanceled()) {
                    CardView cardView = new CardView(this);
                    TextView textView = new TextView(this);

                    // Set text view
                    textView.setTextSize(20);
                    textView.setText(reservation.getUsername() + "\n" + reservation.toString());

                    // Add to card view
                    cardView.addView(textView);

                    // Add to linear layout
                    cancellationsLinearLayout.addView(cardView);
                }
            }
        }
    }
}
