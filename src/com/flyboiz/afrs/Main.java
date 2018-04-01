/* Authors:
Kent Brown
*/

// Package //
package com.flyboiz.afrs;

// Imports //

import com.flyboiz.afrs.Controller.QueryExecutor;
import com.flyboiz.afrs.Controller.QueryMaker;
import com.flyboiz.afrs.Model.*;
import com.flyboiz.afrs.View.GUI.ViewManager;
import com.flyboiz.afrs.View.InputReader;
import com.flyboiz.afrs.View.Output;
import com.flyboiz.afrs.View.OutputSender;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// Implementation //
public class Main extends Application {

	// Scene Constants
	private static final String TITLE = "Airline Flight Reservation Service: GUI Version";

	// Argument Constants
	private static final String ARG_GUI = "gui";
	private static final String ARG_TEXT = "text";
	private static final String ERROR_ARG1 = "The launch argument '%s' is not a valid argument.";
	private static final String ERROR_ARG2 = "Accepted arguments: '%s', '%s'";

	// Font Constants
	private static final String FONT_FAMILY = "Verdana";
	private static final double FONT_SIZE = 12.0;

	// Query Type Constants
	public static final String PARTIAL_REQUEST_STRING = "pr";
	public static final String CONNECT_REQUEST_STRING = "connect";
	public static final String CONNECTED_STRING = "Connected! Client ID: ";

	// Implementation
	public static void main(String[] args) {

		// Parse input arguments.
		String arg = args[0];

		// start going
		if (arg.equals(ARG_GUI)) {
			launch();
		} else if (arg.equals(ARG_TEXT)) {
			//Instantiate Databases and store data
			AirportDatabase airportDatabase = new AirportDatabase();
			FlightDatabase flightDatabase = new FlightDatabase(airportDatabase);
			ReservationDatabase reservationDatabase = new ReservationDatabase(flightDatabase);
			ReadFile readFile = new ReadFile(flightDatabase, airportDatabase, reservationDatabase);

			readFile.storeData();

			// Instantiate controller objects. //
			QueryMaker queryMaker = new QueryMaker(flightDatabase, airportDatabase, reservationDatabase);
			QueryExecutor queryExecutor;
			InputReader reader = new InputReader();
			OutputSender output = new OutputSender();
			queryExecutor = new QueryExecutor(output, queryMaker);
			reader.setExecutor(queryExecutor);
			reader.setSender(output);
			reader.waitOnInput();
		} else {
			System.out.println(String.format(ERROR_ARG1, arg));
			System.out.println(String.format(ERROR_ARG2, ARG_GUI, ARG_TEXT));
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Initialize databases and FileReader.
		AirportDatabase airportDatabase = new AirportDatabase();
		FlightDatabase flightDatabase = new FlightDatabase(airportDatabase);
		ReservationDatabase reservationDatabase = new ReservationDatabase(flightDatabase);
		ReadFile readFile = new ReadFile(flightDatabase, airportDatabase, reservationDatabase);

		// Generate data in the databases.
		readFile.storeData();

		// Generate the QueryMaker, QueryExecutor, and Input/Output GUI.
		QueryMaker queryMaker = new QueryMaker(flightDatabase, airportDatabase, reservationDatabase);
		QueryExecutor queryExecutor = new QueryExecutor(null, queryMaker);
		ViewManager viewManager = new ViewManager(queryExecutor, getFont());
		queryExecutor.setOutput(viewManager);

		// Set up the scene and stage, then show.
		Scene scene = new Scene(viewManager);
		primaryStage.setScene(scene);
		primaryStage.setTitle(TITLE);
		primaryStage.show();
	}

	/**
	 * This method returns the GUI application's default font.
	 * @return the default font for the GUI.
	 */
	private Font getFont() {
		return Font.font(FONT_FAMILY, FONT_SIZE);
	}
}
