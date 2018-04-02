package com.flyboiz.afrs.Controller.Commands;

import com.flyboiz.afrs.Model.ClientDatabase;

public class QueryUndo extends Query {

    /**
     * Constructor for QueryUndo command
     * @param cid client id
     */
    public QueryUndo(int cid, ClientDatabase clientDatabase) {
        super(cid, clientDatabase);
    }


    /**
     * generates response after performing action.
     * response format: cid,undo, operation[reserve/delete],passenger, itinerary
     * @return response
     */
    @Override
    public String generateResponse() {
        Query undo = clientDB.getLastUndoQuery(cid);
        if (!(undo instanceof QueryReserve) && !(undo instanceof QueryDelete)){
            return "error";
        }
        if(undo instanceof QueryReserve){
            return ((QueryReserve) undo).undo();
        }
        else {
            return ((QueryDelete)undo).undo();
        }
    }
}
