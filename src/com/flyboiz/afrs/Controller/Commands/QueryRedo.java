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
        Query redo = clientDB.getLastRedoQuery(cid);
        if (!(redo instanceof QueryReserve) && !(redo instanceof QueryDelete)){
            return "error";
        }
        if(redo instanceof QueryReserve){
            return ((QueryReserve) redo).redo();
        }
        else {
            return ((QueryDelete)redo).redo();
        }
    }
}
