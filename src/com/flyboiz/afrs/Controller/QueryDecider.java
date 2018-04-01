package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Controller.Commands.Query;
import com.flyboiz.afrs.Controller.QueryCreators.QueryCreator;

import java.util.Map;

public class QueryDecider
{
	private Map<String, QueryCreator> factories;
	private final String DELIMETER = ",";

	public QueryDecider(Map<String, QueryCreator> factories)
	{
		this.factories = factories;
	}

	public Query queryDecide(String userInput)
	{
		String key;
		String[] split = userInput.split(DELIMETER);
		if (split.length > 1) {
			key = split[1];
		} else {
			key = userInput;
		} if (factories.containsKey(key)) {
			return (factories.get(key)).makeQuery(userInput);
		} else {
			return null;
		}
	}
}
