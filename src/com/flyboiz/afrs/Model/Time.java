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
        int convertedHours = getHours();
        String timeSuffix;
        if (convertedHours > 12) {
            convertedHours -= 12;
            timeSuffix = "p";
        } else {
            timeSuffix = "a";
        }
        int minutes = getMinutes();
        return Integer.toString(convertedHours) +
                ":"                             +
                Integer.toString(minutes)       +
                timeSuffix                      ;
    }

    // method returns true if this time occurs before parameter time
    public boolean occursBefore(Time t) {
        if (getHours() < t.getHours()) {
            return true;
        } else if (getHours() > t.getHours()) {
            return false;
        } else {
            return (getMinutes() < t.getMinutes());
        }
    }

    // method returns true if this time still occurs before time T, even when additional minutes are added
    public boolean stillBefore(Time t, int additionalMinutes) {
        int addHours = additionalMinutes / 60;
        int addMinutes = additionalMinutes % 60;
        Time newTime = new Time(getHours() + addHours, getMinutes() + addMinutes);
        return newTime.occursBefore(t);
    }

    // method to determine the number of minutes that must pass until this time reaches parameter time
    public int totalMinutesUntil(Time t) {
        int hrs = t.getHours() - getHours();
        int mins = t.getMinutes() - getMinutes();
        return (hrs * 60) + mins;
    }

}
