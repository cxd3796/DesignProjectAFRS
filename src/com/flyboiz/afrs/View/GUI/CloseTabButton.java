/* created by Kent Brown on 4/1/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */
import javafx.scene.control.Button;
import javafx.scene.text.Font;

/* implementation */
class CloseTabButton extends Button{

    // STATE //
    private ViewManager viewManager;
    private int tabID;
    private Font font;

    // CONSTRUCTOR //

    /**
     * Construct the CloseTabButton
     * @param vm The ViewManager
     * @param tabID The ID of the tab this button is associated with
     * @param font the font of the button
     * @param width the width of the button
     * @param height the height of the button
     */
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

    // BEHAVIOUR //

    /**
     * When the button is pushed, call this method (close the current tab).
     */
    private void pushed() {
        viewManager.closeTab(tabID);
    }

    /**
     * Set the height of the control, absolutely and without question.
     * @param height the desired height.
     */
    private void setAbsHeight(double height) {
        setMinHeight(height);
        setPrefHeight(height);
        setHeight(height);
        setMaxHeight(height);
    }
    /**
     * Absolutely, positively set the width of this node.
     * @param width the desired width
     * */
    private void setAbsWidth(double width) {
        setMinWidth(width);
        setPrefWidth(width);
        setWidth(width);
        setMaxWidth(width);
    }

    /**
     * Set the font of this button
     * @param font the font
     */
    public void setMyFont(Font font) {
        setFont(font);
        this.font = font;
    }

    /**
     * Resize this object's height
     * @param newValue desired height
     */
    void resizeHeight(double newValue) {
        setAbsHeight(newValue);
    }

    /**
     * Resize this object's width
     * @param newValue the desired width
     */
    void resizeWidth(double newValue) {
        setAbsWidth(newValue);
    }
}
