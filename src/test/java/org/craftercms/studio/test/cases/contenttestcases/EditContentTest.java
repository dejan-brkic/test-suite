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

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class EditContentTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String createFormFrameElementCss;
	private String createFormMainTitleElementXPath;
	private String createFormSaveAndCloseElement;
	private String myRecentActivityTestingItem;
	private String randomURL;
	private String randomInternalName;

	@BeforeMethod
	public void beforeTest() {

		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormMainTitleElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.createformTitle");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		myRecentActivityTestingItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.testingcontentitemedited.myrecentactivity");

		randomURL = "Test1";
		randomInternalName = "Testing1";
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

		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// creating random values for URL field and InternalName field

			// Set basics fields of the new content created
			dashboardPage.setBasicFieldsOfNewContent(randomURL, randomInternalName);

			// Set the title of main content
			driverManager.sendText("xpath", createFormMainTitleElementXPath, "MainTitle");

			// save and close

			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormSaveAndCloseElement)
					.click();
		});

		this.driverManager.waitUntilSidebarOpens();
	}

	public void editingContentRecentlyCreated() {

		dashboardPage.rightClickToSelectEditOption();

		this.driverManager.waitForAnimation();

		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// edit internal name
			dashboardPage.editInternalName("Edited");
		});

	}

	@Test(priority = 0)
	public void verifyTheEditionOfAPageUsingRightClickEditOptionTest() {

		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to closes
		driverManager.waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage();

		// body not required
		this.changeBodyToNotRequiredOnEntryContent();

		// expand pages folder
		dashboardPage.expandPagesTree();

		// create new content
		createNewContent();

		// Expand Home Tree
		dashboardPage.expandHomeTree();

		// Edited content recently created
		driverManager.getDriver().navigate().refresh();

		editingContentRecentlyCreated();

		Assert.assertNotNull(driverManager.waitUntilElementIsDisplayed("xpath", myRecentActivityTestingItem),
				"Content page is not displayed on the My Recent Activity Widget");
	}

}
