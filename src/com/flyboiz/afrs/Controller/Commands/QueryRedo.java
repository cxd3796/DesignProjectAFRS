package com.flyboiz.afrs.Controller.Commands;

public class QueryRedo extends Query {

    /**
     * Constructor for QueryRedo command
     * @param cid client id
     */
    public QueryRedo(int cid) {
        super(cid);
    }

    @Override
    public String generateResponse() {
        return null;
    }
}
