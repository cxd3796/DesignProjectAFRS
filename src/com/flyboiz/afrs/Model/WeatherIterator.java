package com.flyboiz.afrs.Model;

import java.util.List;

/**
 * designproject-flyboiz
 * Created by Trevor Lezynski on 3/31/2018.
 */
public class WeatherIterator implements GeneralIterator{

    private int currentPosition;
    private final int MAX_SIZE;
    private List<Weather> list;

    public WeatherIterator(List list){
        currentPosition = 0;
        MAX_SIZE = list.size();
        this.list = list;
    }

    public Weather next(){
        Weather w = getCurrentItem();
        if(currentPosition != MAX_SIZE) {
            currentPosition++;
        } else {
            currentPosition = 0;
        }
        return w;
    }

    public Weather getCurrentItem(){
        return list.get(getCurrentIndex());
    }

    public int getCurrentIndex(){
        return currentPosition;
    }

}
