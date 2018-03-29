package com.flyboiz.afrs.Controller.Commands;

public class QueryUndo extends Query {

    /**
     * Constructor for QueryUndo command
     * @param cid client id
     */
    public QueryUndo(int cid) {
        super(cid);
    }

    @Override
    public String generateResponse() {
        return null;
    }
}
