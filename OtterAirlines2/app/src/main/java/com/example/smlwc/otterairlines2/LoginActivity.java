package com.example.smlwc.otterairlines2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    // UI components
    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    Button registerButton;

    // Necessary items
    AccountLog mAccountLog;
    AccountLogItem mAccountLogItem;

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

        // Get account log
        mAccountLog = AccountLog.get(this.getApplicationContext());

        // Login button functionality
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(isEmpty(usernameEditText) || isEmpty(passwordEditText))) {
                    mAccountLogItem = getAccountLogItemFromDisplay();
                    AccountLogItem checkItem = mAccountLog.getLog(mAccountLogItem);

                    if (mAccountLogItem.getPassword() == checkItem.getPassword()) {
                        toaster("You got it!");
                    }
                }
            }
        });
    }

    private void toaster(String message) {
        Toast t = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        t.setGravity(Gravity.BOTTOM, 0, 0);
        t.show();
    }

    private void toaster(String message, int gravity) {
        Toast t = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        t.setGravity(gravity, 0, 0);
        t.show();
    }

    private AccountLogItem getAccountLogItemFromDisplay() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        return new AccountLogItem(username, password);
    }

    private boolean isEmpty(EditText textToCheck){
        return textToCheck.getText().toString().trim().length() == 0;
    }
}
