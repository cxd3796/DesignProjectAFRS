package com.flyboiz.afrs.Controller.QueryCreators;
import com.flyboiz.afrs.Controller.Commands.QueryRetrieve;
import com.flyboiz.afrs.Model.AirportDatabase;
import com.flyboiz.afrs.Model.ClientDatabase;
import com.flyboiz.afrs.Model.ReservationDatabase;

public class QRetrieveCreator extends QueryCreator {
    private ReservationDatabase reservationDB;
    private AirportDatabase airportDB;

    /**
     * constructor
     * @param reservationDB reservation database
     * @param airportDB airport database
     */
    public QRetrieveCreator( ReservationDatabase reservationDB,
                            AirportDatabase airportDB){
        this.reservationDB = reservationDB;
        this.airportDB = airportDB;
    }

    /**
     * makes a QueryRetrieve from user input;
     *input should be in format retrieve, name, [origin],[destination]
     * @param input
     * @return
     */
    public QueryRetrieve makeQuery(String input){
        String[] split = input.split(",");
        if(split.length<3 || split.length>5){
            return null;
        }
        int cid = Integer.parseInt(split[0]);
        String name = split[2];
        String origin = "";
        String destination = "";
        if(split.length>3){
            origin = split[3];
        }
        if(split.length==5){
            destination = split[4];
        }
        return new QueryRetrieve(cid, name, origin, destination, reservationDB, airportDB);
    }

}
