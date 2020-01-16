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
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class DuplicateOptionTest extends StudioBaseTest {

	private String createFormFrameElementCss;
	private String testItemXpath;
	private String duplicateButtonXpath;
	private String copyTestItemXpath;

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		testItemXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.testingcontentitem");
		duplicateButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.duplicatedialog.duplicate");
		copyTestItemXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitecontent.copytestitem");
	}

	public void duplicateContentCreated() {
		dashboardPage.clickOnDuplicateOption();

		// click on duplicate in the popup
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", duplicateButtonXpath)
				.click();

		this.getWebDriverManager().waitForAnimation();

		// Switch to the iframe
		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// edit internal name
			dashboardPage.editInternalName("COPY");
		});

		// Switch back to the dashboard page
		getWebDriverManager().getDriver().switchTo().defaultContent();

		// reload page
		getWebDriverManager().getDriver().navigate().refresh();
	}

	@Parameters({"testId"})
	@Test()
	public void duplicatePageUsingContextualNavigationDuplicateOptionOption(String testId) {
		loginPage.loginToCrafter();
		homePage.goToPreviewPage(testId);
		previewPage.clickSidebar();
		this.getWebDriverManager().waitUntilSidebarOpens();
		previewPage.createEntryContent("Test1", "Testing1", "title" + testId, "body" + testId);

		// Select the content to duplicate.
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", testItemXpath).click();

		// Duplicate content created
		duplicateContentCreated();

		previewPage.goToDashboard();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", copyTestItemXpath).click();
		Assert.assertTrue(getWebDriverManager().isElementPresentByXpath(copyTestItemXpath),
				"Duplicated Option is not displayed");

	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
