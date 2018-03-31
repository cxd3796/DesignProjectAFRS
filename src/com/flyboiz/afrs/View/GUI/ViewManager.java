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

    // STATE //
    private QueryExecutor queryExecutor;

    private TabManager tabManager;
    private List<TabPanePair> pairs;
    private Tab currentTab;
    private IOPane activeWindow;

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
        this.tabManager = new TabManager(this, this.font);
        this.currentTab = null;
        this.activeWindow = null;

        // Add a NewWindowButton.
        setTop(tabManager);
    }

    // GETTERS & SETTERS //

    // BEHAVIOUR //
    void newWindow() {
        Tab newTab = tabManager.newTab();
        IOPane newPane = new IOPane(this);
        TabPanePair tpp = new TabPanePair(newTab, newPane);
        pairs.add(tpp);
        changeTab(newTab);
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

    @Override
    public void submit(String queryText) {
        queryExecutor.makeQuery(queryText);
    }

}
