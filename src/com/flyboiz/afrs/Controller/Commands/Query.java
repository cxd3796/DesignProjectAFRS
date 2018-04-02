package com.flyboiz.afrs.Controller.Commands;

import com.flyboiz.afrs.Model.ClientDatabase;

/**
 * Encapsulates the query from the client.
 * Command within the Command pattern
 * Product within the Factory pattern
 */
public abstract class Query {
    int cid;                        // Client id
	protected ClientDatabase clientDB;
	public Query(int cid) {
		this.cid = cid;
	}

	/**
	 * this constructor is for queries that do not require cid's. (QueryConnect)
	 * @param clientDB
	 */
	public Query(ClientDatabase clientDB){
		this.clientDB = clientDB;
	}

	public Query(int cid, ClientDatabase clientDB)
	{
		this.cid = cid;
		this.clientDB = clientDB;
	}


	/**
	 * @return A string that  represents the desired result.
	 */
	public abstract String generateResponse();
}
