package com.flyboiz.afrs.Model;

/**
 * designproject-flyboiz
 * Created by Trevor Lezynski on 3/31/2018.
 */
public class WeatherIterator {

    private int currentPosition;
    private final int MAX_SIZE;

    public WeatherIterator(int maxSize){
        currentPosition = 0;
        MAX_SIZE = maxSize;
    }

    public void next(){
        if(currentPosition != MAX_SIZE) {
            currentPosition++;
        } else {
            currentPosition = 0;
        }
    }

    public int currentIndex(){
        return currentPosition;
    }

}
