/*
 * Copyright (C) 2007-2019 Crafter Software Corporation. All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.craftercms.studio.test.pages;

import org.craftercms.studio.test.utils.UIElementsPropertiesManager;
import org.craftercms.studio.test.utils.WebDriverManager;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro 
 *
 */

public class MyRecentActivityPage {

	private WebDriverManager driverManager;
    private String expandDefaultSection;
    private String tittleField1;
   
    /**
     * 
     */
    public MyRecentActivityPage(WebDriverManager driverManager, UIElementsPropertiesManager UIElementsPropertiesManager) {
        this.driverManager = driverManager;
        this.driverManager.getDriver();

        expandDefaultSection = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("frame1.expand_Default_Section");
        tittleField1 = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("frame1.tittle_Field1");
    }
	// Expand default section

	public void clickExpandOption() {
		WebElement expandOpt = this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable( "xpath", expandDefaultSection);
		expandOpt.click();
	}

	public void expandDefaultSection() {
		// Expand default section
		this.clickExpandOption();
	}

	// Clear title field
	public void clearTitleField() {
		WebElement clearTitleField = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath", tittleField1);
		clearTitleField.clear();
	}

	public void cleaningTitleField() {
		// Clear title field
		this.clearTitleField();
	}

	// Type new content on title text field.
	public void typeNewTextOnBodyField(String newText1) {
		WebElement clearTitleField = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath", tittleField1);
		clearTitleField.sendKeys(newText1);
	}

	// Type new content on title text field.
	public void typingNewTextOnBodyField(String newText1) {
		// Typing
		this.typeNewTextOnBodyField(newText1);
	}

}