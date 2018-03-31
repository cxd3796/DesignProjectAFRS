package com.flyboiz.afrs.Controller.Commands;

import com.flyboiz.afrs.Model.Itinerary;
import com.flyboiz.afrs.Model.Reservation;
import com.flyboiz.afrs.Model.ReservationDatabase;

/**
 * Handles the DeleteResevation Query
 */
public class QueryDelete extends Query {

	private String name;
	private String origin;
	private String destination;
	private ReservationDatabase reservationDB;
	private Itinerary itinerary; //used for purpose of undoing

	/**
	 * Constructs a concrete Query which will be used to delete a reservation from the database.
	 * @param cid client id
	 * @param name The name of the passenger
	 * @param origin The origin city
	 * @param destination The destination city
	 * @param reservationDB The Reservation Database
	 */
	public QueryDelete(int cid, String name, String origin, String destination, ReservationDatabase reservationDB) {
		super(cid);
		this.name = name;
		this.origin = origin;
		this.destination = destination;
		this.reservationDB = reservationDB;
	}

	/**
	 * Removes a reservation from the reservation database.
	 *
	 * @return String that represents the result of the operation
	 */
	public String generateResponse() {
		Reservation reservation = reservationDB.retrieveReservations(name, origin, destination).get(0);
		itinerary = reservation.getItinerary();
		if (reservationDB.deleteReservation(name, origin, destination)) {
			return "delete,successful";
		} else {
			reservationDB.deleteReservation(name, origin, destination);
			return "error,reservation not found";
		}
	}

	public void undo(){
		reservationDB.bookReservation(itinerary, name);
	}
}
