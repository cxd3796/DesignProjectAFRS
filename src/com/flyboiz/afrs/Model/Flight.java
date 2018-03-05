/* Authors
Kent Brown
 */

/* Package */
package com.flyboiz.afrs.Model;

/* Imports */

/* Implementation */
public class Flight {
	// State //
	private String originAirport;
	private String destinationAirport;
	private Time departureTime;
	private Time arrivalTime;
	private int flightNumber;
	private int airfare;

	// Constructors //
	public Flight(String originAirport,
				  String destinationAirport,
				  Time departureTime,
				  Time arrivalTime,
				  int flightNumber,
				  int airfare) {
		this.originAirport = originAirport;
		this.destinationAirport = destinationAirport;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.flightNumber = flightNumber;
		this.airfare = airfare;
	}

	// Getters & Setters //

	/**
	 * Basic getter to retrieve flight number. //
	 * @return the flight's flight number
	 */
	int getFlightNumber() {
		return this.flightNumber;
	}

	/**
	 * Get the flight's airfare (basic getter)
	 * @return the flights
	 */
	int getAirfare() {
		return this.airfare;
	}

	/**
	 * Get the flight's origin airport (code form)
	 * @return the flight's origin
	 */
	String getOrigin() {
		return this.originAirport;
	}

	/**
	 * Get the destination airport for this flight
	 * @return the destination airport for this flight
	 */
	String getDestination() {
		return this.destinationAirport;
	}

	/**
	 * Basic getter for departure time
	 * @return departure time
	 */
	Time getDepartureTime() {
		return departureTime;
	}

	/**
	 * Get arrival time
	 * @return arrival time
	 */
	Time getArrivalTime() {
		return arrivalTime;
	}

	//

	/**
	 * Return format FlightNumber,OriginAirport,DepartureTime,DestinationAirport,ArrivalTime
	 * @return toString
	 */
	public String toString() {
		return String.format("%d,%s,%s,%s,%s", flightNumber, originAirport, departureTime.toString(), destinationAirport, arrivalTime.toString());
	}
}
