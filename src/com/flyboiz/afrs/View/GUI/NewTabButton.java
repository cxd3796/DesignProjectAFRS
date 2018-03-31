/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */
import javafx.scene.control.Button;

/* implementation */
public class NewTabButton extends Button {

    // STATE //
    private ViewManager viewManager;

    // CONSTRUCTOR //
    public NewTabButton(ViewManager vm) {
        this.viewManager = vm;
        setOnMouseClicked(e -> {
            vm.newWindow();
        });
    }

    // GETTERS & SETTERS //

    // BEHAVIOUR //

}
