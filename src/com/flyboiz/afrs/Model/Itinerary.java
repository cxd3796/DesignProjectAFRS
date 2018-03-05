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

	// Standard getter
	public int getFare(){
		return fare;
	}

	// Returns departure time of the first flight
	public Time getDepartureTime(){
		Flight departureFlight = flights.get(0);
		return departureFlight.getDepartureTime();
	}

	// Returns arrival time of the last flight.
	public Time getArrivalTime(){
		Flight arrivalFlight = flights.get(flights.size()-1);
		return arrivalFlight.getArrivalTime();
	}

	// ToString for Itineraries
	public String toString(){
		String output = String.format("%d,%d", fare, flights.size());
		for(Flight f:flights){
			output += String.format(",%s", f.toString());
		}
		return output;
	}


	public String toFileString()
	{
		String output = "";
		for (Flight f : flights)
		{
			output+= "," + f.getFlightNumber();
		}
		return output;
	}

}
