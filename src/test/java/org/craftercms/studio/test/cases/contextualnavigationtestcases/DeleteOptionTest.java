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

public class DeleteOptionTest extends StudioBaseTest {

	private static final Logger logger = LogManager.getLogger(DeleteOptionTest.class);
	private String testingItemURLXpath;
	private String testItemXpath;

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		testingItemURLXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.myrecentactivity.firstelementurl");
		testItemXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general" + ".testingcontentitem");
	}

	@Parameters({"testId"})
	@Test()
	public void deletePageUsingContextualNavigationDeleteOptionTest(String testId) {
		logger.info("Starting test case");
		loginPage.loginToCrafter();
		homePage.goToPreviewPage(testId);
		previewPage.clickSidebar()
				.createEntryContent("Test1", "Testing1", "title" + testId, "body" + testId)
				.goToDashboard();
		// Select the content to delete.
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", testItemXpath).click();
		previewPage.clickOnDeleteOption();
		previewPage.clickOnDeleteDependencies();
		previewPage.clickOnOKDeleteDependencies();
		previewPage.goToDashboard();
		String contentDelete = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", testingItemURLXpath).getText();
		Assert.assertEquals(contentDelete, "/test1");
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
