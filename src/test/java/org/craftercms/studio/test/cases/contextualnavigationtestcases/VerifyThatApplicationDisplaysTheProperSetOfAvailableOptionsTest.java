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
import org.apache.commons.lang3.RandomStringUtils;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * @author luishernandez
 *
 */

public class VerifyThatApplicationDisplaysTheProperSetOfAvailableOptionsTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownXpath;
	private String homeXpath;
	private String siteDropdownListElementXPath;
	private String createFormFrameElementCss;
	private String createFormTitleElementXPath;
	private String editOption;
	private String deleteOption;
	private String historyOption;
	private String dependenciesOption;
	private String approveAndPublishOption;
	private String scheduleOption;
	private String styleLandingPage;
	private String duplicateOption;

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitedropdown");
		homeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.home");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormTitleElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformtitle");
		editOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.editcontenttopnavoption");
		deleteOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.deletecontenttopnavoption");
		historyOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.historycontenttopnavoption");
		dependenciesOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.dependenciescontenttopnavoption");
		approveAndPublishOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.approveandpublishtopnavoption");
		scheduleOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.scheduletopnavoption");
		styleLandingPage = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.pagestree.style.landingpage");
		duplicateOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.duplicatetopnavoption");
	}

	@Parameters({"testId"})
	@Test()
	public void verifyThatApplicationDisplaysTheProperSetOfAvailableOptionsTest(String testId) {

		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to closes
		getWebDriverManager().waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage(testId);

		// Show site content panel
		if (!(this.getWebDriverManager().waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
				.getAttribute("class").contains("site-dropdown-open")))
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownXpath)
					.click();

		// expand pages folder
		previewPage.expandPagesTree();

		getWebDriverManager().getDriver().navigate().refresh();

		// expand home content
		previewPage.expandHomeTree();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", homeXpath).click();

		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath",
						editOption)
				.isDisplayed());
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath",
						deleteOption)
				.isDisplayed());
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath",
						historyOption)
				.isDisplayed());
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath",
						dependenciesOption)
				.isDisplayed());

		this.editHome();

		this.getWebDriverManager().getDriver().navigate().refresh();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", homeXpath).click();

		Assert.assertTrue(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				approveAndPublishOption)
				.isDisplayed());

		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath",
						scheduleOption)
				.isDisplayed());

		this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", styleLandingPage)
				.click();
		this.getWebDriverManager().waitUntilContentTooltipIsHidden();
		this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", styleLandingPage)
				.click();

		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath",
						duplicateOption)
				.isDisplayed());
	}

	public void editHome() {
		this.getWebDriverManager().waitForAnimation();

		dashboardPage.clickHomeTree();

		dashboardPage.clickOnContextualNavigationEditOption();

		this.editSelectedContent();

	}

	public void editSelectedContent() {

		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// Typing new text on title text field
			getWebDriverManager().sendText("xpath", createFormTitleElementXPath,
					RandomStringUtils.randomAlphabetic(5).toLowerCase());

			// Save and close button.
			dashboardPage.clickSaveClose();
		});
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
