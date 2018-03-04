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
    @Override
    public String toString() {
        String tmp = "a";
        int localHours = this.hours;
        if(localHours > 12){
            localHours -= 12;
            tmp = "p";
        }

        String minString = Integer.toString(minutes);
        if(minutes < 10){
            minString = "0" + minString;
        }

        return localHours + ":" + minString + tmp;
    }

}
