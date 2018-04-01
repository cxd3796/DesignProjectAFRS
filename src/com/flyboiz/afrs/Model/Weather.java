package com.flyboiz.afrs.Model;

/**
 * designproject-flyboiz
 * Created by Trevor Lezynski on 3/31/2018.
 */
public class Weather {

    private String condition;
    private int temperature;

    public Weather(String condition, int temperature) {
        this.condition = condition;
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return String.format("%s,%d", condition, temperature);
    }

}


