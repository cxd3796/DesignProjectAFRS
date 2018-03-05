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
	 * Weather class to track Weather for airports easier
	 */
	private class Weather {

		private String condition;
		private int temperature;

		public Weather(String condition, int temperature) {
			this.condition = condition;
			this.temperature = temperature;
		}

		@Override
		public String toString() {
			return String.format("%s,%d", condition, temperature);
		}
	}

	/**
	 * Airport Constructor.
	 * Only adds the airportCode. Everything else has to be added later.
	 *
	 * @param airportCode
	 */
	public Airport(String airportCode) {
		this.airportCode = airportCode;
		connectionTime = 0;
		delayTime = 0;
		weatherList = new ArrayList<>();
		weatherIndex = 0;
	}

	/**
	 * Add new weather state into the airport.
	 *
	 * @param condition
	 * @param temperature
	 */
	public void storeAirportWeather(String condition, int temperature) {
		weatherList.add(new Weather(condition, temperature));
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

	public int getConnectionTime() {
		return connectionTime;
	}

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
