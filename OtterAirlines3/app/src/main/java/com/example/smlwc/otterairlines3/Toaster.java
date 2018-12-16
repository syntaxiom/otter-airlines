package com.example.smlwc.otterairlines3;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class Toaster {
    private static Context applicationContext;

    public static void setApplicationContext(Context applicationContext) {
        Toaster.applicationContext = applicationContext;
    }

    public static void accountAlreadyExistsToast() {
        makeToast("Account already exists. Please login.");
    }

    public static void noSuchAccountToast() {
        makeToast("Account doesn't exist. Would you like to register?");
    }

    public static void invalidLoginToast() {
        makeToast("Invalid login. Please try again.");
    }

    public static void invalidRegisterToast() {
        makeToast("Username and password must contain 3 letters and 1 digit.");
    }

    public static void successfulLoginToast() {
        makeToast("You're in!");
    }

    public static void successfulRegisterToast() {
        makeToast("Welcome aboard!");
    }

    public static void ticketsLeftBlankToast() {
        makeToast("Tickets field cannot be left blank.");
    }

    public static void citiesMustDifferToast() {
        makeToast("The cities must be different.");
    }

    public static void atLeast1TicketToast() {
        makeToast("Must purchase at least 1 ticket.");
    }

    public static void notEnoughTicketsToast() {
        makeToast("Sorry, not enough tickets for this flight.");
    }

    public static void makeToast(String message) {
        Toast t = Toast.makeText(applicationContext, message, Toast.LENGTH_LONG);
        t.setGravity(Gravity.BOTTOM, 0, 0);
        t.show();
    }
}
