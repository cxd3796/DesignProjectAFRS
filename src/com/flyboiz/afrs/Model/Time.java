/* created by Kent Brown on 3/3/2018 */

/* package */
package com.flyboiz.afrs.Model;

/* imports */

/* implementation */
public class Time implements Comparable {

    // STATE //
    private int hours;
    private int minutes;

    // CONSTRUCTOR //
    // Pre-condition : s is of the form "H:MM(char)" or "HH:MM(char)"
    public Time(String s) {
        String[] initialSplit = s.split(":");

        this.hours = Integer.parseInt(initialSplit[0]);

        if(initialSplit[1].contains("p")){
            this.hours += 12;
        }

        String tmp = initialSplit[1].substring(0, 2);

        this.minutes = Integer.parseInt(tmp);
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
        String tmp = "a";
        int localHours = this.hours;
        if(localHours >= 12){
            localHours -= 12;
            tmp = "p";
        }
        return localHours + ":" + minutes + tmp;
    }


    @Override
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
