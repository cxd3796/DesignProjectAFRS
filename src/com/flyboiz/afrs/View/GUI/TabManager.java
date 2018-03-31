/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */

import com.sun.org.apache.bcel.internal.generic.NEW;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

/* implementation */
public class TabManager extends HBox {

    // STATE //
    private ViewManager viewManager;

    private NewTabButton newButton;
    private List<Tab> allTabs;

    private Font normalFont;
    private Font highlightedFont;

    // CONSTRUCTOR //
    public TabManager(ViewManager vm, Font font) {

        // Initialize.
        super();
        getChildren().clear();

        // Set up the font.
        this.normalFont = font;
        this.highlightedFont = Font.font(font.getFamily(), FontWeight.BOLD, font.getSize());

        // Initialize.
        newButton = new NewTabButton(vm);
    }

    // GETTERS & SETTERS //

    // BEHAVIOUR //
    Tab newTab() {
        Tab newTab = new Tab(allTabs.size());
        allTabs.add(newTab);
        getChildren().add(newTab);
        return newTab;
    }

    void highlightTab(int tabID) {
        for (Tab tb : allTabs) {
            if (tb.getTabID() == tabID) {
                tb.setFont(highlightedFont);
            } else {
                tb.setFont(normalFont);
            }
        }
    }
}
