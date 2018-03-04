package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Model.FlightDatabase;

public class QueryItineraryInfo implements Query {
    private String origin;
    private String destination;
    private int maxConnection;
    private SortStrategy sortType;
    private FlightDatabase flightDB;

  
    public QueryItineraryInfo(String origin, String destination, int maxConnection, SortStrategy sortType,
                              FlightDatabase flightDB){
        this.origin = origin;
        this.destination = destination;
        this.maxConnection = maxConnection;
        this.sortType = sortType;
        this.flightDB = flightDB;
    }
  
    //TODO
    public String generateResponse() {
      return "";
    } 
}
