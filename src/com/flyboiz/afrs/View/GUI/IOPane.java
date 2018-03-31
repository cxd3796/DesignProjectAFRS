/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */

import com.flyboiz.afrs.View.Input;
import com.flyboiz.afrs.View.Output;
import javafx.scene.layout.Pane;

/* implementation */
public class IOPane extends Pane implements Output, Input {

    // STATE //
    private ViewManager viewManager;

    private int clientID;
    private int tabID;
    private InputBox inputBox;
    private OutputBox outputBox;

    // CONSTRUCTOR //
    IOPane(ViewManager vm) {
        this.viewManager = vm;
    }

    // GETTERS & SETTERS //

    // BEHAVIOUR //
    @Override
    public void update(String updateText) {

    }

    @Override
    public void submit(String queryText) {
        String updatedText = clientID + "," + queryText;
        viewManager.submit(updatedText);
    }
}
