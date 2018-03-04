/* Authors
Calvin Do
Kent Brown
 */

/* Package */
package com.flyboiz.afrs.Model;

/* Import */
import java.util.ArrayList;
import java.util.List;

/* Implementation */
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
			flightList.add(flightDatabase.getFlightFromNumber(Integer.parseInt(number)));
		}
		Itinerary itinerary = flightDatabase.createItinerary(flightList);
		Reservation reservation = new Reservation(itinerary, passengerName);
	}

	public void deleteReservation(String passengerName, String origin, String destination)
	{

	}


}
