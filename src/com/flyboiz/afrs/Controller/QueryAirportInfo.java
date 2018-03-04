package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Controller.Query;
import com.flyboiz.afrs.Model.AirportDatabase;

public class QueryAirportInfo implements Query {
    private String airport;
    private AirportDatabase airportDB;

    public QueryAirportInfo(String airport, AirportDatabase airportDB){
        this.airport = airport;
        this.airportDB = airportDB;
    }


    public String generateResponse() {
        return null;
    }
}