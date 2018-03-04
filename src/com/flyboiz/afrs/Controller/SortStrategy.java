package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Model.Itinerary;

import java.util.List;

public interface SortStrategy {

	public void sort(List<Itinerary> list);

}
