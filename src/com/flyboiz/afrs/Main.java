/* Authors:
Kent Brown
*/

// Package //
package com.flyboiz.afrs;

// Imports //

import com.flyboiz.afrs.Controller.QueryMaker;
import com.flyboiz.afrs.View.InputReader;
import com.flyboiz.afrs.View.Output;
import com.flyboiz.afrs.View.OutputSender;

// Implementation //
public class Main
{

    public static void main(String[] args) {

        // Instantiate controller objects. //
        QueryMaker queryMaker = new QueryMaker();
        InputReader reader = new InputReader(queryMaker);
        Output output = new OutputSender(reader);

    }

}
