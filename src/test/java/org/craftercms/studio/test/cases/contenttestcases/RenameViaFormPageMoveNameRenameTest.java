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
// Test Case Studio- Site Content ID:33
public class RenameViaFormPageMoveNameRenameTest extends StudioBaseTest {

	private String createFormFrameElementCss;
	private String createFormSaveAndCloseElement;
	private String randomURL;
	private String randomInternalName;
	private String dashboardLink;
	private String editRecentlyContentCreated;
	private String recentActivityContentURL;
	private String recentActivityContentName;
	private String fooContentXpath;
	private String editURLButton;
	private String warningTitle;
	private String warningOkButton;
	private String filenameInput;

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		dashboardLink = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.dashboard.dashboardlink");
		editRecentlyContentCreated = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.edit.option");
		recentActivityContentURL = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contenturl");
		recentActivityContentName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contentname");
		fooContentXpath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.foocontent");
		editURLButton = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("frame1.editurlbutton");
		warningTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame1.warning.warningtitle");
		warningOkButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame1.warning.okbutton");
		filenameInput = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("frame1.filenameinput");
		randomURL = "foo";
		randomInternalName = "foo";

	}

	public void setup(String siteId) {
		loginPage.loginToCrafter();
		homePage.goToPreviewPage(siteId);
		previewPage.clickSidebar();
		previewPage.createEntryContent(randomURL, randomInternalName, "title" + siteId, "body" + siteId)
				.goToDashboard();

		// check items on My Recent Activity widget
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL);

		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName)
						.getText().contains("foo"));
		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL)
						.getText().contains("/foo"));
	}

	public void step2() {
		dashboardPage.expandPagesTree();
		//dashboardPage.expandHomeTree();
	}

	public void step9() {
		// click on dashboard
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink).click();

		// check items on My Recent Activity widget
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitUntilDashboardLoadingAnimationIsNotDisplayedOnRecentActivity();
		this.getWebDriverManager().waitUntilDashboardWidgetsAreLoaded();
		
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL);

		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName)
						.getText().contains("foo"));
		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL)
						.getText().contains("/bar"));
	}

	public void step3() {
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fooContentXpath);
		this.getWebDriverManager().contextClick("xpath", fooContentXpath, false);
		getWebDriverManager().usingContextMenu(() -> {
			WebElement editOption = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
					editRecentlyContentCreated);
			editOption.click();
		}, "Pages");
	}

	@Parameters({"testId"})
	@Test()
	public void renameViaFormPageMoveNameRenameTest(String testId) {
		this.setup(testId);

		this.step2();

		this.step3();

		this.getWebDriverManager().waitForAnimation();
		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// check that the edit form was opened
			// step 4
			this.getWebDriverManager().waitForAnimation();
			Assert.assertTrue(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", ".//h1/span")
					.getText().equalsIgnoreCase("foo"));

			this.getWebDriverManager().waitForAnimation();
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", editURLButton).click();

			this.getWebDriverManager().waitForAnimation();
			this.getWebDriverManager().getDriver().switchTo().activeElement();
			Assert.assertTrue(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", warningTitle)
					.getText().equalsIgnoreCase("Warning"));

			// step 5
			this.getWebDriverManager().waitForAnimation();
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", warningOkButton).click();

			// step 6
			this.getWebDriverManager().waitForAnimation();
			this.getWebDriverManager().getDriver().switchTo().activeElement();
			this.getWebDriverManager().sendText("xpath", filenameInput, "bar");
			this.getWebDriverManager().waitForAnimation();

			// save and close
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormSaveAndCloseElement)
					.click();

		});

		// Step 8
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fooContentXpath).click();
		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(this.getWebDriverManager().getDriver().getCurrentUrl().contains("page=/bar"));

		// Step 9
		this.step9();
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
