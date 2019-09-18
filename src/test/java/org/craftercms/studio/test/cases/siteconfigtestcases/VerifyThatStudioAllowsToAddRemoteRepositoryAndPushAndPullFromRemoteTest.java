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

	private String userName;
	private String password;
	private String siteDropdownXpath;
	private String adminConsoleXpath;
	private String siteDropdownListElementXPath;
	private String gitPrivateKey;
	private String gitRepoUrl;
	private String pushButtonXpath;
	private String notificationText;
	private String createFormFrameElementCss;
	private String createFormSaveAndCloseElement;
	private String createFormMainTitleElementXPath;
	private String testingItemRecentActivity;
	private String pullButtonXpath;
	private String deleteButtonXpath;
	private static Logger logger = LogManager
			.getLogger(VerifyThatStudioAllowsToAddRemoteRepositoryAndPushAndPullFromRemoteTest.class);

	@Parameters({"testId", "blueprint", "remoteSshUrl"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint, String remoteSshUrl) {
		apiTestHelper.createSite(testId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		gitRepoUrl = remoteSshUrl;
		gitPrivateKey = FilesLocations.PRIVATEKEYCONTENTFILEPATH;
		siteDropdownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitedropdown");
		adminConsoleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.adminconsole");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
		pushButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("remoterepositories.newrepo.pushbutton");
		pullButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("remoterepositories.newrepo.pullbutton");
		deleteButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("remoterepositories.newrepo.deletebutton");
		notificationText = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("remoterepositories.newrepo.push.notificationtext");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		createFormMainTitleElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.createformTitle");
		testingItemRecentActivity = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.testingcontentitem.myrecentactivity");

	}

	public void step1(String siteId) {
		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to closes
		getWebDriverManager().waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage(siteId);

		// Show site content panel
		if (!(this.getWebDriverManager().waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
				.getAttribute("class").contains("site-dropdown-open")))
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownXpath)
					.click();

		logger.info("Going to Site Config page");
		this.getWebDriverManager().clickElement("xpath", adminConsoleXpath);

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

	public void changeBodyToNotRequiredOnEntryContent() {
		previewPage.changeBodyOfEntryContentPageToNotRequired();
	}

	public void goToDashboardAndCreateNewArticlePage() {
		// body not required for Entry content type
		logger.info("Change the entry body content to not required");
		this.changeBodyToNotRequiredOnEntryContent();

		// go to sidebar
		this.getWebDriverManager().waitUntilSidebarOpens();
		// expand Pages tree
		this.dashboardPage.expandPagesTree();

		this.createContent();

		// reload page
		getWebDriverManager().getDriver().navigate().refresh();

		// dashboardPage.expandHomeTree();

		Assert.assertNotNull(getWebDriverManager().waitUntilElementIsDisplayed("xpath", testingItemRecentActivity));
	}

	public void createContent() {
		// right click to see the the menu
		logger.info("Creating a new entry content on Home");
		getWebDriverManager().waitUntilPageLoad();
		getWebDriverManager().waitUntilSidebarOpens();
		dashboardPage.rightClickToSeeMenu();

		// Select Entry Content Type
		dashboardPage.clickEntryCT();

		// Confirm the Content Type selected
		dashboardPage.clickOKButton();

		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// creating random values for URL field and InternalName field

			// Set basics fields of the new content created
			dashboardPage.setBasicFieldsOfNewContent("Test", "Testing1");

			// Set the title of main content
			getWebDriverManager().sendText("xpath", createFormMainTitleElementXPath, "MainTitle");

			// save and close

			this.getWebDriverManager()
					.driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormSaveAndCloseElement)
					.click();
		});

		this.getWebDriverManager().waitUntilSidebarOpens();

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
