package com.flyboiz.afrs.Model;

import java.util.List;

/**
 * designproject-flyboiz
 * Created by Trevor Lezynski on 3/31/2018.
 */
public class WeatherIterator {

    private int currentPosition;
    private final int MAX_SIZE;
    private List list;

    public WeatherIterator(List list){
        currentPosition = 0;
        MAX_SIZE = list.size();
        this.list = list;
    }

    public Object next(){
        Object obj;
        if(currentPosition != MAX_SIZE) {
            obj = list.get(getCurrentIndex());
            currentPosition++;
        } else {
            obj = list.get(getCurrentIndex());
            currentPosition = 0;
        }
        return obj;
    }

    public int getCurrentIndex(){
        return currentPosition;
    }

}
