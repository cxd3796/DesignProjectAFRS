package com.flyboiz.afrs.Controller.Commands;

public class QueryServer extends Query {

    /**
     * Constructor for QueryServer command
     * @param cid client id
     */
    public QueryServer(int cid) {
        super(cid);
    }

    @Override
    public String generateResponse() {
        return null;
    }
}
