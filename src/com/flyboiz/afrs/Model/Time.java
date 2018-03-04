/* created by Kent Brown on 3/3/2018 */

/* package */
package com.flyboiz.afrs.Model;

/* imports */

/* implementation */
public class Time {

    // STATE //
    private int hours;
    private int minutes;

    // CONSTRUCTOR //
    public Time(int h, int m) {
        //TODO this needs to be revamped. This should take a String object and parse it, then calculate the time in military time
        this.hours = h;
        this.minutes = m;
    }

    // GETTERS & SETTERS //
    public int getHours() {
        return hours;
    }

    public int getMinutes () {
        return minutes;
    }

    // BEHAVIOUR //

    public Time calculateDifference(Time t){
        return null;
    }

    @Override
    public String toString(){
        return null;
    }

}
