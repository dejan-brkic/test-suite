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
package org.craftercms.studio.test.cases.sitedropdowntestcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * 
 * 
 * @author Juan Camacho A
 *
 */
//Test Case Studio- Site Dropdown ID:67
public class VerifyTheApplicationRedirectstoTheSiteConfigPageWhenTheSiteConfigOptionIsClicked extends StudioBaseTest{

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String menuSitesButton;
	private String siteConfigLink;
	private String contentTypeOption;
	private String siteDropdownListElementXPath;
	private static Logger logger = LogManager
			.getLogger(VerifyTheApplicationRedirectstoTheSiteConfigPageWhenTheSiteConfigOptionIsClicked.class);
	
	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownmenuinnerxpath");
		menuSitesButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("preview.sites.menu.button");
		siteConfigLink = uiElementsPropertiesManager.getSharedUIElementsLocators()
		.getProperty("general.adminconsole");
		contentTypeOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.content_type_option");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
	}
	
	public void deleteSite() {
		
		this.driverManager.getDriver().switchTo().defaultContent();
		
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable(
				"xpath", menuSitesButton).click();

		// Click on Delete icon
		homePage.clickOnDeleteSiteIcon();

		// Click on YES to confirm the delete.
		homePage.clickOnYesToDeleteSite();
		
		//Refresh the page
		driverManager.getDriver().navigate().refresh();

	}

	@AfterMethod
	public void afterTest() {
		deleteSite();
	}

	@Test(priority = 0)
	public void verifyTheApplicationRedirectstoTheSiteConfigPageWhenTheSiteConfigOptionIsClicked() {

		// login to application
		logger.info("Login into Crafter");
		loginPage.loginToCrafter(
				userName,password);
		
		//Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// Click on the create site button
		logger.info("Creating Web Editorial Site");
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
	
		//Expand the site bar
		logger.info("Opening the site bar");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",siteDropdownElementXPath);
		
		if (this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath)
				.isDisplayed()) {
			if (!(this.driverManager.waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
					.getAttribute("class").contains("site-dropdown-open")))
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath)
						.click();
		}else
				throw new NoSuchElementException(
						"Site creation process is taking too long time and the element was not found");
		
		Assert.assertTrue(this.driverManager.isElementPresentAndClickableByXpath(siteDropdownElementXPath));
		
		logger.info("Click on the Site ConFig Page");
		WebElement siteConfigLinkElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteConfigLink);
		siteConfigLinkElement.click();
		
		logger.info("Verify Site Config Page is displayed");
		this.driverManager.waitForAnimation();

		
		WebElement contentTypesOptionElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", contentTypeOption);
		Assert.assertTrue(contentTypesOptionElement.isDisplayed(),
				"ERROR: Content Type Option is not present, verify if Site config Page is displayed");
		}	
	}

