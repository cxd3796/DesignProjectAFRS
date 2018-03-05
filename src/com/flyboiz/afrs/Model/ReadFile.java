package com.flyboiz.afrs.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
	private FlightDatabase flightDatabase;
	private AirportDatabase airportDatabase;
	private ReservationDatabase reservationDatabase;


	 //Using relative paths here, requires setting the working directory
	 private final String CONNECTION_FILE =  "Data/connections.txt";
	 private final String AIRPORT_FILE =     "Data/airports.txt";
	 private final String DELAY_FILE =       "Data/delays.txt";
	 private final String FLIGHT_FILE =      "Data/flights.txt";
	 private final String WEATHER_FILE =     "Data/weather.txt";
	 private final String RESERVATION_FILE = "Data/reservations.txt";

	 private final String FILE_DELIMETER = ",";

	 BufferedReader br;

	/**
	 * The purpose of this class is to take in data file information and parse it out to every database in the system
	 * Its other purpose is to read in old reservations from a file
	 * @param flightDatabase The flight database.
	 * @param airportDatabase The airport database.
	 * @param reservationDatabase The reservation database.
	 */
	 public ReadFile(FlightDatabase flightDatabase , AirportDatabase airportDatabase , ReservationDatabase reservationDatabase)
	 {
	 	this.flightDatabase = flightDatabase;
	 	this.airportDatabase = airportDatabase;
	 	this.reservationDatabase = reservationDatabase;

	 }

	/**
	 * A helper function to help parse the CSV files
	 * @param fileName The name of the file that needs to be parsed
	 * @return Returns a list of string arrays that are the lines split up by delimeter
	 */
	 private List<String[]> readCSV(String fileName)
	 {
		 try
		 {
			 br = new BufferedReader(new FileReader(fileName));
			 String curLine = br.readLine();
			 List<String[]> list = new ArrayList<>();

			 while(curLine != null)
			 {
				 String[] splitLine = curLine.split(FILE_DELIMETER);
				 list.add(splitLine);
				 curLine = br.readLine();
			 }
			 br.close();
			 return list;

		 }
		 catch (FileNotFoundException e)
		 {
			 e.printStackTrace();
			 return null;
		 }
		 catch (IOException e)
		 {
			 e.printStackTrace();
			 return null;
		 }
	 }

	/**
	 * This function is what starts the parsing of data and storing of data into databases
	 */
	public void storeData()
	 {
	 	List<String[]> airportList = readCSV(constructConcurrentAddress(AIRPORT_FILE));
	 	for (String[] airportInfo :airportList)
		{
			airportDatabase.generateAirport(airportInfo[0]);
			airportDatabase.storeAirportName(airportInfo[0], airportInfo[1]);
//			System.out.println(airportInfo[0] + " " + airportInfo[1]);
		}

		List<String[]> connectionList = readCSV(constructConcurrentAddress(CONNECTION_FILE));
	 	for (String[] connectionInfo : connectionList)
		{
			airportDatabase.storeAirportConnectionTime(connectionInfo[0],Integer.parseInt(connectionInfo[1]));
//			System.out.println(connectionInfo[0] + " " + connectionInfo [1]);
		}

		List<String[]> delayList = readCSV(constructConcurrentAddress(DELAY_FILE));
	 	for(String[] delayInfo : delayList)
		{
			airportDatabase.storeAirportDelay(delayInfo[0], Integer.parseInt(delayInfo[1]));
//			System.out.println(delayInfo[0] + " " + delayInfo[1]);
		}

		List<String[]> flightList = readCSV(constructConcurrentAddress(FLIGHT_FILE));
	 	for (String[] flightInfo : flightList)
		{
			flightDatabase.generateFlight(flightInfo[0],flightInfo[1], new Time(flightInfo[2]), new Time(flightInfo[3]), Integer.parseInt(flightInfo[4]), Integer.parseInt(flightInfo[5]));
//			System.out.println(flightInfo[0] + " " + flightInfo[1] + " " + flightInfo[2] + " " + flightInfo[3] + " " + flightInfo[4] + " " + flightInfo[5]);
		}

		List<String[]> weatherList = readCSV(constructConcurrentAddress(WEATHER_FILE));
	 	for(String[] weatherInfo : weatherList)
		{
			for (int x = 1; x < weatherInfo.length ; x = x + 2)
			{
				airportDatabase.storeAirportWeather(weatherInfo[0], weatherInfo[x], Integer.parseInt(weatherInfo[x+1]));
//				System.out.println(weatherInfo[0] + " " + weatherInfo[x] + " " + weatherInfo[x+1] );
			}
		}

		List<String[]> reservationList = readCSV(constructConcurrentAddress(RESERVATION_FILE));
	 	for(String[] reservationInfo : reservationList)
		{
			List<String> flightNumbers = new ArrayList<>();
			for(int x = 1; x < reservationInfo.length; x++)
			{
				flightNumbers.add(reservationInfo[x]);
			}
			reservationDatabase.storeReservation(reservationInfo[0],flightNumbers);
		}
	 }

	 public static String constructConcurrentAddress(String relativeAddress) {
		 return System.getProperty("user.dir") + File.separator + relativeAddress;
	 }

}
