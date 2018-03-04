package com.flyboiz.afrs;

import com.flyboiz.afrs.Model.AirportDatabase;
import com.flyboiz.afrs.Model.FlightDatabase;
import com.flyboiz.afrs.Model.ReadFile;
import com.flyboiz.afrs.Model.ReservationDatabase;

public class Main {

    public static void main(String[] args) {
        FlightDatabase flightDatabase = new FlightDatabase();
        AirportDatabase airportDatabase = new AirportDatabase();
        ReservationDatabase reservationDatabase = new ReservationDatabase(flightDatabase);
        ReadFile readFile = new ReadFile(flightDatabase,airportDatabase,reservationDatabase);

        readFile.storeData();
	// write your code here
    }

}
