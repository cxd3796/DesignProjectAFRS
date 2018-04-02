package com.flyboiz.afrs.Controller.QueryCreators;

import com.flyboiz.afrs.Controller.Commands.QueryInfo;
import com.flyboiz.afrs.Controller.SortStrategy.SortByAirfare;
import com.flyboiz.afrs.Controller.SortStrategy.SortByArrival;
import com.flyboiz.afrs.Controller.SortStrategy.SortByDeparture;
import com.flyboiz.afrs.Controller.SortStrategy.SortStrategy;
import com.flyboiz.afrs.Model.AirportDatabase;
import com.flyboiz.afrs.Model.ClientDatabase;
import com.flyboiz.afrs.Model.FlightDatabase;

import java.util.HashMap;
import java.util.Map;

public class QInfoCreator extends QueryCreator {
	private AirportDatabase airportDB;
	private FlightDatabase flightDB;
	private Map<String, SortStrategy> sorts;

	/**
	 * constructor. instantiates hashmap of sort strategies
	 *
	 * @param airportDB
	 */
	public QInfoCreator(AirportDatabase airportDB,
						FlightDatabase flightDB) {
		this.airportDB = airportDB;
		this.flightDB = flightDB;
		sorts = new HashMap<>();
		sorts.put("departure", new SortByDeparture());
		sorts.put("arrival", new SortByArrival());
		sorts.put("airfare", new SortByAirfare());
	}

	/**
	 * creates QueryInfo from user input
	 * input should be in form cid, "info", origin, destination,
	 * [connection],[sort-order]
	 *
	 * @param input user input
	 * @return QueryInfo
	 */
	public QueryInfo makeQuery(String input) {
		String[] split = input.split(",");
		if (split.length < 4 || split.length > 6) {
			return null;
		}
		int cid = Integer.parseInt(split[0]);
		String origin = split[2];
		String destination = split[3];
		int connections = 2;
		SortStrategy sort = new SortByDeparture();
		if (split.length > 4 && !split[4].equals("")) {
			try {
				connections = Integer.parseInt(split[4]);
			} catch (NumberFormatException e) {
				connections = 5;
			}
		}
		if (split.length == 6) {
			if (sorts.containsKey(split[5])) {
				sort = sorts.get(split[5]);
			} else {
				sort = null;
			}
		}
		return new QueryInfo(cid, origin, destination, connections, sort,
				flightDB, airportDB);

	}
}
