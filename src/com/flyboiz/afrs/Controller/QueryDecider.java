package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Controller.Commands.Query;

import java.util.Map;

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
			return (factories.get(split[1])).makeQuery(userInput);
		}
		else
		{
			return (factories.get(userInput)).makeQuery(userInput);
		}

	}
}
