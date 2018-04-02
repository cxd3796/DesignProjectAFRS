package com.flyboiz.afrs.Controller.Commands;

import com.flyboiz.afrs.Model.ClientDatabase;

public class QueryRedo extends Query {


	/**
	 * Constructor for QueryRedo Command
	 *
	 * @param cid            The client id
	 * @param clientDatabase The client database
	 */
	public QueryRedo(int cid, ClientDatabase clientDatabase) {
		super(cid, clientDatabase);
	}

	/**
	 * Generates a response for the output
	 *
	 * @return A string representation of the response
	 */
	@Override
	public String generateResponse() {
		Query redo = clientDB.getLastRedoQuery(cid);
		if (redo == null) {
			return cid + ",error,no request available";
		}
		if (!(redo instanceof QueryReserve) && !(redo instanceof QueryDelete)) {
			return "error";
		}
		if (redo instanceof QueryReserve) {
			clientDB.moveLastRedoToUndo(cid);
			return ((QueryReserve) redo).redo();
		} else {
			clientDB.moveLastRedoToUndo(cid);
			return ((QueryDelete) redo).redo();
		}
	}
}
