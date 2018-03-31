package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Controller.Commands.Query;
import com.flyboiz.afrs.Model.ClientDatabase;

public abstract class QueryCreator {

    private ClientDatabase clientDB;


    public QueryCreator(ClientDatabase clientDB){
        this.clientDB = clientDB;
    }

    /**
     * takes input and returns a Query
     * @param userInput
     * @return
     */
    public abstract Query makeQuery(String userInput);

}
