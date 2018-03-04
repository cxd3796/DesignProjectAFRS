package com.flyboiz.afrs.Model;

public class Reservation {

	private Itinerary itinerary;
	private String passengerName;

	public Reservation(Itinerary itinerary, String passengerName) {
		this.itinerary = itinerary;
		this.passengerName = passengerName;
	}

	// Standard getter
    public Itinerary getItinerary() {
        return itinerary;
    }

    // Standard getter
    public String getPassengerName() {
        return passengerName;
    }
}
