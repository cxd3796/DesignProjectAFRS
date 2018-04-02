/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */

import com.flyboiz.afrs.Main;
import com.flyboiz.afrs.View.Input;
import com.flyboiz.afrs.View.Output;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

/* implementation */
public class IOPane extends AnchorPane implements Output, Input, Resizeable {

    // CONSTANTS //
    private static final String MSG_CONNECTION_FAILED = "Connection failed. Please try again.";
    private static final String MSG_DISCONNECTED = "You are currently disconnected. Please connect before making queries.";

    // STATE //
    private ViewManager viewManager;
    private InputBox inputBox;
    private OutputBox outputBox;
    private CloseTabButton closeTabButton;

    private int clientID;
    private int tabID;

    private Font font;

    private IOPaneState currentState;
    private IOPaneState nextState;

    // CONSTRUCTOR //
    IOPane(int tabID, ViewManager vm, Font font, double width, double height) {

        // Initialize.
        super();
        getChildren().clear();
        this.tabID = tabID;
        this.clientID = -1;

        // Set the font.
        this.font = font;

        // Set the current pane state.
        this.currentState = new IOPaneDisconnectedState();
        this.nextState = null;

        // Set the size of the IOPane.
        setAbsHeight(height);
        setAbsWidth(width);

        // Set up ViewManager state.
        this.viewManager = vm;
        this.inputBox = new InputBox(this, font, getPrefWidth(), getPrefHeight() / 2);
        this.outputBox = new OutputBox(font, getPrefWidth(), getPrefHeight() / 2);
        this.closeTabButton = new CloseTabButton(viewManager, this.tabID, font, getPrefWidth() / 20, getPrefHeight() / 20);

        // Insert boxes.
        getChildren().add(inputBox);
        getChildren().add(outputBox);
        getChildren().add(closeTabButton);
        resizeHeight(getHeight());
        resizeWidth(getWidth());
    }

    // BEHAVIOUR //
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
    private void setNextState(IOPaneState nextState) {
        this.nextState = nextState;
    }
    void changeState() {
        if (nextState != null) {
            currentState = nextState;
        }
        nextState = null;
    }
    void forceDisconnect() {
        submit("disconnect");
    }
    private void connect(String connectedResponse) {
        String idString = connectedResponse.replace("connect, ", "");
        int newID = Integer.parseInt(idString);
        if (clientID == -1) {
            clientID = newID;
        }
    }
    private void disconnect() {
        clientID = -1;
    }
    private String prependClientID(String text) {
        return clientID + "," + text;
    }
    private String removePrependedClientID(String text) {
        int firstUsefulIndex = 0;
        while (text.charAt(firstUsefulIndex) != ',' && firstUsefulIndex != (text.length() - 1)) {
            firstUsefulIndex++;
        }
        if (firstUsefulIndex == text.length() - 1) {
            return text;
        }
        return text.substring(firstUsefulIndex + 1);
    }
    private void submitText(String text) {
        changeState();
        viewManager.submit(text);
    }
    private void updateText(String text) {
        changeState();
        outputBox.update(text);
    }

    // BEHAVIOUR (OVERRIDE) //
    @Override
    public void update(String updateText) {
        currentState.update(updateText);
    }
    @Override
    public void submit(String queryText) {
        currentState.submit(queryText);
    }
    @Override
    public void resizeHeight(double newValue) {
        inputBox.resizeHeight(newValue / 2.0);
        inputBox.setLayoutY(0.0);
        outputBox.resizeHeight(newValue / 2.0);
        outputBox.setLayoutY(newValue / 2.0);
        closeTabButton.resizeHeight(newValue / 20.0);
        closeTabButton.setLayoutY(0.0);
        closeTabButton.toFront();
    }
    @Override
    public void resizeWidth(double newValue) {
        inputBox.resizeWidth(newValue);
        outputBox.resizeWidth(newValue);
        closeTabButton.resizeWidth(closeTabButton.getHeight());
        closeTabButton.setLayoutX(inputBox.getWidth() - (closeTabButton.getHeight()));
    }
    @Override
    public String toString() {
        return "{IOPane: cid=" + clientID + ", tabID=" + tabID + "}";
    }

    // INNER STATE CLASSES //
    private abstract class IOPaneState {
        abstract void update(String updateText);
        abstract void submit(String queryText);
    }
    private class IOPaneDisconnectedState extends IOPaneState {

        IOPaneDisconnectedState() {
            System.out.println("New Disconnected created for " + IOPane.this.toString());
        }

        @Override
        public void update(String updateText) {
            // do nothing
            //System.out.println("This should not happen!");
        }

        @Override
        public void submit(String queryText) {
            // do something
            if (queryText.equals(Main.CONNECT_REQUEST_STRING) || queryText.equals(Main.PARTIAL_REQUEST_STRING)) {
                setNextState(new IOPaneAwaitingConnectionState());
                submitText(queryText);
            } else {
                // please deprecate
                updateText(MSG_CONNECTION_FAILED);
            }
        }
    }
    private class IOPaneAwaitingConnectionState extends IOPaneState {

        IOPaneAwaitingConnectionState() {
            System.out.println("New AwaitingConnection created for " + IOPane.this.toString());
        }

        @Override
        public void update(String updateText) {
            // do something
            if (updateText.contains(Main.CONNECT_REQUEST_STRING + ",")) {
                setNextState(new IOPaneConnectedState());
                updateText(updateText);
                connect(updateText);
            } else {
                setNextState(new IOPaneDisconnectedState());
                updateText(updateText + " -> " + MSG_CONNECTION_FAILED);
            }
        }

        @Override
        public void submit(String queryText) {
            // do nothing
        }
    }
    private class IOPaneConnectedState extends IOPaneState {

        IOPaneConnectedState() {
            System.out.println("New Connected created for " + IOPane.this.toString());
        }

        @Override
        public void update(String updateText) {
            // do nothing
        }

        @Override
        public void submit(String queryText) {
            // do something
            if (queryText.contains("disconnect")) {
                setNextState(new IOPaneAwaitingDisconnectState());
            } else {
                setNextState(new IOPaneAwaitingResponseState());
            }
            submitText(prependClientID(queryText));
        }
    }
    private class IOPaneAwaitingResponseState extends IOPaneState {

        IOPaneAwaitingResponseState() {
            System.out.println("New AwaitingResponse created for " + IOPane.this.toString());
        }

        @Override
        public void update(String updateText) {
            // do something
            setNextState(new IOPaneConnectedState());
            updateText = removePrependedClientID(updateText);
            updateText(updateText);
        }

        @Override
        public void submit(String queryText) {
            // do nothing
        }
    }
    private class IOPaneAwaitingDisconnectState extends IOPaneState {

        IOPaneAwaitingDisconnectState() {
            System.out.println("New AwaitingDisconnect created for " + IOPane.this.toString());
        }

        @Override
        public void update(String updateText) {
            // do something
            setNextState(new IOPaneDisconnectedState());
            updateText = removePrependedClientID(updateText);
            updateText(updateText);
            disconnect();
        }

        @Override
        public void submit(String queryText) {
            // do nothing
        }
    }
}
