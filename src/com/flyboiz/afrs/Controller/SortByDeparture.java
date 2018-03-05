package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Model.FlightDatabase;
import com.flyboiz.afrs.Model.Itinerary;
import com.flyboiz.afrs.Model.Time;

import java.util.List;

public class SortByDeparture implements SortStrategy {

	public void sort(List<Itinerary> list) {
		int n = list.size();
		for (int i = 1; i < n; i++) {
			Itinerary key = list.get(i);
			int j = i - 1;
			while (j >= 0 && list.get(j).getDepartureTime().compareTo(key.getDepartureTime()) > 0) {
				list.set(j + 1, list.get(j));
				j = j - 1;
			}
			list.set(j + 1, key);
		}
	}
}
