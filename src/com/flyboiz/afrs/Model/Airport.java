/* Authors
Calvin Do
Kent Brown
*/

/* Package */
package com.flyboiz.afrs.Model;

/* Imports */

import java.util.ArrayList;
import java.util.List;

/* Implementation */
public class Airport {

	private String airportCode;
	private String airportName;
	private int connectionTime;
	private int delayTime;
	private List<Weather> weatherList;
	private int weatherIndex;

	/**
	 * Airport Constructor.
	 * Only adds the airportCode. Everything else has to be added later.
	 * @param airportCode this is the code for the airport
	 */
	public Airport(String airportCode) {
		this.airportCode = airportCode;
		connectionTime = 0;
		delayTime = 0;
		weatherList = new ArrayList<>();
		weatherIndex = 0;
	}

	/**
	 * Add new weather state into the airport (the two parameters are paired).
	 * @param condition the type of condition to store in this airport.
	 * @param temperature the temperature to store in this airport.
	 */
	public void storeAirportWeather(String condition, int temperature) {
		weatherList.add(new Weather(condition, temperature, delayTime));
	}

	/**
	 * Adds airport name into the airport
	 *
	 * @param airportName - String, name of the airport. Ex jfk-New York City
	 */
	public void storeAirportName(String airportName) {
		this.airportName = airportName;
	}

	/**
	 * Set connection time
	 *
	 * @param connectionTime- connection time given by read file
	 */
	public void setConnectionTime(int connectionTime) {
		this.connectionTime = connectionTime;
	}

	/**
	 * Set delay time
	 *
	 * @param delayTime- delay time given by the read file
	 */
	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}

	/**
	 * Basic getter to get the airport's connection time.
	 * @return the airport's connection time
	 */
	public int getConnectionTime() {
		return connectionTime;
	}

	/**
	 * Basic getter for the delay time
	 * @return the delay time
	 */
	public int getDelayTime() {
		return delayTime;
	}

	/**
	 * Get Weather object in the weatherList.
	 * Every time this function is called, go to the next weather condition
	 *
	 * @return Weather object
	 */
	private Weather getWeather() {
		int currentIndex = weatherIndex;
		if (weatherIndex + 1 == weatherList.size()) {
			weatherIndex = 0;
		} else {
			weatherIndex += 1;
		}
		return weatherList.get(currentIndex);
	}

	/**
	 * @return airport-name,weather,temperature,delay in string format
	 */
	@Override
	public String toString() {
		return String.format("%s,%s,%d", airportName, getWeather(), delayTime);
	}

}
