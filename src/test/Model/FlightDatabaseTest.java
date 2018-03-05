package test.Model;

import com.flyboiz.afrs.Model.AirportDatabase;
import com.flyboiz.afrs.Model.FlightDatabase;
import com.flyboiz.afrs.Model.Itinerary;
import com.flyboiz.afrs.Model.Time;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class FlightDatabaseTest {
	// STATE //
	private static final String AIRPORT1 = "ABC";
	private static final String AIRPORT2 = "DEF";
	private static final String AIRPORT3 = "GHI";

	private static final int CONNECTION_TIME = 15;
	private static final int DELAY_TIME = 0;

	private static final Time TIME1 = new Time(10, 0);
	private static final Time TIME2 = new Time(10, 30);
	private static final Time TIME3 = new Time(11, 0);
	private static final Time TIME4 = new Time(11, 30);
	private static final Time TIME5 = new Time(14, 0);
	private static final Time TIME6 = new Time(14, 30);

	private static final int AIRFARE = 60;
	private FlightDatabase CuT;
	private AirportDatabase additional;

	private static final String FLIGHT_OUTPUT_ONE = "120,2,1,ABC,10:0a,DEF,10:30a,2,DEF,11:0a,GHI,11:30a";
	private static final String FLIGHT_OUTPUT_TWO = "60,1,4,ABC,2:0p,GHI,2:30p";

	@Before
	public void setup() {
		additional = new AirportDatabase();
		CuT = new FlightDatabase(additional);
	}

	@Test
	public void potentialFlightGeneration() {
		CuT.generateFlight(AIRPORT1, AIRPORT2, TIME1, TIME2, 1, AIRFARE);
		CuT.generateFlight(AIRPORT2, AIRPORT3, TIME3, TIME4, 2, AIRFARE);
		CuT.generateFlight(AIRPORT2, AIRPORT1, TIME3, TIME4, 3, AIRFARE);
		CuT.generateFlight(AIRPORT1, AIRPORT3, TIME5, TIME6, 4, AIRFARE);
		CuT.generateFlight(AIRPORT1, AIRPORT2, TIME5, TIME6, 5, AIRFARE);
		additional.generateAirport(AIRPORT1);
		additional.generateAirport(AIRPORT2);
		additional.generateAirport(AIRPORT3);
		additional.storeAirportConnectionTime(AIRPORT1, CONNECTION_TIME);
		additional.storeAirportConnectionTime(AIRPORT2, CONNECTION_TIME);
		additional.storeAirportConnectionTime(AIRPORT3, CONNECTION_TIME);
		additional.storeAirportDelay(AIRPORT1, DELAY_TIME);
		additional.storeAirportDelay(AIRPORT2, DELAY_TIME);
		additional.storeAirportDelay(AIRPORT3, DELAY_TIME);

		List<Itinerary> result = CuT.getPotentialItineraries(AIRPORT1, AIRPORT3, 2);

		assertEquals(FLIGHT_OUTPUT_ONE, result.get(0).toString());
		assertEquals(FLIGHT_OUTPUT_TWO, result.get(1).toString());

	}

}
