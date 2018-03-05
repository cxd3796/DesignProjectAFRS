package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.View.OutputSender;

public class QueryExecutor
{

    private Query lastQuery;
    private Query currentQuery;
    private OutputSender outputSender;
    private QueryMaker queryMaker;

    /**
     * Constructor for the QueryExecutor object. When created, this object doesn't have any Query objects attached to it.
     */
    public QueryExecutor(OutputSender outputSender, QueryMaker queryMaker){
        lastQuery = null;
        currentQuery = null;
        this.outputSender = outputSender;
        this.queryMaker = queryMaker;
    }

    /**
     * When a query is created by the factory, it is given to this object to execute and store the output.
     * Todo- design needs to be finalized here
     * @param query
     */
    public void setCurrentQuery(Query query){
        if (query == null){
            outputSender.update("error,unkown request");
        }
        else {
            currentQuery = query;
            String response = currentQuery.generateResponse();
            outputSender.update(response);
        }
    }

    /**
     * Creates a query from the user input and sends it to the execution function
     * @param userInput-
     */
    public void makeQuery(String userInput){
        Query query = queryMaker.makeQuery(userInput);
        setCurrentQuery(query);
    }

    public Query getLastQuery() {
        return lastQuery;
    }
}
