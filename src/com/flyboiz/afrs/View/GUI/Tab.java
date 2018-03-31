/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */
import javafx.scene.control.Button;

/* implementation */
public class Tab extends Button {

    // STATE //
    private int tabID;
    private ViewManager manager;

    // CONSTRUCTOR //
    public Tab(int tabID) {
        this.tabID = tabID;
        setOnMouseClicked(e -> {
                    manager.changeTab(this);
        });
    }

    // GETTERS & SETTERS //

    // BEHAVIOUR //
    public int getTabID() {
        return tabID;
    }
}
