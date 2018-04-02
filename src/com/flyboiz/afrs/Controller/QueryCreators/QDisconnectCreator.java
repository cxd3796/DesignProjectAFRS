package com.flyboiz.afrs.Controller.QueryCreators;

import com.flyboiz.afrs.Controller.Commands.QueryDisconnect;
import com.flyboiz.afrs.Model.ClientDatabase;

public class QDisconnectCreator extends QueryCreator {

	private ClientDatabase clientDB;

	/**
	 * constructor
	 *
	 * @param clientDB client database
	 */
	public QDisconnectCreator(ClientDatabase clientDB) {
		this.clientDB = clientDB;
	}


	/**
	 * returns a QueryDisconnect given user input
	 * input should be in form cid, disconnect
	 *
	 * @param input user input
	 * @return QueryDisconnect
	 */
	public QueryDisconnect makeQuery(String input) {
		String[] split = input.split(",");
		if (split.length != 2) {
			return null;
		}
		int cid = Integer.parseInt(split[0]);
		return new QueryDisconnect(cid, clientDB);
	}
}
