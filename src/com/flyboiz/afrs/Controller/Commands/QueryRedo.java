package com.flyboiz.afrs.Controller.Commands;

import com.flyboiz.afrs.Model.ClientDatabase;

public class QueryRedo extends Query {

    /**
     * Constructor for QueryRedo command
     * @param cid client id
     */
    public QueryRedo(int cid, ClientDatabase clientDatabase) {
        super(cid, clientDatabase);
    }

    @Override
    public String generateResponse() {
        Query redo = clientDB.getLastRedoQuery(cid);
        if (!(redo instanceof QueryReserve) && !(redo instanceof QueryDelete)){
            return "error";
        }
        if(redo instanceof QueryReserve){
            clientDB.moveLastRedoToUndo(cid);
            return ((QueryReserve) redo).redo();
        }
        else {
            clientDB.moveLastRedoToUndo(cid);
            return ((QueryDelete)redo).redo();
        }
    }
}
