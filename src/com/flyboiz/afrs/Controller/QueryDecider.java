package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Controller.Commands.Query;

public class QueryDecider
{
	private Map<String, QueryCreator> factories;


	public QueryDecider(Map<String, QueryCreator> factories)
	{
		this.factories = factories;
	}

	public Query queryDecide(String userInput)
	{
		String[] split = userInput.split(",");
		QueryCreator factory = factories.get(split[1]);
		return factory.makeQuery(userInput);
	}
}
