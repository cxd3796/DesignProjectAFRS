package com.flyboiz.afrs.Model;

import java.util.List;

public class Itinerary
{
	private List<Flight> flights;
	private String origin;
	private String destination;
	private int time;
	private int fare;


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
}
