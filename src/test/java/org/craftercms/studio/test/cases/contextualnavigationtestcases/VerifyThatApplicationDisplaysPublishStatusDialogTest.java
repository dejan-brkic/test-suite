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

public class VerifyThatApplicationDisplaysPublishStatusDialogTest extends StudioBaseTest {

	private String homeXpath;
	private String publishStatusTitleText;
	private String publishStatusProccessStatusText;

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		homeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.home");
		publishStatusTitleText = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.publishingstatus.title");
		publishStatusProccessStatusText = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.publishingstatus.proccesstext");
	}

	@Parameters({"testId"})
	@Test()
	public void verifyThatApplicationDisplaysPublishStatusDialogTest(String testId) {
		loginPage.loginToCrafter();
		homePage.goToPreviewPage(testId);
		previewPage.clickSidebar();
		// expand home content
		previewPage.expandHomeTree();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", homeXpath).click();
		
		// click on history option
		previewPage.clickOnPublishingStatusOption();

		// Assertions
		String publishStatusTitle = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", publishStatusTitleText).getText();
		Assert.assertEquals(publishStatusTitle, "Publish Status");
		
		Assert.assertTrue(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", publishStatusProccessStatusText).isDisplayed());

		String publishStatusStatusText = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", publishStatusProccessStatusText).getText();
		Assert.assertEquals(publishStatusStatusText, "Ready");
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
