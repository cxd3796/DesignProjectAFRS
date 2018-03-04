package Controller;

import Model.Reservation;
import Model.ReservationDatabase;

public class QueryRetrieveReservation implements Query {
	private String name;
	private String origin;
	private String destination;
	private ReservationDatabase reservationDB;

	public QueryRetrieveReservation(String name, String origin, String destination, ReservationDatabase reservationDB ){
		this.name = name;
		this.origin = origin;
		this.destination = destination;
		this.reservationDB= reservationDB;
	}

	public String generateResponse()
	{
		return null;
	}
}
