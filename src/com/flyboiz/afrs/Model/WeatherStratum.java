package com.flyboiz.afrs.Model;

public interface WeatherStratum {

    /**
     * Get the weather of the airport.
     * Format slightly changes depending on the Concrete Strategy
     *
     * @return String usually including Airport Name, Weather Condition, Temperature, Delay
     */
    public String getWeather(int cid);

    /**
     * Get the delay time at the airport.
     * If the weather is gathered from the FAA, delay time could be 0.
     *
     * @return int 0 to UnitedWaitingRoom
     */
    public int getDelay();
}
