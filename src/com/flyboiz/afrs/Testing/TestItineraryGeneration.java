/* created by Kent Brown on 3/4/2018 */

/* package */
package com.flyboiz.afrs.Testing;

/* imports */

import com.flyboiz.afrs.Model.FlightDatabase;
import com.flyboiz.afrs.Model.Itinerary;
import com.flyboiz.afrs.Model.Time;

import java.util.List;

/* implementation */
public class TestItineraryGeneration {

    // STATE //
    private static final String AIRPORT1 = "ABC";
    private static final String AIRPORT2 = "DEF";
    private static final String AIRPORT3 = "GHI";

    private static final Time TIME1 = new Time(10,0);
    private static final Time TIME2 = new Time( 10, 30);
    private static final Time TIME3 = new Time(11, 0);
    private static final Time TIME4 = new Time(11,30);
    private static final Time TIME5 = new Time(14, 0);
    private static final Time TIME6 = new Time(14, 30);

    private static final int AIRFARE = 60;

    // CONSTRUCTOR //

    // GETTERS & SETTERS //

    // BEHAVIOUR //
    public static void main(String args[]) {

        // create and populate test database
        FlightDatabase CuT = new FlightDatabase();
        CuT.generateFlight(AIRPORT1, AIRPORT2, TIME1, TIME2, 1, AIRFARE);
        CuT.generateFlight(AIRPORT2, AIRPORT3, TIME3, TIME4, 2, AIRFARE);
        CuT.generateFlight(AIRPORT2, AIRPORT1, TIME3, TIME4, 3, AIRFARE);
        CuT.generateFlight(AIRPORT1, AIRPORT3, TIME5, TIME6, 4, AIRFARE);

        // run it
        List<Itinerary> result = CuT.getPotentialItineraries(AIRPORT1, AIRPORT3, 2);
        for (Itinerary i : result) {
            System.out.println(i);
            System.out.println("");
        }
    }
}
