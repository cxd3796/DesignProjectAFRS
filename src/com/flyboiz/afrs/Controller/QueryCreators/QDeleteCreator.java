package com.flyboiz.afrs.Controller.QueryCreators;

import com.flyboiz.afrs.Controller.Commands.QueryDelete;
import com.flyboiz.afrs.Model.ClientDatabase;
import com.flyboiz.afrs.Model.ReservationDatabase;

public class QDeleteCreator extends QueryCreator {

	private ReservationDatabase reservationDB;
	private ClientDatabase clientDatabase;

	/**
	 * constructor
	 *
	 * @param reservationDB reservation database
	 */
	public QDeleteCreator(ReservationDatabase reservationDB, ClientDatabase clientDatabase) {
		this.reservationDB = reservationDB;
		this.clientDatabase = clientDatabase;
	}

	/**
	 * makes a QueryDelete from the given user input.
	 * user input shoulf be in form of cid,delete,passenger,origin,destination
	 *
	 * @param input user input
	 * @return QueryDelete
	 */
	public QueryDelete makeQuery(String input) {
		String[] split = input.split(",");
		int cid = Integer.parseInt(split[0]);
		if (split.length == 5) {
			QueryDelete newDelete = new QueryDelete(cid, split[2], split[3], split[4], reservationDB);
			clientDatabase.addUndoQuery(newDelete, cid);
			return newDelete;
		}
		return null;
	}

}
