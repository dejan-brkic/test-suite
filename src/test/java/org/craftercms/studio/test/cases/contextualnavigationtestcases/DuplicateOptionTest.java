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

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class DuplicateOptionTest extends StudioBaseTest {

	private String userName;
	private String password;

	private String createFormFrameElementCss;
	private String createFormSaveAndCloseElementId;
	private String createFormMainTitleElementXPath;
	private String studioLogo;
	private String testItemXpath;
	private String duplicateButtonXpath;

	private String copyTestItemXpath;
	private static Logger logger = LogManager.getLogger(DuplicateOptionTest.class);

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");

		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormSaveAndCloseElementId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		createFormMainTitleElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.createformTitle");
		studioLogo = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.studiologo");
		testItemXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.testingcontentitem");
		duplicateButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.duplicatedialog.duplicate");
		copyTestItemXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitecontent.copytestitem");
	}

	public void changeBodyToNotRequiredOnEntryContent() {

		previewPage.changeBodyOfEntryContentPageToNotRequired();

	}

	public void createNewContent() {

		// right click to see the the menu
		dashboardPage.rightClickToSeeMenu();

		// Select Entry Content Type
		dashboardPage.clickEntryCT();

		// Confirm the Content Type selected
		dashboardPage.clickOKButton();

		// Switch to the iframe
		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {

			// Set basics fields of the new content created
			logger.info("Set the fields of the new content");
			dashboardPage.setBasicFieldsOfNewContent("Test1", "Testing1");

			// Set the title of main content

			this.getWebDriverManager().sendText("xpath", createFormMainTitleElementXPath, "MainTitle");

			// save and close
			logger.info("Click on Save and close button");
			this.getWebDriverManager()
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", createFormSaveAndCloseElementId)
					.click();

		});

	}

	public void duplicateContentCreated() {
		dashboardPage.clickOnDuplicateOption();

		// click on duplicate in the popup
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", duplicateButtonXpath)
				.click();

		this.getWebDriverManager().waitForAnimation();

		// Switch to the iframe
		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// edit internal name
			dashboardPage.editInternalName("COPY");
		});

		// Switch back to the dashboard page
		getWebDriverManager().getDriver().switchTo().defaultContent();

		// reload page
		getWebDriverManager().getDriver().navigate().refresh();
	}

	@Parameters({"testId"})
	@Test()
	public void duplicatePageUsingContextualNavigationDuplicateOptionOption(String testId) {

		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to closes
		getWebDriverManager().waitUntilLoginCloses();

		// goto preview page
		homePage.goToPreviewPage(testId);

		// select the content type to the test
		changeBodyToNotRequiredOnEntryContent();

		// expand pages folder
		dashboardPage.expandPagesTree();

		// expand home content
		this.getWebDriverManager().waitUntilPageLoad();
		this.getWebDriverManager().waitUntilSidebarOpens();
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.expandHomeTree();

		// create a new content
		createNewContent();

		// reload page
		getWebDriverManager().getDriver().navigate().refresh();

		// Select the content to duplicate.
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", testItemXpath).click();

		// Duplicate content created
		duplicateContentCreated();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", studioLogo).click();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", copyTestItemXpath).click();
		Assert.assertTrue(getWebDriverManager().isElementPresentByXpath(copyTestItemXpath),
				"Duplicated Option is not displayed");

	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
