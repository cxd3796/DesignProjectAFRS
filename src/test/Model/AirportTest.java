package test.Model;

import com.flyboiz.afrs.Model.Airport;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class AirportTest {
	private Airport CuT;
	private String atlantaCode = "ATL";
	private String atlantaName = "Atlanta";
	private String sunnyCondition = "Sunny";
	private int sunnyTemperature = 9001;
	private int delayTime = 6969;


	@Before
	public void setup() {
		CuT = new Airport(atlantaCode);
	}

	@Test
	public void storingAirportWeather() {
		CuT.storeAirportWeather(sunnyCondition, sunnyTemperature);
		CuT.setDelayTime(delayTime);
		CuT.storeAirportName(atlantaName);

		assertEquals("Atlanta,Sunny,9001,6969", CuT.toString());
	}

}
