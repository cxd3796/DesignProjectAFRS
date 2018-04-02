/* created by Kent Brown on 4/1/2018 */

/* package */
package com.flyboiz.afrs.Controller.QueryCreators;

/* imports */

import com.flyboiz.afrs.Controller.Commands.Query;
import com.flyboiz.afrs.Controller.Commands.QueryPartialRequest;
import com.flyboiz.afrs.Controller.QueryExecutor;

/* implementation */
public class QPartialRequestCreator extends QueryCreator {

    // STATE //

    // CONSTRUCTOR //

    // GETTERS & SETTERS //

    // BEHAVIOUR //
    @Override
    public Query makeQuery(String userInput) {
        return new QueryPartialRequest();
    }

}
