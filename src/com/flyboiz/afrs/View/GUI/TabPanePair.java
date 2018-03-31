/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */
import javafx.scene.layout.Pane;

/* implementation */
public class TabPanePair {

    // STATE //
    private int id;
    private Tab tab;
    private IOPane pane;

    // CONSTRUCTOR //
    public TabPanePair(Tab tab, IOPane pane) {
        this.tab = tab;
        this.pane = pane;
        this.id = this.tab.getTabID();
    }

    // GETTERS & SETTERS //
    int getID() {
        return id;
    }

    // BEHAVIOUR //

}
