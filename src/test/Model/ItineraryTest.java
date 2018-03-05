package test.Model;

import com.flyboiz.afrs.Model.Flight;
import com.flyboiz.afrs.Model.Itinerary;
import com.flyboiz.afrs.Model.Time;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class ItineraryTest {
	private List<Flight> testFlights;
	private final String airport1 = "LAX";
	private final String airport2 = "JFK";
	private final String airport3 = "IAD";
	private final String airport4 = "ATL";


	private Flight flight1;
	private final Time time1 = new Time("8:00a");
	private final Time time2 = new Time("1:00p");
	private final int fnum1 = 101;
	private final int airfare1 = 901;

	private Flight flight2;
	private final Time time3 = new Time("9:00a");
	private final Time time4 = new Time("2:00p");
	private final int fnum2 = 102;
	private final int airfare2 = 902;

	private Flight flight3;
	private final Time time5 = new Time("10:00a");
	private final Time time6 = new Time("3:00p");
	private final int fnum3 = 103;
	private final int airfare3 = 903;

	private final String itineraryString = "2706,3,101,LAX,8:0a,JFK,1:0p,102,JFK,9:0a,IAD,2:0p,103,IAD,10:0a,ATL,3:0p";

	private Itinerary CuT;

	@Before
	public void setup() {
		testFlights = new ArrayList<>();
		flight1 = new Flight(airport1, airport2, time1, time2, fnum1, airfare1);
		flight2 = new Flight(airport2, airport3, time3, time4, fnum2, airfare2);
		flight3 = new Flight(airport3, airport4, time5, time6, fnum3, airfare3);
		testFlights.add(flight1);
		testFlights.add(flight2);
		testFlights.add(flight3);

		CuT = new Itinerary(testFlights);
	}

	@Test
	public void itineraryCreationValid() {
		assertNotNull(CuT);
	}

	@Test
	public void itineraryOriginAndDestination() {
		assertEquals(airport1, CuT.getOrigin());
		assertEquals(airport4, CuT.getDestination());
	}

	@Test
	public void itineraryAirfare() {
		assertEquals((airfare1 + airfare2 + airfare3), CuT.getFare());
	}

	@Test
	public void itineraryDepartureAndArrivalTime() {
		assertEquals(time1, CuT.getDepartureTime());
		assertEquals(time6, CuT.getArrivalTime());
	}

	@Test
	public void itineraryOutput() {
		assertEquals(itineraryString, CuT.toString());
	}

}
