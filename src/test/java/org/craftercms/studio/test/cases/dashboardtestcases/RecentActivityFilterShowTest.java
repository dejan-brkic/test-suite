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
package org.craftercms.studio.test.cases.dashboardtestcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class RecentActivityFilterShowTest extends StudioBaseTest {

	private static final Logger logger = LogManager.getLogger(RecentActivityFilterShowTest.class);

	private String homeElementXPath;
	private String myRecentActivityShowInputXPath;
	private String myRecentActivityFirstItemURLXPath;
	private String myRecentActivitySecondItemURLXPath;

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		homeElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.home");
		myRecentActivityShowInputXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.myrecentactivity.showinput");
		myRecentActivityFirstItemURLXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.myrecentactivity.firstelementurl");
		myRecentActivitySecondItemURLXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.myrecentactivity.secondelementurl");

	}

	public void filtersAndAsserts() {

		// clean filter
		this.getWebDriverManager().clearInputUsingDeleteKeys("xpath", myRecentActivityShowInputXPath);

		// Show only 1 item edited
		getWebDriverManager().sendText("xpath", myRecentActivityShowInputXPath, "1", false);
		getWebDriverManager().waitUntilElementIsDisplayed("xpath", myRecentActivityShowInputXPath).sendKeys(Keys.ENTER);
		this.getWebDriverManager().waitForAnimation();
		getWebDriverManager().waitUntilElementIsDisplayed("xpath", myRecentActivityFirstItemURLXPath);

		// Assert filter 1
		this.getWebDriverManager().waitForAnimation();
		String edit1 = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", myRecentActivityFirstItemURLXPath).getText();

		Assert.assertEquals(edit1, "/aboutus1");

		// clean filter
		this.getWebDriverManager().clearInputUsingDeleteKeys("xpath", myRecentActivityShowInputXPath);

		// Show only 2 item edited
		getWebDriverManager().sendText("xpath", myRecentActivityShowInputXPath, "2", false);
		getWebDriverManager().waitUntilElementIsDisplayed("xpath", myRecentActivityShowInputXPath).sendKeys(Keys.ENTER);
		this.getWebDriverManager().waitForAnimation();
		getWebDriverManager().waitUntilElementIsDisplayed("xpath", myRecentActivitySecondItemURLXPath);

		// Assert filter 2
		this.getWebDriverManager().waitForAnimation();
		String edit2 = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", myRecentActivitySecondItemURLXPath).getText();
		Assert.assertEquals(edit2, "/aboutus");
	}

	@Parameters({"testId"})
	@Test()
	public void verifyThatTheShowInputWorksProperlyOnRecentActivityWidgetTest(String testId) {
		logger.info("Starting test case");
		loginPage.loginToCrafter();
		homePage.goToPreviewPage(testId);
		previewPage.clickSidebar();
		previewPage.createEntryContent("AboutUs", "AboutUs", "title" + testId, "body" + testId);

		// create a content with level descriptor content type
		// create another content to use a filter
		this.getWebDriverManager().isElementPresentAndClickableByXpath(homeElementXPath);
		previewPage.createEntryContent("AboutUs1", "AboutUs1", "title" + testId, "body" + testId);
		previewPage.goToDashboard();
		// filters and asserts
		this.filtersAndAsserts();

	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
