/* created by Kent Brown on 3/3/2018 */

/* package */
package com.flyboiz.afrs.Model;

public class Time implements Comparable {

    private int hours;
    private int minutes;


    /**
     *
     * @param s: String formatted in the form "H:MM(char)" or "HH:MM(char)"
     */
    public Time(String s) {
        String[] initialSplit = s.split(":");

        this.hours = Integer.parseInt(initialSplit[0]);

        if(initialSplit[1].contains("p")){
            this.hours += 12;
        }

        String tmp = initialSplit[1].substring(0, 2);

        this.minutes = Integer.parseInt(tmp);
    }

    /**
     *
     * @param hrs: int type, hours
     * @param mins: int type, minutes
     */
    public Time (int hrs, int mins) {
        this.hours = hrs;
        this.minutes = mins;
    }


    /**
     *
     * @return int hours (standard getter)
     */
    public int getHours() {
        return hours;
    }

    /**
     *
     * @return int minutes (standard getter)
     */
    public int getMinutes () {
        return minutes;
    }


    /**
     *
     * @param t: Time object
     * @return boolean: True if this Time occurs before the parameter time
     */
    public boolean occursBefore(Time t) {
        if (getHours() < t.getHours()) {
            return true;
        } else if (getHours() > t.getHours()) {
            return false;
        } else {
            return (getMinutes() < t.getMinutes());
        }
    }


    /**
     *
     * @param t: Time object
     * @param additionalMinutes: int minutes
     * @return boolean : True if this Time is object is before the parameter time object with the added minutes
     */
    public boolean stillBefore(Time t, int additionalMinutes) {
        int addHours = additionalMinutes / 60;
        int addMinutes = additionalMinutes % 60;
        Time newTime = new Time(getHours() + addHours, getMinutes() + addMinutes);
        return newTime.occursBefore(t);
    }

    /**
     *
     * @param t: Time object T
     * @return int total number of minutes between this time and the parameter
     */
    public int totalMinutesUntil(Time t) {
        int hrs = t.getHours() - getHours();
        int mins = t.getMinutes() - getMinutes();
        return (hrs * 60) + mins;
    }

    @Override
    /**
     * Returns the object in the "H:MM(char)" or "HH:MM(char)" format
     */
    public String toString(){
        String tmp = "a";
        String min = Integer.toString(getMinutes());
        int localHours = this.hours;
        if(localHours >= 12){
            localHours -= 12;
            tmp = "p";
        } if (getMinutes() < 10) {
            min = "0" + min;
        }
        return localHours + ":" + min + tmp;
    }


    @Override
    /**
     * @param o: Object, usually a Time object
     * @return -1 if this is less than o, 0 if they are the same, 1 if this is more than o
     */
    public int compareTo(Object o) {
        // Make sure it is a Time object
        if(o instanceof Time){
            // If it's in the same hour, compare minutes
            if(this.hours == ((Time) o).getHours()){
                return Integer.compare(getMinutes(), ((Time) o).getMinutes());
            }
            // Otherwise compare hours
            return Integer.compare(getHours(), ((Time) o).getHours());
        }
        return 0;
    }
    
}
