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
package org.craftercms.studio.test.cases.dashboardtestcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class RecentActivityFilterShowTest extends StudioBaseTest {

	private static final Logger logger = LogManager.getLogger(RecentActivityFilterShowTest.class);

	private String userName;
	private String password;

	private String createFormFrameElementCss;
	private String createFormMainTitleElementXPath;
	private String createFormExpandAll;
	private String createFormSaveAndCloseElement;
	private String homeElementXPath;
	private String myRecentActivityShowInputXPath;
	private String myRecentActivityFirstItemURLXPath;
	private String myRecentActivitySecondItemURLXPath;

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		createFormExpandAll = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformexpandall");
		createFormMainTitleElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.createformMainTitle");
		homeElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.home");
		myRecentActivityShowInputXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.myrecentactivity.showinput");
		myRecentActivityFirstItemURLXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.myrecentactivity.firstelementurl");
		myRecentActivitySecondItemURLXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.myrecentactivity.secondelementurl");

	}

	public void changeBodyToNotRequiredOnEntryContent() {

		previewPage.changeBodyOfEntryContentPageToNotRequired();

	}

	public void createContent() {
		logger.info("Creating first content");

		getWebDriverManager().waitUntilPageLoad();
		getWebDriverManager().waitUntilSidebarOpens();
		// right click to see the the menu
		dashboardPage.rightClickToSeeMenu();

		// Select Entry Content Type
		dashboardPage.clickEntryCT();

		// Confirm the Content Type selected
		dashboardPage.clickOKButton();

		// Switch to the iframe
		getWebDriverManager().usingCrafterForm("cssselector", createFormFrameElementCss, () -> {
			// Set basics fields of the new content created
			dashboardPage.setBasicFieldsOfNewContent("AboutUs", "AboutUs");

			// Set the title of main content
			getWebDriverManager().sendText("xpath", createFormMainTitleElementXPath, "MainTitle");

			// click necessary to validate all fields required
			this.getWebDriverManager().scrollUp();
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormExpandAll).click();

			// save and close
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormSaveAndCloseElement)
					.click();
		});
	}

	public void createSecondContent() {
		logger.info("Creating second content");

		getWebDriverManager().waitUntilPageLoad();
		getWebDriverManager().waitUntilSidebarOpens();
		// right click to see the the menu
		dashboardPage.rightClickToSeeMenu();

		// Select Entry Content Type
		dashboardPage.clickEntryCT();

		// Confirm the Content Type selected

		dashboardPage.clickOKButton();

		// Switch to the iframe
		getWebDriverManager().usingCrafterForm("cssselector", createFormFrameElementCss, () -> {
			// Set basics fields of the new content created
			dashboardPage.setBasicFieldsOfNewContent("AboutUs1", "AboutUs1");

			// Set the title of main content
			getWebDriverManager().sendText("xpath", createFormMainTitleElementXPath, "MainTitle");

			// click necessary to validate all fields required
			this.getWebDriverManager().scrollUp();
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormExpandAll).click();

			// save and close

			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormSaveAndCloseElement)
					.click();
		});
	}

	public void filtersAndAsserts() {

		// clean filter
		this.getWebDriverManager().clearInputUsingDeleteKeys("xpath", myRecentActivityShowInputXPath);

		// Show only 1 item edited
		getWebDriverManager().sendText("xpath", myRecentActivityShowInputXPath, "1", false);
		getWebDriverManager().waitUntilElementIsDisplayed("xpath", myRecentActivityShowInputXPath).sendKeys(Keys.ENTER);
		this.getWebDriverManager().waitForAnimation();
		getWebDriverManager().waitUntilElementIsDisplayed("xpath", myRecentActivityFirstItemURLXPath);

		// Assert filter 1
		this.getWebDriverManager().waitForAnimation();
		String edit1 = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", myRecentActivityFirstItemURLXPath).getText();

		Assert.assertEquals(edit1, "/aboutus1");

		// clean filter
		this.getWebDriverManager().clearInputUsingDeleteKeys("xpath", myRecentActivityShowInputXPath);

		// Show only 2 item edited
		getWebDriverManager().sendText("xpath", myRecentActivityShowInputXPath, "2", false);
		getWebDriverManager().waitUntilElementIsDisplayed("xpath", myRecentActivityShowInputXPath).sendKeys(Keys.ENTER);
		this.getWebDriverManager().waitForAnimation();
		getWebDriverManager().waitUntilElementIsDisplayed("xpath", myRecentActivitySecondItemURLXPath);

		// Assert filter 2
		this.getWebDriverManager().waitForAnimation();
		String edit2 = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", myRecentActivitySecondItemURLXPath).getText();
		Assert.assertEquals(edit2, "/aboutus");
	}

	@Parameters({"testId"})
	@Test()
	public void verifyThatTheShowInputWorksProperlyOnRecentActivityWidgetTest(String testId) {
		logger.info("Starting test case");

		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage(testId);

		// body not requiered
		changeBodyToNotRequiredOnEntryContent();

		dashboardPage.expandPagesTree();

		// create content
		createContent();

		// expand home
		dashboardPage.expandHomeTree();

		// reload page
		getWebDriverManager().getDriver().navigate().refresh();

		// create a content with level descriptor content type
		// create another content to use a filter
		this.getWebDriverManager().isElementPresentAndClickableByXpath(homeElementXPath);
		createSecondContent();

		// reload page
		getWebDriverManager().getDriver().navigate().refresh();

		// filters and asserts
		this.filtersAndAsserts();

	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
