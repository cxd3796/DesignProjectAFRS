package com.flyboiz.afrs.Controller;

import com.flyboiz.afrs.Model.Itinerary;
import com.flyboiz.afrs.Model.Itinerary;

import java.util.ArrayList;
import java.util.List;

public interface SortStrategy
{
	List<Itinerary> sort(List<Itinerary> list);
}
