/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */

import com.flyboiz.afrs.View.Output;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* implementation */
public class ViewManager extends BorderPane implements Output {

    // STATE //
    private TabManager tabManager;
    private List<TabPanePair> pairs;
    private Tab currentTab;
    private IOPane activeWindow;

    private Font font;

    // CONSTRUCTOR //
    public ViewManager(Font font) {

        // Perform basic node initialization. //
        super();
        getChildren().clear();

        // Set the font.
        this.font = font;

        // Local initialization (for this class). //
        this.pairs = new LinkedList<>();
        this.tabManager = new TabManager(this, this.font);
        this.currentTab = null;
        this.activeWindow = null;

        // Add a NewWindowButton.

    }

    // GETTERS & SETTERS //

    // BEHAVIOUR //
    void newWindow() {
        Tab newTab = tabManager.newTab();
        IOPane newPane = new IOPane();
    }

    void changeTab(Tab newTab) {
        try {
            TabPanePair tpp = getPair(newTab);
            tabManager.highlightTab(tpp.getID());
        } catch (Exception e) {
            System.out.println("ALERT! An error occurred in the GUI!");
            System.out.println(" -> " + e.getMessage());
        }
    }

    private TabPanePair getPair(Tab newTab) throws Exception {
        int i = 0;
        TabPanePair tpp;
        do {
            tpp = pairs.get(i++);
        } while ((tpp.getID() != newTab.getTabID()) && (i < pairs.size()));
        if (tpp.getID() != newTab.getTabID()) {
            throw new Exception("The application was unable to find the related GUI pair.");
        }
        return tpp;
    }

    @Override
    public void update(String updateText) {

    }

}
