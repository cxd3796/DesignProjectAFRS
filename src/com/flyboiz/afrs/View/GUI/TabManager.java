/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */

import com.sun.org.apache.bcel.internal.generic.NEW;
import javafx.scene.control.Control;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.LinkedList;
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
    public TabManager(ViewManager vm, Font font, double width, double height) {

        // Initialize.
        super();
        getChildren().clear();
        allTabs = new LinkedList<>();
        viewManager = vm;

        // Set up the font.
        this.normalFont = font;
        this.highlightedFont = Font.font(font.getFamily(), FontWeight.EXTRA_BOLD, font.getSize());

        // Initialize New Tab Button.
        newButton = new NewTabButton(vm, width / 20, height);
        getChildren().add(newButton);

        // Set up the visuals of the TabManager.
        setAbsHeight(height);
        setAbsWidth(width);
    }

    // GETTERS & SETTERS //

    // BEHAVIOUR //
    Tab newTab() {

        // Create a new tab and set its properties.
        Tab newTab = new Tab(viewManager, allTabs.size(), getPrefWidth() / 25, getPrefHeight());

        // Add a new tab to the tab list.
        allTabs.add(newTab);

        // Add the tab to the children, and replace the NewTabButton to the end of the HBox.
        getChildren().remove(newButton);
        getChildren().add(newTab);
        getChildren().add(newButton);

        // Return the newly created.
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
