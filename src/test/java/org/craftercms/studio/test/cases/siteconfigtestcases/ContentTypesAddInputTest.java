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
package org.craftercms.studio.test.cases.siteconfigtestcases;

import org.openqa.selenium.WebElement;
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

public class ContentTypesAddInputTest extends StudioBaseTest{
	
	private String userName;
	private String password;
	private String controlsSectionFormSectionLocator;
	private String contentTypeContainerLocator;
	private String controlsSectionInputLocator;
	private String contentTypeContainerFormSectionContainerLocator;
	private String contentTypeContainerInputTitleLocator;
	private String siteDropdownXpath;
	private String adminConsoleXpath;
	private String siteDropdownListElementXPath;

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");

		this.controlsSectionFormSectionLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.controlssectionformsection");
		this.contentTypeContainerLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainer");
		this.controlsSectionInputLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.controlsinput");
		this.contentTypeContainerFormSectionContainerLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerformsectioncontainer");
		this.contentTypeContainerInputTitleLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerinputtitle");
		siteDropdownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitedropdown");
		adminConsoleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.adminconsole");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
	}


	public void dragAndDrop() {

		// Getting the Form Section control input for drag and drop action
		WebElement FromControlSectionFormSectionElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
						controlsSectionFormSectionLocator);

		// Getting the Content Type Container for drag and drop action
		// (destination)
		WebElement ToContentTypeContainer = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed( "xpath", contentTypeContainerLocator);

		getWebDriverManager().dragAndDropElement(FromControlSectionFormSectionElement, ToContentTypeContainer);

		WebElement FromRepeatingGroup = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed( "xpath", controlsSectionInputLocator);

		WebElement ToDefaultSection = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed(
				"xpath", contentTypeContainerFormSectionContainerLocator);

		siteConfigPage.getDriverManager().dragAndDropElement(FromRepeatingGroup, ToDefaultSection);

		// Complete the input fields basics
		siteConfigPage.completeControlFieldsBasics("TestTitle", "TestICEGroup", "TestDescription", "TestDefault");

		// Save the data
		siteConfigPage.saveDragAndDropProcess(true);
	}
	@Parameters({"testId"})
	@Test()
	public void verifyThatStudioAllowsToAddAnInputControlToExistingContentTypeTest(String testId) {

		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to closes
		getWebDriverManager().waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage(testId);

		// Show site content panel
		if (!(this.getWebDriverManager().waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
				.getAttribute("class").contains("site-dropdown-open")))
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed( "xpath",
				siteDropdownXpath).click();

		this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed( "xpath", adminConsoleXpath)
				.click();

		// Select the content type to the test
		siteConfigPage.selectEntryContentTypeFromAdminConsole();

		// drag and drop
		this.dragAndDrop();

		// open content types
		siteConfigPage.clickExistingTypeOption();

		// Confirm the content type selected
		siteConfigPage.confirmContentTypeSelected();

		// Click on input section to can view the properties
		getWebDriverManager().waitUntilPopupIsHidden();
		siteConfigPage.clickInputSection();

		// Asserts that fields are not empty.
		String titleText = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed( "xpath",
				contentTypeContainerInputTitleLocator).getText();

		Assert.assertTrue(titleText.contains("TestTitle"));
		siteConfigPage.cancelChangesOnContentType();

	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
