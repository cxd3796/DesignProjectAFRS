/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.Controller;

/* imports */

/* implementation */
public class QueryPartialRequest implements Query {

    // PARTIAL REQUEST STRING //
    public static final String PARTIAL_REQUEST_STRING = "partial-request";

    // STATE //

    // CONSTRUCTOR //
    public QueryPartialRequest() {
        // no initialization
    }

    // GETTERS & SETTERS //

    // BEHAVIOUR //
    @Override
    public String generateResponse() {
        return PARTIAL_REQUEST_STRING;
    }

}
