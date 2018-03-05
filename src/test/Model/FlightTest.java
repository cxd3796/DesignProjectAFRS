package test.Model;

import com.flyboiz.afrs.Model.Flight;
import com.flyboiz.afrs.Model.Time;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class FlightTest
{
	private String originAirport = "ATL";
	private String destinationAirport = "JFK";
	private Time departureTime;
	private Time arrivalTime;
	private int flightNumber = 101;
	private int airfare = 999;

	private Flight CuT;

	private String flightToString;

	@Before
	public void setup()
	{
		departureTime = new Time("8:00a");
		arrivalTime = new Time("5:00p");

		CuT = new Flight(originAirport,destinationAirport,departureTime,arrivalTime,flightNumber,airfare);
		flightToString =  flightNumber + "," + originAirport + "," + departureTime.toString() + "," + destinationAirport + "," + arrivalTime.toString();
	}

	@Test
	public void flightCreation()
	{
		assertEquals(flightToString, CuT.toString());
	}

	@Test
	public void flightNotNullUponCreation()
	{
		assertNotNull(CuT);
	}

}
