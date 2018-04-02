package com.flyboiz.afrs.Controller.Commands;

import com.flyboiz.afrs.Model.AirportDatabase;

public class QueryAirport extends Query {
	private String airport;
	private AirportDatabase airportDB;

	/**
	 * Constructs a concrete Query object using the inputted Airport Code and Airport Database
	 * @param cid client id
	 * @param airport Airport Code
	 * @param airportDB Airport Database
	 */
	public QueryAirport(int cid, String airport, AirportDatabase airportDB) {
		super(cid);
		this.airport = airport;
		this.airportDB = airportDB;
	}

	/**
	 * Gets a String representation of the airport
	 *
	 * @return String airportCode,weather,temperature,delay in string format
	 */
	public String generateResponse() {
		if(!airportDB.isAirportReal(airport)){
			return cid+"error,unknown airport";
		}
		return cid+",airport,"+ airportDB.getAirportInfo(cid, airport);
	}
}