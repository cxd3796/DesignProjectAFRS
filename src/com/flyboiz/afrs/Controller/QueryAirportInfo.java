package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Model.AirportDatabase;

public class QueryAirportInfo implements Query {
    private String airport;
    private AirportDatabase airportDB;

    public QueryAirportInfo(String airport, AirportDatabase airportDB){
        this.airport = airport;
        this.airportDB = airportDB;
    }

    /**
     * Gets a String representation of the airport
     *
     * @return String airportCode,weather,temperature,delay in string format
     */
    public String generateResponse() {
        return airportDB.getAirportInfo(airport);
    }
}