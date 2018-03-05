package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Model.ReservationDatabase;

/**
 * Handles the DeleteResevation Query
 */
public class QueryDeleteReservation implements Query {

    private String name;
    private String origin;
    private String destination;
    private ReservationDatabase reservationDB;

    public QueryDeleteReservation(String name, String origin, String destination, ReservationDatabase reservationDB) {
        this.name = name;
        this.origin = origin;
        this.destination = destination;
        this.reservationDB = reservationDB;
    }

    /**
     * Removes a reservation from the reservation database.
     * @return String that represents the result of the operation
     */
    public String generateResponse(){
        if (reservationDB.deleteReservation(name, origin, destination)) {
            return "delete,successful";
        }
        else{
            reservationDB.deleteReservation(name, origin, destination);
            return "error,reservation not found";
        }
    }
}
