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
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class DeleteContentTest extends StudioBaseTest {

	private static final Logger logger = LogManager.getLogger(DeleteContentTest.class);
	private String testingItemURLXpath;

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String siteId, String blueprint) {
		apiTestHelper.createSite(siteId, "", blueprint);
		testingItemURLXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.myrecentactivity.firstelementurl");
	}

	@Parameters({"testId"})
	@Test()
	public void deletePageUsingContextualClickDeleteOptionTest(String testId) {
		logger.info("Starting test case");
		loginPage.loginToCrafter();
		homePage.goToDashboardPage(testId);
		previewPage.clickSidebar();
		getWebDriverManager().waitUntilSidebarOpens();
		dashboardPage.expandPagesTree();
		previewPage.createEntryContent("Test1", "Testing1", "title", "body");
		getWebDriverManager().waitUntilSidebarOpens();
		dashboardPage.expandHomeTree();
		dashboardPage.rightClickToDeleteContent();
		dashboardPage.clicktoDeleteContent();
		dashboardPage.clickOKSubmittalComplete();

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitForFullExpansionOfTree();
		String contentDeleted = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", testingItemURLXpath).getText();
		Assert.assertEquals(contentDeleted, "/test1");
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
