package com.example.smlwc.otterairlines;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smlwc.otterairlines.Account.Account;

public class LoginActivity extends AppCompatActivity {

    // UI components
    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set the title on startup
        setTitle("Welcome!");

        // Get UI components
        usernameEditText = findViewById(R.id.UsernameEditText);
        passwordEditText = findViewById(R.id.PasswordEditText);
        loginButton = findViewById(R.id.LoginButton);
        registerButton = findViewById(R.id.RegisterButton);

        // Set register button functionality
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account account = new Account(usernameEditText.getText().toString(), passwordEditText.getText().toString());

                // Move on if account is valid, otherwise notify the user
                if (account.isValid()) {
//                    AccountHelper accountHelper = new AccountHelper(getApplicationContext());
//                    accountHelper.insertAccount(account);
//                    Manager.setCurrentAccount(account);

                    // Go to admin activity if applicable
                    if (account.isAdmin()) {

                    }
                    else {
                        Intent intent = new Intent(LoginActivity.this, ReservationsActivity.class);
                        startActivity(intent);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Username and password require at least 3 letters and 1 number!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
