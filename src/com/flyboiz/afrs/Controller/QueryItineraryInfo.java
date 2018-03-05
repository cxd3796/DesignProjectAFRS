package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Model.AirportDatabase;
import com.flyboiz.afrs.Model.FlightDatabase;
import com.flyboiz.afrs.Model.Itinerary;
import com.flyboiz.afrs.Controller.Query;
import com.flyboiz.afrs.Controller.SortStrategy;
import com.flyboiz.afrs.Model.FlightDatabase;

import java.util.List;

public class QueryItineraryInfo implements Query {
	private String origin;
	private String destination;
	private int maxConnection;
	private SortStrategy sortType;
	private FlightDatabase flightDB;
	private List<Itinerary> itineraries;
	private AirportDatabase airportDB;

	/**
	 * Constructs a query command that will retrieve potential itineraries from the Flight Database and Airport Database
	 * @param origin Origin City
	 * @param destination Destination City
	 * @param maxConnection The maximum number of connections
	 * @param sortType A sort strategy that is created by the QueryMaker
	 * @param flightDB The flight database that holds all of the flights
	 * @param airportDB The airport database that holds all of the airports
	 */
	public QueryItineraryInfo(String origin, String destination, int maxConnection, SortStrategy sortType,
							  FlightDatabase flightDB, AirportDatabase airportDB) {
		this.origin = origin;


		this.destination = destination;
		this.maxConnection = maxConnection;
		this.sortType = sortType;
		this.flightDB = flightDB;
		this.airportDB = airportDB;
	}

	/**
	 * Generates a response when invoked sorted by the sort strategy
	 * @return A list of ordered itineraries
	 */
	public String generateResponse() {
		if (!airportDB.isAirportReal(origin)) {
			return "error,unknown origin";
		}
		if (!airportDB.isAirportReal(destination)) {
			return "error,unknown destination";
		}
		if (maxConnection < 0 || maxConnection > 2) {
			return "error,invalid connection limit";
		}
		if (sortType == null) {
			return "error,invalid sort order";
		}
		itineraries = flightDB.getPotentialItineraries(origin, destination, maxConnection);
		int n = itineraries.size();
		String response = "info," + n;
		sortType.sort(itineraries);
		for (int i = 0; i < n; i++) {
			response = response + "\n" + (i + 1) + "," + itineraries.get(i).toString();
		}
		return response;
	}

	/**
	 * Provides a way to retrive an Itinerary from the list of generated itineraries
	 * @param index Index within the list of itineraries
	 * @return Returns the Itinerary at the index within the list of itineraries
	 */
	public Itinerary getItinerary(int index) {
		return itineraries.get(index - 1);
	}

	/**
	 * Retrieve all of the itineraries that were queried by the user
	 * @return An arraylist of itineraries that were requested
	 */
	public List<Itinerary> getItineraries() {
		return itineraries;
	}
}
