/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */

import com.flyboiz.afrs.Controller.QueryPartialRequest;
import com.flyboiz.afrs.View.Input;
import com.flyboiz.afrs.View.Output;
import javafx.scene.layout.BorderPane;

/* implementation */
public class IOPane extends BorderPane implements Output, Input {
    
    // STATE //
    private ViewManager viewManager;

    private int clientID;
    private int tabID;
    private InputBox inputBox;
    private OutputBox outputBox;

    // CONSTRUCTOR //
    IOPane(int tabID, ViewManager vm, double width, double height) {

        // Initialize.
        super();
        getChildren().clear();
        this.tabID = tabID;

        // Set the size of the IOPane.
        setAbsHeight(height);
        setAbsWidth(width);

        // Set up ViewManager state.
        this.viewManager = vm;
        this.inputBox = new InputBox(this, getPrefWidth(), getPrefHeight() / 2);
        this.outputBox = new OutputBox(getPrefWidth(), getPrefHeight() / 2);

        // Insert boxes.
        setTop(inputBox);
        setBottom(outputBox);

    }

    // BEHAVIOUR //
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

    // BEHAVIOUR (INTERFACE) //
    @Override
    public void update(String updateText) {
        if (updateText.equals(QueryPartialRequest.PARTIAL_REQUEST_STRING)) {
            inputBox.retreatCharacter();
        }
        outputBox.update(updateText);
    }
    @Override
    public void submit(String queryText) {

        String noSemicolon = queryText.replace(";","");
        String updatedText = /*clientID; + "," +*/ noSemicolon;
        viewManager.submit(updatedText);
    }

    // INNER STATE CLASSES //
    private interface IOPaneState {
        void update(String updateText);
        void submit(String queryText);
    }
}
