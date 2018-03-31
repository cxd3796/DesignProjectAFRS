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
		if(split.length > 1)
		{
			QueryCreator factory = factories.get(split[1]);
		}
		else
		{
			QueryCreator factory = factories.get(userInput);
		}
		return factory.makeQuery(userInput);
	}
}
