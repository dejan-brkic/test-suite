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
package org.craftercms.studio.test.cases.siteconfigtestcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.craftercms.studio.test.utils.FilesLocations;

/**
 * 
 * @author luishernandez
 *
 */

// Test Case Studio - Site Config ID:58
public class VerifyThatStudioAllowsToAddRemoteRepositoryAndPushAndPullFromRemoteTest extends StudioBaseTest {

	private String adminConsoleXpath;
	private String gitPrivateKey;
	private String gitRepoUrl;
	private String pushButtonXpath;
	private String notificationText;
	private String testingItemRecentActivity;
	private String pullButtonXpath;
	private String deleteButtonXpath;
	private static Logger logger = LogManager
			.getLogger(VerifyThatStudioAllowsToAddRemoteRepositoryAndPushAndPullFromRemoteTest.class);

	@Parameters({"testId", "blueprint", "remoteSshUrl"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint, String remoteSshUrl) {
		apiTestHelper.createSite(testId, "", blueprint);
		gitRepoUrl = remoteSshUrl;
		gitPrivateKey = FilesLocations.PRIVATEKEYCONTENTFILEPATH;
		adminConsoleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.adminconsole");
		pushButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("remoterepositories.newrepo.pushbutton");
		pullButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("remoterepositories.newrepo.pullbutton");
		deleteButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("remoterepositories.newrepo.deletebutton");
		notificationText = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("remoterepositories.newrepo.push.notificationtext");
		testingItemRecentActivity = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.testingcontentitem.myrecentactivity");
	}

	public void step1(String siteId) {
		loginPage.loginToCrafter();
		homePage.goToPreviewPage(siteId);
		previewPage.clickSidebar()
				.clickAdminConsoleOption();
	}

	public void step2() {
		this.siteConfigPage.clickRemoteRepositoriesOption();
		this.siteConfigPage.checkThatRepositoriesListIsEmpty();
	}

	public void step3() {
		this.siteConfigPage.clickAddNewRepositoryButton();
	}

	public void addNewRepository() {
		siteConfigPage.addNewRepositoryUsingPrivateKeyAuthentication("origin", this.gitRepoUrl, gitPrivateKey);
	}

	public void step9() {
		this.goToDashboardAndCreateNewArticlePage();
	}


	public void goToDashboardAndCreateNewArticlePage() {
		previewPage.goToDashboard();
		previewPage.createEntryContent("Test", "Testing1", "Title", "Body");
		Assert.assertNotNull(getWebDriverManager().waitUntilElementIsDisplayed("xpath", testingItemRecentActivity));
	}


	public void step10() {
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", adminConsoleXpath).click();
	}

	public void step11() {
		this.siteConfigPage.clickRemoteRepositoriesOption();
	}

	public void pushSiteChangesToRemoteRepo() {
		logger.info("Pushing changes to existent repository");
		this.siteConfigPage.pushSiteChangesToRemoteRepo(pushButtonXpath);

		this.getWebDriverManager().getDriver().switchTo().activeElement();
		// check notification dialog is displayed
		Assert.assertTrue(
				this.getWebDriverManager().waitUntilElementIsDisplayed("xpath", notificationText)
						.getText().contains("Successfully Pushed"));

		this.getWebDriverManager().waitUntilNotificationModalIsNotPresent();

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().getDriver().switchTo().defaultContent();

	}

	public void pullSiteChangesFromRemoteRepo() {
		logger.info("Pulling changes from existent repository");
		this.siteConfigPage.pullSiteChangesFromRemoteRepo(pullButtonXpath);

		this.getWebDriverManager().getDriver().switchTo().activeElement();
		// check notification dialog is displayed
		Assert.assertTrue(
				this.getWebDriverManager().waitUntilElementIsDisplayed("xpath", notificationText)
						.getText().contains("Successfully Pulled"));

		this.getWebDriverManager().waitUntilNotificationModalIsNotPresent();

		this.getWebDriverManager().getDriver().switchTo().defaultContent();
	}

	public void deleteRemoteRepo() {
		logger.info("Deleting an existent repository");
		this.siteConfigPage.deleteRemoteRepo(deleteButtonXpath);

		this.getWebDriverManager().getDriver().switchTo().activeElement();

		// check notification dialog is displayed
		Assert.assertTrue(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", notificationText)
						.getText().contains("deleted."));

		this.getWebDriverManager().waitUntilNotificationModalIsNotPresent();

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().getDriver().switchTo().defaultContent();

		// waiting for update process on the list of repos
		this.getWebDriverManager().waitForBulkPublish(5000);

		this.siteConfigPage.checkThatRepositoriesListIsEmpty();
	}

	@Parameters({"testId"})
	@Test()
	public void verifyThatStudioAllowsToAddRemoteRepositoryAndPushAndPullFromRemoteTest(String testId) {

		// Step 1
		this.step1(testId);

		// Step 2
		this.step2();

		// Step 3
		this.step3();

		// Step 4, 5, 6, 7 and 8
		this.addNewRepository();

		// waiting for update process on the list of repos
		this.getWebDriverManager().waitForBulkPublish(5000);

		// checking list of repositories displayed
		this.siteConfigPage.checkThatRepositoriesListIsNotEmptyAndListContainsRepo("origin", this.gitRepoUrl);

		// Step 9
		this.step9();

		// Step 10
		this.step10();

		// Step 11
		this.step11();

		// waiting for update process on the list of repos
		this.getWebDriverManager().waitForBulkPublish(5000);

		// Steps 12 and 13
		this.pushSiteChangesToRemoteRepo();

		// Steps 14 and 15
		this.pullSiteChangesFromRemoteRepo();

		// Step 16 and 17
		this.deleteRemoteRepo();
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
