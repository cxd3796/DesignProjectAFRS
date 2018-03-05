package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Model.AirportDatabase;
import com.flyboiz.afrs.Model.FlightDatabase;
import com.flyboiz.afrs.Model.ReservationDatabase;


public class QueryMaker {
    private Query lastQuery;
    private FlightDatabase flightDB;
    private AirportDatabase airportDB;
    private ReservationDatabase reservationDB;

    public QueryMaker(FlightDatabase flightDB, AirportDatabase airportDB,
                      ReservationDatabase reservationDB){
        this.flightDB = flightDB;
        this.airportDB = airportDB;
        this.reservationDB = reservationDB;
        this.lastQuery = null;
    }

    public Query makeQuery(String input){
        String[] fields = input.split(",");
        String origin = "";
        String passenger = "";
        String destination = "";
        Query query = null;
        switch (fields[0]){
            case "info":
                if(fields.length<3 || fields.length >5 ){ //missing or extra required params
                    query = null;
                }
                origin = fields[1];
                destination = fields[2];
                int connection = 2;
                SortStrategy sortOrder = new SortByDeparture();
                if(fields.length >= 4){
                    connection = Integer.parseInt(fields[3]);
                }
                if(fields.length == 5){
                    String sort = fields[4];
                    if (sort.equals("arrival")){
                        sortOrder = new SortByArrival();
                    }
                    else if(sort.equals("departure")){
                        sortOrder = new SortByDeparture();
                    }
                    else if (sort.equals("airfare")){
                        sortOrder = new SortByAirfare();
                    }
                    else if (!sort.equals("")){   //if invalid sort is inputted, null is passed in
                        sortOrder = null;
                    }
                }

                return new QueryItineraryInfo(origin, destination, connection, sortOrder, flightDB, airportDB);
            case "reserve":
                if(fields.length != 3){
                    return null;
                }
                int id = Integer.parseInt(fields[1]);
                String name = fields[2];
                return new QueryMakeReservation(id, name, reservationDB, lastQuery);
            case "retrieve":
                if(fields.length<2 || fields.length> 4){
                    return null;
                }
                passenger = fields[1];
                if(fields.length>2){
                    origin = fields[2];
                }
                if(fields.length>3){
                    destination = fields[3];
                }
                return new QueryRetrieveReservation(passenger, origin, destination, reservationDB, airportDB);
            case "delete":
                if(fields.length != 4){
                    return null;
                }
                passenger = fields[1];
                origin = fields[2];
                destination = fields[3];
                return new QueryDeleteReservation(passenger, origin, destination, reservationDB);

            case "airport":
                if(fields.length != 2){
                    return null;
                }
                String airport = fields[1];
                return new QueryAirportInfo(airport, airportDB);
        }
        return query;
    }
}