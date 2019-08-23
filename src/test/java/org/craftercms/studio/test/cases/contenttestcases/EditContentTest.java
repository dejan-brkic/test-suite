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

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
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

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
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

		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// creating random values for URL field and InternalName field

			// Set basics fields of the new content created
			dashboardPage.setBasicFieldsOfNewContent(randomURL, randomInternalName);

			// Set the title of main content
			getWebDriverManager().sendText("xpath", createFormMainTitleElementXPath, "MainTitle");

			// save and close

			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormSaveAndCloseElement)
					.click();
		});

		this.getWebDriverManager().waitUntilSidebarOpens();
	}

	public void editingContentRecentlyCreated() {

		dashboardPage.rightClickToSelectEditOption();

		this.getWebDriverManager().waitForAnimation();

		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// edit internal name
			dashboardPage.editInternalName("Edited");
		});

	}

	@Parameters({"testId"})
	@Test()
	public void verifyTheEditionOfAPageUsingRightClickEditOptionTest(String testId) {

		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to closes
		getWebDriverManager().waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage(testId);

		// body not required
		this.changeBodyToNotRequiredOnEntryContent();

		// expand pages folder
		dashboardPage.expandPagesTree();

		// create new content
		createNewContent();

		// Expand Home Tree
		dashboardPage.expandHomeTree();

		// Edited content recently created
		getWebDriverManager().getDriver().navigate().refresh();

		editingContentRecentlyCreated();

		Assert.assertNotNull(getWebDriverManager().waitUntilElementIsDisplayed("xpath", myRecentActivityTestingItem),
				"Content page is not displayed on the My Recent Activity Widget");
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
