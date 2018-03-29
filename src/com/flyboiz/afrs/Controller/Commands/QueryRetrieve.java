package com.flyboiz.afrs.Controller.Commands;

import com.flyboiz.afrs.Model.AirportDatabase;
import com.flyboiz.afrs.Model.Itinerary;
import com.flyboiz.afrs.Model.Reservation;
import com.flyboiz.afrs.Model.ReservationDatabase;

import java.util.List;

public class QueryRetrieve implements Query {

	private String name;
	private String origin;
	private String destination;
	private ReservationDatabase reservationDB;
	private AirportDatabase airportDB;

	/**
	 * Construct a command that will be used to retrieve a reservation from the databases.
	 * @param name The name of the passenger
	 * @param origin The origin city
	 * @param destination The destination city
	 * @param reservationDB The reservation database
	 * @param airportDB The airport database
	 */
	public QueryRetrieve(String name, String origin, String destination, ReservationDatabase reservationDB, AirportDatabase airportDB) {
		this.name = name;
		this.origin = origin;
		this.destination = destination;
		this.reservationDB = reservationDB;
		this.airportDB = airportDB;
	}

	/**
	 * Creates a user-friendly output version of the request response
	 * @return A request response to tell the user the result of the request
	 */
	public String generateResponse() {
		// Check origin airport for validity
		if (!airportDB.isAirportReal(origin) && !(origin.equals(""))) {
			return "error,unknown origin";
		}
		// Check destination airport for validity
		if (!airportDB.isAirportReal(destination) && !(destination.equals(""))) {
			return "error,unknown destination";
		}

		// Create output from reservations
		List<Reservation> reservations;
		if (origin.equals("")) {
			reservations = reservationDB.retrieveReservations(name);
		} else {
			if (destination.equals("")) {
				reservations = reservationDB.retrieveReservations(name, origin);
			} else {
				reservations = reservationDB.retrieveReservations(name, origin, destination);
			}
		}
		int amountOfReservations = reservations.size();
		// Begin string building
		String output = String.format("retrieve,%d", amountOfReservations);
		for (Reservation r : reservations) {
			Itinerary i = r.getItinerary();
			output += "\n" + i.toString();
		}
		return output;
	}
}
