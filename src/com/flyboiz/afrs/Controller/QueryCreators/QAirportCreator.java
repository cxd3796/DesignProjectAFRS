package com.flyboiz.afrs.Controller.QueryCreators;

import com.flyboiz.afrs.Controller.Commands.QueryAirport;
import com.flyboiz.afrs.Model.AirportDatabase;
import com.flyboiz.afrs.Model.ClientDatabase;

public class QAirportCreator extends QueryCreator {

	private AirportDatabase airportDB;

	/**
	 * Constructor for Query Airport Creator
	 *
	 * @param airportDB Airport Database
	 */
	public QAirportCreator(AirportDatabase airportDB) {
		this.airportDB = airportDB;
	}

	/**
	 * creates a QueryAirport from the given input
	 * input should be in form cid,"airport",airport
	 * checks that length is correct.
	 *
	 * @param input user input
	 * @return QueryAirport
	 */
	public QueryAirport makeQuery(String input) {
		String[] split = input.split(",");
		if (split.length == 3) {
			int cid = Integer.parseInt(split[0]);
			return new QueryAirport(cid, split[2], airportDB);

		}
		return null;
	}
}
