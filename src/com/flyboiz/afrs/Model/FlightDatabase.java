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
	private AirportDatabase adb;

	private final int MAX_CONNECTIONS = 2;
	private final int DEFAULT_CONNECTIONS = 2;

	// Constructors //
	public FlightDatabase(AirportDatabase adb) {
		this.flights = new ArrayList<>();
		this.adb = adb;
	}

	// Behavior //

	/**
	 * Create a new flight and add it to the flight database.
	 * @param originAirport 		The origin airport.
	 * @param destinationAirport	The destination airport.
	 * @param departureTime			The departure time.
	 * @param arrivalTime			The arrival time.
	 * @param flightNumber			The flight number.
	 * @param airfare				The cost of the flight.
	 */
	public void generateFlight(String originAirport,
							   String destinationAirport,
							   Time departureTime,
							   Time arrivalTime,
							   int flightNumber,
							   int airfare) {
		Flight newFlight = new Flight(originAirport, destinationAirport, departureTime, arrivalTime, flightNumber, airfare);
		flights.add(newFlight);
	}

	/**
	 * Casually create an itinerary (basically just a constructor @kent)
	 * @param flights set of flights to put in the itinerary
	 * @return a newly created itinerary
	 */
	public Itinerary createItinerary(List<Flight> flights) {
		return new Itinerary(flights);
	}

	/**
	 * This method returns a list of flights with the given code as their origin
	 *
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
	 * Gets a potential list of itineraries which satisfy the parameters
	 *
	 * @param originCode      the origin airport of the itinerary
	 * @param destinationCode the destination airport for the itinerary
	 * @param maxConnections  the maximum number of connections
	 * @return the list of itineraries
	 */
	public List<Itinerary> getPotentialItineraries(String originCode, String destinationCode, int maxConnections) {
		// code
		List<Flight> flightsToCheck = getFlightsFromOrigin(originCode);
		List<Itinerary> potentialItineraries = new LinkedList<>();
		List<String> visitedAirports = new LinkedList<>();
		List<Flight> currentFlights = new LinkedList<>();

		// sanity check, can't have fewer than 0 connecting flights, nor more than 2 (2 is default)
		if (maxConnections < 0 || maxConnections > MAX_CONNECTIONS) {
			maxConnections = DEFAULT_CONNECTIONS;
		}

		// dfs
		recursiveTryItinerary(destinationCode, maxConnections, null, flightsToCheck, potentialItineraries, visitedAirports, currentFlights);

		// stub code
		return potentialItineraries;
	}

	/**
	 * Get the flight whose number matches the parameter
	 * @param flightNumber the number to search for
	 * @return the flight with the parameter number
	 */
	Flight getFlightFromNumber(int flightNumber) {
		Flight f = null;
		for (int i = 0; i < flights.size(); i++) {
			f = flights.get(i);
			if (f.getFlightNumber() == flightNumber) {
				return f;
			}
		}
		return f;
	}

	/**
	 * Copy a list of flights into a different list to prevent modification
	 * @param flights set of flights to copy into a new list of flights
	 * @return the copied list of flights
	 */
	private List<Flight> copyFlights(List<Flight> flights) {
		List<Flight> newList = new LinkedList<>();
		newList.addAll(flights);
		return newList;
	}

	/**
	 * Recursive function to build lists of itineraries.
	 *
	 * @param checkFlights        the list of flights to check on this level of recursion
	 * @param destinationCode     the final destination
	 * @param existingItineraries the current set of existing itineraries
	 * @param currentFlights      the current set of flights for this level of recursion
	 * @param depth               the current depth
	 */
	private void recursiveTryItinerary(String destinationCode, int depth, Time arrivalTime, List<Flight> checkFlights, List<Itinerary> existingItineraries, List<String> visitedAirports, List<Flight> currentFlights) {

		// this method does nothing if there are no flights so
		if (checkFlights.size() == 0) {
			return;
		}

		String nextOrigin = "";
		String currentOrigin = checkFlights.get(0).getOrigin();
		boolean recurse;
		int layoverTime = -1;
		visitedAirports.add(currentOrigin);
		for (Flight f : checkFlights) {

			// get a reference to the current departure time
			Time depTime = f.getDepartureTime();

			if (arrivalTime != null) {
				layoverTime = adb.getLayoverTime(f.getOrigin());
			}

			// determine if the thing needs to recurse
			if (currentFlights.size() == 0 || layoverTime == -1) {
				recurse = true;
			} else if (arrivalTime.stillBefore(depTime, layoverTime)) {
				recurse = true;
			} else {
				recurse = false;
			}

			// print debug, and add f to all the guacamole
			nextOrigin = f.getDestination();
			currentFlights.add(f);

			// determine whether to recurse or
			if (f.getDestination().equals(destinationCode)) {
				// add a newly created itinerary whose flights are a copy of the current set of flights.
				if (((arrivalTime != null) && arrivalTime.stillBefore(depTime, layoverTime)) || layoverTime == -1) {
					existingItineraries.add(createItinerary(copyFlights(currentFlights)));
				}
			} else if (depth > 0 && !(visitedAirports.contains(nextOrigin)) && recurse) {
				recursiveTryItinerary(destinationCode, depth - 1, f.getDepartureTime(), getFlightsFromOrigin(nextOrigin), existingItineraries, visitedAirports, currentFlights);
			}

			// remove f from all the guacamole
			currentFlights.remove(f);
		}
		visitedAirports.remove(currentOrigin);
	}

}
