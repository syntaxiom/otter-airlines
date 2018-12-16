package com.example.smlwc.otterairlines3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smlwc.otterairlines3.Account.Account;
import com.example.smlwc.otterairlines3.Account.AccountManager;

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

        // Set toaster
        Toaster.setApplicationContext(getApplicationContext());

        // Set title on startup
        setTitle("Welcome!");

        // Get UI components
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        // Login button click
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = getUsernameFromDisplay();
                String password = getPasswordFromDisplay();
                Account checkAccount = AccountManager.getAccount(username);

                // Invalid login if account doesn't exist or if password is incorrect
                if (checkAccount == null) {
                    Toaster.noSuchAccountToast();
                }
                else {
                    if (!password.equals(checkAccount.getPassword())) {
                        Toaster.invalidLoginToast();
                    }
                    else {
                        Toaster.successfulLoginToast();
                        AccountManager.setCurrentAccount(checkAccount);
                        startReservationsActivity();
                    }
                }
            }
        });

        // Register button click
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = getUsernameFromDisplay();
                String password = getPasswordFromDisplay();
                Account checkAccount = AccountManager.getAccount(username);

                // Inform user if account already exists or if information is invalid, else register them
                if (checkAccount != null) {
                    Toaster.accountAlreadyExistsToast();
                }
                else {
                    Account account = new Account(username, password, false);

                    if (!account.isValidInfo()) {
                        Toaster.invalidRegisterToast();
                    }
                    else {
                        Toaster.successfulRegisterToast();
                        AccountManager.addAccount(username, account);
                        AccountManager.setCurrentAccount(account);
                        startReservationsActivity();
                    }
                }
            }
        });
    }

    private String getUsernameFromDisplay() {
        return usernameEditText.getText().toString();
    }

    private String getPasswordFromDisplay() {
        return passwordEditText.getText().toString();
    }

    private void startReservationsActivity() {
        Intent intent = new Intent(LoginActivity.this, ReservationsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
