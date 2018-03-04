package com.flyboiz.afrs.Model;

/**
 * designproject-flyboiz
 * Created by Trevor Lezynski on 3/3/2018.
 */
public class Time {

    private int hours;
    private int minutes;
    private timeOfDay tod;

    enum timeOfDay{
        AM, PM
    }

    public Time(int hours, int minutes, String timeOfDay){
        this.hours = hours;
        this.minutes = minutes;

        if (timeOfDay.equals("p")){
            this.tod = Time.timeOfDay.AM;
        }
        else if (timeOfDay.equals("a")){
            this.tod = Time.timeOfDay.PM;
        }
    }

    @Override
    public String toString(){
        String timeOfDay = "";

        if (this.tod.equals(Time.timeOfDay.AM)){
            timeOfDay = "a";
        }
        else if (this.tod.equals(Time.timeOfDay.PM)){
            timeOfDay = "p";
        }

        return hours + ":" + minutes + timeOfDay;
    }



}
