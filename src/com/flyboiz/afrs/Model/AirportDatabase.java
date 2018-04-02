package com.flyboiz.afrs.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AirportDatabase {
	private Map<String, Airport> airports;

	/**
	 * Constructor for AirportDatabase. Creates an empty HashMap for airports
	 */
	public AirportDatabase() {
		airports = new HashMap<>();
	}

	/**
	 * Adds a new airport object to the database
	 *
	 * @param airportCode
	 */
	public void generateAirport(String airportCode) {
		airports.put(airportCode, new Airport(airportCode));
	}

	/**
	 * Stores airport delay time to a specified airport
	 *
	 * @param airportCode - 3 letter string specifying the airport
	 * @param delayTime   - int representing the delay time
	 */
	public void storeAirportDelay(String airportCode, int delayTime) {
		Airport airport = airports.get(airportCode);
		airport.setDelayTime(delayTime);
	}

	/**
	 * Stores airport connection time to a specified airport
	 *
	 * @param airportCode    - 3 letter string specifying the airport
	 * @param connectionTime - int representing the connection time
	 */
	public void storeAirportConnectionTime(String airportCode, int connectionTime) {
		Airport airport = airports.get(airportCode);
		airport.setConnectionTime(connectionTime);
	}

	/**
	 * Stores airport weather conditions
	 *
	 * @param airportCode - 3 letter string specifying the airport
	 * @param condition   - String being on of the following: sunny, cloudy, partly cloudy, rain, partly sunny, or hazy
	 * @param temperature - int containing the temperature of the weather pair
	 */
	public void storeAirportWeather(String airportCode, String condition, int temperature) {
		Airport airport = airports.get(airportCode);
		airport.storeAirportWeather(condition, temperature);
	}

	/**
	 * Stores airport name
	 *
	 * @param airportCode- 3 letter string specifying the airport
	 * @param name-        Name of the airport city
	 */
	public void storeAirportName(String airportCode, String name) {
		Airport airport = airports.get(airportCode);
		airport.storeAirportName(name);
	}

	/**
	 * Gets the airport info string that describes the weather.
	 *
	 * @param cid
	 * @param airportCode
	 * @return String format: name, condition, temperature, delay
	 */
	public String getAirportInfo(int cid, String airportCode) {
		Airport airport = airports.get(airportCode);
		return airport.getWeather(cid);
	}

	/**
	 * Checks to see if the airportCode exists
	 *
	 * @param airportCode- 3 letter string specifying the airport
	 * @return true if the airport exists, false otherwise
	 */
	public Boolean isAirportReal(String airportCode) {
		return airports.containsKey(airportCode);
	}

	/**
	 *
	 * @param cid
	 * @param server
	 */
	public void setServer(int cid, String server) {
		for (Airport airport:airports.values()) {
			airport.setServer(cid, server);
		}
	}

	// GETTERS //

	/**
	 * Gets the current layover time
	 *
	 * @param airportCode the airport code of the airport whose layover you need
	 * @return the layover time
	 */
	public int getLayoverTime(String airportCode, int cid) {
		Airport ap = airports.get(airportCode);
		return ap.getConnectionTime() + ap.getDelayTime(cid);
	}


	public void setServer(int clientID, String infoServer) {
		// do nothing?
	}

}
