/* Package */
package com.flyboiz.afrs.View;

/* Imports */
import com.flyboiz.afrs.Controller.QueryExecutor;
import com.flyboiz.afrs.Controller.QueryMaker;
import java.util.Scanner;

/**
 * This class is in charge of getting the input from the user
 */
public class InputReader
{
    // State //
    private final Scanner scanner;
    private final QueryExecutor queryExecutor;

    // Constructor //
    public InputReader(QueryExecutor queryExecutor) {
        this.scanner = new Scanner(System.in);
        this.queryExecutor = queryExecutor;
    }

    /**
     * Gets a line of input from the console.
     * @return a String of input from the console.
     */
    private String getInputLine(){
        return scanner.nextLine();
    }

    /**
     * Wait for a string to be entered, then send it to the QueryExecutor to be parsed.
     */
    public void waitOnInput() {
        String input = getInputLine();
        queryExecutor.makeQuery(input);
    }
}
