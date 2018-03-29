package com.flyboiz.afrs.Controller.Commands;


public class QueryDisconnect extends Query {

    /**
     * Constructor for QueryDisconnect command
     * @param cid client id
     */
    public QueryDisconnect(int cid) {
        super(cid);
    }

    @Override
    public String generateResponse() {
        return null;
    }
}
