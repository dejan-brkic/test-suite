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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.TimeoutException;


/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class HistoryOptionTest extends StudioBaseTest{

	private String homeXpath;
	private String historyDialogTitle;
	private String actionsHeaderXpath;

	private static Logger logger = LogManager.getLogger(HistoryOptionTest.class);

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		homeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.home");
		historyDialogTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.historydialogtitle");
		actionsHeaderXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.historydialogactionsheader");
	}

	@Parameters({"testId"})
	@Test()
	public void verifyThatTheHistoryDialogIsDisplayedTest(String testId) {
		loginPage.loginToCrafter();
		homePage.goToPreviewPage(testId);
		previewPage.clickSidebar();
		
		this.getWebDriverManager().waitUntilSidebarOpens();
		
		// expand pages folder
		previewPage.expandPagesTree();
		this.getWebDriverManager().clickElement("xpath", homeXpath);

		// click on history option
		previewPage.clickOnHistoryOption();

		// Assert
		this.getWebDriverManager().waitForAnimation();
		try {
			this.getWebDriverManager().waitUntilElementIsDisplayed("xpath", actionsHeaderXpath);
		} catch (TimeoutException e) {
			this.getWebDriverManager().takeScreenshot("HistoryDialogNotCompletedRendered");
			logger.warn("History dialog is not completely rendered");
		}
		
		this.getWebDriverManager().waitForAnimation();
		String historyPage = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", historyDialogTitle).getText();
		this.getWebDriverManager().waitForAnimation();
		Assert.assertEquals(historyPage, "Version History");

	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
