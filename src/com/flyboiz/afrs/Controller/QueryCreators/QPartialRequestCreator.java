/* created by Kent Brown on 4/1/2018 */

/* package */
package com.flyboiz.afrs.Controller.QueryCreators;

/* imports */

import com.flyboiz.afrs.Controller.Commands.Query;
import com.flyboiz.afrs.Controller.Commands.QueryPartialRequest;
import com.flyboiz.afrs.Controller.QueryExecutor;

/* implementation */
public class QPartialRequestCreator extends QueryCreator {

    // BEHAVIOUR //

    /**
     * Creates a query for partial requests
     * @param userInput The user's input
     * @return Returns a QueryPartialRequest
     */
    @Override
    public Query makeQuery(String userInput) {
        return new QueryPartialRequest();
    }

}
