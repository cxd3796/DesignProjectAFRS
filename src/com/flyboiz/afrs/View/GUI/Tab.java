/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/* implementation */
public class Tab extends Button{

    // STATE //
    private int tabID;
    private ViewManager manager;

    // CONSTRUCTOR //

    /**
     * Construct a tab
     * @param vm the viewmanager
     * @param tabID the tabID for this tab
     * @param width the width of the tab
     * @param height the height of the tab
     */
    public Tab(ViewManager vm, int tabID, double width, double height) {
        this.manager = vm;
        this.tabID = tabID;
        setText("Tab " + Integer.toString(tabID + 1));
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

    /**
     * Call manager.changeTab(this);
     */
    private void selectThisTab() {
        manager.changeTab(this);
    }
    private void setAbsHeight(double height) {
        setMinHeight(height);
        setPrefHeight(height);
        setHeight(height);
        setMaxHeight(height);
    }
    private void setAbsWidth(double width) {
        setMinWidth(width);
        setPrefWidth(width);
        setWidth(width);
        setMaxWidth(width);
    }

    public void resizeHeight(double newValue) {
        setAbsHeight(newValue);
    }
    public void resizeWidth(double newValue) {
        setAbsWidth(newValue);
    }
}
