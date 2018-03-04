package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Model.Reservation;
import com.flyboiz.afrs.Model.ReservationDatabase;

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
        Reservation reservation;
        if (origin.equals("")){
            reservation = reservationDB.retrieveReservation(name);
        } else {
            if (destination.equals("")){
                reservation = reservationDB.retrieveReservation(name, origin);
            } else {
                reservation = reservationDB.retrieveReservation(name, origin, destination);
            }
        }
        if (reservation == null) {
            return "error,reservation not found";
        }
        else{
            reservationDB.deleteReservation(name, origin, destination);
            return "delete,successful";
        }
    }
}
