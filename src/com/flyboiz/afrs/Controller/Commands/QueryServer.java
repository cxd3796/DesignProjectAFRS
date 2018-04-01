package com.flyboiz.afrs.Controller.Commands;

import com.flyboiz.afrs.Model.AirportDatabase;
import com.flyboiz.afrs.Model.ClientDatabase;

public class QueryServer extends Query {

    AirportDatabase airportDB;
    String infoServer;
    ClientDatabase clientDB;

    /**
     * Constructor for QueryServer command
     * @param cid client id
     */
    public QueryServer(int cid, String infoServer, AirportDatabase airportDB, ClientDatabase clientDB) {
        super(cid);
        this.infoServer = infoServer;
        this.airportDB=airportDB;
        this.clientDB= clientDB;
    }

    @Override
    public String generateResponse() {
        return null;
    }
}
