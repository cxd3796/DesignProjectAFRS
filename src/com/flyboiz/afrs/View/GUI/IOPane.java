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

    private IOPaneState ioPaneState;

    // CONSTRUCTOR //
    IOPane(int tabID, ViewManager vm, double width, double height) {

        // Initialize.
        super();
        getChildren().clear();
        this.tabID = tabID;
        this.clientID = -1;

        // Set the current pane state.
        this.ioPaneState = new IOPaneDisconnectedState(this);

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
    private void changeState(IOPaneState state) {
        System.out.println(state.getClass().toString());
        this.ioPaneState = state;
    }
    private int getClientID() {
        return clientID;
    }
    private void setClientID(int id) {
        clientID = id;
    }

    // BEHAVIOUR (INTERFACE) //
    @Override
    public void update(String updateText) {
        ioPaneState.update(updateText);
    }
    @Override
    public void submit(String queryText) {
        ioPaneState.submit(queryText);
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
        }

        @Override
        public void update(String updateText) {
            // do nothing
        }

        @Override
        public void submit(String queryText) {
            System.out.println("Attempting to submit: " + queryText);
            if (queryText.contains(Main.PARTIAL_REQUEST_STRING) || queryText.contains(Main.CONNECT_REQUEST_STRING)) {
                String noSemicolon = queryText.replace(";","");
                pane.viewManager.submit(noSemicolon);
                pane.ioPaneState = new IOPaneAwaitingConnectionState(pane);
            } else {
                pane.outputBox.update(MSG_DISCONNECTED);
            }
        }
    }
    private static class IOPaneAwaitingConnectionState extends IOPaneState {

        IOPaneAwaitingConnectionState(IOPane pane) {
            super(pane);
        }

        @Override
        public void update(String updateText) {
            if (updateText.contains(Main.CONNECTED_STRING)) {
                String clientIDOnly = updateText.replace(Main.CONNECTED_STRING, "");
                pane.setClientID(Integer.parseInt(clientIDOnly));
                pane.outputBox.update(updateText);
                pane.changeState(new IOPaneConnectedState(pane));
            } else {
                pane.outputBox.update(MSG_CONNECTION_FAILED);
                pane.changeState(new IOPaneDisconnectedState(pane));
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
            String noSemicolon = queryText.replace(";","");
            String updatedText = pane.getClientID() + "," + noSemicolon;
            pane.viewManager.submit(updatedText);
            pane.changeState(new IOPaneAwaitingResponseState(pane));
        }
    }
    private static class IOPaneAwaitingResponseState extends IOPaneState {

        public IOPaneAwaitingResponseState(IOPane pane) {
            super(pane);
        }

        @Override
        public void update(String updateText) {
            if (updateText.equals(QueryPartialRequest.PARTIAL_REQUEST_STRING)) {
                pane.inputBox.retreatCharacter();
            }
            pane.outputBox.update(updateText);
            pane.changeState(new IOPaneConnectedState(pane));
        }

        @Override
        public void submit(String queryText) {
            // do nothing
        }
    }
}
