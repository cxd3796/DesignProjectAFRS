/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */
import com.flyboiz.afrs.View.Input;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

/* implementation */
public class InputBox extends TextArea implements Input, Resizeable{

    // CONSTANTS //
    private static final String SUBMIT_CHARACTER = "\r"; // Escape character is a carriage return.
    private static final char DELIMITING_CHARACTER = ';';

    // STATE //
    private IOPane ioPane;
    private Font font;
    private String currentLine;

    // CONSTRUCTOR //
    public InputBox(IOPane pane, Font font, double width, double height) {
        this.ioPane = pane;
        this.font = font;
        setFont(font);
        currentLine = "";
        setAbsWidth(width);
        setAbsHeight(height);
        setOnKeyTyped(e -> {
            keyTyped(e.getCharacter());
        });
    }

    // GETTERS & SETTERS //
    @Override
    public void resizeHeight(double newValue) {
        setAbsHeight(newValue);
    }
    @Override
    public void resizeWidth(double newValue) {
        setAbsWidth(newValue);
    }

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
                ioPane.submit("pr");
            } else {
                ioPane.submit(currentLine.substring(0, currentLine.length() - 1));
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
        setMaxWidth(width);
    }
}
