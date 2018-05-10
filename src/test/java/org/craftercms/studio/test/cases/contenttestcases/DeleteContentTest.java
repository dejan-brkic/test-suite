/*
 * Copyright (C) 2007-2018 Crafter Software Corporation. All Rights Reserved.
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
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class DeleteContentTest extends StudioBaseTest {

	private static final Logger logger = LogManager.getLogger(DeleteContentTest.class);

	private String userName;
	private String password;
	private String createFormFrameElementCss;
	private String createFormMainTitleElementXPath;
	private String createFormSaveAndCloseElement;
	private String testingItemURLXpath;

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
		testingItemURLXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.myrecentactivity.firstelementurl");
	}

	public void changeBodyToNotRequiredOnEntryContent() {

		previewPage.changeBodyOfEntryContentPageToNotRequired();

	}

	public void createContent() {
		logger.info("Creating new content");
		// right click to see the the menu
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

			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormSaveAndCloseElement).click();
		});

	}

	@Test(priority = 0)

	public void deletePageUsingContextualClickDeleteOptionTest() {
		logger.info("Starting test case");
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to closes
		driverManager.waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage();

		// body not required
		this.changeBodyToNotRequiredOnEntryContent();

		driverManager.waitUntilSidebarOpens();

		// expand pages folder
		dashboardPage.expandPagesTree();

		// create content

		createContent();

		driverManager.waitUntilSidebarOpens();

		// Expand Home Tree
		dashboardPage.expandHomeTree();

		// right click to delete

		dashboardPage.rightClickToDeleteContent();

		// confirmation

		dashboardPage.clicktoDeleteContent();

		// submittal complete ok
		dashboardPage.clickOKSubmittalComplete();

		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();
		String contentDeleted = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", testingItemURLXpath).getText();
		Assert.assertEquals(contentDeleted, "/test1");

	}

}
