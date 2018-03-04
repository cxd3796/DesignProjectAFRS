package com.flyboiz.afrs.Model;

import java.util.List;

public class Itinerary
{
	private List<Flight> flights;
	private String origin;
	private String destination;
	private int time;
	private int fare = -1;


	public Itinerary (List<Flight> flights) {
		this.flights = flights;
		calculateAirfare();
		origin = flights.get(0).getOrigin(); 							// the origin of the itinerary is the origin of the first flight
		destination = flights.get(flights.size() - 1).getDestination(); // the destination is the destination of the last flight
	}

	private void calculateAirfare() {
		this.fare = 0;
		for (Flight f : flights) {
			this.fare = this.fare + f.getAirfare();
		}
	}

	// Standard getter
	public String getOrigin() {
		return origin;
	}

	// Standard getter
	public String getDestination() {
		return destination;
	}

	// Standard getter for airfare
	public int getAirfare() {
		if (fare == -1) {
			calculateAirfare();
		}
		return fare;
	}

	// get the flights as strings
	@Override
	public String toString() {
		String toString = getAirfare() + "," + flights.size();
		Flight f;
		for (int i = 0; i < flights.size(); i++) {
			toString += ",";
			f = flights.get(i);
			toString += f.toString();
		}
		return toString;
	}

}
