package com.example.smlwc.otterairlines3.Reservation;

import com.example.smlwc.otterairlines3.Account.Account;

import java.util.ArrayList;
import java.util.HashMap;

public class ReservationManager {
    private static HashMap<String, String> flights = new HashMap<String, String>(){{
        put("Monterey-Los Angeles", "Otter101");
        put("Los Angeles-Monterey", "Otter102");
        put("Monterey-Seattle", "Otter204");
        put("Seattle-Monterey", "Otter205");
        put("Los Angeles-Seattle", "Otter307");
        put("Seattle-Los Angeles", "Otter308");
    }};

    private static HashMap<String, Integer> tickets = new HashMap<String, Integer>(){{
        put("Otter101", 0);
        put("Otter102", 100);
        put("Otter204", 100);
        put("Otter205", 100);
        put("Otter307", 100);
        put("Otter308", 100);
    }};

    private static String cities(String departureCity, String arrivalCity) {
        return departureCity + "-" + arrivalCity;
    }

    private static void subtractTickets(String flight, int numberOfTickets) {
        tickets.put(flight, tickets.get(flight) - numberOfTickets);
    }

    public static String getFlight(String departureCity, String arrivalCity) {
        return flights.get(cities(departureCity, arrivalCity));
    }

    public static int getTickets(String departureCity, String arrivalCity) {
        return tickets.get(getFlight(departureCity, arrivalCity));
    }

    public static void bookFlight(Account account, String departureCity, String arrivalCity, int numberOfTickets) {
        Reservation reservation = new Reservation(account.getUsername(), departureCity, arrivalCity, numberOfTickets);
        account.addReservation(reservation);
        subtractTickets(getFlight(departureCity, arrivalCity), numberOfTickets);
    }
}
