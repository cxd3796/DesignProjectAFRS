package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Model.Itinerary;
import com.flyboiz.afrs.Model.Reservation;
import com.flyboiz.afrs.Model.ReservationDatabase;

import java.util.List;

public class QueryMakeReservation implements Query {
	private int id;
	private String name;
	private ReservationDatabase reservationDB;
	private QueryExecutor queryExecutor;

	public QueryMakeReservation(int id, String name, ReservationDatabase reservationDB, QueryExecutor queryExecutor){
		this.id = id;
		this.name = name;
		this.reservationDB = reservationDB;
		this.queryExecutor = queryExecutor;
	}

	/**
	 * Creates a string that tells the user whether or not the operation was successful
	 * @return String- error or success
	 */
	public String generateResponse() {
		if(queryExecutor.getLastQuery() instanceof QueryItineraryInfo){
			QueryItineraryInfo lastQuery = (QueryItineraryInfo)queryExecutor.getLastQuery();
			if(id < 0 || id>= lastQuery.getItineraries().size()){
				return "error,invalid id";
			}
			Itinerary reserving =lastQuery.getItinerary(id);
			List<Reservation> check = reservationDB.retrieveReservations(name, reserving.getOrigin(), reserving.getDestination());
			if(check.size()>0){
				return "error,duplicate reservation";
			}
			else{
				reservationDB.bookReservation(reserving, name);
				return "reserve,successful";
			}
		}

		return null;
	}
}
