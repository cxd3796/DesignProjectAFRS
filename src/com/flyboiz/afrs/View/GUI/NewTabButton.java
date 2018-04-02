/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */

import javafx.scene.control.Button;

/* implementation */
public class NewTabButton extends Button {

	// CONSTANTS //
	private static final String BUTTON_TEXT = "+";

	// STATE //
	private ViewManager viewManager;

	// CONSTRUCTOR //
	public NewTabButton(ViewManager vm, double width, double height) {
		this.viewManager = vm;
		this.setText(BUTTON_TEXT);
		setAbsWidth(width);
		setAbsHeight(height);
		setOnMouseClicked(e -> {
			makeNewTab();
		});
	}

	// GETTERS & SETTERS //
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

	// BEHAVIOUR //

	/**
	 * This method is called when the button is pressed.
	 */
	private void makeNewTab() {
		viewManager.newWindow();
	}

	public void resizeHeight(double newValue) {
		setAbsHeight(newValue);
	}

	public void resizeWidth(double newValue) {
		setAbsWidth(newValue);
	}

}
