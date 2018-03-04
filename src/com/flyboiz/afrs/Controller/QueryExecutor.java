package com.flyboiz.afrs.Controller;

public class QueryExecutor
{

    private Query lastQuery;
    private Query currentQuery;

    /**
     * Constructor for the QueryExecutor object. When created, this object doesn't have any Query objects attached to it.
     */
    public QueryExecutor(){
        lastQuery = null;
        currentQuery = null;
    }

    /**
     * After a Query is completed, move it to the lastQuery
     * This function might be redundant
     * @param query- The previous Query command
     */
    public void setLastQuery(Query query){
        lastQuery = query;
    }

    /**
     * When a query is created by the factory, it is given to this object to execute and store the output.
     * Todo- design needs to be finalized here
     * @param query
     */
    public void setCurrentQuery(Query query){
        currentQuery = query;
        currentQuery.generateResponse();
    }
}
