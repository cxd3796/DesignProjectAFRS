package com.flyboiz.afrs.Model;

import java.util.List;

public class Itinerary {

	private List<Flight> flights;
	private String origin;
	private String destination;
	private int time;
	private int fare = -1;

	// CONSTRUCTOR //
	public Itinerary(List<Flight> flights) {
		this.flights = flights;
		calculateAirfare();
		origin = flights.get(0).getOrigin();                            // the origin of the itinerary is the origin of the first flight
		destination = flights.get(flights.size() - 1).getDestination(); // the destination is the destination of the last flight
	}

	/**
	 * Method to set the itineraries Airfare.
	 */
	private void calculateAirfare() {
		this.fare = 0;
		for (Flight f : flights) {
			this.fare = this.fare + f.getAirfare();
		}
	}

	/**
	 * Standard getter for the origin airport.
	 *
	 * @return the origin airport
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * Get the destination of the airport.
	 *
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Return the fare
	 *
	 * @return the fare of the itinerary (total cost of all flights)
	 */
	public int getFare() {
		return fare;
	}

	/**
	 * The departure time of the itinerary is the departure time of the first flight.
	 *
	 * @return the departure time
	 */
	public Time getDepartureTime() {
		Flight departureFlight = flights.get(0);
		return departureFlight.getDepartureTime();
	}

	/**
	 * The arrival time of the itinerary is the arrival time of the last flight.
	 *
	 * @return the arrival time of the itinerary
	 */
	public Time getArrivalTime() {
		Flight arrivalFlight = flights.get(flights.size() - 1);
		return arrivalFlight.getArrivalTime();
	}

	/**
	 * A string representation of the itinerary
	 *
	 * @return the string representation of the itinerary
	 */
	public String toString() {
		String output = String.format("%d,%d", fare, flights.size());
		for (Flight f : flights) {
			output += String.format(",%s", f.toString());
		}
		return output;
	}


	public String toFileString() {
		String output = "";
		for (Flight f : flights) {
			output += "," + f.getFlightNumber();
		}
		return output;
	}

}
