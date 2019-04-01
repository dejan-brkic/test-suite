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
package org.craftercms.studio.test.cases.sitestestcases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * @author luishernandez
 *
 */

//Test Case Studio- Sites ID:6
public class VerifyStudioAllowsToFilterNumberOfSitesPerPage extends StudioBaseTest{

	private String userName;
	private String password;
	private String sitesPerPageInputXpath;
	private String firstSiteXpath;
	private String secondSiteXpath;
	private String thirdSiteXpath;
	private String createSiteButton;
	private String siteDropdownElementXPath;
	private String topNavDeleteOption;
	private String topNavEditOption;
	private String topNavSitesOption;
	private String lastNumberOfPaginationXpath;
	private String firstNumberOfPaginationXpath;
	private String lastArrowOfPaginationXpath;
	private String firstArrowOfPaginationXpath;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		
		sitesPerPageInputXpath= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.sitesperpageinput");
		firstSiteXpath= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.firstSiteNameOnList");
		secondSiteXpath= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.secondSiteNameOnList");
		thirdSiteXpath= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.thirdSiteNameOnList");
		createSiteButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.createsitebutton");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		topNavDeleteOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.deletetopnavoption");
		topNavEditOption= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.edittopnavoption");
		topNavSitesOption= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.preview.sitesoption");
		lastNumberOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.pagination.lastnumberelement");
		firstNumberOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.pagination.firstnumberelement");
		lastArrowOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.pagination.lastarrowelement");
		firstArrowOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.pagination.firstarrowelement");
		
		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		driverManager.waitUntilLoginCloses();
		
		// Create site 1
		createSitesRandom();
		// Create site 2
		createSitesRandom();
		// Create site 3
		createSitesRandom();

	}

	@AfterMethod
	public void afterTest() {
		// Delete all sites
		deleteSites();
	}

	public void createSitesRandom() {

		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		//select blueprint, set site name, set description, click review and create site
		createSitePage.selectEmptyBluePrintOption()
				.setSiteName()
				.setDescription("Description")
				.clickReviewAndCreate()
				.clickOnCreateButton();
		
		this.driverManager.waitUntilElementIsClickable("xpath", siteDropdownElementXPath);
		this.driverManager.waitUntilElementIsClickable("xpath", topNavDeleteOption);
		this.driverManager.waitUntilElementIsClickable("xpath", topNavEditOption);

		Assert.assertTrue(this.driverManager
				.waitUntilElementIsClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());

		this.driverManager.waitUntilElementIsClickable("xpath", topNavSitesOption).click();
	}

	public void filters() {

		// Show 1 sites
		this.driverManager.waitUntilElementIsDisplayed("xpath",
				sitesPerPageInputXpath).clear();
		this.driverManager.waitUntilElementIsDisplayed("xpath",
				sitesPerPageInputXpath).sendKeys("1");

		// Assert only 1 sites displayed
		WebElement page1 = this.driverManager.waitUntilElementIsDisplayed("xpath",
				firstSiteXpath);
		Assert.assertTrue(page1.isDisplayed());

		// Show 2 sites
		this.driverManager.waitUntilElementIsDisplayed("xpath",
				sitesPerPageInputXpath).clear();
		this.driverManager.waitUntilElementIsDisplayed("xpath",
				sitesPerPageInputXpath).sendKeys("2");

		// Assert only 2 sites displayed
		WebElement page2 = this.driverManager.waitUntilElementIsDisplayed("xpath",
				secondSiteXpath);
		Assert.assertTrue(page2.isDisplayed());

		// Show 3 site
		this.driverManager.waitUntilElementIsDisplayed("xpath",
				sitesPerPageInputXpath).clear();
		this.driverManager.waitUntilElementIsDisplayed("xpath",
				sitesPerPageInputXpath).sendKeys("3");

		// Assert only 3 site displayed
		WebElement page3 = this.driverManager.waitUntilElementIsDisplayed("xpath",
				thirdSiteXpath);
		Assert.assertTrue(page3.isDisplayed());

		// Show 10 sites
		this.driverManager.waitUntilElementIsDisplayed("xpath",
				sitesPerPageInputXpath).clear();
		this.driverManager.waitUntilElementIsDisplayed("xpath",
				sitesPerPageInputXpath).sendKeys("10");
		
		this.driverManager.waitForAnimation();
		
		this.navigationOfPage();
	}

	public void deleteSites() {

		//delete all the sites present
		this.driverManager.isElementPresentAndClickableByXpath(createSiteButton);
		this.driverManager.waitForAnimation();
		homePage.deleteAllSites();
	}

	public void navigationOfPage() {

		this.driverManager.waitUntilElementIsDisplayed("xpath", sitesPerPageInputXpath).clear();

		this.driverManager.waitUntilElementIsDisplayed("xpath", sitesPerPageInputXpath).sendKeys("1");

		this.driverManager.waitUntilElementIsDisplayed("xpath", sitesPerPageInputXpath).clear();

		this.driverManager.waitUntilElementIsDisplayed("xpath", sitesPerPageInputXpath).sendKeys("2");

		// navigation
		this.driverManager.waitUntilElementIsDisplayed("xpath", lastNumberOfPaginationXpath).click();

		this.driverManager.waitUntilElementIsDisplayed("xpath", firstNumberOfPaginationXpath).click();

		this.driverManager.waitUntilElementIsDisplayed("xpath", lastArrowOfPaginationXpath).click();

		this.driverManager.waitUntilElementIsDisplayed("xpath", firstArrowOfPaginationXpath).click();

		this.driverManager.waitUntilElementIsDisplayed("xpath", sitesPerPageInputXpath).clear();

		this.driverManager.waitUntilElementIsDisplayed("xpath", sitesPerPageInputXpath).sendKeys("10");
		
		this.driverManager.waitForAnimation();
		
	}
	@Test(priority = 0)
	public void verifyStudioAllowsToFilterNumberOfSitesPerPage() {
		// filters
		filters();
	}
}
