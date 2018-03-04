package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Controller.Query;
import com.flyboiz.afrs.Model.Reservation;
import com.flyboiz.afrs.Model.ReservationDatabase;

import java.util.List;

public class QueryRetrieveReservation implements Query {
    private String name;
    private String origin;
    private String destination;
    private ReservationDatabase reservationDB;

    public QueryRetrieveReservation(String name, String origin, String destination, ReservationDatabase reservationDB ){
        this.name = name;
        this.origin = origin;
        this.destination = destination;
        this.reservationDB= reservationDB;
    }

    public String generateResponse()
    {
        List<Reservation> reservations;
        if (origin.equals("")){
            reservations = reservationDB.retrieveReservations(name);
        } else {
            if (destination.equals("")){
                reservations = reservationDB.retrieveReservations(name, origin);
            } else {
                reservations = reservationDB.retrieveReservations(name, origin, destination);
            }
        }
        int amountOfReservations = reservations.size();
        String output;
        // Begin string building
        output += String.format("retrieve,%d", amountOfReservations);
    }
}
