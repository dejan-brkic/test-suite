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

//Test Case Studio- Sites ID:7
public class VerifyThatPaginationOfSiteListWorksProperlyTest extends StudioBaseTest{
	
	private String userName;
	private String password;

	private String sitesPerPageInputXpath;
	private String lastNumberOfPaginationXpath;
	private String firstNumberOfPaginationXpath;
	private String lastArrowOfPaginationXpath;
	private String firstArrowOfPaginationXpath;
	private String siteDropdownElementXPath;
	private String topNavDeleteOption;
	private String topNavEditOption;
	private String topNavSitesOption;
	private String createSiteButton;
	
	@BeforeMethod
	public void beforeTest() {
		
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		sitesPerPageInputXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.sitesperpageinput");
		lastNumberOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.pagination.lastnumberelement");
		firstNumberOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.pagination.firstnumberelement");
		lastArrowOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.pagination.lastarrowelement");
		firstArrowOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.pagination.firstarrowelement");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		topNavDeleteOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.deletetopnavoption");
		topNavEditOption= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.edittopnavoption");
		topNavSitesOption= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.preview.sitesoption");
		createSiteButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.createsitebutton");
		
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		driverManager.waitUntilLoginCloses();
		
		// Create Site 1
		createSitesRandom();
		// Create Site 2
		createSitesRandom();
		// Create Site 3
		createSitesRandom();
	}

	@AfterMethod
	public void afterTest() {
		// Delete All the sites
		this.driverManager.waitForAnimation();
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

		this.driverManager.waitWhileElementIsDisplayedAndClickableByXpath(siteDropdownElementXPath);
		
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", topNavDeleteOption);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", topNavEditOption);

		Assert.assertTrue(this.driverManager.isElementPresentAndClickableByXpath(siteDropdownElementXPath));

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", topNavSitesOption).click();
	}

	public void navigationOfPage() {

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", sitesPerPageInputXpath).clear();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", sitesPerPageInputXpath).sendKeys("1");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", sitesPerPageInputXpath).clear();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", sitesPerPageInputXpath).sendKeys("2");

		// navigation
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", lastNumberOfPaginationXpath).click();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", firstNumberOfPaginationXpath).click();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", lastArrowOfPaginationXpath).click();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", firstArrowOfPaginationXpath).click();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", sitesPerPageInputXpath).clear();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", sitesPerPageInputXpath).sendKeys("10");
		
		this.driverManager.waitForAnimation();
		
	}

	public void deleteSites() {
		//delete all the sites present
		this.driverManager.isElementPresentAndClickableByXpath(createSiteButton);
		homePage.deleteAllSites();

	}

	@Test(priority = 0)
	public void verifyThatThePaginationOnSitesListWorkProperly() {
		navigationOfPage();
	}

}
