package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Model.AirportDatabase;
import com.flyboiz.afrs.Model.FlightDatabase;
import com.flyboiz.afrs.Model.ReservationDatabase;

import java.util.ArrayList;

public class QueryMaker {
    private String query;
    private QueryExecutor queryExecutor;
    private FlightDatabase flightDB;
    private AirportDatabase airportDB;
    private ReservationDatabase reservationDB;

    public QueryMaker(QueryExecutor queryExecutor, FlightDatabase flightDB, AirportDatabase airportDB,
                      ReservationDatabase reservationDB){
        this.queryExecutor = queryExecutor;
        this.flightDB = flightDB;
        this.airportDB = airportDB;
        this.reservationDB = reservationDB;
    }

    public Query makeQuery(String input){
        String[] fields = input.split(",");
        String origin = "";
        String passenger ="";
        String destination ="";
        switch (fields[0]){
            case "info":
                origin = fields[1];
                destination = fields[2];
                int connection = 2;
                SortStrategy sortOrder = new SortByDeparture();
                if(fields.length >= 4){
                    connection = Integer.parseInt(fields[3]);
                }
                if(fields.length == 5){
                    if (fields[4].equals("arrival")){
                        sortOrder = new SortByArrival();
                    }
                    else if (fields[4].equals("airfare")){
                        sortOrder = new SortByAirfare();
                    }
                }
                return new QueryItineraryInfo(origin, destination, connection, sortOrder, flightDB, airportDB);
            case "reserve":
                int id = Integer.parseInt(fields[1]);
                String name = fields[2];
                return new QueryMakeReservation(id, name, reservationDB);

            case "retrieve":
                passenger = fields[1];
                if(fields.length>2){
                    origin = fields[2];
                }
                if(fields.length>3){
                    destination = fields[3];
                }
                return new QueryRetrieveReservation(passenger, origin, destination, reservationDB, airportDB);

            case "delete":
                passenger = fields[1];
                origin = fields[2];
                destination = fields[3];
                return new QueryDeleteReservation(passenger, origin, destination, reservationDB);

            case "airport":
                String airport = fields[1];
                return new QueryAirportInfo(airport, airportDB);
        }
        return null;
    }
}