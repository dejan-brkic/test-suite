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

// Test Case Studio- Sites ID:14
public class VerifyStudioAllowsToCreateASiteBasedOnARemoteGitRepositoryWithTokenAuthenticationTypeTest
		extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteId;
	private String siteDropdownElementXPath;
	private String gitUsername;
	private String gitToken;
	private String gitRepositoryURL;

	@Parameters({"testId", "remoteUrl", "remoteUsername", "remoteToken"})
	@BeforeMethod
	public void beforeTest(String testId, String remoteUrl, String remoteUsername, String remoteToken) {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		gitRepositoryURL = remoteUrl;
		gitUsername = remoteUsername;
		gitToken = remoteToken;
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");

		siteId = testId;
	}

	public void step2() {
		// Click on the create site button
		homePage.clickOnCreateSiteButton();
	}

	public void step3() {
		createSitePage.clickUseRemoteGitRepoSiteCheckbox();

	}

	public void step4() {
		createSitePage.clickBasicInformation();
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
		createSitePage.setFromGitRepositoryURL(gitRepositoryURL);
	}

	public void step9() {
		createSitePage.selectFromGitRepoTokenAuthenticationType();
	}

	public void step10() {
		createSitePage.setFromGitRepositoryUserName(gitUsername);
	}

	public void step11() {
		createSitePage.setFromGitRepositoryToken(gitToken);
	}

	public void step12() {
		createSitePage.clickReviewAndCreate();
	}

	public void step13() {
		// Click on Create button
		createSitePage.clickOnCreateButton();
		Assert.assertTrue(this.getWebDriverManager()
				.waitUntilElementIsClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());
	}

	@Test()
	public void verifyStudioAllowsToCreateASiteBasedOnARemoteGitRepositoryWithTokenAuthenticationTypeTest() {
		this.testScenario();
	}

	public void testScenario() {
		// login to application
		loginPage.loginToCrafter(userName, password);

		getWebDriverManager().waitUntilLoginCloses();

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
		
		// Step9
		step9();
		
		// Step 10
		step10();

		// Step 10
		step11();

		// Step 10
		step12();

		// Step 10
		step13();

	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
