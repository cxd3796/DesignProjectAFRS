package com.flyboiz.afrs.Model;

import java.util.ArrayList;
import java.util.List;

public class ReservationDatabase
{
	private List<Reservation> reservations;
	private FlightDatabase flightDatabase;

	public ReservationDatabase(FlightDatabase flightDatabase)
	{
		this.flightDatabase = flightDatabase;
	}

	public void bookReservation(Itinerary itinerary, String passengerName)
	{
		Reservation reservation = new Reservation(itinerary, passengerName);
		reservations.add(reservation);
	}

	public void storeReservation(String passengerName, List<String> flightNumbers )
	{
		List<Flight> flightList = new ArrayList<>();
		for(String number : flightNumbers)
		{
		//	flightList.add(flightDatabase.getFlight(number));
		}
		//Itinerary itinerary = flightDatabase.createItinerary(flightList);
		//Reservation reservation = new Reservation(itinerary, passengerName);
	}

	public void deleteReservation(String passengerName, String origin, String destination)
	{

	}


}
