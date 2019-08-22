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
package org.craftercms.studio.test.cases.contextualnavigationtestcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author luishernandez
 *
 */

public class VerifyThatApplicationDisplaysTheInContextEditIconProperlyTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String homeXpath;
	private String inContextEditOption;
	private String expandPagesTree;
	private String siteDropdownElementXPath;
	private String createSiteButton;
	private String userOptions;
	private String userOptionsLogout;
	private String inContextEditEnabledOnHomeTitle;
	private String inContextEditEnabledOnMainTitle;
	private static Logger logger = LogManager
			.getLogger(VerifyThatApplicationDisplaysTheInContextEditIconProperlyTest.class);

	@Parameters({"testId", "blueprint", "testUser", "testGroup"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint, String testUser, String testGroup) {
		apiTestHelper.createSite(testId, "", blueprint);
		apiTestHelper.createUserAddToGroup(testUser, testGroup);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		homeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.home");
		expandPagesTree = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.expand_Pages_Tree");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownmenuinnerxpath");
		createSiteButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsitebutton");
		userOptions = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.user_options");
		userOptionsLogout = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.user_options_logout");
		inContextEditEnabledOnHomeTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.incontextedit.enabledonhometitle");
		inContextEditEnabledOnMainTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.incontextedit.enabledonmaintitle");
		inContextEditOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.incontextedittopnavoption");
	}

	@Parameters({"testId", "testUser"})
	@Test()
	public void verifyThatApplicationDisplaysTheInContextEditIconProperlyTest(String testId, String testUser) {

		this.setup(testId);

		// expand home content
		previewPage.expandHomeTree();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", homeXpath).click();

		// click on history option
		previewPage.clickOnInContextEditOption();
		this.getWebDriverManager().waitForAnimation();

		this.getWebDriverManager().getDriver().switchTo().defaultContent();
		this.getWebDriverManager().getDriver().switchTo()
				.frame(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("id", "engineWindow"));
		// Assertions
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", inContextEditEnabledOnHomeTitle)
				.isDisplayed());
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", inContextEditEnabledOnMainTitle)
				.isDisplayed());

		this.getWebDriverManager().getDriver().switchTo().defaultContent();

		// logout from Crafter
		logger.info("logout from Crafter");
		this.logoutFromCrafter();

		// login to application with reviewer user
		logger.info("login to application with {} user", testUser);
		loginPage.loginToCrafter(testUser, testUser);

		getWebDriverManager().waitUntilLoginCloses();

		logger.info("Go to Preview Page");
		this.homePage.goToPreviewPage(testId);

		// Expand the site bar
		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(this.getWebDriverManager().isElementPresentAndClickableByXpath(siteDropdownElementXPath));
		this.getWebDriverManager().waitForAnimation();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", homeXpath).click();
		this.getWebDriverManager().waitForAnimation();

		// Assertions
		Assert.assertFalse(this.getWebDriverManager().isElementPresentAndClickableByXpath(inContextEditOption));

		this.getWebDriverManager().getDriver().switchTo().defaultContent();
		this.getWebDriverManager().getDriver().switchTo()
				.frame(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("id", "engineWindow"));

		Assert.assertFalse(
				this.getWebDriverManager().isElementPresentAndClickableByXpath(inContextEditEnabledOnHomeTitle));
		Assert.assertFalse(
				this.getWebDriverManager().isElementPresentAndClickableByXpath(inContextEditEnabledOnMainTitle));

		this.getWebDriverManager().getDriver().switchTo().defaultContent();
	}

	public void setup(String testId) {
		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to closes
		getWebDriverManager().waitUntilLoginCloses();

		logger.info("Go to Site Preview");
		this.goToSiteContentPagesStructure(testId);

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", expandPagesTree);

		this.getWebDriverManager().waitUntilSidebarOpens();
	}

	private void goToSiteContentPagesStructure(String testId) {

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", createSiteButton);

		this.getWebDriverManager().waitForAnimation();
		homePage.goToPreviewPage(testId);

		this.getWebDriverManager().waitForAnimation();
		getWebDriverManager().clickElement("xpath", siteDropdownElementXPath);
	}

	private void logoutFromCrafter() {

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", userOptions);

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", userOptions).click();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				userOptionsLogout);

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", userOptionsLogout)
				.click();

	}

	@Parameters({"testId", "testUser"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId, String testUser) {
		apiTestHelper.deleteSite(testId);
		apiTestHelper.deleteUser(testUser);
	}
}
