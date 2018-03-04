/* Authors
Kent Brown
 */

// Package //
package com.flyboiz.afrs.Model;

// Imports //
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// Implementation //
public class FlightDatabase {

    // State //
    private List<Flight> flights;

    // Constructors //
    public FlightDatabase() {
        this.flights = new ArrayList<>();
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
    private List<Flight> getFlightsToDestination(String destinationCode) {
        List<Flight> possibleFlights = new LinkedList<>();
        for (Flight f : flights) {
            if (f.getOrigin().equals(destinationCode)) {
                possibleFlights.add(f); // add it to the returned list if it has that as the origin
            }
        }
        return possibleFlights;
    }

    /**
     * Gets a potential list of itineraries which satisfy the parameters
     * @param originCode the origin airport of the itinerary
     * @param destinationCode the destination airport for the itinerary
     * @param maxConnections the maximum number of connections
     * @return the list of itineraries
     */
    public List<Itinerary> getPotentialItineraries(String originCode, String destinationCode, int maxConnections) {
        // code
        List<Itinerary> potentialItineraries = new LinkedList<>();

        // sanity check, can't have fewer than 0 connecting flights, nor more than 2 (2 is default)
        if (maxConnections < 0 || maxConnections > 2) {
            maxConnections = 2;
        }

        // dfs
        recursiveTryItinerary(getFlightsFromOrigin(originCode), destinationCode, potentialItineraries, new LinkedList<Flight>(), maxConnections);

        // stub code
        return potentialItineraries;
    }

    // A private method to get a flight whose flightNumber matches the provided parameter
    Flight getFlightFromNumber(int flightNumber) {
        Flight f = null;
        for (int i = 0; i < flights.size(); i++) {
            f = flights.get(i);
            if (f.getFlightNumber() == i) {
                return f;
            }
        }
        return f;
    }

    // A private method to copy all the flights from one list into a new, separate list
    // This is required in order to ensure that the flights in the itinerary are not modified by reference
    private List<Flight> copyFlights(List<Flight> flights) {
        List<Flight> newList = new LinkedList<>();
        newList.addAll(flights);
        return newList;
    }

    /**
     * Recursive function to build lists of itineraries.
     * @param checkFlights the list of flights to check on this level of recursion
     * @param destinationCode the final destination
     * @param existingItineraries the current set of existing itineraries
     * @param currentFlights the current set of flights for this level of recursion
     * @param depth the current depth
     */
    private void recursiveTryItinerary(List<Flight> checkFlights, String destinationCode, List<Itinerary> existingItineraries, List<Flight> currentFlights, int depth) {
        String nextOrigin = "";
        System.out.println(checkFlights);
        for (Flight f : checkFlights) {
            nextOrigin = f.getDestination();
            currentFlights.add(f);
            if (f.getDestination().equals(destinationCode)) {
                // add a newly created itinerary whose flights are a copy of the current set of flights.
                existingItineraries.add(createItinerary(copyFlights(currentFlights)));
            } else if (depth > 0) {
                recursiveTryItinerary(getFlightsFromOrigin(nextOrigin), destinationCode, existingItineraries, currentFlights,depth - 1);
            }
            currentFlights.remove(f);
        }
    }

}
