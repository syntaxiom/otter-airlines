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
                    noSuchAccountToast();
                }
                else {
                    if (!password.equals(checkAccount.getPassword())) {
                        invalidLoginToast();
                    }
                    else {
                        successfulLoginToast();
                        AccountManager.setCurrentAccount(checkAccount);

                        Intent intent = new Intent(LoginActivity.this, ReservationsActivity.class);
                        startActivity(intent);
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
                    accountAlreadyExistsToast();
                }
                else {
                    Account account = new Account(username, password, false);

                    if (!account.isValidInfo()) {
                        invalidRegisterToast();
                    }
                    else {
                        successfulRegisterToast();
                        AccountManager.addAccount(username, account);
                        AccountManager.setCurrentAccount(account);

                        Intent intent = new Intent(LoginActivity.this, ReservationsActivity.class);
                        startActivity(intent);
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

    private void accountAlreadyExistsToast() {
        toaster("Account already exists. Please login.");
    }

    private void noSuchAccountToast() {
        toaster("Account doesn't exist. Would you like to register?");
    }

    private void invalidLoginToast() {
        toaster("Invalid login. Please try again.");
    }

    private void invalidRegisterToast() {
        toaster("Username and password must contain 3 letters and 1 digit.");
    }

    private void successfulLoginToast() {
        toaster("You're in!");
    }

    private void successfulRegisterToast() {
        toaster("Welcome aboard!");
    }

    private void toaster(String message) {
        Toast t = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        t.setGravity(Gravity.BOTTOM, 0, 0);
        t.show();
    }
}
