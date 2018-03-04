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

	public boolean deleteReservation(String passengerName, String origin, String destination) {
		for (Reservation r : reservations) {
			if(r.getPassengerName().equals(passengerName)){
				Itinerary i = r.getItinerary();
				if(i.getOrigin().equals(origin) && i.getDestination().equals(destination)){
					reservations.remove(r);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns all of the reservations that matches the passenger's name
	 * @param pName- name of the passenger
	 * @return list of all reservations that match
	 */
	public List<Reservation> retrieveReservations(String pName){
		ArrayList<Reservation> matchedReservations = new ArrayList<>();
		for (Reservation r:reservations){
			if (pName.equals(r.getPassengerName())){
				matchedReservations.add(r);
			}
		}
		return matchedReservations;
	}

	/**
	 * Returns all of the reservations that matches the passenger's name and the origin
	 * @param pName- name of the passenger
	 * @param origin- code of the origin airport
	 * @return list of all reservations that match
	 */
	public List<Reservation> retrieveReservations(String pName, String origin){
		ArrayList<Reservation> matchedReservations = new ArrayList<>();
		for (Reservation r:reservations){
			if (pName.equals(r.getPassengerName()) && origin.equals(r.getOrigin())){
				matchedReservations.add(r);
			}
		}
		return matchedReservations;
	}

	/**
	 * Returns all of the reservations that matches the passenger's name, the origin, and destination
	 * @param pName- name of the passenger
	 * @param origin- code of the origin airport
	 * @param des- code of the destination airport
	 * @return list of all reservations that match
	 */
	public List<Reservation> retrieveReservations(String pName, String origin, String des){
		ArrayList<Reservation> matchedReservations = new ArrayList<>();
		for (Reservation r:reservations){
			if (pName.equals(r.getPassengerName()) && origin.equals(r.getOrigin()) && des.equals(r.getDestination())){
				matchedReservations.add(r);
			}
		}
		return matchedReservations;
	}

}
