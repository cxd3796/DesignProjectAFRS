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

	// Constructor
	public ReservationDatabase(FlightDatabase flightDatabase)
	{
		this.flightDatabase = flightDatabase;
	}

	// Create a reservation object and add it to the list of reservations
	public void bookReservation(Itinerary itinerary, String passengerName) {
		Reservation reservation = new Reservation(itinerary, passengerName);
		reservations.add(reservation);
	}

	// Create a reservation from data and book it
	public void storeReservation(String passengerName, List<String> flightNumbers ) {
		List<Flight> flightList = new ArrayList<>();
		for(String number : flightNumbers) {
			flightList.add(flightDatabase.getFlightFromNumber(Integer.parseInt(number)));
		}
		Itinerary itinerary = flightDatabase.createItinerary(flightList);
		bookReservation(itinerary, passengerName);
	}

	public void deleteReservation(String passengerName, String origin, String destination) {
		for (Reservation r : reservations) {
			if(r.getPassengerName().equals(passengerName)){
				Itinerary i = r.getItinerary();
				if(i.getOrigin().equals(origin) && i.getDestination().equals(destination)){
					reservations.remove(r);
				}
			}
		}
	}


}
