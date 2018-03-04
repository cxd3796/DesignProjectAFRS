/* created by Kent Brown on 3/3/2018 */

/* package */
package com.flyboiz.afrs.Model;

/* imports */

import java.util.Comparator;

/* implementation */
public class Time implements Comparator {

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
        return null;
    }

    @Override
    public int compare(Object o1, Object o2) {
        // Make sure they are Time objects
        if(o1 instanceof Time && o2 instanceof Time){
            // If it's in the same hour, compare minutes
            if(((Time) o1).getHours() == ((Time) o2).getHours()){
                return Integer.compare(((Time) o1).getMinutes(), ((Time) o2).getMinutes());
            }
            // Otherwise compare hours
            return Integer.compare(((Time) o1).getHours(), ((Time) o2).getHours());
        }
            
        return 0;
    }
}
