package com.flyboiz.afrs.Controller.QueryCreators;

import com.flyboiz.afrs.Controller.Commands.QueryUndo;
import com.flyboiz.afrs.Model.ClientDatabase;

public class QUndoCreator extends QueryCreator {
    private ClientDatabase clientDB;

    /**
     * constructor
     * @param clientDB client database
     */
    public QUndoCreator (ClientDatabase clientDB){
        this.clientDB = clientDB;
    }


    /**
     * makes a QueryUndo object given input
     * @param input user input
     * @return QueryUndo object
     */
    public QueryUndo makeQuery(String input){
        String[] split = input.split(",");
        if(split.length!= 2){
            return null;
        }
        int cid = Integer.parseInt(split[0]);
        return new QueryUndo(cid);
    }

}
