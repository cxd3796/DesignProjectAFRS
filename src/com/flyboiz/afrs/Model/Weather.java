package com.flyboiz.afrs.Model;

/**
 * designproject-flyboiz
 * Created by Trevor Lezynski on 3/31/2018.
 */
public class Weather {

    private String condition;
    private int temperature;
    private int delay;

    public Weather(String condition, int temperature, int delay) {
        this.condition = condition;
        this.temperature = temperature;
        this.delay = delay;
    }

    @Override
    public String toString() {
        return String.format("%s,%d", condition, temperature);
    }
}


