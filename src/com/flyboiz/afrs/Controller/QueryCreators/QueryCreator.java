package com.flyboiz.afrs.Controller.QueryCreators;

import com.flyboiz.afrs.Controller.Commands.Query;
import com.flyboiz.afrs.Model.ClientDatabase;

public abstract class QueryCreator {

	/**
	 * takes input and returns a Query
	 *
	 * @param userInput The user's input
	 * @return Returns a Query that can be executed
	 */
	public abstract Query makeQuery(String userInput);

}
