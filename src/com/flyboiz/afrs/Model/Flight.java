/* Authors
Kent Brown
 */

/* Package */
package com.flyboiz.afrs.Model;

/* Imports */

/* Implementation */
public class Flight
{
    // State //
    private String originAirport;
    private String destinationAirport;
    private Time departureTime;
    private Time arrivalTime;
    private int flightNumber;
    private int airfare;

    // Constructors //
    public Flight (String originAirport,
                   String destinationAirport,
                   Time departureTime,
                   Time arrivalTime,
                   int flightNumber,
                   int airfare) {
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.flightNumber = flightNumber;
        this.airfare = airfare;
    }

    // Getters & Setters //
    int getFlightNumber() {
        return this.flightNumber;
    }

    int getAirfare() {
        return this.airfare;
    }

    String getOrigin() {
        return this.originAirport;
    }

    String getDestination() {
        return this.destinationAirport;
    }

    Time getDepartureTime(){
        return departureTime;
    }

    Time getArrivalTime(){
        return arrivalTime;
    }

    // Return format FlightNumber,OriginAirport,DepartureTime,DestinationAirport,ArrivalTime
    public String toString(){
        return String.format("%d,%s,%s,%s,%s", flightNumber, originAirport, departureTime.toString(), destinationAirport, arrivalTime.toString());
    }
}
