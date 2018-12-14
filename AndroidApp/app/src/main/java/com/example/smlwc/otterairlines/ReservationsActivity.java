package com.example.smlwc.otterairlines;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ReservationsActivity extends AppCompatActivity {

    Toolbar toolbar;
    FloatingActionButton fab;
    TextView addTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        // Set the title on startup
        setTitle("Reservations");

        // Get UI components
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        addTextView = findViewById(R.id.AddTextView);

        // Set toolbar in action bar
        setSupportActionBar(toolbar);

        // Set FAB functionality
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
