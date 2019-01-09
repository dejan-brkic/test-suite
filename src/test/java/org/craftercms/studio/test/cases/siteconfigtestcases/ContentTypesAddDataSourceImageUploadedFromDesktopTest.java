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

import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author luishernandez
 *
 */

public class ContentTypesAddDataSourceImageUploadedFromDesktopTest extends StudioBaseTest {
	
	private String userName;
	private String password;
	private String contentTypeContainerLocator;
	private String dataSourceSectionImageUploadedFromDesktopLocator;
	private String contentTypeContainerImageUploadedFromDesktopTitleLocator;
	private String siteDropdownXpath;
	private String adminConsoleXpath;
	private String siteDropdownListElementXPath;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		this.contentTypeContainerLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainer");
		this.dataSourceSectionImageUploadedFromDesktopLocator = uiElementsPropertiesManager
			.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.datasourceimageuploadedfromdesktop");
		this.contentTypeContainerImageUploadedFromDesktopTitleLocator = uiElementsPropertiesManager
			.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerimageuploadedfromdesktoptitle");
		siteDropdownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitedropdown");
		adminConsoleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.adminconsole");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
	}

	public void dragAndDrop() {
		this.driverManager.scrollDownPx(3000);
		// Getting the ChildContent for drag and drop action
		WebElement FromDataSourceImageUploadedFromDesktopElement =  this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
						dataSourceSectionImageUploadedFromDesktopLocator);

		// Getting the Content Type Container for drag and drop action
		// (destination)
		WebElement ToContentTypeContainer = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
						contentTypeContainerLocator);
		
		driverManager.dragAndDropElement(FromDataSourceImageUploadedFromDesktopElement, ToContentTypeContainer);

		// Complete the input fields basics
		siteConfigPage.completeDataSourceFieldsBasics("TestTitle");

		// Save the data
		siteConfigPage.saveDragAndDropProcess();
	}

	@Test(priority = 0)
	public void verifyThatStudioAllowsToAddADataSourceImageUploadedFromDesktopToExistingContentTypeTest() {

		// login to application
		loginPage.loginToCrafter(
				userName,password);
		
		//Wait for login page to closes
		driverManager.waitUntilLoginCloses();
		
		// go to preview page
		homePage.goToPreviewPage();
		
		// Show site content panel
		if (!(this.driverManager.waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
				.getAttribute("class").contains("site-dropdown-open")))
		 this.driverManager
			.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
					siteDropdownXpath).click();

		// Show admin console page
		 this.driverManager
			.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
					adminConsoleXpath).click();

		// Select the content type to the test
		siteConfigPage.selectEntryContentTypeFromAdminConsole();

		// drag and drop
		this.dragAndDrop();

		// open content types
		siteConfigPage.clickExistingTypeOption();

		// Confirm the content type selected
		siteConfigPage.confirmContentTypeSelected();

		// Click on input section to can view the properties
		driverManager.waitUntilPopupIsHidden();
		siteConfigPage.clickDataSourceImageUploadedFromDesktopSection();

		// Asserts that fields are not empty.
		String titleText =this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
						contentTypeContainerImageUploadedFromDesktopTitleLocator).getText();
		Assert.assertTrue(titleText.contains("TestTitle"));
		siteConfigPage.cancelChangesOnContentType();


	}

}
