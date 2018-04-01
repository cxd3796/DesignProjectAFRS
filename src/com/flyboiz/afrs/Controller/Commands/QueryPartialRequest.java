/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.Controller.Commands;

/* imports */

import com.flyboiz.afrs.Controller.Commands.Query;
import com.flyboiz.afrs.Model.ClientDatabase;

/* implementation */
public class QueryPartialRequest extends Query {

    // PARTIAL REQUEST STRING //
    public static final String PARTIAL_REQUEST_STRING = "partial-request";

    // STATE //

    // CONSTRUCTOR //
    public QueryPartialRequest() {
        super(null);
        // no initialization
    }

    // GETTERS & SETTERS //

    // BEHAVIOUR //
    @Override
    public String generateResponse() {
        return PARTIAL_REQUEST_STRING;
    }

}
