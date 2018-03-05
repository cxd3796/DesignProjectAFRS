package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Model.FlightDatabase;
import com.flyboiz.afrs.Model.Itinerary;

import java.util.*;

public class SortByAirfare implements SortStrategy {

	/**
	 * Sorting the itineraries based on airfare (Low to High)
	 * @param itineraries The list of itineraries to be sorted
	 */
	public void sort(List<Itinerary> itineraries) {
		int n = itineraries.size();
		for (int i = 1; i < n; i++) {
			Itinerary key = itineraries.get(i);
			int j = i - 1;
			while (j >= 0 && itineraries.get(j).getFare() > key.getFare()) {
				itineraries.set(j + 1, itineraries.get(j));
				j = j - 1;
			}
			itineraries.set(j + 1, key);
		}

	}


}
