package com.flyboiz.afrs.Controller.Commands;


public class QueryConnect extends Query {

    /**
     * Constructor for QueryConnect command object
     * @param cid client id
     */
    public QueryConnect(int cid) {
        super(cid);
    }

    @Override
    public String generateResponse() {
        return null;
    }
}
