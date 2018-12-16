package com.example.smlwc.otterairlines;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smlwc.otterairlines.Account.AccountLog;
import com.example.smlwc.otterairlines.Account.AccountItem;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    Button registerButton;

    AccountLog mAccountLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set title at startup
        setTitle("Welcome!");

        // Get UI components
        usernameEditText = findViewById(R.id.UsernameEditText);
        passwordEditText = findViewById(R.id.PasswordEditText);
        loginButton = findViewById(R.id.LoginButton);
        registerButton = findViewById(R.id.RegisterButton);

        // Get database stuff
        mAccountLog = AccountLog.get(this.getApplicationContext());

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                AccountItem accountItem = mAccountLog.getAccountItem(username, password);

                if (accountItem == null) {
                    Toast.makeText(getApplicationContext(), "Username and/or password incorrect.", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "You're in!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
