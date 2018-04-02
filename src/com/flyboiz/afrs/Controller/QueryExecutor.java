package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Controller.Commands.Query;
import com.flyboiz.afrs.View.Output;

public class QueryExecutor {
	private Query currentQuery;
	private Output output;
	private QueryDecider queryDecider;

	/**
	 * Constructor for the QueryExecutor object. When created, this object doesn't have any Query objects attached to it.
	 */
	public QueryExecutor(Output output, QueryDecider queryDecider) {
		currentQuery = null;
		this.output = output;
		this.queryDecider = queryDecider;
	}

	/**
	 * When a query is created by the factory, it is given to this object to execute and store the output.
	 *
	 * @param query
	 */
	public void setCurrentQuery(Query query) {
		if (query == null) {
			output.update("error,unknown request");
		} else {
			currentQuery = query;
			//System.out.println("Current query class: " + currentQuery.getClass());
			String response = currentQuery.generateResponse();
			output.update(response);
		}
	}

	/**
	 * Method to set the output of the QueryExecutor.
	 *
	 * @param output the specified object to which output should be sent.
	 */
	public void setOutput(Output output) {
		this.output = output;
	}

	/**
	 * Creates a query from the user input and sends it to the execution function
	 *
	 * @param userInput-
	 */
	public void makeQuery(String userInput) {
		//System.out.println("UserInput: " + userInput);
		Query query = queryDecider.queryDecide(userInput);
		setCurrentQuery(query);
	}

}
