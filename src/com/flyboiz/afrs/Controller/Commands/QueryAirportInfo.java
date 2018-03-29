package com.flyboiz.afrs.Controller.Commands;

import com.flyboiz.afrs.Model.AirportDatabase;

public class QueryAirportInfo implements Query {
	private String airport;
	private AirportDatabase airportDB;

	/**
	 * Constructs a concrete Query object using the inputted Airport Code and Airport Database
	 * @param airport Airport Code
	 * @param airportDB Airport Database
	 */
	public QueryAirportInfo(String airport, AirportDatabase airportDB) {
		this.airport = airport;
		this.airportDB = airportDB;
	}

	/**
	 * Gets a String representation of the airport
	 *
	 * @return String airportCode,weather,temperature,delay in string format
	 */
	public String generateResponse() {
		return airportDB.getAirportInfo(airport);
	}
}