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

// Test Case Studio- Sites ID:11
public class VerifyStudioAllowsToCreateASiteBasedOnARemoteGitRepositoryWithNoneAuthenticationTypeTest
		extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteId;
	private String siteDropdownElementXPath;
	private String emptyBPSiteId;

	@Parameters({"testId"})
	@BeforeMethod
	public void beforeTest(String testId) {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		emptyBPSiteId = testId + "emptysitefornoneauth";
		siteId = testId + "targetsite";
	}

	public void step2() {
		this.clickOnCreateSiteButton();
	}

	public void step3() {
		this.createSitePage.clickUseRemoteGitRepoSiteCheckbox();
	}

	public void step4() {
		this.createSitePage.clickBasicInformation();
	}

	public void step5() {
		createSitePage.setSiteName(siteId);
	}

	public void step6() {
		createSitePage.clickBasicDeveloperOptions();
	}

	public void step7() {
		createSitePage.setFromGitRepositoryName("origin");
	}

	public void step8() {
		createSitePage.setFromGitRepositoryURL(this.getWebDriverManager().getAuthoringSiteSandboxRepoURL(emptyBPSiteId));
	}

	public void step10() {
		createSitePage.clickReviewAndCreate();
	}

	public void step11() {
		createSitePage.clickOnCreateButton();
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());
	}

	public void clickOnCreateSiteButton() {
		// Click on the create site button
		homePage.clickOnCreateSiteButton();
	}

	@Test()
	public void verifyStudioallowsToCreateASiteBasedOnARemoteGitRepositoryWithNoneAuthenticationTypeTest() {
		this.testScenario();
	}

	public void testScenario() {

		this.setup();

		// Step 2
		step2();

		// Step 3
		step3();

		// Step 4
		step4();

		// Step 5
		step5();

		// Step 6
		step6();

		// Step 7
		step7();

		// Step 8
		step8();

		// Step 9
		step10();

		// Step11
		step11();

	}

	public void createSiteUsingEmptyBluePrint(String siteId) {
		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		// Select empty blueprint
		// Fill the name of site
		// Fill the description of the site
		// Click on Create button
		createSitePage.selectEmptyBluePrintOption()
				.setSiteName(emptyBPSiteId)
				.setDescription("Description")
				.clickReviewAndCreate()
				.clickOnCreateButton();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath);

		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());
		
		dashboardPage.clickOnSitesOption();

	}

	public void setup() {
		// login to application
		loginPage.loginToCrafter(userName, password);

		getWebDriverManager().waitUntilLoginCloses();

		this.createSiteUsingEmptyBluePrint(emptyBPSiteId);
	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		apiTestHelper.deleteSite(siteId);
		apiTestHelper.deleteSite(emptyBPSiteId);
	}
}
