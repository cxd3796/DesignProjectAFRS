package com.flyboiz.afrs.Controller.Commands;

import com.flyboiz.afrs.Model.Itinerary;
import com.flyboiz.afrs.Model.Reservation;
import com.flyboiz.afrs.Model.ReservationDatabase;

import java.util.List;

public class QueryMake extends Query {
	private int itineraryId;
	private String name;
	private ReservationDatabase reservationDB;
	private Query lastQuery;
	private Itinerary reserving; //itinerary that's being reserved

	/**
	 * Constructor for Query command for creating reservations
	 * @param cid client id
	 * @param itineraryId The number that corresponds to the itinerary that was previously queried
	 * @param name The name of the passenger
	 * @param reservationDB The reservation database
	 * @param lastQuery The last query object that was created by the factory (QueryMaker)
	 */
	public QueryMake(int cid, int itineraryId, String name, ReservationDatabase reservationDB, Query lastQuery) {
		super(cid);
		this.itineraryId = itineraryId;
		this.name = name;
		this.reservationDB = reservationDB;
		this.lastQuery = lastQuery;
	}

	/**
	 * Creates a string that tells the user whether or not the operation was successful
	 *
	 * @return String- error or success
	 */
	public String generateResponse() {
		if (lastQuery instanceof QueryInfo) {
			QueryInfo itineraryQuery = (QueryInfo) lastQuery;
			if (itineraryId < 0 || itineraryId > itineraryQuery.getItineraries().size()) {
				return "error,invalid id";
			}
			reserving = itineraryQuery.getItinerary(itineraryId);
			List<Reservation> check = reservationDB.retrieveReservations(name, reserving.getOrigin(), reserving.getDestination());
			if (check.size() > 0) {
				return "error,duplicate reservation";
			} else {
				reservationDB.bookReservation(reserving, name);
				return "reserve,successful";
			}
		}
		return null;
	}

	public void undo(){
		reservationDB.deleteReservation(name, reserving.getOrigin(), reserving.getDestination() );
	}
}
