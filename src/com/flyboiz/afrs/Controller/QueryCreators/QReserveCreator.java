package com.flyboiz.afrs.Controller.QueryCreators;

import com.flyboiz.afrs.Controller.Commands.Query;
import com.flyboiz.afrs.Controller.Commands.QueryInfo;
import com.flyboiz.afrs.Controller.Commands.QueryReserve;
import com.flyboiz.afrs.Model.ClientDatabase;
import com.flyboiz.afrs.Model.ReservationDatabase;

public class QReserveCreator extends QueryCreator {

	private ClientDatabase clientDB;
	private ReservationDatabase reservationDB;

	/**
	 * constructor
	 *
	 * @param clientDB      client database
	 * @param reservationDB reservation database
	 */
	public QReserveCreator(ClientDatabase clientDB, ReservationDatabase reservationDB) {
		this.clientDB = clientDB;
		this.reservationDB = reservationDB;
	}


	/**
	 * constructs a QueryReserve from user input, input should be in format
	 * cid,"reserve",id,passenger
	 *
	 * @param input user input
	 * @return QueryReserve object
	 */
	public QueryReserve makeQuery(String input) {
		String[] split = input.split(",");
		if (split.length != 4) {
			return null;
		}
		int cid = Integer.parseInt(split[0]);
		Query last = clientDB.getLastQuery(cid);
		int id = Integer.parseInt(split[2]);
		String name = split[3];

		QueryReserve newReserve = new QueryReserve(cid, id, name, reservationDB, last, clientDB);
		clientDB.addUndoQuery(newReserve, cid);
		return newReserve;
	}


}
