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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;


/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class AddNewContentSectionDefaultsTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String createFormFrameElementCss;
	private String createFormSaveAndCloseElement;
	private String siteDropDownXpath;
	private String sectionDefaultsXpath;
	private String siteDropdownListElementXPath;
	private static Logger logger = LogManager.getLogger(AddNewContentSectionDefaultsTest.class);

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String siteId, String blueprint) {
		apiTestHelper.createSite(siteId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		siteDropDownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitedropdown");
		sectionDefaultsXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitecontent.sectiondefaults");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
	}

	public void createLevelDescriptorContent() {
		
		// right click to see the the menu
		dashboardPage.rightClickToSeeMenu();
		
		// create a content with level descriptor content type
		// right click to see the the menu
		dashboardPage.selectLDCT(); 

		// Select Entry Content Type
		dashboardPage.clickLevelDescriptorCT();

		// Confirm the Content Type selected
		dashboardPage.clickOKButton();

		// Switch to the iframe
		getWebDriverManager().getDriver().switchTo().defaultContent();
		getWebDriverManager().getDriver().switchTo().frame(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed(
				"cssSelector", createFormFrameElementCss));

		// save and close
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed( "xpath", createFormSaveAndCloseElement).click();

	
		// Switch back to the dashboard page
		getWebDriverManager().getDriver().switchTo().defaultContent();

		// reload page
		getWebDriverManager().getDriver().navigate().refresh();
	}

	@Parameters({"testId"})
	@Test()
	public void addLevelDescriptorItemUsingContextualClickOptionsTest(String testId) {

		// login to application
		logger.info("Login into Crafter");
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page closes
		getWebDriverManager().waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage(testId);

		// Show site content panel
		if (!(this.getWebDriverManager().waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
				.getAttribute("class").contains("site-dropdown-open")))
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed( "xpath", siteDropDownXpath)
				.click();

		// expand pages folder
		logger.info("Expanding pages folder");
		dashboardPage.expandPagesTree();
		
		// Expand Home Tree
		logger.info("Expanding Home Tree");
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.expandHomeTree();

		// Create level descriptor content
		logger.info("Creating level Descriptor content");
		createLevelDescriptorContent();

		// Assert of the test case is fine
		String levelDescriptor = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed( "xpath", sectionDefaultsXpath).getText();

		logger.info("Verify Level Descriptor was created");
		Assert.assertTrue(levelDescriptor.contains("Section Defaults"),
				"Level descriptors are not the same, check if the level descriptor was succesfully created");
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}