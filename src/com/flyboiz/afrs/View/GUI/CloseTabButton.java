/* created by Kent Brown on 4/1/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */

import javafx.scene.control.Button;
import javafx.scene.text.Font;

/* implementation */
public class CloseTabButton extends Button{

    // STATE //
    private ViewManager viewManager;
    private int tabID;
    private Font font;

    // CONSTRUCTOR //
    CloseTabButton(ViewManager vm, int tabID, Font font, double width, double height) {
        this.viewManager = vm;
        this.tabID = tabID;
        setText("X");
        setMyFont(font);
        setOnMouseClicked(e -> {
            pushed();
        });
        resizeWidth(width);
        resizeHeight(height);
        toFront();
    }

    // GETTERS & SETTERS //

    // BEHAVIOUR //
    private void pushed() {
        viewManager.closeTab(tabID);
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

    public void setMyFont(Font font) {
        setFont(font);
        this.font = font;
    }

    public void resizeHeight(double newValue) {
        setAbsHeight(newValue);
    }

    public void resizeWidth(double newValue) {
        setAbsWidth(newValue);
    }
}
