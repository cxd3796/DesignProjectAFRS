package com.flyboiz.afrs.Model;

import java.util.ArrayList;

public class Airport {

    private String weather;
    private String airportCode;
    private Time ConnectionTime;
    private Time delayTime;
    private ArrayList<String> connectedCities;

    public Airport(String weather, String airportCode, Time connectionTime, Time delayTime, ArrayList<String> connectedCities) {
        this.weather = weather;
        this.airportCode = airportCode;
        ConnectionTime = connectionTime;
        this.delayTime = delayTime;
        this.connectedCities = connectedCities;
    }
}
