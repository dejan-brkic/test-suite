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

// Test Case Studio- Sites ID:10
public class VerifyStudioAllowsToCreateSiteBasedOnBluePrintThenPushToRemoteGitRepoTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String localRepoName;
	private String siteId;
	private String siteDropdownElementXPath;
	private String topNavSitesOption;
	private String notificationTitle;
	private String notificationText;
	private String notificationError;
	private String siteIdFromLocalRepo;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		localRepoName = constantsPropertiesManager.getSharedExecutionConstants()
				.getProperty("crafter.gitrepository.localrepositoryname");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		topNavSitesOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.preview.sitesoption");
		notificationTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.notificationdialog.title");
		notificationText = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.notificationdialog.text");
		notificationError = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.notificationdialog.error");
		siteId = "testsitewithremotebarerepositoryforpushlocal";
		siteIdFromLocalRepo = "testsitefromlocalrepo";

		this.setup();
	}

	@AfterMethod
	public void afterTest() {
		this.deleteRepositoryFolder();
	}

	public void step2() {
		homePage.clickOnCreateSiteButton();	}

	public void step3() {
		createSitePage.selectWebSiteEditorialBluePrintOption();
	}

	public void step4() {
		createSitePage.setSiteName(siteId);
	}

	public void step5() {
		createSitePage.clickAdditionalDeveloperOptions();
	}

	public void step6() {
		createSitePage.clickPushSiteToRemoteGitCheckbox();
	}

	public void step7() {
		createSitePage.setPushRepositoryName("origin");
	}

	public void step8() {
		createSitePage.setPushRepositoryURL(this.driverManager.getLocalBareRepoURL(localRepoName));
	}

	public void step10() {
		createSitePage.clickReviewAndCreate();
	}

	public void step11() {
		// Click on Create button
		createSitePage.clickOnCreateButton();

		this.driverManager.waitUntilCreateSiteModalCloses();
		Assert.assertTrue(this.driverManager
				.waitUntilElementIsClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());
	}

	public void step12() {
		this.driverManager.waitUntilElementIsClickable("xpath", topNavSitesOption).click();
	}

	public void step13() {
		homePage.clickOnCreateSiteButton();
	}

	public void step14() {
		createSitePage.selectWebSiteEditorialBluePrintOption();
	}

	public void step15() {
		createSitePage.setSiteName(siteIdFromLocalRepo);
	}

	public void step16() {
		createSitePage.clickAdditionalDeveloperOptions();
	}

	public void step17() {
		createSitePage.clickPushSiteToRemoteGitCheckbox();
	}

	public void step18() {
		createSitePage.setPushRepositoryName("origin");
	}

	public void step19() {
		createSitePage.setPushRepositoryURL(this.driverManager.getLocalBareRepoURL(localRepoName));
	}

	public void step21() {
		createSitePage.clickReviewAndCreate();
	}

	public void step22() {
		// Click on Create button
		createSitePage.clickOnCreateButton();
		this.driverManager.waitForAnimation();
		
		String notificationTitleText = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", notificationTitle).getText();

		while (!notificationTitleText.equalsIgnoreCase("Notification")) {
			this.driverManager.waitForAnimation();
			notificationTitleText = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayed("xpath", notificationTitle).getText();
		}

		notificationTitleText = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", notificationTitle).getText();
		String notificationFirstText = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", notificationText).getText();
		String notificationSecondText = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", notificationError).getText();

		Assert.assertTrue("Notification".equals(notificationTitleText));
		Assert.assertTrue(
				"Unable to create site. Please contact your system administrator.".equals(notificationFirstText));
		Assert.assertTrue("Error: Remote repository not bare".equals(notificationSecondText));
	}


	@Test(priority = 0)
	public void verifyStudioAllowsToCreateSiteBasedOnBluePrintThenPushToRemoteGitRepoTest() {
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

		// Step 10
		step10();

		// Step 11
		step11();

		// Step 12
		step12();

		// Step 13
		step13();

		// Step 14
		step14();

		// Step 15
		step15();

		// Step 16
		step16();

		// Step 17
		step17();

		// Step 18
		step18();

		// Step 19
		step19();

		// Step 20
		step21();

		// Step 22
		step22();

	}

	public void setup() {
		int exitCode = this.driverManager.goToFolderAndExecuteGitInitBareRepository(localRepoName);
		Assert.assertTrue(exitCode == 0, "Init bare repository process failed");
	}

	public void deleteRepositoryFolder() {
		int exitCode = this.driverManager.goToFolderAndExecuteDeleteBareRepositoryFolder(localRepoName);
		Assert.assertTrue(exitCode == 0, "Delete bare repository process failed");
	}
}
