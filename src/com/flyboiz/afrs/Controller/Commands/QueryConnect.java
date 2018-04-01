package com.flyboiz.afrs.Controller.Commands;


import com.flyboiz.afrs.Model.ClientDatabase;

public class QueryConnect extends Query {

    private ClientDatabase clientDB;

    public QueryConnect(ClientDatabase clientDB){
        super(clientDB);
    }

    @Override
    public String generateResponse() {
        return null;
    }
}
