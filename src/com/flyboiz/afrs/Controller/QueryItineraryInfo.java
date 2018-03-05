package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Model.AirportDatabase;
import com.flyboiz.afrs.Model.FlightDatabase;
import com.flyboiz.afrs.Model.Itinerary;
import com.flyboiz.afrs.Controller.Query;
import com.flyboiz.afrs.Controller.SortStrategy;
import com.flyboiz.afrs.Model.FlightDatabase;

import java.util.List;

public class QueryItineraryInfo implements Query {
    private String origin;
    private String destination;
    private int maxConnection;
    private SortStrategy sortType;
    private FlightDatabase flightDB;
    private List<Itinerary> itineraries;
    private AirportDatabase airportDB;



    public QueryItineraryInfo(String origin, String destination, int maxConnection, SortStrategy sortType,
                              FlightDatabase flightDB, AirportDatabase airportDB){
        this.origin = origin;


       this.destination = destination;
        this.maxConnection = maxConnection;
        this.sortType = sortType;
        this.flightDB = flightDB;
        this.airportDB = airportDB;
    }

    public String generateResponse(){
        if (!airportDB.isAirportReal(origin)){
            return "error,unknown origin";
        }
        if(!airportDB.isAirportReal(destination)){
            return "error,unknown destination";
        }
        if (maxConnection <0 || maxConnection <2){
            return "error,invalid connection limit";
        }
        if (sortType ==null){
            return "error,invalid sort order";
        }
        itineraries = flightDB.getPotentialItineraries(origin, destination, maxConnection);
        int n = itineraries.size();
        String response = "info," + n;
        sortType.sort(itineraries);
        for(int i=0; i<n; i++){
            response = response + "\n" + (i + 1) + "," + itineraries.get(i).toString();
        }
        return response;
    }

    public Itinerary getItinerary(int index){
        return itineraries.get(index-1);
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }
}
