/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */
import com.flyboiz.afrs.View.Output;
import javafx.scene.control.TextArea;

/* implementation */
public class OutputBox extends TextArea implements Output {

    // CONSTANTS //
    private static final String NEWLINE = "\r\n";

    // STATE //

    // CONSTRUCTOR //
    OutputBox(double width, double height) {

        // Change the properties of the box.

        // Set up the size of the box.
        setAbsWidth(width);
        setAbsHeight(height);
    }

    // BEHAVIOUR //
    @Override
    public void update(String updateText) {
        appendText(updateText);
        appendText(NEWLINE);
    }

    // PRIVATE BEHAVIOUR //
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
