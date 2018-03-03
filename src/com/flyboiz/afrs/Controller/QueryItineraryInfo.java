package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Model.FlightDatabase;

public class QueryItineraryInfo implements Query {
    private String origin;
    private String destination;
    private int maxConnection;
    private SortStrategy sortType;
    private FlightDatabase flightDB;


    public void generateResponse(){}
}
