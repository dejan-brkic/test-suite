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
import org.testng.annotations.Parameters;
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

	@Parameters({"testId"})
	@BeforeMethod
	public void beforeTest(String testId) {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		emptyBPSiteId = testId + "empty";
		headlessBlogBPSiteId = testId + "headlessblog";
		headlessStoreBPSiteId = testId + "headlessstore";
		editorialBPSiteId = testId + "editorial";
		videoCenterBPSiteId = testId + "videocenter";
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

		Assert.assertTrue(this.getWebDriverManager()
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

		Assert.assertTrue(this.getWebDriverManager()
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

		Assert.assertTrue(this.getWebDriverManager()
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

		Assert.assertTrue(this.getWebDriverManager()
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
		Assert.assertTrue(this.getWebDriverManager()
				.waitUntilElementIsClickable("xpath", siteDropdownElementXPath,80)
				.isDisplayed());

	}

	public void verifySiteAvailable(String siteId) {
		dashboardPage.clickOnSitesOption();
		this.homePage.checkIfSiteIsListedOnSitesPage(siteId);
	}

	@Test()
	public void verifyStudioAllowsToCreateSitesWithEachBlueprint() {

		// login to application
		loginPage.loginToCrafter(userName, password);

		getWebDriverManager().waitUntilLoginCloses();

		// Steps 3 and 4
		createSiteUsingEmptyBluePrint();

		verifySiteAvailable(emptyBPSiteId);

		// Steps 6 and 7
		createSiteUsingWebSiteEditorialBluePrint();

		verifySiteAvailable(editorialBPSiteId);

		// Steps 9 and 10
		createSiteUsingHeadlessBlogBluePrint();

		verifySiteAvailable(headlessBlogBPSiteId);

		// Steps 12 and 13
		createSiteUsingHeadlessStoreBluePrint();

		verifySiteAvailable(headlessStoreBPSiteId);

		// Steps 15 and 16
		createSiteUsingVideoCenterBlueprint();

		verifySiteAvailable(videoCenterBPSiteId);
	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		apiTestHelper.deleteSite(emptyBPSiteId);
		apiTestHelper.deleteSite(editorialBPSiteId);
		apiTestHelper.deleteSite(headlessBlogBPSiteId);
		apiTestHelper.deleteSite(headlessStoreBPSiteId);
		apiTestHelper.deleteSite(videoCenterBPSiteId);
	}
}
