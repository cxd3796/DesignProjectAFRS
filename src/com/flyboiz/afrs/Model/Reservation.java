package com.flyboiz.afrs.Model;

public class Reservation
{
	private Itinerary itinerary;
	private String passengerName;

	public Reservation(Itinerary itinerary, String passengerName)
	{
		this.itinerary = itinerary;
		this.passengerName = passengerName;
	}


}
