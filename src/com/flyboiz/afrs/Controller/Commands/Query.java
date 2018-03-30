package com.flyboiz.afrs.Controller.Commands;

/**
 * Encapsulates the query from the client.
 * Command within the Command pattern
 * Product within the Factory pattern
 */
public abstract class Query {
    int cid;                        // Client id

	public Query(int cid) {
		this.cid = cid;
	}

	/**
	 * @return A string that  represents the desired result.
	 */
	public abstract String generateResponse();
}
