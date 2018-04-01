/* Package */
package com.flyboiz.afrs.View;

/* Imports */

import com.flyboiz.afrs.Controller.QueryExecutor;

import java.util.Scanner;

/**
 * This class is in charge of getting the input from the user
 */
public class InputReader implements Input {
	// State //
	private final Scanner scanner;
	private Output sender;
	private QueryExecutor queryExecutor;
	private String currentString;

	// Constructor //
	public InputReader() {
		this.scanner = new Scanner(System.in);
		currentString = "";
	}

	/**
	 * Gets a line of input from the console.
	 *
	 * @return a String of input from the console.
	 */
	private void getInputLine() {
		currentString += scanner.nextLine();
	}

	/**
	 * Wait for a string to be entered, then send it to the QueryExecutor to be parsed.
	 */
	public void waitOnInput() {
		while (true) {
			getInputLine();
			char lastChar = currentString.charAt(currentString.length() - 1);
			if (lastChar == ';') {
				String requestString = currentString.substring(0, currentString.length() - 1);
				submit(requestString);
				currentString = "";
			} else {
				sender.update("partial-request");
			}
		}
	}

	/**
	 * Set the queryExecutor. only works once.
	 *
	 * @param qe the queryexecutor.
	 */
	public void setExecutor(QueryExecutor qe) {
		if (queryExecutor == null) {
			this.queryExecutor = qe;
		}
	}

	/**
	 * Set the outputreader. only works once.
	 *
	 * @param op the outputreader.
	 */
	public void setSender(Output op) {
		if (sender == null) {
			this.sender = op;
		}
	}

	@Override
	public void submit(String queryText) {
		queryExecutor.makeQuery(queryText);
	}
}
