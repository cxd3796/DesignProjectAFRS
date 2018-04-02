/* Authors
Calvin Do
Kent Brown
*/

/* Package */
package com.flyboiz.afrs.Model;

/* Imports */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* Implementation */
public class Airport {

	private String airportCode;
	private String airportName;
	private int connectionTime;
	private List<Weather> weatherList;
	private HashMap<Integer, WeatherStratum> weatherStratums;
	private HashMap<String, WeatherStratum> weatherServers;
	private int weatherIndex;

	private LocalWeather localWeather;

	/**
	 * Airport Constructor.
	 * Only adds the airportCode. Everything else has to be added later.
	 * @param airportCode this is the code for the airport
	 */
	public Airport(String airportCode) {
		this.airportCode = airportCode;
		connectionTime = 0;
		weatherList = new ArrayList<>();
		weatherIndex = 0;
		weatherServers = new HashMap<>();
        weatherStratums = new HashMap<>();
        localWeather = new LocalWeather(weatherList);
        generateWeatherServers();

	}

    /**
     * Populates the hashmap of concrete strategies.
     */
	private void generateWeatherServers() {
	    weatherServers.put("faa", new FAAProxy(airportCode));
	    weatherServers.put("local", localWeather);
    }

	/**
	 * Add new weather state into the airport (the two parameters are paired).
	 * @param condition the type of condition to store in this airport.
	 * @param temperature the temperature to store in this airport.
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
		localWeather.setName(airportName);
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
        localWeather.setDelay(delayTime);
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
	public int getDelayTime(int cid) {
        if (!weatherStratums.containsKey(cid)) {
            weatherStratums.put(cid, weatherServers.get("local"));
        }
        WeatherStratum stratum = weatherStratums.get(cid);
        return stratum.getDelay();
	}

	/**
	 * Get Weather object in the weatherList.
	 * Every time this function is called, go to the next weather condition
	 *
	 * @return String weather.
	 */
	public String getWeather(int cid) {
		if (!weatherStratums.containsKey(cid)) {
		    weatherStratums.put(cid, weatherServers.get("local"));
        }
        WeatherStratum stratum = weatherStratums.get(cid);
		return stratum.getWeather(cid);
	}

    /**
     * Sets the weather server of the user
     *
     * @param cid client id
     */
	public void setServer(int cid, String server) {
        if (!weatherStratums.containsKey(cid)) {
            weatherStratums.put(cid, weatherServers.get("local"));
        }
	   weatherStratums.put(cid, weatherServers.get(server));
    }


}
