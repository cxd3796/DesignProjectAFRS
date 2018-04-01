package com.flyboiz.afrs.Controller.Commands;


import com.flyboiz.afrs.Model.ClientDatabase;

public class QueryDisconnect extends Query {

    private ClientDatabase clientDB;
    /**
     * Constructor for QueryDisconnect command
     * @param cid client id
     */
    public QueryDisconnect(int cid, ClientDatabase clientDB) {
        super(cid);
        this.clientDB = clientDB;
    }

    @Override
    /**
     * generates response after performing action.
     * response format: cid,"disconnect"
     */
    public String generateResponse() {
        clientDB.disconnectClient(cid);
        return "disconnect";
    }
}
