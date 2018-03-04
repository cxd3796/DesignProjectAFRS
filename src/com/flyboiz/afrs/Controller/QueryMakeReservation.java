package com.flyboiz.afrs.Controller;

import Model.ReservationDatabase;

public class QueryMakeReservation implements Query {
	private int id;
	private String name;
	private ReservationDatabase reservationDB;

	public QueryMakeReservation(int id, String name, ReservationDatabase reservationDB){
		this.id = id;
		this.name = name;
		this.reservationDB = reservationDB;
	}

	public String generateResponse()
	{
		return null;
	}
}
