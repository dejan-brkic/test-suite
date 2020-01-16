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

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

/**
 * 
 * @author luishernandez
 *
 */

public class PublishingSiteTest extends StudioBaseTest {

	private String testingContentItem;
	private String topNavStatusIcon;
	private String homeXpath;
	private int numberOfAttemptsForElementsDisplayed;

	private static Logger logger = LogManager.getLogger(PublishingSiteTest.class);

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		testingContentItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.testingcontentitem");
		topNavStatusIcon = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.statustopbaricon");
		homeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.home");
		this.numberOfAttemptsForElementsDisplayed = Integer.parseInt(constantsPropertiesManager
				.getSharedExecutionConstants().getProperty("crafter.numberofattemptsforelementdisplayed"));

	}

	public void approveAndPublish() {

		// approve and publish

		previewPage.clickOnApprovePublish();

		// submit

		previewPage.clickOnSubmitButtonOfApprovePublish();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeXpath);

	}

	@Parameters({"testId"})
	@Test()
	public void publishingSite(String testId) {
		loginPage.loginToCrafter();
		homePage.goToPreviewPage(testId);
		previewPage.clickSidebar()
				.createEntryContent("Test1", "Testing1", "title" + testId, "body" + testId);

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingContentItem).click();

		// approve and publish
		approveAndPublish();

		this.getWebDriverManager().waitForAnimation();

		// expand pages folder
		dashboardPage.expandPagesTree();

		for (int i = 0; i < numberOfAttemptsForElementsDisplayed; i++) {
			try {
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingContentItem)
						.click();
				this.getWebDriverManager().waitUntilAttributeContains("xpath", topNavStatusIcon, "class", "undefined live");
				break;
			} catch (TimeoutException e) {
				this.getWebDriverManager().takeScreenshot("PageNotPublishedOnTopNavBar");
				logger.warn("Content page is not published yet, checking again if it has published icon on top bar");
				getWebDriverManager().getDriver().navigate().refresh();
			}
		}

		String elementClassValue = this.getWebDriverManager().getDriver().findElement(By.xpath(topNavStatusIcon))
				.getAttribute("class");
		Assert.assertTrue(elementClassValue.contains("undefined live"));

	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
