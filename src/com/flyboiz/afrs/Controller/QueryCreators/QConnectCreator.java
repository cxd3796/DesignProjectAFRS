package com.flyboiz.afrs.Controller.QueryCreators;

import com.flyboiz.afrs.Controller.Commands.QueryConnect;
import com.flyboiz.afrs.Model.ClientDatabase;

public class QConnectCreator extends QueryCreator{

    private ClientDatabase clientDB;

    /**
     * constructor
     * @param clientDB client database
     */
    public QConnectCreator(ClientDatabase clientDB){
        this.clientDB= clientDB;
    }


    /**
     *creates a QueryConnect
     * @param input the user input. should be just "connect"
     * @return QueryConnect
     */
    public QueryConnect makeQuery(String input){
        String[] split = input.split(",");
        if(split.length!=1){
            return null;
        }
        return new QueryConnect(clientDB);
    }
}
