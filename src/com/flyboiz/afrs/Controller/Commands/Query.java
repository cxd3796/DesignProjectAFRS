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

	/**
	 * Constructor for queries that don't need a client database
	 * @param cid
	 */
	public Query(int cid) {
		this.cid = cid;
	}

	/**
	 * Constructor for queries that need a client database, but not an id (connect)
	 * @param clientDB
	 */
	public Query(ClientDatabase clientDB){
		this.clientDB = clientDB;
	}

	/**
	 * Constructor for queries that need both a cid and a client database.
	 * @param cid
	 * @param clientDB
	 */
	public Query(int cid, ClientDatabase clientDB)
	{
		this.cid = cid;
		this.clientDB = clientDB;
	}


	/**
	 * @return A string that represents the desired result.
	 */
	public abstract String generateResponse();
}
