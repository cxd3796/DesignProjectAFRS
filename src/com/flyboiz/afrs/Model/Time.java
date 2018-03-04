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

}
