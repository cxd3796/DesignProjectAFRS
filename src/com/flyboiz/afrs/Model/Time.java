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

}
