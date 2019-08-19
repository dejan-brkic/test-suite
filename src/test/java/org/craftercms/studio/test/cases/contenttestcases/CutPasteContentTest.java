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

public class CutPasteContentTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String adminConsoleXpath;
	private String entryContentTypeBodyXpath;
	private String entryContentTypeBodyCheckCss;
	private String studioLogo;
	private String createFormFrameElementCss;
	private String createFormSaveAndCloseElement;
	private String createFormMainTitleElementXPath;
	private String newFolderCreated;
	private String newFolderSpanXpath;
	private String testingItemURLXpath;
	private String siteDropdownListElementXPath;
	private String lastPropertiesElementCssSelector;
	private static Logger logger = LogManager.getLogger(CutPasteContentTest.class);

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String siteId, String blueprint) {
		apiTestHelper.createSite(siteId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		adminConsoleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.adminconsole");
		entryContentTypeBodyXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.entrycontenttype.body");
		entryContentTypeBodyCheckCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.entrycontenttype.bodyrequiredcheck");
		studioLogo = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.studiologo");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		createFormMainTitleElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.createformTitle");
		testingItemURLXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.cutpaste.pastedelement");
		newFolderCreated = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.add_new_folder");
		newFolderSpanXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitecontent.newfolder");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
		lastPropertiesElementCssSelector = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.entrycontenttype.propertiesdivlastelement");

	}

	@Parameters({"testId"})
	@Test()
	public void cutAndPasteContentUsingContextualClickOptionsTest(String testId) {

		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for loging page to close
		driverManager.waitUntilLoginCloses();

		// go to dashboard page
		homePage.goToDashboardPage(testId);

		// Show site content panel
		logger.debug("Click on Site Dropdown");
		if (!(this.driverManager.waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
				.getAttribute("class").contains("site-dropdown-open")))
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath).click();

		// go to admin console page
		logger.debug("Click on Admin Console");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", adminConsoleXpath).click();

		// select content types
		siteConfigPage.selectContentTypeOption();

		// open content types
		siteConfigPage.clickExistingTypeOption();

		// Confirm the content type selected
		siteConfigPage.confirmContentTypeSelected();

		// select main content
		logger.debug("Select Main content");
		this.driverManager.waitUntilSiteConfigMaskedModalCloses();
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", entryContentTypeBodyXpath).click();

		// Body not required
		logger.debug("Disable RTE for the selected content");
		this.driverManager.waitForAnimation();
		this.driverManager.focusAndScrollDownToBottomInASection("#properties-container",lastPropertiesElementCssSelector);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", entryContentTypeBodyCheckCss)
				.click();

		// save
		siteConfigPage.saveDragAndDropProcess();

		// Switch to the form
		driverManager.getDriver().navigate().refresh();
		driverManager.getDriver().switchTo().defaultContent();

		logger.debug("Return to preview page.");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", studioLogo).click();

		// expand pages folder
		dashboardPage.expandPagesTree();

		// right click to see the the menu

		dashboardPage.rightClickToSeeMenu();

		// Select Entry Content Type

		dashboardPage.clickEntryCT();

		// Confirm the Content Type selected
		dashboardPage.clickOKButton();

		// Switch to the iframe
		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {

			// Set basics fields of the new content created
			logger.info("Set the fields of the new content");
			dashboardPage.setBasicFieldsOfNewContent("Test1", "Testing1");

			// Set the title of main content

			this.driverManager.sendText("xpath", createFormMainTitleElementXPath, "MainTitle");

			// save and close
			logger.info("Click on Save and close button");
			this.driverManager

					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", createFormSaveAndCloseElement)
					.click();

		});

		// reload page
		driverManager.getDriver().navigate().refresh();

		// expand home content
		dashboardPage.expandHomeTree();

		// right click to see the menu
		dashboardPage.rightClickToFolderOnHome();

		// Set the name of the folder
		dashboardPage.setFolderName("addnewfolder");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newFolderCreated);

		// Right click and cut content.
		this.driverManager.waitForAnimation();
		dashboardPage.rightClickToCutOption();

		// Right click and paste content.

		dashboardPage.rightClickToPasteIntoFolder();

		// reload page
		driverManager.getDriver().navigate().refresh();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", newFolderSpanXpath).click();

		// Assert of the content copied
		this.driverManager.waitWhileElementIsDisplayedAndClickableByXpath(testingItemURLXpath);
		String contentCopied = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", testingItemURLXpath).getText();
		Assert.assertTrue(contentCopied.contains("Testing1"), "New folder does not contain the cut/paste content");

	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
