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
package org.craftercms.studio.test.cases.sanitytesttestcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.WebElement;

/**
 * 
 * 
 * @author Juan Camacho A.
 *
 */
// Test Case created to cover ticket
// https://github.com/craftercms/craftercms/issues/1876
public class AutomateVerifyingNoErrorsPresentInWebsiteEditorialBPHomePage extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String createSiteErrorNotificationWindow;
	private String menuSitesButton;
	private String dashboardSiteContent;
	private String siteErrorMessage;
	private String wesiteEditorialLeftRailIcon;


	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		createSiteErrorNotificationWindow = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.createsite.errowindow");
		menuSitesButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("preview.sites.menu.button");
		dashboardSiteContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.site_content");
		siteErrorMessage = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.errormessage");
		wesiteEditorialLeftRailIcon = uiElementsPropertiesManager.getSharedUIElementsLocators()
		.getProperty("general.preview.websiteeditorial.leftrail.icon");
		
	}

	public void deleteSite() {

		this.driverManager.getDriver().switchTo().defaultContent();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", menuSitesButton).click();

		// Click on Delete icon
		homePage.clickOnDeleteSiteIcon();

		// Click on YES to confirm the delete.
		homePage.clickOnYesToDeleteSite();

		// Refresh the page
		driverManager.getDriver().navigate().refresh();

	}

	@AfterMethod
	public void afterTest() {
		deleteSite();
	}

	@Test(priority = 0)
	public void automateVerifyingNoErrorsPresentInWebsiteEditorialBPHomePage() {

		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		// Filling the name of site
		createSitePage.fillSiteName();

		// Filling the description of the site
		createSitePage.fillDescription("Description");

		// Open blueprint combo
		// Select blueprint
		createSitePage.selectWebSiteEditorialBluePrintOption();

		// Click on Create button
		createSitePage.clickOnCreateSiteButton();

		// Verify No error messages after clicking on the Create button
		Assert.assertFalse(driverManager.isElementPresentByXpath(createSiteErrorNotificationWindow));
		this.driverManager.waitWhileElementIsDisplayedAndClickableByXpath(siteDropdownElementXPath);

		driverManager.getDriver().switchTo().defaultContent();
        driverManager.getDriver().switchTo().frame(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed(
                "id", "engineWindow"));
       
        //Check that the Hero section of the Home Page has no errors.
		Assert.assertFalse(driverManager.isElementPresentByXpath(siteErrorMessage),
				"ERROR: Website Editorial Blueprint HomePage has displayed errors");
		
		//Check that the Features section Erat lacinia, has no errors.
		this.driverManager.scrollDownPx(500);
		Assert.assertFalse(driverManager.isElementPresentByXpath(siteErrorMessage),
				"ERROR: Website Editorial Blueprint HomePage has displayed errors");
		
		//Check that there are no errors in the Featured Articles section.
		this.driverManager.scrollDownPx(980);
		Assert.assertFalse(driverManager.isElementPresentByXpath(siteErrorMessage),
				"ERROR: Website Editorial Blueprint HomePage has displayed errors");
		
		this.driverManager.scrollDownPx(2300);
		Assert.assertFalse(driverManager.isElementPresentByXpath(siteErrorMessage),
				"ERROR: Website Editorial Blueprint HomePage has displayed errors");
		
		//Verify Left Rail has no errors
		this.driverManager.scrollUp();
		WebElement leftRail = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", wesiteEditorialLeftRailIcon);
		leftRail.click();
		
		Assert.assertFalse(driverManager.isElementPresentByXpath(siteErrorMessage),
				"ERROR: Website Editorial Blueprint HomePage has displayed errors");
		
		driverManager.getDriver().switchTo().defaultContent();
		
		WebElement sidebar = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				dashboardSiteContent);
		sidebar.click();
		
		Assert.assertFalse(driverManager.isElementPresentByXpath(siteErrorMessage),
				"ERROR: Website Editorial Blueprint HomePage has displayed errors");
		
	}

}
