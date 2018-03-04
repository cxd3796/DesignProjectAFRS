/* Authors:
Kent Brown
*/

// Package //
package com.flyboiz.afrs;

// Imports //

import com.flyboiz.afrs.Controller.QueryExecutor;
import com.flyboiz.afrs.Controller.QueryMaker;
import com.flyboiz.afrs.View.InputReader;
import com.flyboiz.afrs.View.Output;
import com.flyboiz.afrs.View.OutputSender;
import com.flyboiz.afrs.Model.AirportDatabase;
import com.flyboiz.afrs.Model.FlightDatabase;
import com.flyboiz.afrs.Model.ReadFile;
import com.flyboiz.afrs.Model.ReservationDatabase;

// Implementation //
public class Main
{
    public static void main(String[] args) {
    	//Instantiate Databases and store data
        FlightDatabase flightDatabase = new FlightDatabase();
        AirportDatabase airportDatabase = new AirportDatabase();
        ReservationDatabase reservationDatabase = new ReservationDatabase(flightDatabase);
        ReadFile readFile = new ReadFile(flightDatabase,airportDatabase,reservationDatabase);

        readFile.storeData();

        // Instantiate controller objects. //
		QueryExecutor queryExecutor = new QueryExecutor();
        QueryMaker queryMaker = new QueryMaker(queryExecutor, flightDatabase, airportDatabase, reservationDatabase);
        InputReader reader = new InputReader(queryMaker);
        Output output = new OutputSender(reader);

    }

}
