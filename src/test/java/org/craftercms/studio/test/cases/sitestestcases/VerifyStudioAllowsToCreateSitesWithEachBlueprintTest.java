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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author luishernandez
 *
 */

// Test Case Studio- Sites ID:2
public class VerifyStudioAllowsToCreateSitesWithEachBlueprintTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String emptyBPSiteId;
	private String headlessBlogBPSiteId;
	private String headlessStoreBPSiteId;
	private String editorialBPSiteId;
	private String videoCenterBPSiteId;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		emptyBPSiteId = "emptybpsite";
		headlessBlogBPSiteId = "headlessblogbpsite";
		headlessStoreBPSiteId = "headlessstorebpsite";
		editorialBPSiteId = "editorialbpsite";
		videoCenterBPSiteId = "videocenterbpsite";
	}

	public void createSiteUsingEmptyBluePrint() {
		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		//select blueprint, set site name, set description, click review and create site
		createSitePage.selectEmptyBluePrintOption()
				.setSiteName(emptyBPSiteId)
				.setDescription("Description")
				.clickReviewAndCreate()
				.clickOnCreateButton();

		Assert.assertTrue(this.driverManager
				.waitUntilElementIsClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());
	}

	public void createSiteUsingWebSiteEditorialBluePrint() {
		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		//select blueprint, set site name, set description, click review and create site
		createSitePage.selectWebSiteEditorialBluePrintOption()
				.setSiteName(editorialBPSiteId)
				.setDescription("Description")
				.clickReviewAndCreate()
				.clickOnCreateButton();

		Assert.assertTrue(this.driverManager
				.waitUntilElementIsClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());
	}

	public void createSiteUsingHeadlessBlogBluePrint() {
		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		//select blueprint, set site name, set description, click review and create site
		createSitePage.selectHeadlessStoreBluePrintOption()
				.setSiteName(headlessBlogBPSiteId)
				.setDescription("Description")
				.clickReviewAndCreate()
				.clickOnCreateButton();

		Assert.assertTrue(this.driverManager
				.waitUntilElementIsClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());
	}

	public void createSiteUsingHeadlessStoreBluePrint() {
		// Click on the create site button
		homePage.clickOnCreateSiteButton();
		//select blueprint, set site name, set description, click review and create site
		createSitePage.selectHeadlessStoreBluePrintOption()
				.setSiteName(headlessStoreBPSiteId)
				.setDescription("Description")
				.clickReviewAndCreate()
				.clickOnCreateButton();

		Assert.assertTrue(this.driverManager
				.waitUntilElementIsClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());
	}

	public void createSiteUsingVideoCenterBlueprint() {
		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		//select blueprint, set site name, set description, click review and create site
		createSitePage.selectVideoCenterBluePrintOption()
				.setSiteName(videoCenterBPSiteId)
				.setDescription("Description")
				.clickReviewAndCreate()
				.clickOnCreateButton();
		//video center takes longer to create, lets wait for more
		Assert.assertTrue(this.driverManager
				.waitUntilElementIsClickable("xpath", siteDropdownElementXPath,80)
				.isDisplayed());

	}

	public void step5() {
		dashboardPage.clickOnSitesOption();
		this.homePage.checkIfSiteIsListedOnSitesPage(emptyBPSiteId);
	}

	public void step8() {
		dashboardPage.clickOnSitesOption();
		this.homePage.checkIfSiteIsListedOnSitesPage(editorialBPSiteId);
	}

	public void step11() {
		dashboardPage.clickOnSitesOption();
		this.homePage.checkIfSiteIsListedOnSitesPage(headlessBlogBPSiteId);
	}

	public void step14() {
		dashboardPage.clickOnSitesOption();
		this.homePage.checkIfSiteIsListedOnSitesPage(headlessStoreBPSiteId);
	}

	public void step17() {
		dashboardPage.clickOnSitesOption();
		this.homePage.checkIfSiteIsListedOnSitesPage(videoCenterBPSiteId);
	}

	@Test(priority = 0)
	public void verifyStudioAllowsToCreateSitesWithEachBlueprint() {

		// login to application
		loginPage.loginToCrafter(userName, password);

		driverManager.waitUntilLoginCloses();

		// Steps 3 and 4
		createSiteUsingEmptyBluePrint();

		step5();

		// Steps 6 and 7
		createSiteUsingWebSiteEditorialBluePrint();

		step8();

		// Steps 9 and 10
		createSiteUsingHeadlessBlogBluePrint();

		step11();

		// Steps 12 and 13
		createSiteUsingHeadlessStoreBluePrint();

		step14();

		// Steps 15 and 16
		createSiteUsingVideoCenterBlueprint();

		step17();

	}

	@AfterMethod
	public void afterTest() {
		this.homePage.deleteAllSites();
	}
}
