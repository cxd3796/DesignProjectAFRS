/* Authors:
Kent Brown
*/

// Package //
package com.flyboiz.afrs;

// Imports //

import com.flyboiz.afrs.Controller.QueryCreators.*;
import com.flyboiz.afrs.Controller.QueryDecider;
import com.flyboiz.afrs.Controller.QueryExecutor;
import com.flyboiz.afrs.Model.*;
import com.flyboiz.afrs.View.InputReader;
import com.flyboiz.afrs.View.OutputSender;

import java.util.HashMap;
import java.util.Map;

// Implementation //
public class Main {
	public static void main(String[] args) {
		//Instantiate Databases and store data
		AirportDatabase airportDatabase = new AirportDatabase();
		FlightDatabase flightDatabase = new FlightDatabase(airportDatabase);
		ReservationDatabase reservationDatabase = new ReservationDatabase(flightDatabase);
		ClientDatabase clientDatabase = new ClientDatabase();
		ReadFile readFile = new ReadFile(flightDatabase, airportDatabase, reservationDatabase);

		readFile.storeData();

		//Instantiate factories//
		//TODO Add factory instantiations here

		Map<String, QueryCreator> factoryMap = new HashMap<String, QueryCreator>();
		String[] queryTypes = {"connect", "disconnect", "info", "reserve", "retrieve", "delete", "undo", "redo", "airport", "server" };
		QueryCreator[] queryCreators = {new QConnectCreator(), new QDisconnectCreator(), new QReserveCreator(),
										new QRetrieveCreator(), new QDeleteCreator(), new QAirportCreator(),
										new QUndoCreator(), new QRedoCreator(), new QServerCreator(), new QInfoCreator() };
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
