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
        AirportDatabase airportDatabase = new AirportDatabase();
        FlightDatabase flightDatabase = new FlightDatabase(airportDatabase);
        ReservationDatabase reservationDatabase = new ReservationDatabase(flightDatabase);
        ReadFile readFile = new ReadFile(flightDatabase,airportDatabase,reservationDatabase);

        readFile.storeData();

        // Instantiate controller objects. //
        QueryMaker queryMaker = new QueryMaker(flightDatabase, airportDatabase, reservationDatabase);
        QueryExecutor queryExecutor;
        InputReader reader = new InputReader();
        OutputSender output = new OutputSender();
        queryExecutor = new QueryExecutor(output, queryMaker);
        reader.setExecutor(queryExecutor);
        reader.setSender(output);

        // start going
        reader.waitOnInput();
    }

}
