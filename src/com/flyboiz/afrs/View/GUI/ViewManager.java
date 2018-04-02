/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */

import com.flyboiz.afrs.Controller.QueryExecutor;
import com.flyboiz.afrs.View.Input;
import com.flyboiz.afrs.View.Output;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.util.LinkedList;
import java.util.List;

/* implementation */
public class ViewManager extends Pane implements Output, Input {

	// CONSTANTS //
	private static final double MANAGER_FRACTION = 8.0;

	// STATE //
	private final double startWidth;
	private final double startHeight;

	private QueryExecutor queryExecutor;
	private TabManager tabManager;
	private List<TabPanePair> pairs;
	private TabPanePair current;

	private Font font;

	// CONSTRUCTOR //
	public ViewManager(QueryExecutor qe, Font font, double startWidth, double startHeight) {

		// Perform basic node initialization. //
		super();
		getChildren().clear();

		// Set the queryExecutor.
		this.queryExecutor = qe;

		// Set the font.
		this.font = font;

		// Local initialization (for this class). //
		this.pairs = new LinkedList<>();
		this.tabManager = new TabManager(this, font, startWidth, startHeight / MANAGER_FRACTION);

		// Set the preferred size of the pane.
		setAbsWidth(startWidth);
		setAbsHeight(startHeight);
		this.startHeight = startHeight;
		this.startWidth = startWidth;

		// Format the pane.
		getChildren().add(tabManager);
		tabManager.setLayoutX(0.0);
		tabManager.setLayoutY(0.0);

		// Set size-changed event handler.
		this.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				resizeHeight(newValue.doubleValue());
			}
		});
		this.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				resizeWidth(newValue.doubleValue());
			}
		});
	}

	// BEHAVIOUR (PACKAGE-PRIVATE) //

	/**
	 * Code to generate a new window. Called by the NewTabButton.
	 */
	void newWindow() {

		// Create a new tab, pane, and pair
		Tab newTab = tabManager.newTab();
		IOPane newPane = new IOPane(newTab.getTabID(), this, font, getPrefWidth(), getPaneHeight());
		TabPanePair tpp = new TabPanePair(newTab, newPane);

		// Add the pair
		pairs.add(tpp);

		// Switch to the new tab
		changeTab(newTab);

	}

	/**
	 * Change the active tab to the specified tab.
	 *
	 * @param tab The specified tab.
	 */
	void changeTab(Tab tab) {
		try {
			TabPanePair tpp = getPair(tab);
			if (tpp != null) {
				if (this.current != null) {
					getChildren().remove(current.getPane());
				}
				Pane nextPane = tpp.getPane();
				tabManager.highlightTab(tpp.getID());
				getChildren().add(nextPane);
				nextPane.setLayoutX(0.0);
				nextPane.setLayoutY(getHeight() - getPaneHeight());
				resizeHeight(getHeight());
				resizeWidth(getWidth());
				setCurrentPair(tpp);
			}
		} catch (Exception e) {
			System.out.println("ALERT! An error occurred in the GUI!");
			System.out.println(" -> " + e.getMessage());
		}
	}

	/**
	 * Close a given tab.
	 *
	 * @param tabID The ID of the Tab to close.
	 */
	void closeTab(int tabID) {
		TabPanePair removePair = getPair(tabID);
		IOPane removePane = removePair.getPane();
		pairs.remove(removePair);
		getChildren().remove(removePane);
		removePane.forceDisconnect();
		tabManager.removeTab(tabID);
		resizeWidth(getWidth());
		resizeHeight(getHeight());
	}

	// BEHAVIOUR (PRIVATE) //

	/**
	 * Get a the TabPanePair associated with a given tab.
	 *
	 * @param tab the given tab.
	 * @return the associated tabPanePair.
	 */
	private TabPanePair getPair(Tab tab) {
		TabPanePair tpp = null;
		for (TabPanePair eachTabPair : pairs) {
			tpp = eachTabPair;
			if (tpp.getTab().equals(tab))
				break;
		}
		return tpp;
	}

	private TabPanePair getPair(int tabID) {
		TabPanePair tpp = null;
		for (TabPanePair eachTabPair : pairs) {
			tpp = eachTabPair;
			if (tpp.getID() == tabID)
				break;
		}
		return tpp;
	}

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

	private void setCurrentPair(TabPanePair tpp) {
		this.current = tpp;
	}

	private double getPaneHeight() {
		return getHeight() - (getHeight() / MANAGER_FRACTION);
	}

	private void resizePane(IOPane p) {
		p.setLayoutY(getHeight() - getPaneHeight());
		p.resizeHeight(getPaneHeight());
	}

	// INTERFACE IMPLEMENTS //
	@Override
	public void update(String updateText) {
		System.out.println("ViewManager received updateText: " + updateText);
		for (TabPanePair tpp : pairs) {
			tpp.update(updateText);
		}
	}

	@Override
	public void submit(String queryText) {
		queryExecutor.makeQuery(queryText);
	}

	/**
	 * Resize this object's height
	 *
	 * @param newValue desired height
	 */
	public void resizeHeight(double newValue) {
		double managerSize = newValue / MANAGER_FRACTION;
		setAbsHeight(newValue);
		tabManager.resizeHeight(managerSize);
		for (TabPanePair tpp : pairs) {
			resizePane(tpp.getPane());
		}
	}

	/**
	 * Resize this object's width
	 *
	 * @param newValue the desired width
	 */
	public void resizeWidth(double newValue) {
		if (newValue < (startWidth / 4)) {
			newValue = startWidth / 4;
		}
		setAbsWidth(newValue);
		tabManager.resizeWidth(newValue);
		for (TabPanePair tpp : pairs) {
			tpp.getPane().resizeWidth(newValue);
		}
	}
}
