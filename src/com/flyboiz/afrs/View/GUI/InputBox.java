/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */
import com.flyboiz.afrs.View.Input;
import javafx.scene.control.TextArea;

/* implementation */
public class InputBox extends TextArea implements Input {

    // CONSTANTS //
    private static final String SUBMIT_CHARACTER = "\r"; // Escape character is a carriage return.
    private static final char DELIMITING_CHARACTER = ';';

    // STATE //
    private IOPane ioPane;
    private String currentLine;

    // CONSTRUCTOR //
    public InputBox(IOPane pane, double width, double height) {
        this.ioPane = pane;
        currentLine = "";
        setAbsWidth(width);
        setAbsHeight(height);
        setOnKeyTyped(e -> {
            keyTyped(e.getCharacter());
        });
    }

    // GETTERS & SETTERS //


    // PUBLIC BEHAVIOUR //
    public void retreatCharacter() {
        backward();
    }

    // PUBLIC INTERFACE BEHAVIOUR //
    @Override
    public void submit(String queryText) {
        ioPane.submit(queryText);
    }

    // PRIVATE BEHAVIOUR //
    private void keyTyped(String character) {
        if (character.equals(SUBMIT_CHARACTER)) {
            if (currentLine.charAt(currentLine.length() - 1) != DELIMITING_CHARACTER) {
                ioPane.submit("pr" + DELIMITING_CHARACTER);
            } else {
                ioPane.submit(currentLine);
                clearCurrentLine();
            }
        } else if (character.equals("\b")) {
            if (currentLine.length() > 0) {
                currentLine = currentLine.substring(0, currentLine.length() - 1);
            }
        } else {
            currentLine = currentLine + character;
        }
    }
    private void clearCurrentLine() {
        currentLine = "";
    }
    private void setAbsHeight(double height) {
        setMinHeight(height);
        setPrefHeight(height);
        setMaxHeight(height);
    }
    private void setAbsWidth(double width) {
        setMinWidth(width);
        setPrefWidth(width);
        //setMaxWidth(width);
    }
}
