package com.flyboiz.afrs.Model;

import java.util.HashMap;
import java.util.List;

public class LocalWeather implements WeatherStratum{

    private HashMap<Integer, GeneralIterator> weatherIterators;
    private List<Weather> weatherList;

    public LocalWeather(List<Weather> weatherList){
        weatherIterators = new HashMap<>();
        this.weatherList = weatherList;
    }

    /**
     * Gather weather from the local
     *
     * @param cid client id
     * @return String
     */
    @Override
    public String getWeather(int cid) {
        if (!weatherIterators.containsKey(cid)) {
            WeatherIterator weatherIterator = new WeatherIterator(weatherList);
            weatherIterators.put(cid, weatherIterator);
            Weather weather = (Weather) weatherIterator.next();
        }
        return null;
    }

    @Override
    public int getDelay() {
        return 0;
    }
}
