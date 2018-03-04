package Controller;

import Model.ReservationDatabase;

public class QueryDeleteReservation implements Query {

    private String name;
    private String origin;
    private String destination;
    private ReservationDatabase reservationDB;

    public QueryDeleteReservation(String name, String origin, String destination, ReservationDatabase reservationDB) {
        this.name = name;
        this.origin =origin;
        this.destination = destination;
        this.reservationDB = reservationDB;
    }

    public String generateResponse(){return null;}
}
