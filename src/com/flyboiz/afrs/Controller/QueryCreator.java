package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Controller.Commands.Query;

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
