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

public class EditOptionTest extends StudioBaseTest {

	private static final Logger logger = LogManager.getLogger(EditOptionTest.class);

	private String userName;
	private String password;
	private String adminConsoleXpath;
	private String entryContentTypeBodyXpath;
	private String entryContentTypeBodyCheckCss;
	private String createFormFrameElementCss;
	private String createFormSaveAndCloseElement;
	private String createFormExpandAll;
	private String createFormMainTitleElementXPath;
	private String testingContentItem;
	private String topNavEditOption;
	private String siteDropDownXpath;
	private String crafterLogoId;
	private String testingItemEditedXpath;
	private String siteDropdownListElementXPath;
	private String lastPropertiesElementCssSelector;

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		adminConsoleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.adminconsole");
		entryContentTypeBodyXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.entrycontenttype.body");
		entryContentTypeBodyCheckCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.entrycontenttype.bodyrequiredcheck");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		createFormExpandAll= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformexpandall");
		createFormMainTitleElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.createformMainTitle");
		testingContentItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.testingcontentitem");
		topNavEditOption= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.edittopnavoption");
		siteDropDownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitedropdown");
		crafterLogoId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.studiologo");
		testingItemEditedXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.testingcontentitemedited.sitecontent");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
		lastPropertiesElementCssSelector = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.entrycontenttype.propertiesdivlastelement");
	}

	public void bodyNotRequiered() {
		logger.info("Changing body to not required");
		// go to admin console page
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed( "xpath", adminConsoleXpath).click();

		// select content types
		siteConfigPage.selectContentTypeOption();

		// open content types

		siteConfigPage.clickExistingTypeOption();

		// Confirm the content type selected

		siteConfigPage.confirmContentTypeSelected();

		// select main content
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed( "xpath", entryContentTypeBodyXpath).click();


		// Body not required
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().focusAndScrollDownToBottomInASection("#properties-container",lastPropertiesElementCssSelector);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed( "xpath",
				entryContentTypeBodyCheckCss).click();

		// save
		siteConfigPage.saveDragAndDropProcess();


	}

	public void createNewContent() {
		logger.info("Creating new content");
		// right click to see the the menu
		dashboardPage.rightClickToSeeMenu();

		// Select Entry Content Type

		dashboardPage.clickEntryCT();

		// Confirm the Content Type selected

		dashboardPage.clickOKButton();

		// Switch to the iframe
		getWebDriverManager().usingCrafterForm("cssselector", createFormFrameElementCss, () -> {
			// Set basics fields of the new content created
			dashboardPage.setBasicFieldsOfNewContent("Test1", "Testing1");

			// Expand all fields
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed( "xpath", createFormExpandAll)
				.click();

			// Set the title of main content
			getWebDriverManager().sendText("xpath", createFormMainTitleElementXPath, "MainTitle");

			// save and close
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed( "xpath", createFormSaveAndCloseElement).click();

		});
	}

	public void editingContent() {
		logger.info("Editing existing content");
		this.getWebDriverManager().clickElement( "xpath", testingContentItem);
		// click edit option of the menu
		this.getWebDriverManager().clickElement("xpath",topNavEditOption);

		this.getWebDriverManager().waitForAnimation();

		// Switch to the iframe
		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// edit internal title
			dashboardPage.editInternalName("EDITED");
		});
	}

	@Parameters({"testId"})
	@Test()
	public void verifyTheEditionOfAPageUsingContextualNavigationEditOptionTest(String testId) {
		logger.info("Starting test case");
		// login to application

		loginPage.loginToCrafter(userName, password);

		//Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage(testId);

		// Show site content panel
		getWebDriverManager().clickElement("xpath", siteDropDownXpath);
		// Body Not requiered
		bodyNotRequiered();

		// go to dashboard
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", crafterLogoId).click();

		dashboardPage.expandPagesTree();

		// create a new content
		createNewContent();

		//reload page
        getWebDriverManager().getDriver().navigate().refresh();

		dashboardPage.expandHomeTree();

		// wait for element is clickeable
		editingContent();

		// reload page
		getWebDriverManager().getDriver().navigate().refresh();

		// Assert find the new content created edited
		getWebDriverManager().waitUntilElementIsDisplayed("xpath", testingItemEditedXpath);
		 Assert.assertTrue(this.getWebDriverManager().isElementPresentByXpath(testingItemEditedXpath));

	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
