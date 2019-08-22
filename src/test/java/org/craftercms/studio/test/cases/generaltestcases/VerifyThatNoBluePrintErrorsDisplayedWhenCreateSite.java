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
package org.craftercms.studio.test.cases.generaltestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * 
 * @author Luis Hernandez
 *
 */
// Test Case created to cover ticket
// https://github.com/craftercms/craftercms/issues/1876
// https://github.com/craftercms/craftercms/issues/1877
public class VerifyThatNoBluePrintErrorsDisplayedWhenCreateSite extends StudioBaseTest {

	private String userName;
	private String password;
	private String createSiteErrorNotificationWindow;
	private String menuSitesButton;
	private String siteDropdownElementXPath;


	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		createSiteErrorNotificationWindow = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.createsite.errowindow");
		menuSitesButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("preview.sites.menu.button");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");

	}

	@Parameters({"testId"})
	@Test(priority = 0)
	public void verifyThatNoBluePrintErrorsDisplayedWhenCreateSite(String testId) {

		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		//select blueprint, set site name, set description, click review and create site
		createSitePage.selectWebSiteEditorialBluePrintOption()
				.setSiteName(testId)
				.setDescription("Description")
				.clickReviewAndCreate()
				.clickOnCreateButton();

		// Verify No error messages after clicking on the Create button
		Assert.assertFalse(getWebDriverManager().isElementPresentByXpath(createSiteErrorNotificationWindow));

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath);

		Assert.assertTrue(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",siteDropdownElementXPath).isDisplayed());
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
