/* Authors
Kent Brown
 */

// Package //
package com.flyboiz.afrs.Model;

// Imports //
import java.util.ArrayList;
import java.util.List;

// Implementation //
public class FlightDatabase {

    // State //
    private List<Flight> flights;

    // Constructors //
    public FlightDatabase() {
        this.flights = new ArrayList<>();
    }

    public FlightDatabase(List<Flight> flights) {
        this.flights = flights;
    }

    // Behavior //
    public void generateFlight(String originAirport,
                               String destinationAirport,
                               Time departureTime,
                               Time arrivalTime,
                               int flightNumber,
                               int airfare)
    {
        Flight newFlight = new Flight(originAirport,destinationAirport,departureTime,arrivalTime,flightNumber,airfare);
        flights.add(newFlight);
    }

    public Itinerary createItinerary(List<Flight> flights)
    {
        Itinerary itinerary = new Itinerary(flights);
        return itinerary;
    }

    public Flight getFlightFromNumber(int flightNumber) {
        Flight f = null;
        for (int i = 0; i < flights.size(); i++) {
            f = flights.get(i);
            if (f.getFlightNumber() == i) {
                return f;
            }
        }
        return f;
    }
}
