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

	 private String AIRPORT_FILE = "airports.txt";
	 private String CONNECTION_FILE = "connections.txt";
	 private String DELAY_FILE = "delays.txt";
	 private String FLIGHT_FILE = "flights.txt";
	 private String WEATHER_FILE = "weather.txt";

	 BufferedReader br;

	 public ReadFile(FlightDatabase flightDatabase , AirportDatabase airportDatabase , ReservationDatabase reservationDatabase)
	 {
	 	this.flightDatabase = flightDatabase;
	 	this.airportDatabase = airportDatabase;
	 	this.reservationDatabase = reservationDatabase;

	 }

	 private List<String[]> readCSV(String fileName)
	 {
		 try
		 {
			 br = new BufferedReader(new FileReader(fileName));
			 String curLine = br.readLine();
			 List<String[]> list = new ArrayList<>();

			 while(curLine != null)
			 {
				 String[] splitLine = curLine.split(",");
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

	 public void storeData()
	 {
	 	List<String[]> airportList = readCSV(AIRPORT_FILE);
	 	for (String[] airportInfo :airportList)
		{

		}
	 }

}
