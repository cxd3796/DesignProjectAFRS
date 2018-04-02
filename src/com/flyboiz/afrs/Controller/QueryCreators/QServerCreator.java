package com.flyboiz.afrs.Controller.QueryCreators;

import com.flyboiz.afrs.Controller.Commands.QueryServer;
import com.flyboiz.afrs.Model.AirportDatabase;
import com.flyboiz.afrs.Model.ClientDatabase;

public class QServerCreator extends QueryCreator {
    private AirportDatabase airportDB;

    /**
     * constructor
     */
    public QServerCreator( AirportDatabase airportDB){
        this.airportDB = airportDB;
    }

    /**
     * Creates a query based on the input (will handle switching of the server)
     * @param input InputString
     * @return Returns a QueryServer
     */
    public QueryServer makeQuery(String input) {
        String[] split = input.split(",");
        if(split.length!= 3){
            return null;
        }
        int cid = Integer.parseInt(split[0]);
        String info = split[2];
        return new QueryServer(cid,info,airportDB);
    }
}
