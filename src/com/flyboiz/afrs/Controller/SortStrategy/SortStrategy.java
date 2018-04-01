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
	void sort(List<Itinerary> list);

}
