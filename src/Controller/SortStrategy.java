package Controller;

import Model.Itinerary;

import java.util.List;

public interface SortStrategy
{
	public void sort(List<Itinerary> list);
}
