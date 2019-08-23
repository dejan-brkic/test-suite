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
package org.craftercms.studio.test.cases.sitestestcases;

import org.craftercms.studio.test.cases.StudioBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 
 * @author luishernandez
 *
 */
//Test Case Studio- Sites ID:5
public class VerifyStudioAllowsToDeleteASiteCreatedTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String deletedSiteButton;
	private String createSiteButton;
	private String siteDropdownElementXPath;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		deletedSiteButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.deletesite");
		createSiteButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.createsitebutton");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
	}

	public void createSiteUsingEmptyBluePrint(String siteId) {
		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		//select blueprint, set site name, set description, click review and create site
		createSitePage.selectEmptyBluePrintOption()
				.setSiteName(siteId)
				.setDescription("Description")
				.clickReviewAndCreate()
				.clickOnCreateButton();

		Assert.assertTrue(this.getWebDriverManager()
				.waitUntilElementIsClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());

		dashboardPage.clickOnSitesOption();
	}

	@Parameters({"testId"})
	@Test()
	public void verifyStudioAllowsToDeleteASiteCreatedTest(String testId) {

		// login to application
		loginPage.loginToCrafter(userName, password);

		getWebDriverManager().waitUntilLoginCloses();

		this.createSiteUsingEmptyBluePrint(testId);

		this.getWebDriverManager().waitUntilElementIsDisplayed("xpath", createSiteButton);

		this.homePage.deleteSite(testId);

		// Assert
		Assert.assertFalse(this.getWebDriverManager().isElementPresentAndClickableByXpath(String.format(deletedSiteButton, testId)));
	}
}
