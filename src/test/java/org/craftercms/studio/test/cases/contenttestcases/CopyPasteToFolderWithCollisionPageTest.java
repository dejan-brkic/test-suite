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
package org.craftercms.studio.test.cases.contenttestcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.WebElement;
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
// Test Case Studio- Site Content ID:38
public class CopyPasteToFolderWithCollisionPageTest extends StudioBaseTest {

	private String randomURL;
	private String randomInternalName;
	private String dashboardLink;
	private String recentActivityContentURL;
	private String recentActivityContentName;
	private String fooContentXpath;
	private String pasteContent;
	private String folderChildContent;
	private String folderContent;
	private String copyContent;
	private String fooOriginalContent;
	private String recentActivitySecondContentName;
	private String recentActivitySecondContentURL;
	private String recentActivityThirdContentName;
	private String recentActivityThirdContentURL;
	private String folderSecondChildContent;
	private String secondPasteContentText;
	private static final Logger logger = LogManager.getLogger(CopyPasteToFolderWithCollisionPageTest.class);

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		dashboardLink = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.dashboard.dashboardlink");
		copyContent = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("rightclick.copy.option");
		recentActivityContentURL = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contenturl");
		recentActivitySecondContentURL = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contentsecondurl");
		recentActivityThirdContentURL = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contentthirdurl");
		recentActivityContentName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contentname");
		recentActivitySecondContentName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contentsecondname");
		recentActivityThirdContentName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contentthirdname");
		fooContentXpath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.foocontent");
		pasteContent = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("rightclick.paste.option");
		folderContent = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.afolder");
		folderChildContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.afolder.childcontentafterpaste");
		folderSecondChildContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.afolder.childsecondcontentafterpaste");
		fooOriginalContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.foocontent");
		randomURL = "foo";
		randomInternalName = "foo";
	}

	public void loginAndChangeContentTypeConfiguration(String siteId) {
		loginPage.loginToCrafter();
		homePage.goToDashboardPage(siteId);
		previewPage.clickSidebar()
				.expandPagesTree();

	}

	public void createContentAndFolder() {
		logger.info("Creating new folder");
		dashboardPage.rightClickNewFolderOnHome();
		dashboardPage.setFolderName("a-folder");
		previewPage.createEntryContent(randomURL, randomInternalName, "title", "body");
		getWebDriverManager().waitUntilSidebarOpens();
	}

	public void goToDashboardAndManageContents() {
		// click on dashboard
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink).click();

		// check items on My Recent Activity widget
		this.getWebDriverManager().waitUntilDashboardWidgetsAreLoaded();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL);

		Assert.assertTrue(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName)
						.getText().contains("foo"));
		Assert.assertTrue(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL)
						.getText().contains("/foo"));

		// expand pages folder
		this.getWebDriverManager().waitUntilSidebarOpens();
		this.getWebDriverManager().waitForAnimation();
		
		// reload page
		getWebDriverManager().getDriver().navigate().refresh();
		// check if the folder is opened
		this.getWebDriverManager().waitUntilHomeIsOpened();
		
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fooContentXpath);
		this.getWebDriverManager().contextClick("xpath", fooContentXpath, false);
		getWebDriverManager().usingContextMenu(() -> {
			WebElement copyContent = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
					this.copyContent);
			copyContent.click();
		}, "Pages");

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", folderContent);
		this.getWebDriverManager().contextClick("xpath", folderContent, false);
		getWebDriverManager().usingContextMenu(() -> {
			WebElement pasteContentElement = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
					pasteContent);
			pasteContentElement.click();
		}, "Pages");

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitUntilHomeIsOpened();
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", folderChildContent).isDisplayed());
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fooOriginalContent).isDisplayed());

		// click on dashboard
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink).click();

		// check items on My Recent Activity widget
		this.getWebDriverManager().waitUntilDashboardWidgetsAreLoaded();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL);

		Assert.assertTrue(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName)
						.getText().contains("foo"));
		Assert.assertTrue(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL)
						.getText().contains("/a-folder/foo"));

		Assert.assertTrue(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivitySecondContentName)
						.getText().contains("foo"));
		Assert.assertTrue(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivitySecondContentURL)
						.getText().contains("/foo"));
	}

	public void setup(String siteId) {

		this.loginAndChangeContentTypeConfiguration(siteId);

		this.createContentAndFolder();

		this.goToDashboardAndManageContents();

	}

	public void step4() {
		this.getWebDriverManager().waitUntilSidebarOpens();
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.expandPagesTree();

		// reload page
		getWebDriverManager().getDriver().navigate().refresh();
		//check if the folder is opened
		this.getWebDriverManager().waitUntilHomeIsOpened();
		
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fooContentXpath);
		this.getWebDriverManager().contextClick("xpath", fooContentXpath, false);
		getWebDriverManager().usingContextMenu(() -> {
			WebElement copyContent = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
					this.copyContent);
			copyContent.click();
		}, "Pages");
	}

	public void step5() {
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", folderContent);
		this.getWebDriverManager().contextClick("xpath", folderContent, false);
		getWebDriverManager().usingContextMenu(() -> {
			WebElement pasteContentElement = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
					pasteContent);
			pasteContentElement.click();
		}, "Pages");

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitUntilHomeIsOpened();
		
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", folderSecondChildContent)
				.isDisplayed());

		this.secondPasteContentText = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", folderSecondChildContent).getText();
	}

	public void step6() {
		// click on dashboard
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink).click();

		// check items on My Recent Activity widget
		this.getWebDriverManager().waitUntilDashboardWidgetsAreLoaded();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL);

		Assert.assertTrue(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName)
						.getText().contains(secondPasteContentText));
		String pageUrl = secondPasteContentText.replace("foo ", "foo-");
		Assert.assertTrue(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL)
						.getText().contains("/a-folder/" + pageUrl));
		Assert.assertTrue(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivitySecondContentName)
						.getText().contains("foo"));
		Assert.assertTrue(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivitySecondContentURL)
						.getText().contains("/a-folder/foo"));
		Assert.assertTrue(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityThirdContentName)
						.getText().contains("foo"));
		Assert.assertTrue(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityThirdContentURL)
						.getText().contains("/foo"));

	}

	@Parameters({"testId"})
	@Test()
	public void copyPasteToFolderWithCollisionAndDuplicatePageCopyToFolderTest(String testId) {
		this.setup(testId);

		this.step4();

		this.step5();

		this.step6();
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
