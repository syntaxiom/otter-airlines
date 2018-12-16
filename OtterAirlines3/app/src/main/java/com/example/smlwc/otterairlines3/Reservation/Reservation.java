package com.example.smlwc.otterairlines3.Reservation;

import com.example.smlwc.otterairlines3.Account.Account;

import java.util.Date;

public class Reservation {
    private String username;
    private String departureCity;
    private String arrivalCity;
    private int numberOfTickets;
    private Date date;

    public Reservation(String username, String departureCity, String arrivalCity, int numberOfTickets) {
        this.username = username;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.numberOfTickets = numberOfTickets;
        date = new Date();
    }

    public String getUsername() {
        return username;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public Date getDate() {
        return date;
    }
}
