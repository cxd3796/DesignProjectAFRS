/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */
import com.flyboiz.afrs.View.Input;
import javafx.scene.control.TextField;

/* implementation */
public class InputBox extends TextField implements Input {

    // CONSTANTS //
    private static final String SUBMIT_CHARACTER = "\n";

    // STATE //
    private IOPane ioPane;
    private String currentLine;

    // CONSTRUCTOR //
    public InputBox(IOPane pane) {
        this.ioPane = pane;
        currentLine = "";
        setOnKeyTyped(e -> {
            keyTyped(e.getCharacter());
        });
    }

    // GETTERS & SETTERS //

    // BEHAVIOUR //
    @Override
    public void submit(String queryText) {
        ioPane.submit(queryText);
    }

    private void keyTyped(String character) {
        System.out.println("Key typed: " + character);
        if (character.equals(SUBMIT_CHARACTER)) {
            ioPane.submit(currentLine);
            clearCurrentLine();
        } else {
            setText(getText() + character);
            currentLine = currentLine + character;
        }
    }

    private void clearCurrentLine() {
        currentLine = "";
    }

}
