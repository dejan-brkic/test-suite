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
package org.craftercms.studio.test.cases.contextualnavigationtestcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
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
	private String searchResultsCss;

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
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
		searchResultsCss= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.searchpage.results");
	}

	@Parameters({"testId"})
	@Test()
	public void verifyThatApplicationDisplaysTheSearchPageWhenSearchOptionIsClickedTest(String testId) {

		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to closes
		getWebDriverManager().waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage(testId);

		// Show site content panel
		if (!(this.getWebDriverManager().waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
				.getAttribute("class").contains("site-dropdown-open")))
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed( "xpath",
				siteDropdownXpath).click();

		// expand pages folder
		previewPage.expandPagesTree();
		
		getWebDriverManager().getDriver().navigate().refresh();
		
		// expand home content
		previewPage.expandHomeTree();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", homeXpath).click();
		
		// click on history option
		previewPage.clickOnSearchOption();

		// Assertions	
		Assert.assertTrue(this.getWebDriverManager().getDriver().getCurrentUrl().contains("/studio/search?"));
		String searchPlaceholder = this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",searchPageTitleXpath).getAttribute("placeholder");
		Assert.assertTrue("Search".equalsIgnoreCase(searchPlaceholder));
		this.getWebDriverManager().sendText("xpath", searchPageTitleXpath, "entry");
		this.getWebDriverManager().clickElement("xpath", searchBoxInput);
		Assert.assertTrue("entry.ftl".equals(this.getWebDriverManager().waitForNumberElementsToBe(
				"cssselector", searchResultsCss, 1).getText()));
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
