package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Controller.Query;
import com.flyboiz.afrs.Model.AirportDatabase;
import com.flyboiz.afrs.Model.Itinerary;
import com.flyboiz.afrs.Model.Reservation;
import com.flyboiz.afrs.Model.ReservationDatabase;

import java.util.List;

public class QueryRetrieveReservation implements Query {
    private String name;
    private String origin;
    private String destination;
    private ReservationDatabase reservationDB;
    private AirportDatabase airportDB;

    public QueryRetrieveReservation(String name, String origin, String destination, ReservationDatabase reservationDB, AirportDatabase airportDB){
        this.name = name;
        this.origin = origin;
        this.destination = destination;
        this.reservationDB = reservationDB;
        this.airportDB = airportDB;
    }

    public String generateResponse()
    {
        List<Reservation> reservations;
        if (airportDB.)
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
        // Begin string building
        String output = String.format("retrieve,%d", amountOfReservations);
        for (Reservation r:reservations){
            Itinerary i = r.getItinerary();
            output += "\n" + i.toString();
        }
        return output;
    }
}
