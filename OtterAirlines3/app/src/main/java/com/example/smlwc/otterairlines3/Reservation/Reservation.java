package com.example.smlwc.otterairlines3.Reservation;

import java.util.Date;

public class Reservation {
    private String username;
    private String departureCity;
    private String arrivalCity;
    private int numberOfTickets;
    private Date date;
    private boolean canceled;

    public Reservation(String username, String departureCity, String arrivalCity, int numberOfTickets) {
        this.username = username;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.numberOfTickets = numberOfTickets;
        date = new Date();
        canceled = false;
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

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @Override
    public String toString() {
        return  "Departing: " + departureCity + "\n" +
                "Arriving: " + arrivalCity + "\n" +
                "Tickets: " + numberOfTickets + "\n" +
                "Price: $" + (numberOfTickets * 150);
    }
}
