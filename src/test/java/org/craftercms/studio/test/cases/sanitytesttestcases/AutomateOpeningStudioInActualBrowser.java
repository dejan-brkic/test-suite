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
package org.craftercms.studio.test.cases.sanitytesttestcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * @author Juan Camacho
 */

// Test to cover ticket https://github.com/craftercms/craftercms/issues/1434
public class AutomateOpeningStudioInActualBrowser extends StudioBaseTest {

	private String crafterLoginImage;
	private String userNameXpath;
	private String passwordXpath;
	private String loginXpath;
	private String loginLanguageSelector;

	@BeforeMethod
	public void beforeTest() {

		crafterLoginImage = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("login.crafterloginimage");
		userNameXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("login.username");
		passwordXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("login.password");
		loginXpath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("login.login");
		loginLanguageSelector = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("login.languageselector"); 
	}

	@Test(priority = 0)
	public void automateOpeningStudioInActualBrowser() {

		// Verfy Login page is displayed when URL 'localhost:8080/studio' is load in a browser
		
		//Verify Crafter Logo Image is displayed
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", crafterLoginImage);
		Assert.assertTrue(getWebDriverManager().isElementPresentByXpath(crafterLoginImage),
				"ERROR:  Crafter Logo image is not displayed, check localhost:8080/studio open the login page");

		//Verify Fields are displayed
		Assert.assertTrue(getWebDriverManager().isElementPresentByXpath(userNameXpath),
				"ERROR: User name field is not displayed");
				
		Assert.assertTrue(getWebDriverManager().isElementPresentByXpath(passwordXpath),
				"ERROR: Password field is not displayed");
				
		Assert.assertTrue(getWebDriverManager().isElementPresentByXpath(loginXpath),
				"ERROR: Login submit button is not displayed");
		
		Assert.assertTrue(getWebDriverManager().isElementPresentByXpath(loginLanguageSelector),
				"ERROR: Login language Selector is not displayed");

	}

}