package com.flyboiz.afrs.Model;

import java.util.List;

public class Itinerary
{
	private List<Flight> flights;
	private String origin;
	private String destination;
	private int time;
	private int fare;


	public Itinerary (List<Flight> flights)
	{
		this.flights = flights;

	}
}
