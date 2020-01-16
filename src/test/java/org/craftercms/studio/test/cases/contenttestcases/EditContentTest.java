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

public class EditContentTest extends StudioBaseTest {

	private String createFormFrameElementCss;
	private String myRecentActivityTestingItem;
	private String randomURL;
	private String randomInternalName;

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		myRecentActivityTestingItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.testingcontentitemedited.myrecentactivity");

		randomURL = "Test1";
		randomInternalName = "Testing1";
	}

	public void editingContentRecentlyCreated() {

		dashboardPage.rightClickToSelectEditOption();

		this.getWebDriverManager().waitForAnimation();

		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// edit internal name
			dashboardPage.editInternalName("Edited");
		});

	}

	@Parameters({"testId"})
	@Test()
	public void verifyTheEditionOfAPageUsingRightClickEditOptionTest(String testId) {
		loginPage.loginToCrafter();
		homePage.goToDashboardPage(testId);
		previewPage.clickSidebar();
		dashboardPage.expandPagesTree();
		previewPage.createEntryContent(randomURL, randomInternalName, "title" + testId, "body" + testId);
		dashboardPage.expandHomeTree();

		editingContentRecentlyCreated();
		Assert.assertNotNull(getWebDriverManager().waitUntilElementIsDisplayed("xpath", myRecentActivityTestingItem),
				"Content page is not displayed on the My Recent Activity Widget");
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
