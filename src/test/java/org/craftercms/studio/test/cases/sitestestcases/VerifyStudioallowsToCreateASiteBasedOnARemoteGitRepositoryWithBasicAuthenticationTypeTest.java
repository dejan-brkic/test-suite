/*
 * Copyright (C) 2007-2018 Crafter Software Corporation. All Rights Reserved.
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
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author luishernandez
 *
 */

// Test Case Studio- Sites ID:12
public class VerifyStudioallowsToCreateASiteBasedOnARemoteGitRepositoryWithBasicAuthenticationTypeTest
		extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteId;
	private String createBaseOnGitRepoOption;
	private String siteDropdownElementXPath;
	private String gitUserName;
	private String gitPassword;
	private String gitRepositoryURL;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		gitUserName = constantsPropertiesManager.getSharedExecutionConstants()
				.getProperty("crafter.gitrepository.username");
		gitPassword = constantsPropertiesManager.getSharedExecutionConstants()
				.getProperty("crafter.gitrepository.password");
		gitRepositoryURL = constantsPropertiesManager.getSharedExecutionConstants()
				.getProperty("crafter.gitrepository.url");
		createBaseOnGitRepoOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositorybasedonremotegitrepo");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");

		siteId = "testingtargetsite";
	}

	public void step2() {
		this.clickOnCreateSiteButton();
	}

	public void step3() {
		// Filling the name of site
		createSitePage.setSiteName(siteId);
	}

	public void step4() {
		this.homePage.clickOnLinkToUpstreamRemoteGitRepository();
	}

	public void step5() {
		createSitePage.setRepositoryName("origin");
	}

	public void step6() {
		createSitePage.setRepositoryURL(gitRepositoryURL);
	}

	public void step7() {
		createSitePage.selectGitRepoBasicAutheticationType();
	}

	public void step8() {
		createSitePage.setRepositoryUserName(gitUserName);
	}

	public void step9() {
		createSitePage.setRepositoryUserPassword(gitPassword);
	}

	public void step10() {
		WebElement createUsingRemoteGitRepoOption = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", createBaseOnGitRepoOption);
		createUsingRemoteGitRepoOption.click();
	}

	public void step11() {
		// Click on Create button
		createSitePage.clickOnCreateSiteButton();

		this.driverManager.waitUntilCreateSiteModalCloses();
		
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath);

		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());
	}

	public void clickOnCreateSiteButton() {
		// Click on the create site button
		homePage.clickOnCreateSiteButton();
	}

	@Test(priority = 0)
	public void verifyStudioallowsToCreateASiteBasedOnARemoteGitRepositoryWithBasicAuthenticationTypeTest() {
		this.testScenario();
	}

	public void testScenario() {
		// login to application
		loginPage.loginToCrafter(userName, password);

		driverManager.waitUntilLoginCloses();

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

		// Step 11
		step11();

	}

}
