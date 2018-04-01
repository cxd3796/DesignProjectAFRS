package com.flyboiz.afrs.Controller.SortStrategy;

import com.flyboiz.afrs.Model.Itinerary;

import java.util.List;

public class SortByArrival implements SortStrategy {
	/**
	 * sorts given list of itineraries by earliest arrival time to latest
	 *
	 * @param list The list of itineraries to be sorted
	 */
	public void sort(List<Itinerary> list) {
		int n = list.size();
		for (int i = 1; i < n; i++) {
			Itinerary key = list.get(i);
			int j = i - 1;
			while (j >= 0 && list.get(j).getArrivalTime().compareTo(key.getArrivalTime()) > 0) {
				list.set(j + 1, list.get(j));
				j = j - 1;
			}
			list.set(j + 1, key);
		}
	}
}
