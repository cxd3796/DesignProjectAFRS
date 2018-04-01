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
import com.flyboiz.afrs.View.GUI.ViewManager;
import com.flyboiz.afrs.View.InputReader;
import com.flyboiz.afrs.View.OutputSender;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

// Implementation //
public class Main extends Application {

	// Scene Constants
	private static final String TITLE = "Airline Flight Reservation Service: GUI Version";

	// Argument Constants
	private static final String ARG_GUI = "gui";
	private static final String ARG_TEXT = "text";
	private static final String ERROR_ARG1 = "The launch argument '%s' is not a valid argument.";
	private static final String ERROR_ARG2 = "Accepted arguments: '%s', '%s'";

	// GUI Constants
	private static final String FONT_FAMILY = "Verdana";
	private static final double FONT_SIZE = 12.0;
	private static final double DEFAULT_WIDTH = 800;
	private static final double DEFAULT_HEIGHT = 600;

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
			ClientDatabase clientDatabase = new ClientDatabase();
			ReadFile readFile = new ReadFile(flightDatabase, airportDatabase, reservationDatabase);

			readFile.storeData();

			//Instantiate factories//

			Map<String, QueryCreator> factoryMap = new HashMap<>();
			String[] queryTypes = {
					"connect",
					"disconnect",
					"reserve",
					"retrieve",
					"delete",
					"airport",
					"undo",
					"redo",
					"server",
					"info",
					"pr"};
			QueryCreator[] queryCreators = {
					new QConnectCreator(clientDatabase), 										// connect
					new QDisconnectCreator(clientDatabase), 									// disconnect
					new QReserveCreator(clientDatabase, reservationDatabase),					// reserve
					new QRetrieveCreator(clientDatabase, reservationDatabase, airportDatabase),	// retrieve
					new QDeleteCreator(clientDatabase, reservationDatabase),					// delete
					new QAirportCreator(clientDatabase, airportDatabase),						// airport
					new QUndoCreator(clientDatabase),											// undo
					new QRedoCreator(clientDatabase),											// redo
					new QServerCreator(clientDatabase, airportDatabase),						// server
					new QInfoCreator(clientDatabase, airportDatabase, flightDatabase),			// info
					new QPartialRequestCreator()};												// pr
			for (int i = 0; i < queryTypes.length; i++) {
				factoryMap.put(queryTypes[i], queryCreators[i]);
			}

			QueryDecider queryDecider = new QueryDecider(factoryMap, clientDatabase);


			// Instantiate controller objects. //
			QueryExecutor queryExecutor;
			InputReader reader = new InputReader();
			OutputSender output = new OutputSender();
			queryExecutor = new QueryExecutor(output, queryDecider);
			reader.setExecutor(queryExecutor);
			reader.setSender(output);

			// start going
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
		ClientDatabase clientDatabase = new ClientDatabase();
		ReadFile readFile = new ReadFile(flightDatabase, airportDatabase, reservationDatabase);

		// Generate data in the databases.
		readFile.storeData();

		Map<String, QueryCreator> factoryMap = new HashMap<>();
		String[] queryTypes = {
				"connect",
				"disconnect",
				"reserve",
				"retrieve",
				"delete",
				"airport",
				"undo",
				"redo",
				"server",
				"info",
				"pr"};
		QueryCreator[] queryCreators = {
				new QConnectCreator(clientDatabase), 										// connect
				new QDisconnectCreator(clientDatabase), 									// disconnect
				new QReserveCreator(clientDatabase, reservationDatabase),					// reserve
				new QRetrieveCreator(clientDatabase, reservationDatabase, airportDatabase),	// retrieve
				new QDeleteCreator(clientDatabase, reservationDatabase),					// delete
				new QAirportCreator(clientDatabase, airportDatabase),						// airport
				new QUndoCreator(clientDatabase),											// undo
				new QRedoCreator(clientDatabase),											// redo
				new QServerCreator(clientDatabase, airportDatabase),						// server
				new QInfoCreator(clientDatabase, airportDatabase, flightDatabase),			// info
				new QPartialRequestCreator()};												// pr
		for (int i = 0; i < queryTypes.length; i++) {
			factoryMap.put(queryTypes[i], queryCreators[i]);
		}

		QueryDecider queryDecider = new QueryDecider(factoryMap, clientDatabase);

		// Generate the QueryExecutor, and Input/Output GUI.
		QueryExecutor queryExecutor = new QueryExecutor(null, queryDecider);
		ViewManager viewManager = new ViewManager(queryExecutor, getFont(), DEFAULT_WIDTH, DEFAULT_HEIGHT);
		queryExecutor.setOutput(viewManager);

		// Set up the scene and stage, then show.
		Scene scene = new Scene(viewManager);
		primaryStage.setScene(scene);
		primaryStage.setTitle(TITLE);
		primaryStage.setHeight(DEFAULT_HEIGHT);
		primaryStage.setWidth(DEFAULT_WIDTH);
		primaryStage.setMinHeight(DEFAULT_HEIGHT / 2.0);
		primaryStage.setMinWidth(DEFAULT_WIDTH / 2.0);
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
