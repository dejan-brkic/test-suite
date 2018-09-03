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

// Test Case Studio- Sites ID:
// Related to ticket #2380
public class VerifyStudioAllowsToCreateASiteWithLinkUpstreanRemoteBitBucketRepoWithBasicAuthTest
		extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteId;
	private String siteDropdownElementXPath;
	private String bitBucketUserName;
	private String bitBucketPassword;
	private String bitBucketRepositoryURL;
	private String pushToBareRepoInput;
	private String notificationTitle;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		bitBucketUserName = constantsPropertiesManager.getSharedExecutionConstants()
				.getProperty("crafter.bitbucketrepository.username");
		bitBucketPassword = constantsPropertiesManager.getSharedExecutionConstants()
				.getProperty("crafter.bitbucketrepository.password");
		bitBucketRepositoryURL = constantsPropertiesManager.getSharedExecutionConstants()
				.getProperty("crafter.bitbucketrepository.remoterepourl");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		pushToBareRepoInput = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositorypushtoremotebare");
		notificationTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.notificationdialog.title");
		siteId = "testingtargetsiteforbasicauth";
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
		createSitePage.setRepositoryURL(bitBucketRepositoryURL);
	}

	public void step7() {
		createSitePage.selectGitRepoBasicAutheticationType();

		createSitePage.setRepositoryUserName(bitBucketUserName);

		createSitePage.setRepositoryUserPassword(bitBucketPassword);
	}

	public void step8() {
		WebElement pushRemoteBareRepoInputElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", pushToBareRepoInput);
		pushRemoteBareRepoInputElement.click();
	}

	public void step9() {
		// Select website blueprint
		createSitePage.selectWebSiteEditorialBluePrintOption();
	}

	public void step10() {
		// Click on Create button
		createSitePage.clickOnCreateSiteButton();

		this.driverManager.waitForAnimation();
		this.driverManager.waitUntilCreateSiteModalCloses();
		
		Assert.assertFalse(this.driverManager.isElementPresentByXpath(notificationTitle));

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				siteDropdownElementXPath);

		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());
	}

	public void clickOnCreateSiteButton() {
		// Click on the create site button
		homePage.clickOnCreateSiteButton();
	}

	@Test(
			priority = 0)
	public void verifyStudioAllowsToCreateASiteWithLinkUpstreanRemoteBitBucketRepoWithBasicAuthTest() {
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
		
	}

}
