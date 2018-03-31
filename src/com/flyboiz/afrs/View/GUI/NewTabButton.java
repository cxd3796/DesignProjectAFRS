/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */
import javafx.scene.control.Button;

/* implementation */
public class NewTabButton extends Button {

    // CONSTANTS //
    private static final String BUTTON_TEXT = "+";

    // STATE //
    private ViewManager viewManager;

    // CONSTRUCTOR //
    public NewTabButton(ViewManager vm, double width, double height) {
        this.viewManager = vm;
        this.setText(BUTTON_TEXT);
        setAbsWidth(width);
        setAbsHeight(height);
        setOnMouseClicked(e -> {
            vm.newWindow();
        });
    }

    // GETTERS & SETTERS //
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

    // BEHAVIOUR //

}
