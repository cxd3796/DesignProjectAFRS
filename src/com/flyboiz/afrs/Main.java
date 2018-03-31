/* Authors:
Kent Brown
*/

// Package //
package com.flyboiz.afrs;

// Imports //

import com.flyboiz.afrs.Controller.Commands.Query;
import com.flyboiz.afrs.Controller.QueryCreator;
import com.flyboiz.afrs.Controller.QueryDecider;
import com.flyboiz.afrs.Controller.QueryExecutor;
import com.flyboiz.afrs.View.InputReader;
import com.flyboiz.afrs.View.OutputSender;
import com.flyboiz.afrs.Model.AirportDatabase;
import com.flyboiz.afrs.Model.FlightDatabase;
import com.flyboiz.afrs.Model.ReadFile;
import com.flyboiz.afrs.Model.ReservationDatabase;

import java.util.HashMap;
import java.util.Map;

// Implementation //
public class Main {
	public static void main(String[] args) {
		//Instantiate Databases and store data
		AirportDatabase airportDatabase = new AirportDatabase();
		FlightDatabase flightDatabase = new FlightDatabase(airportDatabase);
		ReservationDatabase reservationDatabase = new ReservationDatabase(flightDatabase);
		ReadFile readFile = new ReadFile(flightDatabase, airportDatabase, reservationDatabase);

		readFile.storeData();

		//Instantiate factories//
		//TODO Add factory instantiations here

		Map<String, QueryCreator> factoryMap = new HashMap<String, QueryCreator>();
		String[] queryTypes = {"connect", "disconnect", "info", "reserve", "retrieve", "delete", "undo", "redo", "airport", "server" };
		QueryCreator[] queryCreators = {new QConnectCreator(), new QDisconnectCreator(), new QMakeReservationCreator(),
										new QRetrieveReservationCreator(), new QDeleteReservationCreator(), new QAirportInfoCreator(),
										new QUndoCreator(), new QRedoCreator(), new QServerCreator(), new QItineraryInfoCreator() };
		for (int i = 0; i < queryTypes.length; i++)
		{
			factoryMap.put(queryTypes[i], queryCreators[i]);

		}

		QueryDecider queryDecider = new QueryDecider(factoryMap);


		// Instantiate controller objects. //
		QueryExecutor queryExecutor;
		InputReader reader = new InputReader();
		OutputSender output = new OutputSender();
		queryExecutor = new QueryExecutor(output, queryDecider);
		reader.setExecutor(queryExecutor);
		reader.setSender(output);

		// start going
		reader.waitOnInput();
	}

}
