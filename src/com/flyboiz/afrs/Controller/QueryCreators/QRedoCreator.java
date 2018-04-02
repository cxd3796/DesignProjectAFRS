package com.flyboiz.afrs.Controller.QueryCreators;

import com.flyboiz.afrs.Controller.Commands.QueryRedo;
import com.flyboiz.afrs.Model.ClientDatabase;

public class QRedoCreator extends QueryCreator{
    ClientDatabase clientDB;

    /**
     * constructor
     * @param clientDB client database
     */
    public QRedoCreator (ClientDatabase clientDB){
        this.clientDB = clientDB;
    }


    /**
     * makes a QueryRedo given a cid.
     * input should be in format: cid, redo
     * @param input
     * @return
     */
    public QueryRedo makeQuery(String input){
        String[] split = input.split(",");
        if(split.length!=2){
            return null;
        }
        int cid = Integer.parseInt(split[0]);
        return new QueryRedo(cid,clientDB);
    }
}
