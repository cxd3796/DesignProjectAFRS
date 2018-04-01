package com.flyboiz.afrs.Controller.QueryCreators;

import com.flyboiz.afrs.Controller.Commands.QueryAirportInfo;
import com.flyboiz.afrs.Model.AirportDatabase;
import com.flyboiz.afrs.Model.ClientDatabase;

public class QAirportCreator extends QueryCreator{

    private ClientDatabase clientdb;
    private AirportDatabase airportdb;


    public QAirportCreator(ClientDatabase clientDB, AirportDatabase airportDB){
        this.clientdb = clientDB;
        airportdb= airportDB;
    }

    /**
     * creates a QueryAirportInfo from the given input
     * input should be in form cid,"airport",airport
     * checks that length is correct.
     * @param input user input
     * @return QueryAirportInfo
     */
    public QueryAirportInfo makeQuery(String input){
        String[] split = input.split(",");
        if(split.length==3){
            int cid= Integer.parseInt(split[0]);
            return new QueryAirportInfo(cid, split[2],airportdb);

        }
        return null;
    }
}
