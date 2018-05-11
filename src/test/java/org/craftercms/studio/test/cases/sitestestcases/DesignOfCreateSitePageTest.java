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
package org.craftercms.studio.test.cases.sitestestcases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;


/**
 * 
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class DesignOfCreateSitePageTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String crafterLogoXpath;
	private String sitesTitleXpath;
	private String createSiteButtonXpath;
	private String usersOptionId;
	private String sitesOptionId;
	private String helpOptionId;
	private String accountDropdownXpath;
	private String sitesPerPageLabelXpath;
	private String sitesPerPageInputXpath;

	@BeforeMethod
	public void beforeTest() {
		
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		crafterLogoXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.crafterlogo");
		sitesTitleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("sites.pagetitle");
		createSiteButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.createsitebutton");
		usersOptionId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.homeusers");
		sitesOptionId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.homesites");
		helpOptionId = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.sites.homehelp");
		accountDropdownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.accountdropdown");
		sitesPerPageLabelXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.sitesperpagelabel");
		sitesPerPageInputXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.sitesperpageinput");
	}

	@Test(priority = 0)
	public void verifyTheDesignOfSitesPage() {

		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// Assert crafter studio logo is present.
		WebElement logoCrafter = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				crafterLogoXpath);

		Assert.assertTrue(logoCrafter.isDisplayed(),"Error: Crafter Logo is not displayed");

		// Assert sites title is present.
		WebElement sitesLabel = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				sitesTitleXpath);

		Assert.assertTrue(sitesLabel.isDisplayed(),"Error:  Sites title is not displayed");

		// Assert create button is present.
		WebElement createButton = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				createSiteButtonXpath);

		Assert.assertTrue(createButton.isDisplayed(),"Error:  Create site button is not displayed");

		// Assert admin tools is present.
		WebElement homeUsers = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", usersOptionId);

		Assert.assertTrue(homeUsers.isDisplayed(), "Error:  admin tools is not displayed");

		// Assert sites option is present.
		WebElement sitesOption = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", sitesOptionId);

		Assert.assertTrue(sitesOption.isDisplayed(),"Error:  Sites option is not displayed");

		// Assert Help option is present.
		WebElement helpOption = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", helpOptionId);

		Assert.assertTrue(helpOption.isDisplayed(), "Error:  Help option is not displayed");

		// Assert account option is present.
		WebElement accountOption = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				accountDropdownXpath);
		Assert.assertTrue(accountOption.isDisplayed(), "Error:  Account option is not displayed");

		// Assert all sites option is present.
		WebElement sitesPerPage = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				sitesPerPageLabelXpath);

		Assert.assertTrue(sitesPerPage.isDisplayed(),"Error:  All sites option is not displayed");

		// Assert site name is present.
		WebElement sitesPerPageCombo = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				sitesPerPageInputXpath);
		Assert.assertTrue(sitesPerPageCombo.isDisplayed(),"Error:  Site name is not displayed");

	}

}
