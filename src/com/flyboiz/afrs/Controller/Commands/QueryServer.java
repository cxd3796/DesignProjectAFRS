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

    /**
     * performs change of info-server and returns response in format
     *cid,server,successful
     * @return
     */
    @Override
    public String generateResponse() {
        if(!infoServer.equals("local")&& !infoServer.equals("faa")){
            return cid+",error,unknown information server";
        }
        airportDB.setServer(cid, infoServer);
        return cid+"," +infoServer+ ",successful";
    }
}
