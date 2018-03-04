package com.flyboiz.afrs.Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile
{
	 private FlightDatabase flightDatabase;
	 private AirportDatabase airportDatabase;
	 private ReservationDatabase reservationDatabase;


	//	 private String AIRPORT_FILE = "D:\\College\\SWEN\\SWEN 262\\DesignProject\\designproject-flyboiz\\src\\com\\flyboiz\\afrs\\Data\\airports.txt"; //You can use absolute file paths or relative

	//Using relative paths here, requires setting the working directory
	 private String CONNECTION_FILE = "..\\src\\com\\flyboiz\\afrs\\Data\\connections.txt";
	 private String AIRPORT_FILE =    "..\\src\\com\\flyboiz\\afrs\\Data\\airports.txt";
	 private String DELAY_FILE =      "..\\src\\com\\flyboiz\\afrs\\Data\\delays.txt";
	 private String FLIGHT_FILE =     "..\\src\\com\\flyboiz\\afrs\\Data\\flights.txt";
	 private String WEATHER_FILE =    "..\\src\\com\\flyboiz\\afrs\\Data\\weather.txt";

	 private String FILE_DELIMETER = ",";

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
	 	List<String[]> airportList = readCSV(AIRPORT_FILE);
	 	for (String[] airportInfo :airportList)
		{
			airportDatabase.generateAirport(airportInfo[0]);
			System.out.println(airportInfo[0]);
		}

		List<String[]> connectionList = readCSV(CONNECTION_FILE);
	 	for (String[] connectionInfo : connectionList)
		{
			airportDatabase.storeAirportConnectionTime(connectionInfo[0],Integer.parseInt(connectionInfo[1]));
			System.out.println(connectionInfo[0] + " " + connectionInfo [1]);
		}

		List<String[]> delayList = readCSV(DELAY_FILE);
	 	for(String[] delayInfo : delayList)
		{
			airportDatabase.storeAirportDelay(delayInfo[0], Integer.parseInt(delayInfo[1]));
			System.out.println(delayInfo[0] + " " + delayInfo[1]);
		}

		List<String[]> flightList = readCSV(FLIGHT_FILE);
	 	for (String[] flightInfo : flightList)
		{
			flightDatabase.generateFlight(flightInfo[0],flightInfo[1], flightInfo[2], flightInfo[3], flightInfo[4], flightInfo[5]);
			//TODO discuss creation format for flight, will need a 6 parameter method if we want to do it all at once
		}

		List<String[]> weatherList = readCSV(WEATHER_FILE);
	 	for(String[] weatherInfo : weatherList)
		{
			for(String weatherSnippet : weatherInfo)
			{
				//airportDatabase.storeAirportWeather(); //TODO Create weather objects and add those to the airport by weather objects into a list that can store the weathers
			}
		}

	 }

}
