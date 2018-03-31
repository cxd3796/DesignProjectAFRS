/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/* implementation */
public class Tab extends Button {

    // STATE //
    private int tabID;
    private ViewManager manager;

    // CONSTRUCTOR //
    public Tab(ViewManager vm, int tabID, double width, double height) {
        this.manager = vm;
        this.tabID = tabID;
        setText(Integer.toString(tabID));
        setAbsWidth(width);
        setAbsHeight(height);
        setOnMouseClicked(e -> {
                selectThisTab();
        });
    }

    // GETTERS & SETTERS //

    // BEHAVIOUR //
    public int getTabID() {
        return tabID;
    }

    private void selectThisTab() {
        manager.changeTab(this);
    }
    private void setAbsHeight(double height) {
        setMinHeight(height);
        setPrefHeight(height);
        setMaxHeight(height);
    }
    private void setAbsWidth(double width) {
        setMinWidth(width);
        setPrefWidth(width);
        setMaxWidth(width);
    }
}
