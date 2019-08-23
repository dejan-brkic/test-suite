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
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
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

public class ContentTypesDragAndDropTest extends StudioBaseTest{

	private String userName;
	private String password;
	private String siteDropdownXpath;
	private String controlsSectionFromSection;
	private String contentFormName;
	private String contentFormContentSection;
	private String siteDropdownListElementXPath;

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitedropdown");
		controlsSectionFromSection = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.controlssection");
		contentFormName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contentformname");
		contentFormContentSection = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contentformcontentsection");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
	}

	@Parameters({"testId"})
	@Test()
	public void contentTypesDragAndDropTest(String testId) {

		// login to application
		loginPage.loginToCrafter(userName,password);

		//Wait for login page to closes
		getWebDriverManager().waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage(testId);

		// Show site content panel
		if (!(this.getWebDriverManager().waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
				.getAttribute("class").contains("site-dropdown-open")))
		this.getWebDriverManager()
		.driverWaitUntilElementIsPresentAndDisplayed( "xpath", siteDropdownXpath).click();

		// go to admin console page

		previewPage.goToAdminConsolePage();

		// select content types
		siteConfigPage.selectContentTypeOption();

		// open content types
		siteConfigPage.clickExistingTypeOption();

		// Confirm the content type selected

		siteConfigPage.confirmContentTypeSelected();

		// wait for element is clickeable
		WebElement From = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed( "xpath", controlsSectionFromSection);
		
		WebElement To = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed( "xpath", contentFormName);

		Actions builder = new Actions(getWebDriverManager().getDriver());

		Action dragAndDrop = builder.clickAndHold(From)

				.moveToElement(To)

				.release(To)

				.build();

		dragAndDrop.perform();

		// Save the drag and drop process

		siteConfigPage.saveDragAndDropProcess(true);
		// validate the control added

		Assert.assertTrue(getWebDriverManager().isElementPresentByXpath(contentFormContentSection));
		
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}