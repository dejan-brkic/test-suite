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
import org.testng.annotations.BeforeMethod;
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

	@BeforeMethod
	public void beforeTest() {
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
		driverManager.waitUntilPageLoad();
		dashboardPage.rightClickToSeeMenu();

		// Select Entry Content Type
		dashboardPage.clickEntryCT();

		// Confirm the Content Type selected
		dashboardPage.clickOKButton();

		// Switch to the iframe
		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// Set basics fields of the new content created
			dashboardPage.setBasicFieldsOfNewContent("Test1", "Testing1");

			// Set the title of main content
			driverManager.sendText("xpath", createFormMainTitleElementXPath, "MainTitle");

			// save and close
			this.driverManager.waitUntilElementIsClickable("xpath", createFormSaveAndCloseElement).click();

		});

	}

	@Test(priority = 0)
	public void copyAndPasteIntoFolderUsingContextualClickOptionsTest() {
		logger.info("Starting test case");
		loginPage.loginToCrafter(userName, password);

		driverManager.waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage();

		this.changeBodyToNotRequiredOnEntryContent();

		// expand pages folder
		dashboardPage.expandPagesTree();

		this.createContent();

		// reload page
		driverManager.getDriver().navigate().refresh();

		// Expand Home Tree
		dashboardPage.expandHomeTree();

		// right click to see the the menu
		logger.info("Creating new folder");
		dashboardPage.rightClickNewFolderOnHome();

		// Set the name of the folder
		dashboardPage.setFolderName("foldertocopy");

		// reload page
		driverManager.getDriver().navigate().refresh(); 

		// Expand Home Tree
		dashboardPage.rightClickToCopyComponentToNewFolder();

		// paste the crafter component in the new folder created
		this.driverManager.waitForAnimation();
		dashboardPage.rightClickToPasteToNewFolder();

		// Copy the new content to the new folder created
		driverManager.getDriver().navigate().refresh();
		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();
		dashboardPage.rightClickToCopyNewContentToNewFolder();

		// paste the content in the new folder created
		this.driverManager.waitForFullExpansionOfTree();
		dashboardPage.rightClickToPasteToNewFolder();
		
		this.driverManager.waitForAnimation();
		this.driverManager.waitUntilPageLoad();
		
		this.driverManager.waitForAnimation();
		this.driverManager.waitUntilTextIs("xpath", myRecentActivityItemsCounterXpath, "3");
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", firstCopiedElementXPath);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", secondCopiedElementXPath);
		
		this.driverManager.waitForAnimation();
		Assert.assertTrue(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", firstCopiedElementXPath).getText()
				.contains("/foldertocopy/test1-"));
		this.driverManager.waitForAnimation();
		Assert.assertTrue(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", secondCopiedElementXPath).getText()
				.equalsIgnoreCase("/foldertocopy/test1"));

	}

}
