/* Authors
Kent Brown
 */

// Package //
package com.flyboiz.afrs.Model;

// Imports //
import java.util.ArrayList;
import java.util.LinkedList;
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

    public Itinerary createItinerary(List<Flight> flights) {
        return new Itinerary(flights);
    }

    /**
     * This method returns a list of flights with the given code as their origin
     * @param originCode only add flights to the list if they have this as their origin
     * @return a list of flights.
     */
    private List<Flight> getFlightsFromOrigin(String originCode) {
        List<Flight> possibleFlights = new LinkedList<>();
        for (Flight f : flights) {
            if (f.getOrigin().equals(originCode)) {
                possibleFlights.add(f); // add it to the returned list if it has that as the origin
            }
        }
        return possibleFlights;
    }

    /**
     * This method returns a list of flights
     * @param destinationCode only add flights to the list if they have this as their destination
     * @return a list of flights.
     */
    private List<Flight> getFlightsFromDestination(String destinationCode) {
        List<Flight> possibleFlights = new LinkedList<>();
        for (Flight f : flights) {
            if (f.getOrigin().equals(destinationCode)) {
                possibleFlights.add(f); // add it to the returned list if it has that as the origin
            }
        }
        return possibleFlights;
    }

    public List<Itinerary> getPotentialItineraries(String originCode, String destinationCode, int maxConnections, String sortOrder) {
        // code
        List<Itinerary> potentialItineraries = new LinkedList<>();

        // bfs


        // stub code
        return potentialItineraries;
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
