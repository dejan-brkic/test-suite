/*
 * Copyright (C) 2007-2018 Crafter Software Corporation. All Rights Reserved.
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
package org.craftercms.studio.test.cases.contextualnavigationtestcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * @author luishernandez
 *
 */

public class VerifyThatApplicationDisplaysTheSearchPageWhenSearchOptionIsClickedTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownXpath;
	private String homeXpath;
	private String siteDropdownListElementXPath;
	private String searchBoxInput;
	private String searchPageTitleXpath;

	@BeforeMethod
	public void beforeTest() {

		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitedropdown");
		homeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.home");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
		searchBoxInput=uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.searchpage.searchbox");
		searchPageTitleXpath=uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.searchpage.title");
	}

	@Test(priority = 0)
	public void verifyThatApplicationDisplaysTheSearchPageWhenSearchOptionIsClickedTest() {

		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to closes
		driverManager.waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage();

		// Show site content panel
		if (!(this.driverManager.waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
				.getAttribute("class").contains("site-dropdown-open")))
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
				siteDropdownXpath).click();

		// expand pages folder
		previewPage.expandPagesTree();
		
		driverManager.getDriver().navigate().refresh();
		
		// expand home content
		previewPage.expandHomeTree();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", homeXpath).click();
		
		// click on history option
		previewPage.clickOnSearchOption();

		// Assertions	
		Assert.assertTrue(this.driverManager.getDriver().getCurrentUrl().contains("/studio/search?"));
		
		String searchPageTitle = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				searchPageTitleXpath).getText();
		
		Assert.assertTrue("Search".equalsIgnoreCase(searchPageTitle));
		Assert.assertTrue(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", searchBoxInput).isDisplayed());
		
	}

}
