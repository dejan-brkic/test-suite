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

import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Action;

import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;

public class EditContentThroughDashboardEditOptionTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String controlsSectionFromSection;
	private String contentFormName;
	private String controlsSectionInputLocator;
	private String contentTypeContainerFormSectionContainerLocator;
	private String adminConsoleXpath;
	private String entryContentTypeBodyXpath;
	private String entryContentTypeBodyCheckCss;
	private String createFormFrameElementCss;
	private String createFormSaveAndCloseElement;
	private String createFormTitle;
	private String siteDropDownXpath;
	private String crafterLogoId;
	private String createFormMainTitleElementXPath;
	private String lastPropertiesElementCssSelector;
	private String siteDropdownListElementXPath;

	private static Logger logger = LogManager.getLogger(EditContentThroughDashboardEditOptionTest.class);

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		controlsSectionFromSection = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.controlssection");
		contentFormName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contentformname");
		controlsSectionInputLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.controlsinput");
		contentTypeContainerFormSectionContainerLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerformsectioncontainer");
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
		createFormTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.createformfiletitle");
		siteDropDownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitedropdown");
		crafterLogoId = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.studiologo");
		createFormMainTitleElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.createformTitle");
		lastPropertiesElementCssSelector = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.entrycontenttype.propertiesdivlastelement");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
	}

	public void dragAndDrop() {

		// go to admin console page
		previewPage.goToAdminConsolePage();

		// select content types
		siteConfigPage.selectContentTypeOption();

		// open content types
		siteConfigPage.clickExistingTypeOption();

		// Confirm the content type selected
		siteConfigPage.confirmContentTypeSelected();

		// Drag and drop Form Section
		WebElement From = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				controlsSectionFromSection);

		WebElement To = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", contentFormName);

		Actions builder = new Actions(getWebDriverManager().getDriver());

		Action dragAndDrop = builder.clickAndHold(From)

				.moveToElement(To)

				.release(To)

				.build();

		dragAndDrop.perform();

		WebElement FromInput = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				controlsSectionInputLocator);

		WebElement ToDefaultSection = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				contentTypeContainerFormSectionContainerLocator);

		Action dragAndDropInput = builder.clickAndHold(FromInput)

				.moveToElement(ToDefaultSection)

				.release(ToDefaultSection)

				.build();

		dragAndDropInput.perform();

		// Complete the input fields basics

		siteConfigPage.completeControlFieldsBasics("TestTitle", "TestICEGroup", "TestDescription", "TestDefaultValue");

		siteConfigPage.saveDragAndDropProcess();

	}

	public void bodyNotRequiered() {
		// go to admin console page
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", adminConsoleXpath).click();

		// select content types
		siteConfigPage.selectContentTypeOption();

		// open content types
		siteConfigPage.clickExistingTypeOption();

		// Confirm the content type selected

		siteConfigPage.confirmContentTypeSelected();

		// wait for element is clickeable
		getWebDriverManager().getDriver().switchTo().defaultContent();

		// select main content
		this.getWebDriverManager().waitUntilSiteConfigMaskedModalCloses();
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", entryContentTypeBodyXpath).click();

		// Mark Body not required
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().focusAndScrollDownToBottomInASection("#properties-container",lastPropertiesElementCssSelector);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", entryContentTypeBodyCheckCss).click();
		// save
		siteConfigPage.saveDragAndDropProcess();
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
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", createFormSaveAndCloseElement)
					.click();

		});

	}

	public void editFormCreated() {

		// Click on edit option of recent activity section

		dashboardPage.clickEditOptionOfRecentActivitySection();

		this.getWebDriverManager().waitForAnimation();

		// Switch to the iframe
		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {

			// Expand default section
			myRecentActivityFramePage1.expandDefaultSection();

			// Clealing title text field

			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormTitle).clear();

			// Typing new text on title text field

			this.getWebDriverManager().sendText("xpath", createFormTitle, "TestQA");

			// save and close
			logger.info("Click on Save and close button");

			this.getWebDriverManager()
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", createFormSaveAndCloseElement)
					.click();

		});

		// Switch back to the dashboard page
		getWebDriverManager().getDriver().switchTo().defaultContent();

	}

	public void clickOnEditOptionOfRecentActivitySection() {

		// Click on edit option of recent activity section

		dashboardPage.clickEditOptionOfRecentActivitySection();

		this.getWebDriverManager().waitForAnimation();
		// Switch to the iframe

		// Switch to the iframe
		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {

			// Expand default section
			myRecentActivityFramePage1.expandDefaultSection();
			// Assert validation

			String textTitle = this.getWebDriverManager()

					.driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormTitle)

					.getAttribute("value");

			Assert.assertEquals(textTitle, "TestQA", "Content is not edited properly");

			// save and close
			logger.info("Click on Save and close button");

			this.getWebDriverManager()
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", createFormSaveAndCloseElement)
					.click();

		});
	}

	public void goToDashboard() {
		// Go to dashboard page
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", crafterLogoId).click();
	}

	@Parameters({"testId"})
	@Test()
	public void verifyTheEditionOfAPageUsingEditLinkOfRecentActivityTest(String testId) {

		// login to application

		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage(testId);

		// Show site content panel
		if (!(this.getWebDriverManager().waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
				.getAttribute("class").contains("site-dropdown-open")))
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropDownXpath).click();

		this.getWebDriverManager().waitUntilSidebarOpens();

		// Select the content type and drag and drop
		dragAndDrop();

		getWebDriverManager().getDriver().switchTo().defaultContent();
		this.getWebDriverManager().getDriver().navigate().refresh();
		this.getWebDriverManager().waitForAnimation();
		
		goToDashboard();

		bodyNotRequiered();

		getWebDriverManager().getDriver().switchTo().defaultContent();
		this.getWebDriverManager().getDriver().navigate().refresh();
		this.getWebDriverManager().waitForAnimation();
		// go to dashboard
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", crafterLogoId).click();

		dashboardPage.expandPagesTree();

		this.getWebDriverManager().waitUntilPageLoad();
		this.getWebDriverManager().waitUntilSidebarOpens();

		// create a new content
		createNewContent();

		getWebDriverManager().getDriver().navigate().refresh();

		// edit the form created
		editFormCreated();

		// reload page

		getWebDriverManager().getDriver().navigate().refresh();

		// click on edit option of recently activity section

		clickOnEditOptionOfRecentActivitySection();

	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
