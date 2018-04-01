/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */

import com.flyboiz.afrs.Controller.QueryExecutor;
import com.flyboiz.afrs.View.Input;
import com.flyboiz.afrs.View.Output;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import java.util.LinkedList;
import java.util.List;

/* implementation */
public class ViewManager extends BorderPane implements Output, Input {

    // CONSTANTS //
    private static final double PREFERRED_WIDTH = 800.0;
    private static final double PREFERRED_HEIGHT = 600.0;
    private static final double TAB_MANAGER_HEIGHT = PREFERRED_HEIGHT / 16;
    private static final double PANE_HEIGHT = PREFERRED_HEIGHT - TAB_MANAGER_HEIGHT;

    // STATE //
    private QueryExecutor queryExecutor;
    private TabManager tabManager;
    private List<TabPanePair> pairs;
    private TabPanePair current;

    private Font font;

    // CONSTRUCTOR //
    public ViewManager(QueryExecutor qe, Font font) {

        // Perform basic node initialization. //
        super();
        getChildren().clear();

        // Set the queryExecutor.
        this.queryExecutor = qe;

        // Set the font.
        this.font = font;

        // Local initialization (for this class). //
        this.pairs = new LinkedList<>();
        this.tabManager = new TabManager(this, font, PREFERRED_WIDTH, PREFERRED_HEIGHT / 16);

        // Set the preferred size of the pane.
        setPrefWidth(PREFERRED_WIDTH);
        setPrefHeight(PREFERRED_HEIGHT);

        // Format the pane.
        setTop(tabManager);
        setCenter(null);
    }

    // GETTERS & SETTERS //

    // BEHAVIOUR //
    void newWindow() {

        // Create a new tab, pane, and pair
        Tab newTab = tabManager.newTab();
        IOPane newPane = new IOPane(newTab.getTabID(),this, PREFERRED_WIDTH, PANE_HEIGHT);
        TabPanePair tpp = new TabPanePair(newTab, newPane);

        // Add the pair
        pairs.add(tpp);

        // Switch to the new tab
        changeTab(newTab);

    }
    void changeTab(Tab tab) {
        try {
            TabPanePair tpp = getPair(tab);
            if (tpp != null) {
                if (this.current != null) {
                    getChildren().remove(current.getPane());
                }
                tabManager.highlightTab(tpp.getID());
                setCenter(tpp.getPane());
                setCurrentPair(tpp);
            }
        } catch (Exception e) {
            System.out.println("ALERT! An error occurred in the GUI!");
            System.out.println(" -> " + e.getMessage());
        }
    }
    private TabPanePair getPair(Tab tab) throws Exception {
        TabPanePair tpp = null;
        for (TabPanePair eachTabPair : pairs) {
            tpp = eachTabPair;
            if (tpp.getTab().equals(tab))
                break;
        }
        return tpp;
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
    private void setCurrentPair(TabPanePair tpp) {
        this.current = tpp;
    }

    // INTERFACE IMPLEMENTS //
    @Override
    public void update(String updateText) {
        current.update(updateText);
    }
    @Override
    public void submit(String queryText) {
        queryExecutor.makeQuery(queryText);
    }
}
