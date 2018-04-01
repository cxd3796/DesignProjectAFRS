package com.flyboiz.afrs.Controller.QueryCreators;

import com.flyboiz.afrs.Controller.Commands.QueryServer;
import com.flyboiz.afrs.Model.AirportDatabase;
import com.flyboiz.afrs.Model.ClientDatabase;

public class QServerCreator extends QueryCreator {
    private ClientDatabase clientDB;
    private AirportDatabase airportDB;

    /**
     * constructor
     * @param clientDB client database
     */
    public QServerCreator(ClientDatabase clientDB, AirportDatabase airportDB){
        this.clientDB = clientDB;
        this.airportDB = airportDB;
    }


    public QueryServer makeQuery(String input) {
        String[] split = input.split(",");
        if(split.length!= 3){
            return null;
        }
        int cid = Integer.parseInt(split[0]);
        String info = split[2];
        return new QueryServer(cid,info,airportDB,clientDB);
    }
}
