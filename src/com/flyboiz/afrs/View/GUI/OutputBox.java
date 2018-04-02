/* created by Kent Brown on 3/31/2018 */

/* package */
package com.flyboiz.afrs.View.GUI;

/* imports */

import com.flyboiz.afrs.View.Output;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

/* implementation */
public class OutputBox extends TextArea implements Output {

	// CONSTANTS //
	private static final String NEWLINE = "\r\n";

	// STATE //
	private Font font;

	// CONSTRUCTOR //

	/**
	 * Generate an OutputBox
	 *
	 * @param font   the font
	 * @param width  the width of the outputBox
	 * @param height the height of the outputBox
	 */
	OutputBox(Font font, double width, double height) {

		// Change the properties of the box.
		this.font = font;
		setFont(font);

		// Set up the size of the box.
		setAbsWidth(width);
		setAbsHeight(height);
		setEditable(false);
	}

	// BEHAVIOUR //
	@Override
	public void update(String updateText) {
		appendText(updateText);
		appendText(NEWLINE);
	}

	// PRIVATE BEHAVIOUR //
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

	public void resizeHeight(double newValue) {
		setAbsHeight(newValue);
	}

	public void resizeWidth(double newValue) {
		setAbsWidth(newValue);
	}
}
