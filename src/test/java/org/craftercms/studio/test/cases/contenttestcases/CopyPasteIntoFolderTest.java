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
package org.craftercms.studio.test.cases.contenttestcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
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

public class CopyPasteIntoFolderTest extends StudioBaseTest {

	private static final Logger logger = LogManager.getLogger(CopyPasteIntoFolderTest.class);

	private String secondCopiedElementXPath;
	private String firstCopiedElementXPath;
	private String myRecentActivityItemsCounterXpath;

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String siteId, String blueprint) {
		apiTestHelper.createSite(siteId, "", blueprint);
		firstCopiedElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.myrecentactivity.firstelementurl");
		secondCopiedElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.myrecentactivity.secondelementurl");
		myRecentActivityItemsCounterXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.myrecentactivity.itemscounter");

	}

	@Parameters({"testId"})
	@Test()
	public void copyAndPasteIntoFolderUsingContextualClickOptionsTest(String testId) {
		logger.info("Starting test case");
		loginPage.loginToCrafter();
		homePage.goToDashboardPage(testId);
		previewPage.clickSidebar();
		dashboardPage.expandPagesTree();
		previewPage.createEntryContent("Test1", "Testing1", "title" + testId, "test" + testId);
		dashboardPage.expandHomeTree();
		logger.info("Creating new folder");
		dashboardPage.rightClickNewFolderOnHome();
		dashboardPage.setFolderName("foldertocopy");
		dashboardPage.rightClickToCopyComponentToNewFolder();

		// paste the crafter component in the new folder created
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.rightClickToPasteToNewFolder();

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitForFullExpansionOfTree();
		dashboardPage.rightClickToCopyNewContentToNewFolder();

		// paste the content in the new folder created
		this.getWebDriverManager().waitForFullExpansionOfTree();
		dashboardPage.rightClickToPasteToNewFolder();
		
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitUntilPageLoad();
		
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitUntilTextIs("xpath", myRecentActivityItemsCounterXpath, "3");
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", firstCopiedElementXPath);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", secondCopiedElementXPath);
		
		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", firstCopiedElementXPath).getText()
				.contains("/foldertocopy/test1-"));
		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", secondCopiedElementXPath).getText()
				.equalsIgnoreCase("/foldertocopy/test1"));

	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
