package com.flyboiz.afrs.Model;

import java.util.HashMap;
import java.util.List;

public class LocalWeather implements WeatherStratum{

    private HashMap<Integer, WeatherIterator> weatherIterators;
    private List<Weather> weatherList;
    int delayTime;

    public LocalWeather(List<Weather> weatherList){
        weatherIterators = new HashMap<>();
        this.weatherList = weatherList;
        delayTime = 0;
    }

    private WeatherIterator getIterator(int cid){
        return weatherIterators.get(cid);
    }

    /**
     * Gather weather from the local
     * @return String
     */
    @Override
    public String getWeather(int cid) {
        if (!weatherIterators.containsKey(cid)) {
            WeatherIterator weatherIterator = new WeatherIterator(weatherList);
            weatherIterators.put(cid, weatherIterator);
        }
        String tmp = getIterator(cid).getCurrentItem().toString();
        getIterator(cid).next();
        return String.format("%s,%d", tmp, getDelay());
    }

    @Override
    public int getDelay() {
        return delayTime;
    }

    public void setDelay(int delay) {
        delayTime = delay;
    }
}
