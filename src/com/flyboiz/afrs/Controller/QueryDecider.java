package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Controller.Commands.Query;
import com.flyboiz.afrs.Controller.QueryCreators.QueryCreator;
import com.flyboiz.afrs.Model.ClientDatabase;

import java.util.Map;

public class QueryDecider
{
	private Map<String, QueryCreator> factories;
	private ClientDatabase clientDatabase;
	private final String DELIMETER = ",";

	public QueryDecider(Map<String, QueryCreator> factories, ClientDatabase clientDatabase)
	{
		this.factories = factories;
		this.clientDatabase = clientDatabase;
	}

	public Query queryDecide(String userInput)
	{
		String[] split = userInput.split(DELIMETER);
		Query query;

		if(split.length > 1)
		{
			int cid = Integer.parseInt(split[0]);
			if(!factories.containsKey(split[1]))
			{
				return null;
			}
			query = (factories.get(split[1])).makeQuery(userInput);
			clientDatabase.addLastQuery(query, cid);

			return query;
		}
		else //This case will only occur for a connect
		{
			if(!factories.containsKey(userInput))
			{
				return null;
			}
			query = (factories.get(userInput)).makeQuery(userInput);
			return query ;
		}

	}
}
