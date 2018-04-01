/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */

import com.flyboiz.afrs.Controller.QueryPartialRequest;
import com.flyboiz.afrs.Main;
import com.flyboiz.afrs.View.Input;
import com.flyboiz.afrs.View.Output;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/* implementation */
public class IOPane extends BorderPane implements Output, Input {

    // CONSTANTS //
    private static final String MSG_CONNECTION_FAILED = "Connection failed. Please try again.";
    private static final String MSG_DISCONNECTED = "You are currently disconnected. Please connect before making queries.";

    // STATE //
    private ViewManager viewManager;
    private InputBox inputBox;
    private OutputBox outputBox;

    private int clientID;
    private int tabID;

    private IOPaneState currentState;
    private IOPaneState nextState;

    // CONSTRUCTOR //
    IOPane(int tabID, ViewManager vm, double width, double height) {

        // Initialize.
        super();
        getChildren().clear();
        this.tabID = tabID;
        this.clientID = -1;

        // Set the current pane state.
        this.currentState = new IOPaneDisconnectedState(this);
        this.nextState = null;

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
        //setMaxHeight(height);
    }
    private void setAbsWidth(double width) {
        setMinWidth(width);
        setPrefWidth(width);
        //setMaxWidth(width);
    }
    private void setNextState(IOPaneState nextState) {
        this.nextState = nextState;
    }
    public void changeState() {
        if (nextState != null) {
            currentState = nextState;
            printState();
        }
        nextState = null;
    }
    private int getClientID() {
        return clientID;
    }
    private void setClientID(int id) {
        clientID = id;
    }
    private void submitText(String text) {
        changeState();
        viewManager.submit(text);
    }
    private void updateText(String text) {
        changeState();
        outputBox.update(text);
    }
    public void printState() {
        System.out.println(currentState.getClass().toString());
    }

    // BEHAVIOUR (INTERFACE) //
    @Override
    public void update(String updateText) {
        currentState.update(updateText);
    }
    @Override
    public void submit(String queryText) {
        currentState.submit(queryText);
    }

    // INNER STATE CLASSES //
    private static abstract class IOPaneState {
        IOPane pane = null;

        public IOPaneState(IOPane pane) {
            this.pane = pane;
        }

        abstract void update(String updateText);
        abstract void submit(String queryText);
    }
    private static class IOPaneDisconnectedState extends IOPaneState {

        public IOPaneDisconnectedState(IOPane pane) {
            super(pane);
            System.out.println("New Disconnected created.");
        }

        @Override
        public void update(String updateText) {
            // do nothing
            //System.out.println("This should not happen!");
        }

        @Override
        public void submit(String queryText) {
            // do something
            if (queryText.contains(Main.CONNECT_REQUEST_STRING) || queryText.contains(Main.PARTIAL_REQUEST_STRING)) {
                pane.setNextState(new IOPaneAwaitingConnectionState(pane));
                pane.submitText(queryText);
            } else {
                // please deprecate
                pane.updateText(MSG_CONNECTION_FAILED);
            }
        }
    }
    private static class IOPaneAwaitingConnectionState extends IOPaneState {

        IOPaneAwaitingConnectionState(IOPane pane) {
            super(pane);
            System.out.println("New AwaitingConnection created.");
        }

        @Override
        public void update(String updateText) {
            // do something
            if (updateText.contains(Main.CONNECTED_STRING)) {
                pane.setNextState(new IOPaneConnectedState(pane));
                pane.updateText(updateText);
            } else {
                pane.setNextState(new IOPaneDisconnectedState(pane));
                pane.updateText(MSG_CONNECTION_FAILED);
            }
        }

        @Override
        public void submit(String queryText) {
            // do nothing
        }
    }
    private static class IOPaneConnectedState extends IOPaneState {

        public IOPaneConnectedState(IOPane pane) {
            super(pane);
        }

        @Override
        public void update(String updateText) {
            // do nothing
        }

        @Override
        public void submit(String queryText) {
            // do something
        }
    }
    private static class IOPaneAwaitingResponseState extends IOPaneState {

        public IOPaneAwaitingResponseState(IOPane pane) {
            super(pane);
        }

        @Override
        public void update(String updateText) {
            // do something
        }

        @Override
        public void submit(String queryText) {
            // do nothing
        }
    }
}
