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

public class CopyPasteIntoFolderTest extends StudioBaseTest {

	private static final Logger logger = LogManager.getLogger(CopyPasteIntoFolderTest.class);

	private String userName;
	private String password;
	private String createFormFrameElementCss;
	private String createFormSaveAndCloseElement;
	private String createFormMainTitleElementXPath;
	private String secondCopiedElementXPath;
	private String firstCopiedElementXPath;
	private String myRecentActivityItemsCounterXpath;

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String siteId, String blueprint) {
		apiTestHelper.createSite(siteId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		createFormMainTitleElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.createformTitle");
		firstCopiedElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.myrecentactivity.firstelementurl");
		secondCopiedElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.myrecentactivity.secondelementurl");
		myRecentActivityItemsCounterXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.myrecentactivity.itemscounter");

	}

	public void changeBodyToNotRequiredOnEntryContent() {
		logger.info("Changing body to not required");
		previewPage.changeBodyOfEntryContentPageToNotRequired();

	}

	public void createContent() {
		logger.info("Creating new content");
		// right click to see the the menu
		getWebDriverManager().waitUntilPageLoad();
		dashboardPage.rightClickToSeeMenu();

		// Select Entry Content Type
		dashboardPage.clickEntryCT();

		// Confirm the Content Type selected
		dashboardPage.clickOKButton();

		// Switch to the iframe
		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// Set basics fields of the new content created
			dashboardPage.setBasicFieldsOfNewContent("Test1", "Testing1");

			// Set the title of main content
			getWebDriverManager().sendText("xpath", createFormMainTitleElementXPath, "MainTitle");

			// save and close
			this.getWebDriverManager().waitUntilElementIsClickable("xpath", createFormSaveAndCloseElement).click();

		});

	}

	@Parameters({"testId"})
	@Test()
	public void copyAndPasteIntoFolderUsingContextualClickOptionsTest(String testId) {
		logger.info("Starting test case");
		loginPage.loginToCrafter(userName, password);

		getWebDriverManager().waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage(testId);

		this.changeBodyToNotRequiredOnEntryContent();

		// expand pages folder
		dashboardPage.expandPagesTree();

		this.createContent();

		// reload page
		getWebDriverManager().getDriver().navigate().refresh();

		// Expand Home Tree
		dashboardPage.expandHomeTree();

		// right click to see the the menu
		logger.info("Creating new folder");
		dashboardPage.rightClickNewFolderOnHome();

		// Set the name of the folder
		dashboardPage.setFolderName("foldertocopy");

		// reload page
		getWebDriverManager().getDriver().navigate().refresh();

		// Expand Home Tree
		dashboardPage.rightClickToCopyComponentToNewFolder();

		// paste the crafter component in the new folder created
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.rightClickToPasteToNewFolder();

		// Copy the new content to the new folder created
		getWebDriverManager().getDriver().navigate().refresh();
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitForFullExpansionOfTree();
		dashboardPage.rightClickToCopyNewContentToNewFolder();

		// paste the content in the new folder created
		this.getWebDriverManager().waitForFullExpansionOfTree();
		dashboardPage.rightClickToPasteToNewFolder();
		
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitUntilPageLoad();
		
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitUntilTextIs("xpath", myRecentActivityItemsCounterXpath, "3");
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", firstCopiedElementXPath);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", secondCopiedElementXPath);
		
		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", firstCopiedElementXPath).getText()
				.contains("/foldertocopy/test1-"));
		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", secondCopiedElementXPath).getText()
				.equalsIgnoreCase("/foldertocopy/test1"));

	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
