/* created by Kent Brown on 3/3/2018 */

/* package */
package com.flyboiz.afrs.Model;

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

		if (initialSplit[1].contains("p")) {
			this.hours += 12;
		}

		String tmp = initialSplit[1].substring(0, 2);

		this.minutes = Integer.parseInt(tmp);
	}

	public Time(int hrs, int mins) {
		this.hours = hrs;
		this.minutes = mins;
	}

	// GETTERS & SETTERS //
	public int getHours() {
		return hours;
	}

	public int getMinutes() {
		return minutes;
	}

	// BEHAVIOUR //

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

	public Time calculateDifference(Time t) {
		return null;
	}

	@Override
	public String toString() {
		String tmp = "a";
		int localHours = this.hours;
		if (localHours >= 12) {
			localHours -= 12;
			tmp = "p";
		}
		return localHours + ":" + minutes + tmp;
	}


	@Override
	public int compareTo(Object o) {
		// Make sure it is a Time object
		if (o instanceof Time) {
			// If it's in the same hour, compare minutes
			if (this.hours == ((Time) o).getHours()) {
				return Integer.compare(getMinutes(), ((Time) o).getMinutes());
			}
			// Otherwise compare hours
			return Integer.compare(getHours(), ((Time) o).getHours());
		}
		return 0;
	}

}
