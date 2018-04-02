/* Authors:
Kent Brown
*/

// Package //
package com.flyboiz.afrs.Controller.SortStrategy;

// Imports //

import com.flyboiz.afrs.Model.Itinerary;

import java.util.List;

public interface SortStrategy {

	// Behaviors //

	/**
	 * Method that will need to be implemented to provide sort functionality
	 * @param list A list of itineraries
	 */
	void sort(List<Itinerary> list);

}
