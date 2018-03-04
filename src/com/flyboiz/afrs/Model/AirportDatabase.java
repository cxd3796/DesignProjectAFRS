package com.flyboiz.afrs.Model;

import java.util.HashMap;
import java.util.Map;

public class AirportDatabase
{
    private Map<String, Airport> airports;

    /**
     * Constructor for AirportDatabase. Creates an empty HashMap for airports
     */
    public AirportDatabase(){
        airports = new HashMap<>();
    }

    /**
     * Adds a new airport object to the database
     * @param airportCode
     */
    public void generateAirport(String airportCode){
        airports.put(airportCode, new Airport(airportCode));
    }

    /**
     * Stores airport delay time to a specified airport
     * @param airportCode - 3 letter string specifying the airport
     * @param delayTime - int representing the delay time
     */
    public void storeAirportDelay(String airportCode, int delayTime){
        Airport airport = airports.get(airportCode);
        airport.setDelayTime(delayTime);
    }

    /**
     * Stores airport connection time to a specified airport
     * @param airportCode - 3 letter string specifying the airport
     * @param connectionTime - int representing the connection time
     */
    public void storeAirportConnectionTime(String airportCode, int connectionTime){
        Airport airport = airports.get(airportCode);
        airport.setConnectionTime(connectionTime);
    }

    /**
     * Stores airport weather conditions
     * @param airportCode - 3 letter string specifying the airport
     * @param condition - String being on of the following: sunny, cloudy, partly cloudy, rain, partly sunny, or hazy
     * @param temperature - int containing the temperature of the weather pair
     */
    public void storeAirportWeather(String airportCode, String condition, int temperature){
        Airport airport = airports.get(airportCode);
        airport.storeAirportWeather(condition, temperature);
    }

    public String getAirportInfo(String airportCode){
        Airport airport = airports.get(airportCode);
        return airport.toString();
    }





}
